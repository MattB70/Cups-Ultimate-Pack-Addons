
package com.mattborle.cupsaddons.effect;

import com.mattborle.cupsaddons.handlers.effect.GamingHandler;
import com.mattborle.cupsaddons.handlers.effect.NightTransportHandler;
import com.mattborle.cupsaddons.handlers.effect.NightTransportingEffectExpires;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;


public class GamingMobEffect extends MobEffect {
	public GamingMobEffect() {
		super(MobEffectCategory.BENEFICIAL, -3355393);
	}

	@Override
	public String getDescriptionId() {
		return "effect.cupsaddons.gaming";
	}

	@Override
	public void addAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
		GamingHandler.execute(entity.level, entity);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}
