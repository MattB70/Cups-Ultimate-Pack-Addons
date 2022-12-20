package com.mattborle.cupsaddons.client.model.item;

import com.mattborle.cupsaddons.CupsAddons;
import com.mattborle.cupsaddons.item.ChrysophilistsPickaxeItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ChrysophilistsPickaxeModel extends AnimatedGeoModel<ChrysophilistsPickaxeItem> {
    @Override
    public ResourceLocation getModelLocation(ChrysophilistsPickaxeItem object) {
        return new ResourceLocation(CupsAddons.MOD_ID, "geo/chrysophilists_pickaxe.geo.json");
    }
    @Override
    public ResourceLocation getTextureLocation(ChrysophilistsPickaxeItem object) {
        return new ResourceLocation(CupsAddons.MOD_ID, "textures/items/chrysophilists_pickaxe.png");
    }
    @Override
    public ResourceLocation getAnimationFileLocation(ChrysophilistsPickaxeItem animatable) {
        return new ResourceLocation(CupsAddons.MOD_ID, "animations/chrysophilists_pickaxe.animation.json");
    }
}
