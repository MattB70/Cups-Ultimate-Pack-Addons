package com.mattborle.cupsaddons.item.curios;

import com.mattborle.cupsaddons.init.ItemRegistry;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class FerromagneticObjectItem extends Item {
    public FerromagneticObjectItem(Properties properties) {
        super(properties
                .tab(ItemRegistry.CreativeTab.instance)
                .rarity(Rarity.EPIC)
                .fireResistant()
                .stacksTo(1)
        );
    }
}
