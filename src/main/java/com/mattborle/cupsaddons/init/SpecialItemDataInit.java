package com.mattborle.cupsaddons.init;

import com.mattborle.cupsaddons.config.CupsAddonsCommonConfigs;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class SpecialItemDataInit {
    // Here we pair every special item in an array with another item it can be fueled with. An array is fine.
    // Example: Chrysophilist's Pickaxe can be fueled with whatever is defined in the common config.
    // Any item pair in this array will play the "animation.model.tool.use" player animation upon right-clicking.
    public static String[][] specialItemData = {
            { "chrysophilists_pickaxe", CupsAddonsCommonConfigs.FUEL_CHRYSOPHILISTS_PICKAXE.get(), CupsAddonsCommonConfigs.MAX_FUEL_CHRYSOPHILISTS_PICKAXE.get().toString()}
    };
}
