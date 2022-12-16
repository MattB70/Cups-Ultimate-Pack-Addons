package com.mattborle.cupsultimatepackaddons.init;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class FuelInit {
    @SubscribeEvent
    public static void furnaceFuelBurnTimeEvent(FurnaceFuelBurnTimeEvent event) {
        ItemStack itemstack = event.getItemStack();
        if (itemstack.getItem() == CupsUltimatePackAddonsItems.BOTTLE_O_MOONSHINE.get())
            event.setBurnTime(3200);
        else if (itemstack.getItem() == CupsUltimatePackAddonsItems.IMPURE_BOTTLE_O_MOONSHINE.get())
            event.setBurnTime(2800);
    }
}
