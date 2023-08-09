package com.mattborle.cupsaddons.item.generic;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;

public class GlowingBlockItem extends BlockItem {
    public GlowingBlockItem(Block block, Properties properties) {
        super(block, properties);
    }
    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }
    @Override
    public Rarity getRarity(ItemStack stack) {
        return Rarity.RARE;
    }
}
