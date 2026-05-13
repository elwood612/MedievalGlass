package com.github.elwood612.medievalglass;

import com.github.elwood612.medievalglass.blocks.EightwayConnectedPane;
import com.github.elwood612.medievalglass.blocks.VerticalConnectedPane;
import com.github.elwood612.medievalglass.registry.BlockType;
import com.github.elwood612.medievalglass.registry.ModBlocks;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;

public class MedievalGlass implements ModInitializer {

    public static final List<Block> REGISTERED_BLOCKS = new ArrayList<>();
    public static final CreativeModeTab TAB = Registry.register(
            BuiltInRegistries.CREATIVE_MODE_TAB,
            Identifier.fromNamespaceAndPath(Constants.MOD_ID, ModBlocks.TAB_ID),
            FabricItemGroup.builder()
                    .title(Component.translatable("itemGroup." + Constants.MOD_ID + ".tab"))
                    .icon(() -> new ItemStack(BuiltInRegistries.BLOCK.getValue(Identifier.fromNamespaceAndPath(Constants.MOD_ID, "leaded_glass_pane"))))
                    .displayItems((params, output) -> {
                        for (Block block : REGISTERED_BLOCKS) {
                            output.accept(block);
                        }
                    })
                    .build()
    );

    @Override
    public void onInitialize() {
        CommonClass.init();
        // FabricRegistry.init();
        ModBlocks.BLOCK_MAP.forEach(MedievalGlass::createRegistry);
    }

    private static void createRegistry(String name, BlockType type) {
        Block block = type == BlockType.VERTICAL_PANE ?
                new VerticalConnectedPane(ModBlocks.GLASS_PROPERTIES.setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Constants.MOD_ID, name)))):
                new EightwayConnectedPane(ModBlocks.GLASS_PROPERTIES.setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Constants.MOD_ID, name))));

        Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(Constants.MOD_ID, name), block);

        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(Constants.MOD_ID, name),
                new BlockItem(block, new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Constants.MOD_ID, name)))));

        REGISTERED_BLOCKS.add(block);
    }
}
