package com.mattborle.cupsaddons.client.renderer.item;

import com.mattborle.cupsaddons.client.model.item.DistinguishedPickaxeModel;
import com.mattborle.cupsaddons.client.model.item.MastersPickaxeModel;
import com.mattborle.cupsaddons.item.tool.DistinguishedPickaxeItem;
import com.mattborle.cupsaddons.item.tool.MastersPickaxeItem;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class MastersPickaxeRenderer extends GeoItemRenderer<MastersPickaxeItem> {
    public MastersPickaxeRenderer() {
        super(new MastersPickaxeModel());
    }
}
