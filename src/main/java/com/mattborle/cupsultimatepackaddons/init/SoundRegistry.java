package com.mattborle.cupsultimatepackaddons.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class SoundRegistry {
    // Registry
    public static Map<ResourceLocation, SoundEvent> MOD_SOUNDS = new HashMap<>();

    // Sounds
    static {
        MOD_SOUNDS.put(new ResourceLocation("cupsultimatepackaddons", "night_crawler_hurt"), new SoundEvent(new ResourceLocation("cupsultimatepackaddons", "night_crawler_hurt")));
        MOD_SOUNDS.put(new ResourceLocation("cupsultimatepackaddons", "night_crawler_death"), new SoundEvent(new ResourceLocation("cupsultimatepackaddons", "night_crawler_death")));
        MOD_SOUNDS.put(new ResourceLocation("cupsultimatepackaddons", "night_crawler_ambient"), new SoundEvent(new ResourceLocation("cupsultimatepackaddons", "night_crawler_ambient")));
        MOD_SOUNDS.put(new ResourceLocation("cupsultimatepackaddons", "transport_end"), new SoundEvent(new ResourceLocation("cupsultimatepackaddons", "transport_end")));
        MOD_SOUNDS.put(new ResourceLocation("cupsultimatepackaddons", "transport_observed"), new SoundEvent(new ResourceLocation("cupsultimatepackaddons", "transport_observed")));
    }

    @SubscribeEvent
    public static void registerSounds(RegistryEvent.Register<SoundEvent> event) {
        for (Map.Entry<ResourceLocation, SoundEvent> sound : MOD_SOUNDS.entrySet())
            event.getRegistry().register(sound.getValue().setRegistryName(sound.getKey()));
    }
}
