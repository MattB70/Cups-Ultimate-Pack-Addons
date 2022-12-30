
package com.mattborle.cupsaddons.effect;

import com.mattborle.cupsaddons.handlers.effect.OverdoseFinishedHandler;
import com.mattborle.cupsaddons.handlers.effect.OverdosedHandler;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

public class OverdosedMobEffect extends MobEffect {
	public OverdosedMobEffect() {
		super(MobEffectCategory.HARMFUL, -12763843);
	}

	@Override
	public String getDescriptionId() {
		return "effect.cupsaddons.overdosed";
	}

	@Override
	public void applyEffectTick(LivingEntity entity, int amplifier) {
		OverdosedHandler.execute(entity);
	}

	@Override
	public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
		super.removeAttributeModifiers(entity, attributeMap, amplifier);
		OverdoseFinishedHandler.execute(entity.level, entity);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}
