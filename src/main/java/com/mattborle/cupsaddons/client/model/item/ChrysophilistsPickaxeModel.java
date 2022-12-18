package com.mattborle.cupsaddons.client.model.item;

import com.mattborle.cupsaddons.client.EntityResources;
import com.mattborle.cupsaddons.item.ChrysophilistsPickaxeItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ChrysophilistsPickaxeModel extends AnimatedGeoModel<ChrysophilistsPickaxeItem> {
    @Override
    public ResourceLocation getModelLocation(ChrysophilistsPickaxeItem object) {
        return EntityResources.CHRYSOPHILISTS_PICKAXE_MODEL;
    }
    @Override
    public ResourceLocation getTextureLocation(ChrysophilistsPickaxeItem object) {
        return EntityResources.CHRYSOPHILISTS_PICKAXE_TEXTURE;
    }
    @Override
    public ResourceLocation getAnimationFileLocation(ChrysophilistsPickaxeItem animatable) {
        return EntityResources.CHRYSOPHILISTS_PICKAXE_ANIMATIONS;
    }
}
