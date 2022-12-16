package com.mattborle.cupsultimatepackaddons.registry;

import com.mattborle.cupsultimatepackaddons.CupsUltimatePackAddons;
import net.minecraft.world.item.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

// Item registration using ReferredRegister. Items are initialized here as to avoid the hassle of static referencing.
public class ItemRegistry {
    // Create a Creative Mode menu tab for this mod
    public static class CreativeTab extends CreativeModeTab {
        private CreativeTab(int index, String label) {
            super(index, label);
        }
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(CHRYSOPHILISTS_PICKAXE.get()); // Use an item as the tab icon
        }
        // Register the new Creative Mod menu tab.
        public static final CreativeTab instance = new CreativeTab(CreativeModeTab.TABS.length, CupsUltimatePackAddons.MOD_ID);
    }

    // Get the item register, so we can tell the game about our items
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, CupsUltimatePackAddons.MOD_ID);

    // Tools ===========================================================================================================
    // Register Chrysophilists Pickaxe with id chrysophilists_pickaxe and reference CHRYSOPHILISTS_PICKAXE.
    public static final RegistryObject<Item> CHRYSOPHILISTS_PICKAXE = ITEMS.register("chrysophilists_pickaxe",
            //TODO: Consider unique tiers for some items
            () -> new PickaxeItem(Tiers.DIAMOND,1, 1.0f, new Item.Properties().tab(CreativeTab.instance)));
}
