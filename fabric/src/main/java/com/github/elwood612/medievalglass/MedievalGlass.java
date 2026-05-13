package com.github.elwood612.medievalglass;

import com.github.elwood612.medievalglass.registry.ModBlocks;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.function.Supplier;

public class MedievalGlass implements ModInitializer {

    public static final CreativeModeTab TAB = Registry.register(
            BuiltInRegistries.CREATIVE_MODE_TAB,
            Identifier.fromNamespaceAndPath(Constants.MOD_ID, ModBlocks.TAB_ID),
            FabricItemGroup.builder()
                    .title(Component.translatable("itemGroup." + Constants.MOD_ID))
                    .icon(() -> new ItemStack(BuiltInRegistries.BLOCK.getValue(Identifier.fromNamespaceAndPath(Constants.MOD_ID, "leaded_glass_pane"))))
                    .displayItems((params, output) -> {
                        for (Supplier<Item> item : ModBlocks.BLOCKITEM_MAP.values()) {
                            output.accept(item.get());
                        }
                    })
                    .build()
    );

    @Override
    public void onInitialize() {
        CommonClass.init();

        ModBlocks.BLOCK_MAP.forEach((s, blockSupplier) ->
                Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(Constants.MOD_ID,s),blockSupplier.get()));
        ModBlocks.BLOCKITEM_MAP.forEach((s, itemSupplier) ->
                Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(Constants.MOD_ID,s),itemSupplier.get()));
    }
}
