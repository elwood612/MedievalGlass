package com.github.elwood612.medievalglass.registry;

import com.github.elwood612.medievalglass.Constants;
import com.github.elwood612.medievalglass.blocks.EightwayConnectedPane;
import com.github.elwood612.medievalglass.blocks.VerticalConnectedPane;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.IronBarsBlock;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class NeoforgeRegistry
{
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Constants.MOD_ID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Constants.MOD_ID);
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Constants.MOD_ID);

    public static final Supplier<CreativeModeTab> TAB =
            TABS.register(ModBlocks.TAB_ID, () ->
                    CreativeModeTab.builder()
                            .title(Component.translatable("itemGroup." + Constants.MOD_ID + ".tab"))
                            .icon(() -> new ItemStack(BuiltInRegistries.BLOCK.getValue(Identifier.fromNamespaceAndPath(Constants.MOD_ID, "leaded_glass_pane"))))
                            .displayItems((params, output) -> {
                                for (DeferredHolder<Block, ? extends Block> block : BLOCKS.getEntries()) {
                                    output.accept(block.get());
                                }
                            })
                            .build()
            );

    public static void init(IEventBus eventBus) {
        ModBlocks.BLOCK_MAP.forEach(NeoforgeRegistry::createRegistry);

        BLOCKS.register(eventBus);
        ITEMS.register(eventBus);
        TABS.register(eventBus);
    }

    private static void createRegistry(String name, BlockType type) {
        DeferredBlock<Block> block;
        switch(type) {
            case VERTICAL_PANE -> block = BLOCKS.register(name, registryName ->
                    new VerticalConnectedPane(ModBlocks.GLASS_PROPERTIES.setId(ResourceKey.create(Registries.BLOCK, registryName))));
            case EIGHTWAY_PANE -> block = BLOCKS.register(name, registryName ->
                    new EightwayConnectedPane(ModBlocks.GLASS_PROPERTIES.setId(ResourceKey.create(Registries.BLOCK, registryName))));
            case REGULAR_PANE -> block = BLOCKS.register(name, registryName ->
                    new IronBarsBlock(ModBlocks.GLASS_PROPERTIES.setId(ResourceKey.create(Registries.BLOCK, registryName))));
            default -> block = null;
        }
        ITEMS.registerSimpleBlockItem(name, block, () -> new Item.Properties());
    }
}
