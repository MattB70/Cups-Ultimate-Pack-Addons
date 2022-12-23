package com.mattborle.cupsaddons.animation;

import com.mattborle.cupsaddons.CupsAddons;
import dev.kosmx.playerAnim.api.layered.IAnimation;
import dev.kosmx.playerAnim.api.layered.KeyframeAnimationPlayer;
import dev.kosmx.playerAnim.api.layered.ModifierLayer;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationAccess;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Objects;

import static com.mattborle.cupsaddons.init.SpecialItemDataInit.specialItemData;

// These animation triggers must remain client sided.
@Mod.EventBusSubscriber(modid = CupsAddons.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class UseSpecialItem {

    // TODO: re-write to remove nested if-statements.

    @SubscribeEvent
    public static void onSpecialItemUsed(PlayerInteractEvent.RightClickItem event) {
        // Run on client side, and only once using the MainHand. 0 = true in this comparison for some reason.
        if (event.getWorld().isClientSide && event.getHand().compareTo(InteractionHand.MAIN_HAND) == 0) {
            var player = Minecraft.getInstance().player;
            if (player == null) return;

            // Get the animations for the player, if this fails, print an error and return without crashing.
            var animation = (ModifierLayer<IAnimation>) PlayerAnimationAccess.getPlayerAssociatedData((AbstractClientPlayer) player)
                    .get(new ResourceLocation(CupsAddons.MOD_ID, "animation"));
            if (animation == null) return;


            // Animation selection =========================================================================================

            // Items the player is holding upon right-clicking
            Item mainHandItem = event.getItemStack().getItem();
            Item offHandItem = player.getOffhandItem().getItem();

            // This is the logic for the animation, and as such can exist on the client. The server side effect is
            // handled in ItemNameItem.java.
            for (int i = 0; i < specialItemData.length; i++) {
                // Check if the player is holding both special item and its fuel, return if not
                if (!mainHandItem.toString().equals(specialItemData[i][0]) || !offHandItem.toString().equals(specialItemData[i][1])) {
                    return;
                }
                // If the item has no fuel nbt,
                if (event.getItemStack().getTag() == null) {
                    return;
                }
                // If the fuel nbt cannot be parsed,
                if (event.getItemStack().getTag().get("cupsaddons.fuel") != null) {
                    return;
                }
                // If the tool's fuel is below its maximum,
                if (Integer.parseInt(event.getItemStack().getTag().get("cupsaddons.fuel").getAsString()) < Integer.parseInt(specialItemData[i][2])) {
                    // Get tool use animation (grab tool with two hands in front of the player) and play the animation.
                    animation.setAnimation(new KeyframeAnimationPlayer(Objects.requireNonNull(PlayerAnimationRegistry
                            .getAnimation(new ResourceLocation("cupsaddons", "animation.model.tool.use")))));
                }
            }
        }
    }
}