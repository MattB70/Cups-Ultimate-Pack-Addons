package com.mattborle.cupsaddons.block;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.SoundType;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MetallicLeavesBlock  extends LeavesBlock {

    public MetallicLeavesBlock(Properties properties) {
        super(properties.sound(SoundType.METAL));
    }


    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable BlockGetter blockGetter, List<Component> tooltip, TooltipFlag tooltipFlag) {
        tooltip.add(new TextComponent("§72% chance to drop an Ore Tree Sapling"));
        super.appendHoverText(itemStack, blockGetter, tooltip, tooltipFlag);
    }
}
