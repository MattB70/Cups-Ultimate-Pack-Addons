package com.mattborle.cupsaddons;

import com.mattborle.cupsaddons.client.renderer.entity.SparkRenderer;
import com.mattborle.cupsaddons.config.CupsAddonsClientConfigs;
import com.mattborle.cupsaddons.config.CupsAddonsCommonConfigs;
import com.mattborle.cupsaddons.init.*;
import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityLeaveWorldEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import org.slf4j.Logger;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

import static org.antlr.runtime.debug.DebugEventListener.PROTOCOL_VERSION;

@Mod("cupsaddons")
public class CupsAddons
{
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final String MOD_ID = "cupsaddons";
    public static final SimpleChannel PACKET_HANDLER = NetworkRegistry.newSimpleChannel(new ResourceLocation(MOD_ID, MOD_ID), () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);
    private static int messageID = 0;

    public CupsAddons()
    {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);

        ItemRegistry.MOD_ITEMS.register(modEventBus);
        EnchantmentRegistry.MOD_ENCHANTMENTS.register(modEventBus);
        MobEffectRegistry.MOD_EFFECTS.register(modEventBus);
        ModEntityTypes.ENTITY_TYPES.register(modEventBus);
        ModParticles.PARTICLE_TYPES.register(modEventBus);

        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, CupsAddonsClientConfigs.SPEC, "cupsaddons-client.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CupsAddonsCommonConfigs.SPEC, "cupsaddons-common.toml");

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // some preinit code
        LOGGER.info("Cup's Ultimate Pack Addons (COMMON)");
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Some client setup code
            LOGGER.info("Cup's Ultimate Pack Addons (CLIENT)");

            // Register mob renderers
            EntityRenderers.register(ModEntityTypes.SPARK.get(), SparkRenderer::new);
        }
    }

    @SubscribeEvent
    public static void onEntityRemoved(EntityLeaveWorldEvent event) {
        if (event.getEntity() == null) {
            return;
        }
        if (event.getEntity().getUUID() == null) {
            return;
        }
        if (event.getWorld().isClientSide) {
            GeoArmorRenderer.LIVING_ENTITY_RENDERERS.values().forEach(instances -> {
                if (instances.containsKey(event.getEntity().getUUID())) {
                    AnimationController.ModelFetcher<?> beGone = instances.get(event.getEntity().getUUID());
                    AnimationController.removeModelFetcher(beGone);
                    instances.remove(event.getEntity().getUUID());
                }
            });
        }
    }

    /*
    public static <T> void addNetworkMessage(Class<T> messageType, BiConsumer<T, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, T> decoder,
                                             BiConsumer<T, Supplier<NetworkEvent.Context>> messageConsumer) {
        PACKET_HANDLER.registerMessage(messageID, messageType, encoder, decoder, messageConsumer);
        messageID++;
    }
    */

    /*  Everything below is leftovers from example file, keeping for future reference if needed
    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // Some example code to dispatch IMC to another mod
        // InterModComms.sendTo("cupsarsenal", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {
        // Some example code to receive and process InterModComms from other mods
        // LOGGER.info("Got IMC {}", event.getIMCStream().
        //        map(m->m.messageSupplier().get()).
        //        collect(Collectors.toList()));
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        // LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents
    {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent)
        {
            // Register a new block here
            // LOGGER.info("HELLO from Register Block");
        }
    }
    */
}
