package com.mattborle.cupsaddons.client.renderer.item;

import com.mattborle.cupsaddons.client.model.item.ExpertsPickaxeModel;
import com.mattborle.cupsaddons.item.tool.ExpertsPickaxeItem;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class ExpertsPickaxeRenderer extends GeoItemRenderer<ExpertsPickaxeItem> {
    public ExpertsPickaxeRenderer() {
        super(new ExpertsPickaxeModel());
    }
}
