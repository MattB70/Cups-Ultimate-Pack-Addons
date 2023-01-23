package com.mattborle.cupsaddons.client.renderer.item;

import com.mattborle.cupsaddons.client.model.item.NeanderthalsPickaxeModel;
import com.mattborle.cupsaddons.item.tool.NeanderthalsPickaxeItem;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class NeanderthalsPickaxeRenderer extends GeoItemRenderer<NeanderthalsPickaxeItem> {
    public NeanderthalsPickaxeRenderer() {
        super(new NeanderthalsPickaxeModel());
    }
}
