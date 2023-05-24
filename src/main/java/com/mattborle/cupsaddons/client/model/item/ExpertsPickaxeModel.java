package com.mattborle.cupsaddons.client.model.item;

import com.mattborle.cupsaddons.CupsAddons;
import com.mattborle.cupsaddons.item.tool.ExpertsPickaxeItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ExpertsPickaxeModel extends AnimatedGeoModel<ExpertsPickaxeItem> {
    @Override
    public ResourceLocation getModelLocation(ExpertsPickaxeItem object) {
        return new ResourceLocation(CupsAddons.MOD_ID, "geo/experts_pickaxe.geo.json");
    }
    @Override
    public ResourceLocation getTextureLocation(ExpertsPickaxeItem object) {
        return new ResourceLocation(CupsAddons.MOD_ID, "textures/models/experts_pickaxe.png");
    }
    @Override
    public ResourceLocation getAnimationFileLocation(ExpertsPickaxeItem animatable) {
        return new ResourceLocation(CupsAddons.MOD_ID, "animations/crysophilists_pickaxe.animation.json");
    }
}
