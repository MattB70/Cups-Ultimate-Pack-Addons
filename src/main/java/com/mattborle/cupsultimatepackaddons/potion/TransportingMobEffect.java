
package com.mattborle.cupsultimatepackaddons.potion;

import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

import com.mattborle.cupsaddons.procedures.NightTransportingEffectExpiresProcedure;
import com.mattborle.cupsaddons.procedures.NightTransportHandlerProcedure;

public class TransportingMobEffect extends MobEffect {
	public TransportingMobEffect() {
		super(MobEffectCategory.BENEFICIAL, -3355393);
	}

	@Override
	public String getDescriptionId() {
		return "effect.cupsaddons.transporting";
	}

	@Override
	public void addAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
		NightTransportHandlerProcedure.execute(entity.level, entity);
	}

	@Override
	public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
		super.removeAttributeModifiers(entity, attributeMap, amplifier);
		NightTransportingEffectExpiresProcedure.execute(entity.level, entity);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}
