package com.mattborle.cupsultimatepackaddons.handlers;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;

import com.mattborle.cupsaddons.init.CupsaddonsModMobEffects;

public class NightEssenceEatingHandler {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (!(entity instanceof LivingEntity _livEnt ? _livEnt.hasEffect(CupsaddonsModMobEffects.TRANSPORTING.get()) : false)) {
			if (entity instanceof LivingEntity _entity)
				_entity.addEffect(new MobEffectInstance(CupsaddonsModMobEffects.TRANSPORTING.get(), 100, 0, (false), (true)));
		}
	}
}
