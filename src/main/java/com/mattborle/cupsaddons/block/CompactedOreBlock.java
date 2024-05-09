package com.mattborle.cupsaddons.block;

import net.minecraft.world.level.block.OreBlock;
import net.minecraft.world.level.block.SoundType;

public class CompactedOreBlock extends OreBlock {

    public CompactedOreBlock(Properties properties) {
        super(properties
                .requiresCorrectToolForDrops()
                .destroyTime(5000)          // TODO: make these values configurable
                .sound(SoundType.DEEPSLATE_BRICKS)  // sounds like deepslate bricks
                .strength(10.0f));           // somewhat resistant to explosions
    }

}
