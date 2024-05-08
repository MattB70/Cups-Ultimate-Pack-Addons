package com.mattborle.cupsaddons.init;

import com.mattborle.cupsaddons.CupsAddons;
import com.mattborle.cupsaddons.structures.CompactedOreVein;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class StructuresInit {

    public static final DeferredRegister<StructureFeature<?>> DEFERRED_REGISTRY_STRUCTURE = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, CupsAddons.MOD_ID);

    //this base structure will have the resourcelocation of cupsaddons:compacted_ore_vein.
    public static final RegistryObject<StructureFeature<?>> COMPACTED_ORE_VEINS = DEFERRED_REGISTRY_STRUCTURE.register("compacted_ore_vein", CompactedOreVein::new);
}
