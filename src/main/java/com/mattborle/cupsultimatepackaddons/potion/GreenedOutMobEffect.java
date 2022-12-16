
package com.mattborle.cupsultimatepackaddons.potion;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

import com.mattborle.cupsaddons.procedures.GreenedOutHandlerProcedure;

public class GreenedOutMobEffect extends MobEffect {
	public GreenedOutMobEffect() {
		super(MobEffectCategory.HARMFUL, -10854576);
	}

	@Override
	public String getDescriptionId() {
		return "effect.cupsaddons.greened_out";
	}

	@Override
	public void applyEffectTick(LivingEntity entity, int amplifier) {
		GreenedOutHandlerProcedure.execute(entity);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}
