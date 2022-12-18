package com.mattborle.cupsaddons.init;

import com.mattborle.cupsaddons.CupsAddons;
import com.mattborle.cupsaddons.item.*;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

// Item registration using ReferredRegister. Items are initialized here as to avoid the hassle of static referencing.
public class ItemRegistry {

    // Creative Mode Tab
    public static class CreativeTab extends CreativeModeTab {
        private CreativeTab(int index, String label) {
            super(index, label);
        }
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(BOTTLE_O_MOONSHINE.get()); // Use an item as the tab icon
        }
        // Register the new Creative Mod menu tab.
        public static final CreativeTab instance = new CreativeTab(CreativeModeTab.TABS.length, CupsAddons.MOD_ID);
    }


    // Registry
    public static final DeferredRegister<Item> MOD_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, CupsAddons.MOD_ID);

    // Tools ===========================================================================================================
    // Register Chrysophilists Pickaxe with id chrysophilists_pickaxe and reference CHRYSOPHILISTS_PICKAXE.
    public static final RegistryObject<Item> CHRYSOPHILISTS_PICKAXE = MOD_ITEMS.register("chrysophilists_pickaxe",
            //TODO: Consider unique tiers for some items
            () -> new PickaxeItem(Tiers.DIAMOND,1, 1.0f, new Item.Properties().tab(CreativeTab.instance)));

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
    public static final RegistryObject<Item> NIGHT_CRAWLER_MOB = MOD_ITEMS.register("night_crawler_mob_spawn_egg", () -> new ForgeSpawnEggItem(EntityRegistry.NIGHT_CRAWLER_MOB, -1, -1184257, new Item.Properties().tab(CreativeTab.instance)));
}
