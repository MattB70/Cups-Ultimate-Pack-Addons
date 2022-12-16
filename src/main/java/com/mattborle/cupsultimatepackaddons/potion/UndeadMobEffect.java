
package com.mattborle.cupsultimatepackaddons.potion;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

import com.mattborle.cupsaddons.procedures.UndeadHandlerProcedure;

public class UndeadMobEffect extends MobEffect {
	public UndeadMobEffect() {
		super(MobEffectCategory.HARMFUL, -12826083);
	}

	@Override
	public String getDescriptionId() {
		return "effect.cupsaddons.undead";
	}

	@Override
	public void applyEffectTick(LivingEntity entity, int amplifier) {
		UndeadHandlerProcedure.execute(entity.level, entity);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}
