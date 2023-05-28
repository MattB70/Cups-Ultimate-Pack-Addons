package com.mattborle.cupsaddons.init;

import com.mattborle.cupsaddons.CupsAddons;
import com.mattborle.cupsaddons.client.renderer.armor.GamerGlassesRenderer;
import com.mattborle.cupsaddons.entity.SparkEntity;
import com.mattborle.cupsaddons.item.armor.GamerGlassesItem;
import com.mattborle.cupsaddons.particle.ElectricityParticles;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

@Mod.EventBusSubscriber(modid = CupsAddons.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventClientBusEvents {

    @SubscribeEvent
    public static void registerArmorRenderers(EntityRenderersEvent.AddLayers event) {
        GeoArmorRenderer.registerArmorRenderer(GamerGlassesItem.class, () -> new GamerGlassesRenderer());
    }

    @SubscribeEvent
    public static void registerParticleFactories(final ParticleFactoryRegisterEvent event) {
        Minecraft.getInstance().particleEngine.register(ModParticles.ELECTRICITY_PARTICLES.get(), ElectricityParticles.Provider::new);
    }

    @SubscribeEvent
    public static void eventAttributeEvent(EntityAttributeCreationEvent event) {
        event.put(ModEntityTypes.SPARK.get(), SparkEntity.setAttributes());
    }
}
