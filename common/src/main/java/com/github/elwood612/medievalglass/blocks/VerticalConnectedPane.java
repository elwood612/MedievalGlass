package com.github.elwood612.medievalglass.blocks;

import com.github.elwood612.medievalglass.registry.Helper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

public class VerticalConnectedPane extends IronBarsBlock
{
    private static final IntegerProperty VERTICAL_POSITION = IntegerProperty.create("vertical_position", 0, 3);

    public VerticalConnectedPane(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState()
                .setValue(NORTH, Boolean.FALSE)
                .setValue(EAST, Boolean.FALSE)
                .setValue(SOUTH, Boolean.FALSE)
                .setValue(WEST, Boolean.FALSE)
                .setValue(WATERLOGGED, Boolean.FALSE)
                .setValue(VERTICAL_POSITION, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{NORTH, EAST, WEST, SOUTH, WATERLOGGED, VERTICAL_POSITION });
    }

    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockGetter blockgetter = context.getLevel();
        BlockPos blockpos = context.getClickedPos();
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        BlockPos blockpos1 = blockpos.north();
        BlockPos blockpos2 = blockpos.south();
        BlockPos blockpos3 = blockpos.west();
        BlockPos blockpos4 = blockpos.east();
        BlockState blockstate = blockgetter.getBlockState(blockpos1);
        BlockState blockstate1 = blockgetter.getBlockState(blockpos2);
        BlockState blockstate2 = blockgetter.getBlockState(blockpos3);
        BlockState blockstate3 = blockgetter.getBlockState(blockpos4);
        boolean north = this.attachsTo(blockstate, blockstate.isFaceSturdy(blockgetter, blockpos1, Direction.SOUTH));
        boolean south = this.attachsTo(blockstate1, blockstate1.isFaceSturdy(blockgetter, blockpos2, Direction.NORTH));
        boolean west = this.attachsTo(blockstate2, blockstate2.isFaceSturdy(blockgetter, blockpos3, Direction.EAST));
        boolean east = this.attachsTo(blockstate3, blockstate3.isFaceSturdy(blockgetter, blockpos4, Direction.WEST));
        return (BlockState)((BlockState)((BlockState)((BlockState)((BlockState)this.defaultBlockState()
                .setValue(NORTH, north))
                .setValue(SOUTH, south))
                .setValue(WEST, west))
                .setValue(EAST, east))
                .setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER)
                .setValue(VERTICAL_POSITION, computeVertical(north, south, west, east, context.getLevel(), blockpos));
    }

    protected BlockState updateShape(BlockState state, LevelAccessor level, BlockPos pos, Direction direction,
                                     BlockPos neighborPos, BlockState neighborState, RandomSource random) {
        if ((Boolean)state.getValue(WATERLOGGED)) {
            level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }

        if (direction.getAxis().isHorizontal()) {
            return (BlockState)state.setValue((BooleanProperty)PROPERTY_BY_DIRECTION.get(direction),
                    this.attachsTo(neighborState, neighborState.isFaceSturdy(level, neighborPos, direction.getOpposite())));
        } else {
            return (BlockState)state
                    .setValue(VERTICAL_POSITION, computeVertical(state, level, pos));
        }
    }

    private int computeVertical(BlockState state, LevelReader level, BlockPos pos) {
        return computeVertical(state.getValue(NORTH), state.getValue(SOUTH), state.getValue(WEST), state.getValue(EAST), level, pos);
    }
    private int computeVertical(boolean north, boolean south, boolean west, boolean east, LevelReader level, BlockPos pos) {
        Direction fullPaneAxis = isFullPane(north, south, west, east);
        BlockState b_up = level.getBlockState(pos.above());
        BlockState b_down = level.getBlockState(pos.below());

        if (fullPaneAxis == null) return computeAltVertical(north, south, west, east, b_up, b_down);

        boolean up = b_up.is(this) && isFullPane(b_up) != null && isFullPane(b_up) == fullPaneAxis;
        boolean down = b_down.is(this) && isFullPane(b_down) != null && isFullPane(b_down) == fullPaneAxis;

        return Helper.getVerticalPosition(up, down);
    }

    private Direction isFullPane(BlockState state) { return isFullPane(state.getValue(NORTH), state.getValue(SOUTH), state.getValue(WEST), state.getValue(EAST)); }
    private Direction isFullPane(boolean north, boolean south, boolean west, boolean east) {
        if (north && south && !west && !east) return Direction.NORTH;
        if (west && east && !north && !south) return Direction.EAST;
        return null;
    }

    private int computeAltVertical(boolean north, boolean south, boolean west, boolean east, BlockState up, BlockState down) {
        boolean matchUp = (up.hasProperty(NORTH) && north == up.getValue(NORTH)) &&
                (up.hasProperty(SOUTH) && south == up.getValue(SOUTH)) &&
                (up.hasProperty(WEST) && west == up.getValue(WEST)) &&
                (up.hasProperty(EAST) && east == up.getValue(EAST));
        boolean matchDown = (down.hasProperty(NORTH) && north == down.getValue(NORTH)) &&
                (down.hasProperty(SOUTH) && south == down.getValue(SOUTH)) &&
                (down.hasProperty(WEST) && west == down.getValue(WEST)) &&
                (down.hasProperty(EAST) && east == down.getValue(EAST));

        // 0 = isolated
        // 1 = top
        // 2 = both
        // 3 = bottom
        if (matchUp) {
            if (matchDown) return 2;
            else return 3;
        } else {
            if (matchDown) return 1;
            else return 0;
        }
    }
}
