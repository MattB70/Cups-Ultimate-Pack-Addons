package com.mattborle.cupsaddons.init;

import com.mattborle.cupsaddons.CupsAddons;
import com.mattborle.cupsaddons.entity.NightCrawlerMobEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

// Entity registration using ReferredRegister. Entities are initialized here as to avoid the hassle of static referencing.
public class EntityRegistry {
    // Registry
    public static DeferredRegister<EntityType<?>> MOD_ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, CupsAddons.MOD_ID);

    // Mobs ============================================================================================================
    // Night Crawler
    public static final RegistryObject<EntityType<NightCrawlerMobEntity>> NIGHT_CRAWLER_MOB = register("night_crawler_mob",
            EntityType.Builder.<NightCrawlerMobEntity>of(NightCrawlerMobEntity::new, MobCategory.MONSTER)
                    .setShouldReceiveVelocityUpdates(true)
                    .setTrackingRange(64)
                    .setUpdateInterval(3)
                    .setCustomClientFactory(NightCrawlerMobEntity::new)
                    .sized(0.6f, 1.9f));

    private static <T extends Entity> RegistryObject<EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
        return MOD_ENTITIES.register(registryname, () -> entityTypeBuilder.build(registryname));
    }
    @SubscribeEvent
    public static void init(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            NightCrawlerMobEntity.init();
        });
    }
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(NIGHT_CRAWLER_MOB.get(), NightCrawlerMobEntity.createAttributes().build());
    }
}