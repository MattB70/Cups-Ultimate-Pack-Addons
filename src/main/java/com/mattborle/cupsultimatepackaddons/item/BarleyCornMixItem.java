
package com.mattborle.cupsultimatepackaddons.item;

import com.mattborle.cupsultimatepackaddons.init.ItemRegistry;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class BarleyCornMixItem extends Item {
	public BarleyCornMixItem() {
		super(new Properties().tab(ItemRegistry.CreativeTab.instance)
				.stacksTo(64)
				.rarity(Rarity.COMMON));
	}
}
