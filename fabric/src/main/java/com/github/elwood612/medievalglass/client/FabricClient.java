package com.github.elwood612.medievalglass.client;

import com.github.elwood612.medievalglass.registry.FabricRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;

public class FabricClient implements ClientModInitializer
{
    @Override
    public void onInitializeClient() {
        FabricRegistry.REGISTERED_BLOCKS.forEach((name, block) -> BlockRenderLayerMap.putBlock(block, ChunkSectionLayer.TRANSLUCENT));
    }
}