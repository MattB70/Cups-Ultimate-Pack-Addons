package com.mattborle.cupsaddons.world.feature;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ModPlacedFeatures {

    public static final Holder<PlacedFeature> GOLDEN_OAK_PLACED = PlacementUtils.register("golden_oak_placed",
            ModConfiguredFeatures.GOLDEN_OAK_SPAWN, VegetationPlacements.treePlacement(
                    PlacementUtils.countExtra(1, 0.1f, 1)));

    public static final Holder<PlacedFeature> IRON_OAK_PLACED = PlacementUtils.register("iron_oak_placed",
            ModConfiguredFeatures.IRON_OAK_SPAWN, VegetationPlacements.treePlacement(
                    PlacementUtils.countExtra(2, 0.1f, 1)));

    public static final Holder<PlacedFeature> COPPER_OAK_PLACED = PlacementUtils.register("copper_oak_placed",
            ModConfiguredFeatures.COPPER_OAK_SPAWN, VegetationPlacements.treePlacement(
                    PlacementUtils.countExtra(2, 0.1f, 1)));

    public static final Holder<PlacedFeature> ZINC_OAK_PLACED = PlacementUtils.register("zinc_oak_placed",
            ModConfiguredFeatures.ZINC_OAK_SPAWN, VegetationPlacements.treePlacement(
                    PlacementUtils.countExtra(2, 0.1f, 1)));

    public static final Holder<PlacedFeature> RICH_OAK_PLACED = PlacementUtils.register("rich_oak_placed",
            ModConfiguredFeatures.RICH_OAK_SPAWN, VegetationPlacements.treePlacement(
                    PlacementUtils.countExtra(1, 0.1f, 1)));

}
