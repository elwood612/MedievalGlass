package com.github.elwood612.medievalglass.registry;

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

    public static final String TAB_ID = "my_tab";

    static {
        BLOCK_MAP.put("leaded_glass_pane", BlockType.VERTICAL_PANE);
        BLOCK_MAP.put("white_leaded_glass_pane", BlockType.EIGHTWAY_PANE);
    }
}
