package com.mattborle.cupsaddons.init;

import com.mattborle.cupsaddons.config.CupsAddonsCommonConfigs;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class SpecialItemDataInit {
    // Here we pair every special item in an array with another item it can be fueled with. An array is fine.
    // Example: Crysophilist's Pickaxe can be fueled with whatever is defined in the common config.
    // Any item pair in this array will play the "animation.model.tool.use" player animation upon right-clicking.
    public static String[][] specialItemData = {
            { "crysophilists_pickaxe", CupsAddonsCommonConfigs.FUEL_CRYSOPHILISTS_PICKAXE.get(), CupsAddonsCommonConfigs.MAX_FUEL_CRYSOPHILISTS_PICKAXE.get().toString()},
            { "neanderthals_pickaxe", CupsAddonsCommonConfigs.FUEL_NEANDERTHALS_PICKAXE.get(), CupsAddonsCommonConfigs.MAX_FUEL_NEANDERTHALS_PICKAXE.get().toString()}
    };
}
