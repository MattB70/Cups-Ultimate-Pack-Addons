
package com.mattborle.cupsultimatepackaddons.item;

import com.mattborle.cupsultimatepackaddons.init.TabInit;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;
import net.minecraft.world.food.FoodProperties;

import com.mattborle.cupsaddons.init.CupsaddonsModTabs;

public class CookedMashedGrainsItem extends Item {
	public CookedMashedGrainsItem() {
		super(new Properties().tab(TabInit.TAB_ITEMS)
				.stacksTo(64)
				.rarity(Rarity.COMMON)
				.food((new FoodProperties.Builder())
				.nutrition(2)
				.saturationMod(1f)
				.build()));
	}
}
