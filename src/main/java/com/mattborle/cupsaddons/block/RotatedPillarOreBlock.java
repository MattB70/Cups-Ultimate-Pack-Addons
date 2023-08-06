package com.mattborle.cupsaddons.block;

import net.minecraft.world.level.block.RotatedPillarBlock;

public class RotatedPillarOreBlock extends RotatedPillarBlock {

    public RotatedPillarOreBlock(Properties properties) {
        super(properties.requiresCorrectToolForDrops());
    }


}
