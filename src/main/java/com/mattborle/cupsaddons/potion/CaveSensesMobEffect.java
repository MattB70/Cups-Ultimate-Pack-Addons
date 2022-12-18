
package com.mattborle.cupsaddons.potion;

import com.mattborle.cupsaddons.handlers.CaveSensesHandler;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

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
		CaveSensesHandler.execute(entity.level, entity);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}
