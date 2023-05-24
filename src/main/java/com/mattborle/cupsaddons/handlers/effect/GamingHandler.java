package com.mattborle.cupsaddons.handlers.effect;

import com.mattborle.cupsaddons.init.MobEffectRegistry;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.LevelAccessor;


public class GamingHandler {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null){
			return;
		}

		if (entity instanceof LivingEntity livingEntity){
			livingEntity.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,
					livingEntity.hasEffect(MobEffectRegistry.GAMING.get())
							? livingEntity.getEffect(MobEffectRegistry.GAMING.get()).getDuration()
							: 0,
					0, (true), (false)));
			livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,
					livingEntity.hasEffect(MobEffectRegistry.GAMING.get())
							? livingEntity.getEffect(MobEffectRegistry.GAMING.get()).getDuration()
							: 0,
					0, (true), (false)));
		}
	}
}
