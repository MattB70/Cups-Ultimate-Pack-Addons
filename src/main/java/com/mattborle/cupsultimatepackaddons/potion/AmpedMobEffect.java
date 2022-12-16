
package com.mattborle.cupsultimatepackaddons.potion;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

import com.mattborle.cupsaddons.procedures.AmpedHandlerProcedure;

public class AmpedMobEffect extends MobEffect {
	public AmpedMobEffect() {
		super(MobEffectCategory.BENEFICIAL, -10704439);
	}

	@Override
	public String getDescriptionId() {
		return "effect.cupsaddons.amped";
	}

	@Override
	public void applyEffectTick(LivingEntity entity, int amplifier) {
		AmpedHandlerProcedure.execute(entity);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}
