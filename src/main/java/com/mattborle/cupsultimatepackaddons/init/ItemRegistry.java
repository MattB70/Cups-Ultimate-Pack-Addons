package com.mattborle.cupsultimatepackaddons.init;

import com.mattborle.cupsultimatepackaddons.CupsUltimatePackAddons;
import com.mattborle.cupsultimatepackaddons.item.*;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

// Item registration using ReferredRegister. Items are initialized here as to avoid the hassle of static referencing.
public class ItemRegistry {
    // Registry
    public static final DeferredRegister<Item> MOD_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, CupsUltimatePackAddons.MOD_ID);

    // Tools ===========================================================================================================
    // Register Chrysophilists Pickaxe with id chrysophilists_pickaxe and reference CHRYSOPHILISTS_PICKAXE.
    public static final RegistryObject<Item> CHRYSOPHILISTS_PICKAXE = MOD_ITEMS.register("chrysophilists_pickaxe",
            //TODO: Consider unique tiers for some items
            () -> new PickaxeItem(Tiers.DIAMOND,1, 1.0f, new Item.Properties().tab(TabInit.TAB_ITEMS)));

    // Items ===========================================================================================================
    public static final RegistryObject<Item> BARLEY_CORN_MIX = MOD_ITEMS.register("barley_corn_mix", BarleyCornMixItem::new);
    public static final RegistryObject<Item> MASHED_GRAINS = MOD_ITEMS.register("mashed_grains", () -> new MashedGrainsItem());
    public static final RegistryObject<Item> BUNDLE_OF_STICKS = MOD_ITEMS.register("bundle_of_sticks", () -> new BundleOfSticksItem());
    public static final RegistryObject<Item> COOKED_MASHED_GRAINS = MOD_ITEMS.register("cooked_mashed_grains", () -> new CookedMashedGrainsItem());
    public static final RegistryObject<Item> BOTTLE_O_MOONSHINE = MOD_ITEMS.register("bottle_o_moonshine", () -> new BottleOMoonshineItem());
    public static final RegistryObject<Item> IMPURE_BOTTLE_O_MOONSHINE = MOD_ITEMS.register("impure_bottle_o_moonshine", () -> new ImpureBottleOMoonshineItem());
    public static final RegistryObject<Item> NIGHT_ESSENCE = MOD_ITEMS.register("night_essence", () -> new NightEssenceItem());
    public static final RegistryObject<Item> EGGNOG_AND_RUM = MOD_ITEMS.register("eggnog_and_rum", () -> new EggnogAndRumItem());
    public static final RegistryObject<Item> SWEETENED_MILK = MOD_ITEMS.register("sweetened_milk", () -> new SweetenedMilkItem());

    // Spawn Eggs ======================================================================================================
    public static final RegistryObject<Item> NIGHT_CRAWLER_MOB = MOD_ITEMS.register("night_crawler_mob_spawn_egg", () -> new ForgeSpawnEggItem(CupsUltimatePackAddons.NIGHT_CRAWLER_MOB, -1, -1184257, new Item.Properties().tab(TabInit.TAB_ITEMS)));
}
