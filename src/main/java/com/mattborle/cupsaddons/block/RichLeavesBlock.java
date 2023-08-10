package com.mattborle.cupsaddons.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RichLeavesBlock extends LeavesBlock {

    public RichLeavesBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable BlockGetter blockGetter, List<Component> tooltip, TooltipFlag tooltipFlag) {
        tooltip.add(new TextComponent("§b1% chance to drop a Rich Ore Tree Sapling"));
        super.appendHoverText(itemStack, blockGetter, tooltip, tooltipFlag);
    }

    @Override
    public void destroy(LevelAccessor levelAccessor, BlockPos blockPos, BlockState blockState) {
        for(int i = 0; i < 5; i++) {
            levelAccessor.addParticle(ParticleTypes.GLOW, blockPos.getX() + 0.5, blockPos.getY() + 0.5, blockPos.getZ() + 0.5, ((Math.random() * (4)) -2), ((Math.random() * (4)) -2), ((Math.random() * (4)) -2));
        }
        super.destroy(levelAccessor, blockPos, blockState);
    }

    @Override
    public float defaultDestroyTime() {
        return 0.0f;
    }
}
