
package com.mattborle.cupsultimatepackaddons.item;

import com.mattborle.cupsultimatepackaddons.init.TabInit;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

import com.mattborle.cupsaddons.init.CupsaddonsModTabs;

public class BarleyCornMixItem extends Item {
	public BarleyCornMixItem() {
		super(new Properties().tab(TabInit.TAB_ITEMS)
				.stacksTo(64)
				.rarity(Rarity.COMMON));
	}
}
