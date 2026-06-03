package com.github.elwood612.medievalglass;

import com.github.elwood612.medievalglass.registry.NeoforgeRegistry;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(Constants.MOD_ID)
public class MedievalGlass
{
    public MedievalGlass(IEventBus eventBus) {
        CommonClass.init();
        NeoforgeRegistry.init(eventBus);
    }
}