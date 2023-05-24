package com.mattborle.cupsaddons.client.model.item;

import com.mattborle.cupsaddons.CupsAddons;
import com.mattborle.cupsaddons.item.tool.CrysophilistsPickaxeItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class CrysophilistsPickaxeModel extends AnimatedGeoModel<CrysophilistsPickaxeItem> {
    @Override
    public ResourceLocation getModelLocation(CrysophilistsPickaxeItem object) {
        return new ResourceLocation(CupsAddons.MOD_ID, "geo/crysophilists_pickaxe.geo.json");
    }
    @Override
    public ResourceLocation getTextureLocation(CrysophilistsPickaxeItem object) {
        return new ResourceLocation(CupsAddons.MOD_ID, "textures/models/crysophilists_pickaxe.png");
    }
    @Override
    public ResourceLocation getAnimationFileLocation(CrysophilistsPickaxeItem animatable) {
        return new ResourceLocation(CupsAddons.MOD_ID, "animations/crysophilists_pickaxe.animation.json");
    }
}
