package com.mattborle.cupsultimatepackaddons.client.renderer;

import com.mattborle.cupsultimatepackaddons.client.model.NightCrawlerModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class NightCrawlerRenderer extends MobRenderer<NightCrawlerMobEntity, Modelnight_crawler<NightCrawlerMobEntity>>{
    public NightCrawlerRenderer(EntityRendererProvider.Context context) {
        super(context, new NightCrawlerModel(context.bakeLayer(NightCrawlerModel.LAYER_LOCATION)), 0.5f);
        this.addLayer(new EyesLayer<NightCrawlerMobEntity, Modelnight_crawler<NightCrawlerMobEntity>>(this) {
            @Override
            public RenderType renderType() {
                return RenderType.eyes(new ResourceLocation("cupsultimatepackaddons:textures/entities/night_crawler_glow.png"));
            }
        });
    }

    @Override
    public ResourceLocation getTextureLocation(NightCrawlerMobEntity entity) {
        return new ResourceLocation("cupsultimatepackaddons:textures/entities/night_crawler.png");
    }
}
