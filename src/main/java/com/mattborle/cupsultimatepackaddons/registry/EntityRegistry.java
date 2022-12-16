package com.mattborle.cupsultimatepackaddons.registry;

import com.mattborle.cupsultimatepackaddons.CupsUltimatePackAddons;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

// Entity registration using ReferredRegister. Entities are initialized here as to avoid the hassle of static referencing.
public class EntityRegistry {
    public static DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, CupsUltimatePackAddons.MOD_ID);
}