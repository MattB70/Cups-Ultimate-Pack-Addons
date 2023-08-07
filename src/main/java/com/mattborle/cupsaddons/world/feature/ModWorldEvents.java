package com.mattborle.cupsaddons.world.feature;

import com.mattborle.cupsaddons.CupsAddons;
import com.mattborle.cupsaddons.world.feature.tree.ModTreeGeneration;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CupsAddons.MOD_ID)
public class ModWorldEvents {
    @SubscribeEvent
    public static void biomeLoadingEvent(final BiomeLoadingEvent event) {
        //ModTreeGeneration.generateTrees(event);
    }
}