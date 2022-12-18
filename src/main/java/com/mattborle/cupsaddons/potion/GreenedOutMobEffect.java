
package com.mattborle.cupsaddons.potion;

import com.mattborle.cupsaddons.handlers.GreenedOutHandler;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

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
		GreenedOutHandler.execute(entity);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}
