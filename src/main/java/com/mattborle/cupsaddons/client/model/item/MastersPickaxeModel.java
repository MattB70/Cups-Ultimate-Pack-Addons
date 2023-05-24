package com.mattborle.cupsaddons.client.model.item;

import com.mattborle.cupsaddons.CupsAddons;
import com.mattborle.cupsaddons.item.tool.DistinguishedPickaxeItem;
import com.mattborle.cupsaddons.item.tool.MastersPickaxeItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class MastersPickaxeModel extends AnimatedGeoModel<MastersPickaxeItem> {
    @Override
    public ResourceLocation getModelLocation(MastersPickaxeItem object) {
        return new ResourceLocation(CupsAddons.MOD_ID, "geo/masters_pickaxe.geo.json");
    }
    @Override
    public ResourceLocation getTextureLocation(MastersPickaxeItem object) {
        return new ResourceLocation(CupsAddons.MOD_ID, "textures/models/masters_pickaxe.png");
    }
    @Override
    public ResourceLocation getAnimationFileLocation(MastersPickaxeItem animatable) {
        return new ResourceLocation(CupsAddons.MOD_ID, "animations/crysophilists_pickaxe.animation.json");
    }
}
