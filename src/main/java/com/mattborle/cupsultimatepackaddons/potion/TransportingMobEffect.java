
package com.mattborle.cupsultimatepackaddons.potion;

import com.mattborle.cupsultimatepackaddons.handlers.NightTransportHandler;
import com.mattborle.cupsultimatepackaddons.handlers.NightTransportingEffectExpires;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;


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
		NightTransportHandler.execute(entity.level, entity);
	}

	@Override
	public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
		super.removeAttributeModifiers(entity, attributeMap, amplifier);
		NightTransportingEffectExpires.execute(entity.level, entity);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}
