package com.mattborle.cupsaddons.handlers.effect;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;

import java.util.Random;

public class NightTransportingEffectExpires {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (world instanceof Level _level) {
			if (!_level.isClientSide()) {
				_level.playSound(null, new BlockPos(entity.getX(), entity.getY(), entity.getZ()),
						ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("cupsaddons:transport_end")), SoundSource.AMBIENT, (float) 0.6,
						(float) Mth.nextDouble(new Random(), 0.8, 1.2));
			} else {
				_level.playLocalSound((entity.getX()), (entity.getY()), (entity.getZ()),
						ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("cupsaddons:transport_end")), SoundSource.AMBIENT, (float) 0.6,
						(float) Mth.nextDouble(new Random(), 0.8, 1.2), false);
			}
		}
		if (world instanceof ServerLevel _level)
			_level.sendParticles(ParticleTypes.SNOWFLAKE, (entity.getX()), (entity.getY()), (entity.getZ()), 15, 1, 1, 1, 0.1);
		if (world instanceof ServerLevel _level)
			_level.sendParticles(ParticleTypes.LARGE_SMOKE, (entity.getX()), (entity.getY()), (entity.getZ()), 3, 1, 1, 1, 0.1);
	}
}
