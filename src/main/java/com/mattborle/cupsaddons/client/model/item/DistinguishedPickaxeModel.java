package com.mattborle.cupsaddons.client.model.item;

import com.mattborle.cupsaddons.CupsAddons;
import com.mattborle.cupsaddons.item.tool.DistinguishedPickaxeItem;
import com.mattborle.cupsaddons.item.tool.ExpertsPickaxeItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class DistinguishedPickaxeModel extends AnimatedGeoModel<DistinguishedPickaxeItem> {
    @Override
    public ResourceLocation getModelLocation(DistinguishedPickaxeItem object) {
        return new ResourceLocation(CupsAddons.MOD_ID, "geo/distinguished_pickaxe.geo.json");
    }
    @Override
    public ResourceLocation getTextureLocation(DistinguishedPickaxeItem object) {
        return new ResourceLocation(CupsAddons.MOD_ID, "textures/items/distinguished_pickaxe.png");
    }
    @Override
    public ResourceLocation getAnimationFileLocation(DistinguishedPickaxeItem animatable) {
        return new ResourceLocation(CupsAddons.MOD_ID, "animations/crysophilists_pickaxe.animation.json");
    }
}
