package com.mattborle.cupsaddons.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class CupsAddonsClientConfigs {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    static {
        BUILDER.push("Configs for Cups Addons (Client)");

        // Define client configurables here!

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
