package com.mattborle.cupsaddons.handlers.effect;

import com.mattborle.cupsaddons.init.MobEffectRegistry;
import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;

import java.util.Random;

public class NightTransportHandler {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof LivingEntity _entity)
			_entity.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY,
					entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(MobEffectRegistry.TRANSPORTING.get())
							? _livEnt.getEffect(MobEffectRegistry.TRANSPORTING.get()).getDuration()
							: 0,
					0, (false), (false)));
		if (entity instanceof LivingEntity _entity)
			_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,
					entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(MobEffectRegistry.TRANSPORTING.get())
							? _livEnt.getEffect(MobEffectRegistry.TRANSPORTING.get()).getDuration()
							: 0,
					2, (false), (false)));
		if (entity instanceof LivingEntity _entity)
			_entity.addEffect(new MobEffectInstance(MobEffects.GLOWING, 1, 0, (false), (false)));
		if (world instanceof ServerLevel _level)
			_level.sendParticles(ParticleTypes.SNOWFLAKE, (entity.getX()), (entity.getY()), (entity.getZ()), 20, 2, 2, 2, (-0.1));
		if (world instanceof ServerLevel _level)
			_level.sendParticles(ParticleTypes.LARGE_SMOKE, (entity.getX()), (entity.getY()), (entity.getZ()), 5, 2, 2, 2, (-0.1));
		if (world instanceof Level _level) {
			if (!_level.isClientSide()) {
				_level.playSound(null, new BlockPos(entity.getX(), entity.getY(), entity.getZ()),
						ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("cupsaddons:transport_observed")), SoundSource.AMBIENT,
						(float) 0.8, (float) Mth.nextDouble(new Random(), 0.8, 1.2));
			} else {
				_level.playLocalSound((entity.getX()), (entity.getY()), (entity.getZ()),
						ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("cupsaddons:transport_observed")), SoundSource.AMBIENT,
						(float) 0.8, (float) Mth.nextDouble(new Random(), 0.8, 1.2), false);
			}
		}
		if (!world.getBlockState(new BlockPos(entity.getX(), entity.getY(), entity.getZ())).canOcclude()) {
			if (!(Blocks.AIR == (world.getBlockState(new BlockPos(entity.getX(), entity.getY() - 1, entity.getZ()))).getBlock())) {
				if (!(Blocks.VOID_AIR == (world.getBlockState(new BlockPos(entity.getX(), entity.getY() - 1, entity.getZ()))).getBlock())) {
					if (!(Blocks.CAVE_AIR == (world.getBlockState(new BlockPos(entity.getX(), entity.getY() - 1, entity.getZ()))).getBlock())) {
						world.setBlock(new BlockPos(entity.getX(), entity.getY(), entity.getZ()), Blocks.SNOW.defaultBlockState(), 3);
					}
				}
			}
		}
	}
}
