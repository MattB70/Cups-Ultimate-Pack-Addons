package com.mattborle.cupsaddons.config;

import net.minecraft.world.item.Items;
import net.minecraftforge.common.ForgeConfigSpec;

public class CupsAddonsCommonConfigs {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Boolean> ABILITY_CRYSOPHILISTS_PICKAXE;
    public static final ForgeConfigSpec.ConfigValue<Double> TUNE_CRYSOPHILISTS_PICKAXE;
    public static final ForgeConfigSpec.ConfigValue<String> FUEL_CRYSOPHILISTS_PICKAXE;
    public static final ForgeConfigSpec.ConfigValue<Integer> MAX_FUEL_CRYSOPHILISTS_PICKAXE;

    static {

        BUILDER.push("Crysophilist's Pickaxe");
        ABILITY_CRYSOPHILISTS_PICKAXE = BUILDER.comment("Enable the Crysophilist's Pickaxe's ability (Default: true)")
                .define("Crysophilist's Pickaxe Ability (Boolean)", true);
        TUNE_CRYSOPHILISTS_PICKAXE = BUILDER.comment("How strong should the Crysophilist's Pickaxe (Applies to most attributes including ability) (Default: 1.0)")
                .defineInRange("Crysophilist's Pickaxe Strength (%)", 1.0, 0.0, 100.0);
        FUEL_CRYSOPHILISTS_PICKAXE = BUILDER.comment("Item to be used as fuel for the Crysophilist's Pickaxe's ability. (Default: \"lapis_lazuli\")")
                .define("Crysophilist's Pickaxe Fuel Item (Vanilla ItemID)", Items.LAPIS_LAZULI.toString());
        MAX_FUEL_CRYSOPHILISTS_PICKAXE = BUILDER.comment("The maximum fueled usages of the item before needing to be refueled. (Default: 32)")
                .defineInRange("Crysophilist's Pickaxe Max Fuel (Integer)", 32, 1, 2147483647);
        BUILDER.pop();


        SPEC = BUILDER.build();
    }
}
