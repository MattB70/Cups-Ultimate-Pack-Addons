package com.mattborle.cupsaddons.init;

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
        MOD_SOUNDS.put(new ResourceLocation("cupsaddons", "transport_end"), new SoundEvent(new ResourceLocation("cupsaddons", "transport_end")));
        MOD_SOUNDS.put(new ResourceLocation("cupsaddons", "transport_observed"), new SoundEvent(new ResourceLocation("cupsaddons", "transport_observed")));
        MOD_SOUNDS.put(new ResourceLocation("cupsaddons", "magnetize"), new SoundEvent(new ResourceLocation("cupsaddons", "magnetize")));
        MOD_SOUNDS.put(new ResourceLocation("cupsaddons", "magic_pop"), new SoundEvent(new ResourceLocation("cupsaddons", "magic_pop")));
        MOD_SOUNDS.put(new ResourceLocation("cupsaddons", "spark_hover"), new SoundEvent(new ResourceLocation("cupsaddons", "spark_hover")));
        MOD_SOUNDS.put(new ResourceLocation("cupsaddons", "spark_death"), new SoundEvent(new ResourceLocation("cupsaddons", "spark_death")));
        MOD_SOUNDS.put(new ResourceLocation("cupsaddons", "spark_attack"), new SoundEvent(new ResourceLocation("cupsaddons", "spark_attack")));
    }

    @SubscribeEvent
    public static void registerSounds(RegistryEvent.Register<SoundEvent> event) {
        for (Map.Entry<ResourceLocation, SoundEvent> sound : MOD_SOUNDS.entrySet())
            event.getRegistry().register(sound.getValue().setRegistryName(sound.getKey()));
    }
}
