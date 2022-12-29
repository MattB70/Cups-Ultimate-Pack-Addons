
package com.mattborle.cupsaddons.item.ingredient;

import com.mattborle.cupsaddons.init.ItemRegistry;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class BundleOfSticksItem extends Item {
	public BundleOfSticksItem() {
		super(new Properties().tab(ItemRegistry.CreativeTab.instance)
				.stacksTo(64)
				.rarity(Rarity.COMMON));
	}
}
