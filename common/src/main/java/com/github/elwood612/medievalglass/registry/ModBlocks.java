package com.github.elwood612.medievalglass.registry;

import com.github.elwood612.medievalglass.blocks.VerticalConnectedPane;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

import java.util.HashMap;

public class ModBlocks {

    public static final BlockBehaviour.Properties GLASS_PROPERTIES = BlockBehaviour.Properties.of()
            .mapColor(MapColor.STONE)
            .sound(SoundType.GLASS)
            .strength(0.3f)
            .explosionResistance(3.0f)
            .requiresCorrectToolForDrops();

    public static final HashMap<String, BlockType> BLOCK_MAP = new HashMap<>();
//    public static final HashMap<String,Supplier<Item>> BLOCKITEM_MAP = new HashMap<>();

    public static final String TAB_ID = "my_tab";

//    public static final Supplier<Block> LEADED_GLASS_PANE = Suppliers.memoize(() -> new VerticalConnectedPane(GLASS_PROPERTIES));

    static {
        BLOCK_MAP.put("leaded_glass_pane", BlockType.VERTICAL_PANE);
//        BLOCKITEM_MAP.put("leaded_glass_pane", Suppliers.memoize(() -> new BlockItem(LEADED_GLASS_PANE.get(), new Item.Properties())));
    }
}
