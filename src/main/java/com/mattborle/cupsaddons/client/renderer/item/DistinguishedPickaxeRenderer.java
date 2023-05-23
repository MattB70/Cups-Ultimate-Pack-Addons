package com.mattborle.cupsaddons.client.renderer.item;

import com.mattborle.cupsaddons.client.model.item.DistinguishedPickaxeModel;
import com.mattborle.cupsaddons.client.model.item.ExpertsPickaxeModel;
import com.mattborle.cupsaddons.item.tool.DistinguishedPickaxeItem;
import com.mattborle.cupsaddons.item.tool.ExpertsPickaxeItem;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class DistinguishedPickaxeRenderer extends GeoItemRenderer<DistinguishedPickaxeItem> {
    public DistinguishedPickaxeRenderer() {
        super(new DistinguishedPickaxeModel());
    }
}
