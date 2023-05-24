package com.mattborle.cupsaddons.client.model.armor;

import com.mattborle.cupsaddons.CupsAddons;
import com.mattborle.cupsaddons.item.silly.GamerGlassesItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GamerGlassesModel extends AnimatedGeoModel<GamerGlassesItem> {
    @Override
    public ResourceLocation getModelLocation(GamerGlassesItem object) {
        return new ResourceLocation(CupsAddons.MOD_ID, "geo/gamer_glasses.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(GamerGlassesItem object) {
        return new ResourceLocation(CupsAddons.MOD_ID, "textures/models/gamer_glasses.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(GamerGlassesItem animatable) {
        return new ResourceLocation(CupsAddons.MOD_ID, "animations/armor_no_animation.json");
    }
}
