package com.github.elwood612.medievalglass;

import com.github.elwood612.medievalglass.registry.FabricRegistry;
import net.fabricmc.api.ModInitializer;

public class MedievalGlass implements ModInitializer
{
    @Override
    public void onInitialize() {
        CommonClass.init();
        FabricRegistry.init();
    }
}
