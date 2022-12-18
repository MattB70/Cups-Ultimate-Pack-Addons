
package com.mattborle.cupsaddons.item;

import com.mattborle.cupsaddons.init.ItemRegistry;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;
import net.minecraft.world.food.FoodProperties;

public class CookedMashedGrainsItem extends Item {
	public CookedMashedGrainsItem() {
		super(new Properties().tab(ItemRegistry.CreativeTab.instance)
				.stacksTo(64)
				.rarity(Rarity.COMMON)
				.food((new FoodProperties.Builder())
				.nutrition(2)
				.saturationMod(1f)
				.build()));
	}
}
