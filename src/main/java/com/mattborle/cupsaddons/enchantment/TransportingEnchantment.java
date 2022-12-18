package com.mattborle.cupsaddons.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class TransportingEnchantment extends Enchantment{
    public TransportingEnchantment(EquipmentSlot... slots) {
        super(Enchantment.Rarity.VERY_RARE, EnchantmentCategory.ARMOR_LEGS, slots);
    }

    @Override
    public int getMinLevel() {
        return 0;
    }

    @Override
    public int getMaxLevel() {
        return 0;
    }

    @Override
    public boolean isAllowedOnBooks() {
        return false;
    }

    @Override
    public boolean isDiscoverable() {
        return false;
    }

    @Override
    public boolean isTradeable() {
        return false;
    }
}
