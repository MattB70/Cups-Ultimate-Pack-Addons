package com.mattborle.cupsultimatepackaddons.init;

import com.mattborle.cupsultimatepackaddons.CupsUltimatePackAddons;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MobEffectRegistry {
    // Registry
    public static final DeferredRegister<MobEffect> MOD_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, CupsUltimatePackAddons.MOD_ID);

    // Effects
    public static final RegistryObject<MobEffect> CAVE_SENSES = MOD_EFFECTS.register("cave_senses", () -> new CaveSensesMobEffect());
    public static final RegistryObject<MobEffect> AQUAPHILIA = MOD_EFFECTS.register("aquaphilia", () -> new AquaphiliaMobEffect());
    public static final RegistryObject<MobEffect> UNDEAD = MOD_EFFECTS.register("undead", () -> new UndeadMobEffect());
    public static final RegistryObject<MobEffect> GREENED_OUT = MOD_EFFECTS.register("greened_out", () -> new GreenedOutMobEffect());
    public static final RegistryObject<MobEffect> AMPED = MOD_EFFECTS.register("amped", () -> new AmpedMobEffect());
    public static final RegistryObject<MobEffect> OVERDOSED = MOD_EFFECTS.register("overdosed", () -> new OverdosedMobEffect());
    public static final RegistryObject<MobEffect> TRANSPORTING = MOD_EFFECTS.register("transporting", () -> new TransportingMobEffect());
}
