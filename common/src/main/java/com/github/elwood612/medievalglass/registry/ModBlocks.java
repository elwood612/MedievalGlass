package com.github.elwood612.medievalglass.registry;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

import java.util.HashMap;

public class ModBlocks {

    public static final BlockBehaviour.Properties GLASS_PROPERTIES = BlockBehaviour.Properties.of()
            .mapColor(MapColor.STONE)
            .sound(SoundType.GLASS)
            .strength(2.0f, 0.3f)
            .noOcclusion();

    public static final HashMap<String, BlockType> BLOCK_MAP = new HashMap<>();
    public static final String TAB_ID = "my_tab";

    static {
        BLOCK_MAP.put("leaded_glass_pane", BlockType.VERTICAL_PANE);
        BLOCK_MAP.put("ring_leaded_glass_pane", BlockType.VERTICAL_PANE);
        BLOCK_MAP.put("midland_leaded_glass_pane", BlockType.VERTICAL_PANE);
        BLOCK_MAP.put("gufy_leaded_glass_pane", BlockType.VERTICAL_PANE);
        BLOCK_MAP.put("avalon_leaded_glass_pane", BlockType.VERTICAL_PANE);
        BLOCK_MAP.put("oakfield_leaded_glass_pane", BlockType.VERTICAL_PANE);

        BLOCK_MAP.put("white_leaded_glass_pane", BlockType.EIGHTWAY_PANE);
        BLOCK_MAP.put("light_gray_leaded_glass_pane", BlockType.EIGHTWAY_PANE);
        BLOCK_MAP.put("gray_leaded_glass_pane", BlockType.EIGHTWAY_PANE);
        BLOCK_MAP.put("black_leaded_glass_pane", BlockType.EIGHTWAY_PANE);
        BLOCK_MAP.put("brown_leaded_glass_pane", BlockType.EIGHTWAY_PANE);
        BLOCK_MAP.put("red_leaded_glass_pane", BlockType.EIGHTWAY_PANE);
        BLOCK_MAP.put("orange_leaded_glass_pane", BlockType.EIGHTWAY_PANE);
        BLOCK_MAP.put("yellow_leaded_glass_pane", BlockType.EIGHTWAY_PANE);
        BLOCK_MAP.put("lime_leaded_glass_pane", BlockType.EIGHTWAY_PANE);
        BLOCK_MAP.put("green_leaded_glass_pane", BlockType.EIGHTWAY_PANE);
        BLOCK_MAP.put("cyan_leaded_glass_pane", BlockType.EIGHTWAY_PANE);
        BLOCK_MAP.put("light_blue_leaded_glass_pane", BlockType.EIGHTWAY_PANE);
        BLOCK_MAP.put("blue_leaded_glass_pane", BlockType.EIGHTWAY_PANE);
        BLOCK_MAP.put("purple_leaded_glass_pane", BlockType.EIGHTWAY_PANE);
        BLOCK_MAP.put("magenta_leaded_glass_pane", BlockType.EIGHTWAY_PANE);
        BLOCK_MAP.put("pink_leaded_glass_pane", BlockType.EIGHTWAY_PANE);

        BLOCK_MAP.put("spruce_framed_glass_pane", BlockType.EIGHTWAY_PANE);
        BLOCK_MAP.put("oak_framed_glass_pane", BlockType.EIGHTWAY_PANE);
        BLOCK_MAP.put("birch_framed_glass_pane", BlockType.EIGHTWAY_PANE);
        BLOCK_MAP.put("jungle_framed_glass_pane", BlockType.EIGHTWAY_PANE);
        BLOCK_MAP.put("acacia_framed_glass_pane", BlockType.EIGHTWAY_PANE);
        BLOCK_MAP.put("dark_oak_framed_glass_pane", BlockType.EIGHTWAY_PANE);
        BLOCK_MAP.put("mangrove_framed_glass_pane", BlockType.EIGHTWAY_PANE);
        BLOCK_MAP.put("cherry_framed_glass_pane", BlockType.EIGHTWAY_PANE);
        BLOCK_MAP.put("crimson_framed_glass_pane", BlockType.EIGHTWAY_PANE);
        BLOCK_MAP.put("warped_framed_glass_pane", BlockType.EIGHTWAY_PANE);
        BLOCK_MAP.put("bamboo_framed_glass_pane", BlockType.EIGHTWAY_PANE);
        BLOCK_MAP.put("pale_oak_framed_glass_pane", BlockType.EIGHTWAY_PANE);
    }
}
