
package com.mattborle.cupsultimatepackaddons.potion;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

import com.mattborle.cupsaddons.procedures.AquaphiliaHandlerProcedure;

public class AquaphiliaMobEffect extends MobEffect {
	public AquaphiliaMobEffect() {
		super(MobEffectCategory.HARMFUL, -10322771);
	}

	@Override
	public String getDescriptionId() {
		return "effect.cupsaddons.aquaphilia";
	}

	@Override
	public void applyEffectTick(LivingEntity entity, int amplifier) {
		AquaphiliaHandlerProcedure.execute(entity.level, entity);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}
