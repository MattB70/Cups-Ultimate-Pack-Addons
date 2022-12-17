package com.mattborle.cupsultimatepackaddons.client.renderer;

import com.mattborle.cupsultimatepackaddons.client.model.NightCrawlerModel;
import com.mattborle.cupsultimatepackaddons.entity.NightCrawlerMobEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;

public class NightCrawlerRenderer extends MobRenderer<NightCrawlerMobEntity, NightCrawlerModel<NightCrawlerMobEntity>> {
    public NightCrawlerRenderer(EntityRendererProvider.Context context) {
        super(context, new NightCrawlerModel(context.bakeLayer(NightCrawlerModel.LAYER_LOCATION)), 0.5f);
        this.addLayer(new EyesLayer<NightCrawlerMobEntity, NightCrawlerModel<NightCrawlerMobEntity>>(this) {
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
