package com.mattborle.cupsaddons.client.renderer.item;

import com.mattborle.cupsaddons.client.model.item.ChrysophilistsPickaxeModel;
import com.mattborle.cupsaddons.item.ChrysophilistsPickaxeItem;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class ChrysophilistsPickaxeRenderer extends GeoItemRenderer<ChrysophilistsPickaxeItem> {
    public ChrysophilistsPickaxeRenderer() {
        super(new ChrysophilistsPickaxeModel());
    }
}
