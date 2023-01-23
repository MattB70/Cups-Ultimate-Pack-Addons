package com.mattborle.cupsaddons.client.model.item;

import com.mattborle.cupsaddons.CupsAddons;
import com.mattborle.cupsaddons.item.tool.NeanderthalsPickaxeItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class NeanderthalsPickaxeModel extends AnimatedGeoModel<NeanderthalsPickaxeItem> {
    @Override
    public ResourceLocation getModelLocation(NeanderthalsPickaxeItem object) {
        return new ResourceLocation(CupsAddons.MOD_ID, "geo/neanderthals_pickaxe.geo.json");
    }
    @Override
    public ResourceLocation getTextureLocation(NeanderthalsPickaxeItem object) {
        return new ResourceLocation(CupsAddons.MOD_ID, "textures/items/neanderthals_pickaxe.png");
    }
    @Override
    public ResourceLocation getAnimationFileLocation(NeanderthalsPickaxeItem animatable) {
        return new ResourceLocation(CupsAddons.MOD_ID, "animations/neanderthals_pickaxe.animation.json");
    }
}
