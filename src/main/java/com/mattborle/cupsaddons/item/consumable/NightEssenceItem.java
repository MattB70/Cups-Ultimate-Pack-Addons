
package com.mattborle.cupsaddons.item.consumable;

import com.mattborle.cupsaddons.init.ItemRegistry;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.Component;

import java.util.List;

import com.mattborle.cupsaddons.handlers.NightEssenceEatingHandler;

public class NightEssenceItem extends Item {
	public NightEssenceItem() {
		super(new Properties().tab(ItemRegistry.CreativeTab.instance)
				.stacksTo(64)
				.rarity(Rarity.UNCOMMON)
				.food((new FoodProperties.Builder())
				.nutrition(1)
				.saturationMod(1f)
				.alwaysEat()
				.build()));
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public boolean isFoil(ItemStack itemstack) {
		return true;
	}

	@Override
	public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, world, list, flag);
		list.add(new TextComponent("\u00A7bThe remains of an otherworldy being"));
		list.add(new TextComponent("\u00A7dEffects:"));
		list.add(new TextComponent("\u00A79Transporting (5s)"));
	}

	@Override
	public ItemStack finishUsingItem(ItemStack itemstack, Level world, LivingEntity entity) {
		ItemStack retval = super.finishUsingItem(itemstack, world, entity);
		double x = entity.getX();
		double y = entity.getY();
		double z = entity.getZ();

		NightEssenceEatingHandler.execute(entity);
		return retval;
	}
}
