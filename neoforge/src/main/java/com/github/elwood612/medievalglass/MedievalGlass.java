package com.github.elwood612.medievalglass;


import com.github.elwood612.medievalglass.blocks.EightwayConnectedPane;
import com.github.elwood612.medievalglass.blocks.VerticalConnectedPane;
import com.github.elwood612.medievalglass.registry.BlockType;
import com.github.elwood612.medievalglass.registry.ModBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

@Mod(Constants.MOD_ID)
public class MedievalGlass
{
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Constants.MOD_ID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Constants.MOD_ID);
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Constants.MOD_ID);

    public static final Supplier<CreativeModeTab> TAB =
            TABS.register(ModBlocks.TAB_ID, () ->
                    CreativeModeTab.builder()
                            .title(Component.translatable("itemGroup." + Constants.MOD_ID))
                            .icon(() -> new ItemStack(BuiltInRegistries.BLOCK.getValue(Identifier.fromNamespaceAndPath(Constants.MOD_ID, "leaded_glass_pane"))))
                            .displayItems((params, output) -> {
                                for (DeferredHolder<Block, ? extends Block> block : BLOCKS.getEntries()) {
                                    output.accept(block.get());
                                }
                            })
                            .build()
            );

    public MedievalGlass(IEventBus eventBus) {
        CommonClass.init();

//        ModBlocks.BLOCK_MAP.forEach(BLOCKS::register);
//        ModBlocks.BLOCKITEM_MAP.forEach(ITEMS::register);

        ModBlocks.BLOCK_MAP.forEach(MedievalGlass::createRegistry);

        BLOCKS.register(eventBus);
        ITEMS.register(eventBus);
        TABS.register(eventBus);
    }

    public static <T extends Block> DeferredBlock<T> createRegistry(String name, BlockType type)
    {
        ResourceKey<Block> blockKey = ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Constants.MOD_ID, name));
        Supplier<Block> blockSupplier = type == BlockType.VERTICAL_PANE ?
                () -> new VerticalConnectedPane(ModBlocks.GLASS_PROPERTIES):
                () -> new EightwayConnectedPane(ModBlocks.GLASS_PROPERTIES);

        blockSupplier.get().properties().setId(blockKey);

        DeferredBlock<T> block = (DeferredBlock<T>) BLOCKS.register(name, blockSupplier);
        ITEMS.registerSimpleBlockItem(name, block, () -> new Item.Properties());

        return block;
    }
}