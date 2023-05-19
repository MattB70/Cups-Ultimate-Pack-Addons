package com.mattborle.cupsaddons.config;

import net.minecraft.world.item.Items;
import net.minecraftforge.common.ForgeConfigSpec;

public class CupsAddonsCommonConfigs {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    // Crysophilist's Pickaxe
    public static final ForgeConfigSpec.ConfigValue<Boolean> ABILITY_CRYSOPHILISTS_PICKAXE;
    public static final ForgeConfigSpec.ConfigValue<String> FUEL_CRYSOPHILISTS_PICKAXE;
    public static final ForgeConfigSpec.ConfigValue<Integer> MAX_FUEL_CRYSOPHILISTS_PICKAXE;
    public static final ForgeConfigSpec.ConfigValue<Double> TUNE_CRYSOPHILISTS_PICKAXE;
    // Neanderthal's Pickaxe
    public static final ForgeConfigSpec.ConfigValue<Boolean> ABILITY_NEANDERTHALS_PICKAXE;
    public static final ForgeConfigSpec.ConfigValue<String> FUEL_NEANDERTHALS_PICKAXE;
    public static final ForgeConfigSpec.ConfigValue<Integer> MAX_FUEL_NEANDERTHALS_PICKAXE;
    public static final ForgeConfigSpec.ConfigValue<Double> TUNE_NEANDERTHALS_PICKAXE;

    // Ferromagnetic Object
    public static final ForgeConfigSpec.ConfigValue<Boolean> ACTIVE_ABILITY_FERROMAGNETIC_OBJECT;
    //public static final ForgeConfigSpec.ConfigValue<Boolean> PASSIVE_ABILITY_FERROMAGNETIC_OBJECT;
    public static final ForgeConfigSpec.ConfigValue<Double> COOLDOWN_FERROMAGNETIC_OBJECT;
    public static final ForgeConfigSpec.ConfigValue<Double> TUNE_FERROMAGNETIC_OBJECT;


    static {

        // Tools
        BUILDER.push("Crysophilist's Pickaxe");
        ABILITY_CRYSOPHILISTS_PICKAXE = BUILDER.comment("Enable the Crysophilist's Pickaxe's ability (Default: true)")
                .define("Crysophilist's Pickaxe Ability (Boolean)", true);
        FUEL_CRYSOPHILISTS_PICKAXE = BUILDER.comment("Item to be used as fuel for the Crysophilist's Pickaxe's ability. (Default: \"lapis_lazuli\")")
                .define("Crysophilist's Pickaxe Fuel Item (Vanilla ItemID)", Items.LAPIS_LAZULI.toString());
        MAX_FUEL_CRYSOPHILISTS_PICKAXE = BUILDER.comment("The maximum fueled usages of the item before needing to be refueled. (Default: 32)")
                .defineInRange("Crysophilist's Pickaxe Max Fuel (Integer)", 32, 1, 2147483647);
        TUNE_CRYSOPHILISTS_PICKAXE = BUILDER.comment("How strong should the Crysophilist's Pickaxe be (Applies to most attributes including abilities) (Default: 1.0 = 100%)")
                .defineInRange("Crysophilist's Pickaxe Strength (%)", 1.0, 0.0, 100.0);
        BUILDER.pop();

        BUILDER.push("Neanderthal's Pickaxe");
        ABILITY_NEANDERTHALS_PICKAXE = BUILDER.comment("Enable the Neanderthal's Pickaxe's ability (Default: true)")
                .define("Neanderthal's Pickaxe Ability (Boolean)", true);
        FUEL_NEANDERTHALS_PICKAXE = BUILDER.comment("Item to be used as fuel for the Neanderthal's Pickaxe's ability. (Default: \"cobblestone\")")
                .define("Neanderthal's Pickaxe Fuel Item (Vanilla ItemID)", Items.COBBLESTONE.toString());
        MAX_FUEL_NEANDERTHALS_PICKAXE = BUILDER.comment("The maximum fueled usages of the item before needing to be refueled. (Default: 4)")
                .defineInRange("Neanderthal's Pickaxe Max Fuel (Integer)", 4, 1, 2147483647);
        TUNE_NEANDERTHALS_PICKAXE = BUILDER.comment("How strong should the Neanderthal's Pickaxe be (Applies to most attributes including abilities) (Default: 1.0 = 100%)")
                .defineInRange("Neanderthal's Pickaxe Strength (%)", 1.0, 0.0, 100.0);
        BUILDER.pop();



        // Weapons



        // Curios
        BUILDER.push("Ferromagnetic Object");
        ACTIVE_ABILITY_FERROMAGNETIC_OBJECT = BUILDER.comment("Enable the Ferromagnetic Object's active ability (Default: true)")
                .define("Ferromagnetic Object's Active Ability (Boolean)", true);
        //PASSIVE_ABILITY_FERROMAGNETIC_OBJECT = BUILDER.comment("Enable the Ferromagnetic Object's passive ability (Default: true)")
        //        .define("Ferromagnetic Object's Passive Ability (Boolean)", true);
        COOLDOWN_FERROMAGNETIC_OBJECT = BUILDER.comment("How low the cooldown should be for the Ferromagnetic Object (Default: 5.0 = 5s)")
                .defineInRange("Ferromagnetic Object Cooldown (s)", 5.0, 0.0, 86400.0);
        TUNE_FERROMAGNETIC_OBJECT = BUILDER.comment("How strong should the Ferromagnetic Object be (Applies to most attributes including abilities) (Default: 1.0 = 100%)")
                .defineInRange("Ferromagnetic Object Strength (%)", 1.0, 0.0, 100.0);
        BUILDER.pop();


        SPEC = BUILDER.build();
    }
}
