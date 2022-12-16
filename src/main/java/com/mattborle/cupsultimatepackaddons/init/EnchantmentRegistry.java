package com.mattborle.cupsultimatepackaddons.init;

import com.mattborle.cupsultimatepackaddons.CupsUltimatePackAddons;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EnchantmentRegistry {
    // Registry
    public static final DeferredRegister<Enchantment> MOD_ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, CupsUltimatePackAddons.MOD_ID);

    // Enchantments
    public static final RegistryObject<Enchantment> TRANSPORTING_ENCHANTMENT = MOD_ENCHANTMENTS.register("transporting_enchantment",
            () -> new TransportingEnchantment());
}
