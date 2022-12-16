
package com.mattborle.cupsultimatepackaddons.potion;

import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

import com.mattborle.cupsaddons.procedures.OverdosedHandlerProcedure;
import com.mattborle.cupsaddons.procedures.OverdoseFinishedHandlerProcedure;

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
		OverdosedHandlerProcedure.execute(entity);
	}

	@Override
	public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
		super.removeAttributeModifiers(entity, attributeMap, amplifier);
		OverdoseFinishedHandlerProcedure.execute(entity.level, entity);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}
