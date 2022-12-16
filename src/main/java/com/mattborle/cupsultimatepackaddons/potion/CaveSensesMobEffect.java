
package com.mattborle.cupsultimatepackaddons.potion;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

import com.mattborle.cupsaddons.procedures.CaveSensesHandlerProcedure;

public class CaveSensesMobEffect extends MobEffect {
	public CaveSensesMobEffect() {
		super(MobEffectCategory.HARMFUL, -12503247);
	}

	@Override
	public String getDescriptionId() {
		return "effect.cupsaddons.cave_senses";
	}

	@Override
	public void applyEffectTick(LivingEntity entity, int amplifier) {
		CaveSensesHandlerProcedure.execute(entity.level, entity);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}
