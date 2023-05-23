package com.mattborle.cupsaddons.client.renderer.item;

import com.mattborle.cupsaddons.client.model.item.GodsPickaxeModel;
import com.mattborle.cupsaddons.item.tool.GodsPickaxeItem;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class GodsPickaxeRenderer extends GeoItemRenderer<GodsPickaxeItem> {
    public GodsPickaxeRenderer() {
        super(new GodsPickaxeModel());
    }
}
