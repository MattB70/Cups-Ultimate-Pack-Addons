package com.mattborle.cupsaddons.init;

import com.mattborle.cupsaddons.CupsAddons;
import com.mattborle.cupsaddons.item.consumable.*;
import com.mattborle.cupsaddons.item.curios.FerromagneticObjectItem;
import com.mattborle.cupsaddons.item.ingredient.BarleyCornMixItem;
import com.mattborle.cupsaddons.item.ingredient.BundleOfSticksItem;
import com.mattborle.cupsaddons.item.ingredient.CookedMashedGrainsItem;
import com.mattborle.cupsaddons.item.ingredient.MashedGrainsItem;
import com.mattborle.cupsaddons.item.raid.RaidCommissionItem;
import com.mattborle.cupsaddons.item.raid.RaidCommissionLegendaryItem;
import com.mattborle.cupsaddons.item.scrap.ScrapItem;
import com.mattborle.cupsaddons.item.armor.GamerGlassesItem;
import com.mattborle.cupsaddons.item.tool.*;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
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
    /*  Diamond Tool reference:
                    ready_speed
        Pickaxe:    -2.8f
        Axe:        -3.0f
        Shovel:     -3.0f
        Hoe:        0.0f
        Sword:      -2.4f
    */
    public static final RegistryObject<Item> CRYSOPHILISTS_PICKAXE = MOD_ITEMS.register("crysophilists_pickaxe",
            () -> new CrysophilistsPickaxeItem(Tiers.NETHERITE,1, -2.0f, new Item.Properties()));
    public static final RegistryObject<Item> NEANDERTHALS_PICKAXE = MOD_ITEMS.register("neanderthals_pickaxe",
            () -> new NeanderthalsPickaxeItem(Tiers.STONE,1, -3.0f, new Item.Properties()));
    public static final RegistryObject<Item> EXPERTS_PICKAXE = MOD_ITEMS.register("experts_pickaxe",
            () -> new ExpertsPickaxeItem(Tiers.IRON,1, -2.8f, new Item.Properties()));
    public static final RegistryObject<Item> DISTINGUISHED_PICKAXE = MOD_ITEMS.register("distinguished_pickaxe",
            () -> new DistinguishedPickaxeItem(Tiers.DIAMOND,1, -2.8f, new Item.Properties()));
    public static final RegistryObject<Item> MASTERS_PICKAXE = MOD_ITEMS.register("masters_pickaxe",
            () -> new MastersPickaxeItem(Tiers.NETHERITE,1, -2.8f, new Item.Properties()));
    public static final RegistryObject<Item> GODS_PICKAXE = MOD_ITEMS.register("gods_pickaxe",
            () -> new GodsPickaxeItem(Tiers.NETHERITE,1, -2.8f, new Item.Properties()));


    // Curios ==========================================================================================================
    public static final RegistryObject<Item> FERROMAGNETIC_OBJECT = MOD_ITEMS.register("ferromagnetic_object", () -> new FerromagneticObjectItem(new Item.Properties()));

    // Raids and Campaigns =============================================================================================
    public static final RegistryObject<Item> RAID_COMMISSION = MOD_ITEMS.register("raid_commission", () -> new RaidCommissionItem());
    public static final RegistryObject<Item> RAID_COMMISSION_LEGENDARY = MOD_ITEMS.register("raid_commission_legendary", () -> new RaidCommissionLegendaryItem());


    // Items ===========================================================================================================
    public static final RegistryObject<Item> BUNDLE_OF_STICKS = MOD_ITEMS.register("bundle_of_sticks", () -> new BundleOfSticksItem());

    public static final RegistryObject<Item> LOOT_SCRAPS = MOD_ITEMS.register("loot_scraps", () -> new ScrapItem());

    public static final RegistryObject<Item> BARLEY_CORN_MIX = MOD_ITEMS.register("barley_corn_mix", BarleyCornMixItem::new);
    public static final RegistryObject<Item> MASHED_GRAINS = MOD_ITEMS.register("mashed_grains", () -> new MashedGrainsItem());
    public static final RegistryObject<Item> COOKED_MASHED_GRAINS = MOD_ITEMS.register("cooked_mashed_grains", () -> new CookedMashedGrainsItem());
    public static final RegistryObject<Item> BOTTLE_O_MOONSHINE = MOD_ITEMS.register("bottle_o_moonshine", () -> new BottleOMoonshineItem());
    public static final RegistryObject<Item> IMPURE_BOTTLE_O_MOONSHINE = MOD_ITEMS.register("impure_bottle_o_moonshine", () -> new ImpureBottleOMoonshineItem());

    public static final RegistryObject<Item> NIGHT_ESSENCE = MOD_ITEMS.register("night_essence", () -> new NightEssenceItem());

    public static final RegistryObject<Item> EGGNOG_AND_RUM = MOD_ITEMS.register("eggnog_and_rum", () -> new EggnogAndRumItem());
    public static final RegistryObject<Item> SWEETENED_MILK = MOD_ITEMS.register("sweetened_milk", () -> new SweetenedMilkItem());


    // Spawn Eggs ======================================================================================================
    public static final RegistryObject<Item> SPARK_SPAWN_EGG = MOD_ITEMS.register("spark_spawn_egg", () -> new ForgeSpawnEggItem(ModEntityTypes.SPARK, 0xc0c1c2, 0x54ddff,
            new Item.Properties().tab(CreativeTab.instance)));

    // Silly Items =====================================================================================================
    public static final RegistryObject<Item> GAMER_GLASSES = MOD_ITEMS.register("gamer_glasses", () -> new GamerGlassesItem(ArmorMaterials.IRON, EquipmentSlot.HEAD,
            new Item.Properties()));


    public static void register(IEventBus eventBus) {
        MOD_ITEMS.register(eventBus);
    }
}
