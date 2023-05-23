package com.mattborle.cupsaddons.client.model.item;

import com.mattborle.cupsaddons.CupsAddons;
import com.mattborle.cupsaddons.item.tool.GodsPickaxeItem;
import com.mattborle.cupsaddons.item.tool.MastersPickaxeItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GodsPickaxeModel extends AnimatedGeoModel<GodsPickaxeItem> {
    @Override
    public ResourceLocation getModelLocation(GodsPickaxeItem object) {
        return new ResourceLocation(CupsAddons.MOD_ID, "geo/gods_pickaxe.geo.json");
    }
    @Override
    public ResourceLocation getTextureLocation(GodsPickaxeItem object) {
        return new ResourceLocation(CupsAddons.MOD_ID, "textures/items/gods_pickaxe.png");
    }
    @Override
    public ResourceLocation getAnimationFileLocation(GodsPickaxeItem animatable) {
        return new ResourceLocation(CupsAddons.MOD_ID, "animations/crysophilists_pickaxe.animation.json");
    }
}
