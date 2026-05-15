package com.github.elwood612.medievalglass.registry;

import com.github.elwood612.medievalglass.Constants;
import com.github.elwood612.medievalglass.blocks.EightwayConnectedPane;
import com.github.elwood612.medievalglass.blocks.VerticalConnectedPane;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.IronBarsBlock;

import java.util.HashMap;
import java.util.Map;

public class FabricRegistry
{
    public static final Map<String, Block> REGISTERED_BLOCKS = new HashMap<>();
    public static final CreativeModeTab TAB = Registry.register(
            BuiltInRegistries.CREATIVE_MODE_TAB,
            ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, ModBlocks.TAB_ID),
            FabricItemGroup.builder()
                    .title(Component.translatable("itemGroup." + Constants.MOD_ID + ".tab"))
                    .icon(() -> new ItemStack(BuiltInRegistries.BLOCK.getValue(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "leaded_glass_pane"))))
                    .displayItems((params, output) -> {
                        for (Block block : REGISTERED_BLOCKS.values()) {
                            output.accept(block.asItem());
                        }
                    })
                    .build()
    );

    public static void init() {
        ModBlocks.BLOCK_MAP.forEach(FabricRegistry::createRegistry);
    }

    private static void createRegistry(String name, BlockType type) {
        Block block;
        switch(type) {
            case VERTICAL_PANE -> block =
                    new VerticalConnectedPane(ModBlocks.GLASS_PROPERTIES.setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name))));
            case EIGHTWAY_PANE -> block =
                    new EightwayConnectedPane(ModBlocks.GLASS_PROPERTIES.setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name))));
            case REGULAR_PANE -> block =
                    new IronBarsBlock(ModBlocks.GLASS_PROPERTIES.setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name))));
            default -> block = null;
        }
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name), block);
        Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name),
                new BlockItem(block, new Item.Properties()
                        .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name)))
                        .useBlockDescriptionPrefix()));

        REGISTERED_BLOCKS.put(name, block);
    }
}
