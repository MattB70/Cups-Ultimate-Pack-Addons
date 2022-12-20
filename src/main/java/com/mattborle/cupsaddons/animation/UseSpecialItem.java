package com.mattborle.cupsaddons.animation;

import com.mattborle.cupsaddons.CupsAddons;
import com.mattborle.cupsaddons.config.CupsAddonsCommonConfigs;
import dev.kosmx.playerAnim.api.layered.IAnimation;
import dev.kosmx.playerAnim.api.layered.KeyframeAnimationPlayer;
import dev.kosmx.playerAnim.api.layered.ModifierLayer;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationAccess;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Objects;

// These animation triggers must remain client sided.
@Mod.EventBusSubscriber(modid = CupsAddons.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class UseSpecialItem {

    @SubscribeEvent
    public static void onSpecialItemUsed(PlayerInteractEvent.RightClickItem event) {
        // Run on client side, and only once using the MainHand. 0 = true in this comparison for some reason.
        if (event.getWorld().isClientSide && event.getHand().compareTo(InteractionHand.MAIN_HAND) == 0){
            var player = Minecraft.getInstance().player;
            if (player == null) return;

            // Get the animations for the player, if this fails, print an error and return without crashing.
            var animation = (ModifierLayer<IAnimation>) PlayerAnimationAccess.getPlayerAssociatedData((AbstractClientPlayer) player)
                    .get(new ResourceLocation(CupsAddons.MOD_ID, "animation"));
            if (animation == null) return;


            // Animation selection =========================================================================================
            // Here we pair every special item in a hash map with another item it can be fueled with.
            //      Example: Chrysophilist's Pickaxe can be fueled with Lapis Lazuli.
            // Any item in this list will play the "animation.model.tool.use" animation upon right-clicking.


            // Items the player is holding upon right-clicking
            Item mainHandItem = event.getItemStack().getItem();
            Item offHandItem = player.getOffhandItem().getItem();
            CupsAddons.LOGGER.info("mainHandItem: " + mainHandItem + ", offHandItem: " + offHandItem + ", Required Fuel: " + CupsAddonsCommonConfigs.FUEL_CHRYSOPHILISTS_PICKAXE.get());

            // This is the logic for the animation, and as such can exist on the client. The effect is handled by the item.
            // check if the player is holding both special item and its fuel, if so, play the animation.
            if (mainHandItem.toString() == "chrysophilists_pickaxe" && offHandItem.toString() == CupsAddonsCommonConfigs.FUEL_CHRYSOPHILISTS_PICKAXE.get()) {

                // Get tool use animation (grab tool with two hands in front of the player)
                animation.setAnimation(new KeyframeAnimationPlayer(Objects.requireNonNull(PlayerAnimationRegistry
                        .getAnimation(new ResourceLocation("cupsaddons", "animation.model.tool.use")))));
            }
        }
    }
}
