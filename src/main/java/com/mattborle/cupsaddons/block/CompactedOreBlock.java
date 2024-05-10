package com.mattborle.cupsaddons.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.OreBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;

public class CompactedOreBlock extends OreBlock {

    public CompactedOreBlock(Properties properties) {
        super(properties
                .requiresCorrectToolForDrops()
                .sound(SoundType.DEEPSLATE_BRICKS)  // sounds like deepslate bricks
                .strength(10.0f));         // somewhat tough to break
    }

    // Skip destroy, and play 'failure to mine' effects on server side
    @Override
    public void playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        super.playerWillDestroy(level, pos, state, player);

        if(player.isCreative()) return; // creative mode -> skip

        // on server
        if(!level.isClientSide())
        {
            // delete block and drop nothing
            level.removeBlock(pos, false);
        }
        // on client
        else
        {//                                                                                 volume       pitch
            level.playSound(player,pos, SoundEvents.GENERIC_BURN, SoundSource.BLOCKS,0.2f,0.7f); // play sizzle sound
            level.playSound(player,pos, SoundEvents.GLASS_BREAK, SoundSource.BLOCKS,0.05f,0.5f); // play shatter sound
            level.removeBlock(pos,true); // simply delete the block the player will destroy, skipping drops.

            // spawn one large smoke ploom
            level.addParticle(
                    ParticleTypes.CAMPFIRE_COSY_SMOKE,
                    pos.getX() + 0.5,
                    pos.getY() + 0.5,
                    pos.getZ() + 0.5,
                    0,0,0
            );
            // spawn several small particles
            for(int i = 0; i < 3; i++) {
                level.addParticle(
                        ParticleTypes.SMOKE,
                        pos.getX() + 0.5,
                        pos.getY() + 0.5,
                        pos.getZ() + 0.5,
                        0,0,0
                );
            }
        }
    }



    @Override
    public void popExperience(ServerLevel level, BlockPos pos, int amount) {
        super.popExperience(level, pos, 5);
    }
}
