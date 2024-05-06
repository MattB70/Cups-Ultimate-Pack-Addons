package com.mattborle.cupsaddons.item.quest;

import com.mattborle.cupsaddons.init.ItemRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class TokenItem extends Item {
    public TokenItem() {
    		super(new Properties().tab(ItemRegistry.CreativeTab.instance)
				.stacksTo(64)
				.rarity(Rarity.EPIC));
    }

    @Override
    public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        list.add(new TextComponent("\u00A7aSpend in your quest book for a permanent buff!"));
        list.add(new TextComponent("\u00A7dSingle Use"));
    }
}
