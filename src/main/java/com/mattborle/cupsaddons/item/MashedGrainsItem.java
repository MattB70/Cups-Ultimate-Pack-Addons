
package com.mattborle.cupsaddons.item;

import com.mattborle.cupsaddons.init.ItemRegistry;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.food.FoodProperties;

public class MashedGrainsItem extends Item {
	public MashedGrainsItem() {
		super(new Properties().tab(ItemRegistry.CreativeTab.instance)
				.stacksTo(64)
				.rarity(Rarity.COMMON)
				.food((new FoodProperties.Builder())
				.nutrition(1)
				.saturationMod(1f)
				.alwaysEat()
				.build()));
	}

	@Override
	public int getUseDuration(ItemStack itemstack) {
		return 64;
	}
}
