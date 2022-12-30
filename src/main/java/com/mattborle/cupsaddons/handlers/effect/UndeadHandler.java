package com.mattborle.cupsaddons.handlers.effect;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;

public class UndeadHandler {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (!entity.fireImmune() && !world.getLevelData().isRaining()
				&& world.canSeeSkyFromBelowWater(new BlockPos(entity.getX(), entity.getY(), entity.getZ()))) {
			if (world instanceof Level _lvl && _lvl.isDay()) {
				entity.setSecondsOnFire(1);
			}
		}
	}
}
