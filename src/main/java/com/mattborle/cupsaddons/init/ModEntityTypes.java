package com.mattborle.cupsaddons.init;

import com.mattborle.cupsaddons.CupsAddons;
import com.mattborle.cupsaddons.entity.SparkEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITIES, CupsAddons.MOD_ID);

    public static final RegistryObject<EntityType<SparkEntity>> SPARK =
            ENTITY_TYPES.register("spark",
                    () -> EntityType.Builder.of(SparkEntity::new, MobCategory.MONSTER)
                            .sized(0.8f, 0.8f)
                            .build(new ResourceLocation(CupsAddons.MOD_ID, "spark").toString()));


    public static void register(IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);
    }
}
