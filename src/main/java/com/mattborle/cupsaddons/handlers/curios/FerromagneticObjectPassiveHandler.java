package com.mattborle.cupsaddons.handlers.curios;

import com.mattborle.cupsaddons.CupsAddons;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.util.NonNullSupplier;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.IItemHandlerModifiable;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.CuriosApi;


@Mod.EventBusSubscriber
public class FerromagneticObjectPassiveHandler {
	@SubscribeEvent
	public static void onEntityAttacked(LivingAttackEvent event) {
		// If the event involves a living entity
		if (event != null && event.getEntityLiving() != null) {
			// If the living entity is a player
			if (event.getEntityLiving() instanceof Player) {
				// Only on the server side:
				if (!event.getEntityLiving().level.isClientSide) {
					// Do something:
					CupsAddons.LOGGER.info("");
					CupsAddons.LOGGER.info("Cups Addons Curios Debug:");
					CupsAddons.LOGGER.info("   " + CuriosApi.getCuriosHelper().getEquippedCurios(event.getEntityLiving()));
					CupsAddons.LOGGER.info("");
				}
			}
		}
	}
}
