
package com.mattborle.cupsaddons.potion;

import com.mattborle.cupsaddons.handlers.UndeadHandler;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

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
		UndeadHandler.execute(entity.level, entity);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}
