package com.mattborle.cupsaddons.item.scrap;

import com.mattborle.cupsaddons.init.ItemRegistry;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.List;

public class ScrapItem extends Item {
    public ScrapItem() {
        super(new Properties().tab(ItemRegistry.CreativeTab.instance)
                .stacksTo(64)
                .rarity(Rarity.UNCOMMON));
    }
    @Override
    public void appendHoverText(ItemStack stack, Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        tooltip.add(new TranslatableComponent("tooltip.cupsaddons.scrap_description"));
    }
}
