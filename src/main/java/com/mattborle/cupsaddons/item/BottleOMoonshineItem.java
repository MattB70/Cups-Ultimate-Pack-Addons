
package com.mattborle.cupsaddons.item;

import com.mattborle.cupsaddons.init.ItemRegistry;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.Component;

import java.util.List;

import com.mattborle.cupsaddons.handlers.MoonshineDrinkHandler;

public class BottleOMoonshineItem extends Item {
	public BottleOMoonshineItem() {
		super(new Properties().tab(ItemRegistry.CreativeTab.instance)
				.stacksTo(16)
				.rarity(Rarity.UNCOMMON)
				.food((new FoodProperties.Builder())
				.nutrition(1)
				.saturationMod(1f)
				.alwaysEat()
				.build()));
	}

	@Override
	public UseAnim getUseAnimation(ItemStack itemstack) {
		return UseAnim.DRINK;
	}

	@Override
	public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, world, list, flag);
		list.add(new TextComponent("\u00A7aPure"));
		list.add(new TextComponent("\u00A7dEffects:"));
		list.add(new TextComponent("\u00A79Resistance II (8m)"));
		list.add(new TextComponent("\u00A79Strength I (8m)"));
		list.add(new TextComponent("\u00A7cNausea II (40s)"));
		list.add(new TextComponent("\u00A7cSlowness I (40s)"));
	}

	@Override
	public ItemStack finishUsingItem(ItemStack itemstack, Level world, LivingEntity entity) {
		ItemStack retval = super.finishUsingItem(itemstack, world, entity);
		double x = entity.getX();
		double y = entity.getY();
		double z = entity.getZ();

		MoonshineDrinkHandler.execute(entity);
		return retval;
	}
}
