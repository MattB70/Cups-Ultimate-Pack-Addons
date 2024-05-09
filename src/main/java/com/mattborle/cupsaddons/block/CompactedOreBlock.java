package com.mattborle.cupsaddons.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.OreBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class CompactedOreBlock extends OreBlock {

    public CompactedOreBlock(Properties properties) {
        super(properties
                .requiresCorrectToolForDrops()
                .sound(SoundType.DEEPSLATE_BRICKS)  // sounds like deepslate bricks
                .strength(30.0f));         // somewhat tough to break
    }

    // override playerDestroy to make it never drop anything when a player breaks it.
    @Override
    public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity entity, ItemStack stack) {
        // on server
        if(!level.isClientSide())
        {
            for(int i = 0; i < 5; i++) {
                // spawn failure to mine particle
                level.addParticle(ParticleTypes.ASH, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, ((Math.random() * (4)) - 2), ((Math.random() * (4)) - 2), ((Math.random() * (4)) - 2));
            }//                                                                              volume       pitch
            level.playSound(player,pos, SoundEvents.GENERIC_BURN, SoundSource.BLOCKS,0.2f,0.8f); // play sizzle sound
            level.playSound(player,pos, SoundEvents.GLASS_BREAK, SoundSource.BLOCKS,0.5f,0.6f); // play shatter sound
            super.playerDestroy(level, player, pos, state, entity, null); // replace stack with null
        }
    }

    @Override
    public void popExperience(ServerLevel level, BlockPos pos, int amount) {
        super.popExperience(level, pos, 5);
    }
}
