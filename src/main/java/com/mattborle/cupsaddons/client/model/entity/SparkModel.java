package com.mattborle.cupsaddons.client.model.entity;

import com.mattborle.cupsaddons.CupsAddons;
import com.mattborle.cupsaddons.entity.SparkEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SparkModel extends AnimatedGeoModel<SparkEntity> {
    @Override
    public ResourceLocation getModelLocation(SparkEntity object) {
        return new ResourceLocation(CupsAddons.MOD_ID, "geo/spark.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(SparkEntity object) {
        return new ResourceLocation(CupsAddons.MOD_ID, "textures/entities/spark.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(SparkEntity animatable) {
        return new ResourceLocation(CupsAddons.MOD_ID, "animations/spark.animation.json");
    }
}
