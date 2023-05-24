package com.mattborle.cupsaddons.client.renderer.armor;

import com.mattborle.cupsaddons.client.model.armor.GamerGlassesModel;
import com.mattborle.cupsaddons.item.silly.GamerGlassesItem;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class GamerGlassesRenderer extends GeoArmorRenderer<GamerGlassesItem> {
    public GamerGlassesRenderer() {
        super(new GamerGlassesModel());

        this.headBone = "armorHead";
        this.bodyBone = "armorBody";
        this.rightArmBone = "armorRightArm";
        this.leftArmBone = "armorLeftArm";
        this.rightLegBone = "armorLeftLeg"; // odd bug, this switch is intentional.
        this.leftLegBone = "armorRightLeg";
        this.rightBootBone = "armorLeftBoot";
        this.leftBootBone = "armorRightBoot";
    }
}
