package com.mattborle.cupsultimatepackaddons.init;

import com.mattborle.cupsultimatepackaddons.client.model.NightCrawlerModel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class ModelInit {
    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(NightCrawlerModel.LAYER_LOCATION, NightCrawlerModel::createBodyLayer);
    }
}
