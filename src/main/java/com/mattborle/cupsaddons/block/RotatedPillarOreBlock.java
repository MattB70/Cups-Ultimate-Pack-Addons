package com.mattborle.cupsaddons.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;

public class RotatedPillarOreBlock extends RotatedPillarBlock {

    public RotatedPillarOreBlock(Properties properties) {
        super(properties.requiresCorrectToolForDrops());
    }

    @Override
    public int getExpDrop(BlockState state, LevelReader level, BlockPos pos, int fortuneLevel, int silkTouchLevel) {

        if(silkTouchLevel > 0) return 0;

        return (int)((fortuneLevel/2)*(float)10); // return some xp, doesn't need to be configurable. Mainly for effect.
    }
}
