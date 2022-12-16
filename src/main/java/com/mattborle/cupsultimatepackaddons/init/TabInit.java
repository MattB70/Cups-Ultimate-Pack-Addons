package com.mattborle.cupsultimatepackaddons.init;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class TabInit {
    public static CreativeModeTab TAB_ITEMS;

    public static void load() {
        TAB_ITEMS = new CreativeModeTab("tabitems") {
            @Override
            public ItemStack makeIcon() {
                return new ItemStack(CupsUltimatePackAddonsModItems.BARLEY_CORN_MIX.get());
            }

            @OnlyIn(Dist.CLIENT)
            public boolean hasSearchBar() {
                return false;
            }
        };
    }
}
