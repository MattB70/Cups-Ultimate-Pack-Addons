package com.mattborle.cupsaddons.client.renderer.item;

import com.mattborle.cupsaddons.client.model.item.CrysophilistsPickaxeModel;
import com.mattborle.cupsaddons.item.CrysophilistsPickaxeItem;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class CrysophilistsPickaxeRenderer extends GeoItemRenderer<CrysophilistsPickaxeItem> {
    public CrysophilistsPickaxeRenderer() {
        super(new CrysophilistsPickaxeModel());
    }
}
