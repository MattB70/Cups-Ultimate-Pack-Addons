
package com.mattborle.cupsaddons.item.consumable;

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

import com.mattborle.cupsaddons.handlers.consumable.EggnogAndRumDrinkHandler;

public class EggnogAndRumItem extends Item {
	public EggnogAndRumItem() {
		super(new Properties().tab(ItemRegistry.CreativeTab.instance)
				.stacksTo(16)
				.rarity(Rarity.UNCOMMON)
				.food((new FoodProperties.Builder())
				.nutrition(5)
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
		list.add(new TextComponent("\u00A7aRelieves eggshaustion and prepares the drinker for a good time."));
		list.add(new TextComponent("\u00A7dEffects:"));
		list.add(new TextComponent("\u00A79Haste II (2m)"));
		list.add(new TextComponent("\u00A79Luck III (2m)"));
		list.add(new TextComponent("\u00A79Resistance I (2m)"));
		list.add(new TextComponent("\u00A76Collectable Event Item"));
	}

	@Override
	public ItemStack finishUsingItem(ItemStack itemstack, Level world, LivingEntity entity) {
		ItemStack retval = super.finishUsingItem(itemstack, world, entity);
		double x = entity.getX();
		double y = entity.getY();
		double z = entity.getZ();

		EggnogAndRumDrinkHandler.execute(entity);
		return retval;
	}
}
