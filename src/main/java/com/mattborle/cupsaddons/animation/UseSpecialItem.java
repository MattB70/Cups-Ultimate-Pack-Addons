package com.mattborle.cupsaddons.animation;

import com.mattborle.cupsaddons.CupsAddons;
import com.mattborle.cupsaddons.init.ItemRegistry;
import dev.kosmx.playerAnim.api.layered.IAnimation;
import dev.kosmx.playerAnim.api.layered.KeyframeAnimationPlayer;
import dev.kosmx.playerAnim.api.layered.ModifierLayer;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationAccess;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Objects;

// These animation triggers must remain client sided.
@Mod.EventBusSubscriber(modid = CupsAddons.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class UseSpecialItem {

    @SubscribeEvent
    public static void onSpecialItemUsed(PlayerInteractEvent.RightClickItem event){
        var player = Minecraft.getInstance().player;
        if (player == null) return;

        // Get the animations for the player, if this fails, print an error and return without crashing.
        var animation = (ModifierLayer<IAnimation>) PlayerAnimationAccess.getPlayerAssociatedData((AbstractClientPlayer) player).get(new ResourceLocation(CupsAddons.MOD_ID, "animation"));
        if (animation == null) return;


        // Animation selection =========================================================================================
        // Here we place every special item and set an animation to play.
        CupsAddons.LOGGER.debug("Held Item = "+event.getPlayer().getItemInHand(InteractionHand.MAIN_HAND).getItem()+" vs "+ItemRegistry.BARLEY_CORN_MIX.get());
        if (event.getPlayer().getItemInHand(InteractionHand.MAIN_HAND).getItem() == ItemRegistry.BARLEY_CORN_MIX.get()) // TODO: replace item reference with correct item.
        {
            CupsAddons.LOGGER.debug("Passed!");
            // Get tool use animation (grab tool with two hands in front of the player)
            animation.setAnimation(new KeyframeAnimationPlayer(Objects.requireNonNull(PlayerAnimationRegistry.getAnimation(new ResourceLocation("cupsaddons", "animation.model.tool.use")))));
        }
    }
}
