package com.mattborle.cupsaddons.handlers.enchantment;

import com.mattborle.cupsaddons.init.EnchantmentRegistry;
import com.mattborle.cupsaddons.init.MobEffectRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.util.Mth;

import javax.annotation.Nullable;

import java.util.Random;

@Mod.EventBusSubscriber
public class TransportEnchantmentHandler {
	@SubscribeEvent
	public static void onEntityAttacked(LivingAttackEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity());
		}
	}

	public static void execute(Entity entity) {
		execute(null, entity);
	}

	private static void execute(@Nullable Event event, Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof Player) {
			if (EnchantmentHelper.getItemEnchantmentLevel(EnchantmentRegistry.TRANSPORTING_ENCHANTMENT.get(),
					(entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY)) == 1
					&& EnchantmentHelper.getItemEnchantmentLevel(EnchantmentRegistry.TRANSPORTING_ENCHANTMENT.get(),
							(entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)) == 1
					&& EnchantmentHelper.getItemEnchantmentLevel(EnchantmentRegistry.TRANSPORTING_ENCHANTMENT.get(),
							(entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY)) == 1
					&& EnchantmentHelper.getItemEnchantmentLevel(EnchantmentRegistry.TRANSPORTING_ENCHANTMENT.get(),
							(entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY)) == 1) {
				if (!(entity instanceof LivingEntity _livEnt ? _livEnt.hasEffect(MobEffectRegistry.TRANSPORTING.get()) : false)) {
					if (Mth.nextDouble(new Random(), 0, 1) >= 0.15) {
						if (entity instanceof LivingEntity _entity)
							_entity.addEffect(new MobEffectInstance(MobEffectRegistry.TRANSPORTING.get(), 100, 0, (false), (true)));
					}
				}
			} else {
				MobEffectRegistry CupsaddonsModMobEffects;
				if (EnchantmentHelper.getItemEnchantmentLevel(EnchantmentRegistry.TRANSPORTING_ENCHANTMENT.get(),
						(entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY)) == 2
						&& EnchantmentHelper.getItemEnchantmentLevel(EnchantmentRegistry.TRANSPORTING_ENCHANTMENT.get(),
								(entity instanceof LivingEntity _entGetArmor
										? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST)
										: ItemStack.EMPTY)) == 2
						&& EnchantmentHelper.getItemEnchantmentLevel(EnchantmentRegistry.TRANSPORTING_ENCHANTMENT.get(),
								(entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY)) == 2
						&& EnchantmentHelper.getItemEnchantmentLevel(EnchantmentRegistry.TRANSPORTING_ENCHANTMENT.get(),
								(entity instanceof LivingEntity _entGetArmor
										? _entGetArmor.getItemBySlot(EquipmentSlot.FEET)
										: ItemStack.EMPTY)) == 2) {
					if (!(entity instanceof LivingEntity _livEnt ? _livEnt.hasEffect(MobEffectRegistry.TRANSPORTING.get()) : false)) {
						if (Mth.nextDouble(new Random(), 0, 1) >= 0.35) {
							if (entity instanceof LivingEntity _entity)
								_entity.addEffect(new MobEffectInstance(MobEffectRegistry.TRANSPORTING.get(), 150, 0, (false), (true)));
						}
					}
				} else {
					if (EnchantmentHelper.getItemEnchantmentLevel(EnchantmentRegistry.TRANSPORTING_ENCHANTMENT.get(),
							(entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY)) == 3
							&& EnchantmentHelper.getItemEnchantmentLevel(EnchantmentRegistry.TRANSPORTING_ENCHANTMENT.get(),
									(entity instanceof LivingEntity _entGetArmor
											? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST)
											: ItemStack.EMPTY)) == 3
							&& EnchantmentHelper.getItemEnchantmentLevel(EnchantmentRegistry.TRANSPORTING_ENCHANTMENT.get(),
									(entity instanceof LivingEntity _entGetArmor
											? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS)
											: ItemStack.EMPTY)) == 3
							&& EnchantmentHelper.getItemEnchantmentLevel(EnchantmentRegistry.TRANSPORTING_ENCHANTMENT.get(),
									(entity instanceof LivingEntity _entGetArmor
											? _entGetArmor.getItemBySlot(EquipmentSlot.FEET)
											: ItemStack.EMPTY)) == 3) {
						if (!(entity instanceof LivingEntity _livEnt ? _livEnt.hasEffect(MobEffectRegistry.TRANSPORTING.get()) : false)) {
							if (entity instanceof LivingEntity _entity)
								_entity.addEffect(new MobEffectInstance(MobEffectRegistry.TRANSPORTING.get(), 200, 0, (false), (true)));
						}
					}
				}
			}
		}
	}
}
