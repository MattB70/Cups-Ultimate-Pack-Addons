package com.mattborle.cupsultimatepackaddons.handlers;

import com.mattborle.cupsultimatepackaddons.init.MobEffectRegistry;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;

import com.mattborle.cupsultimatepackaddons.init.MobEffectRegistry;

public class NightEssenceEatingHandler {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (!(entity instanceof LivingEntity _livEnt ? _livEnt.hasEffect(MobEffectRegistry.TRANSPORTING.get()) : false)) {
			if (entity instanceof LivingEntity _entity)
				_entity.addEffect(new MobEffectInstance(MobEffectRegistry.TRANSPORTING.get(), 100, 0, (false), (true)));
		}
	}
}
