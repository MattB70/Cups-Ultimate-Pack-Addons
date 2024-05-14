package com.mattborle.cupsaddons.world.feature;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ModPlacedFeatures {

    // Ore Trees =======================================================================================================
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


    // Compacted Ores ==================================================================================================
    // These don't generate often, and are meant to act as a red herring for the much larger, taller deposits generated
    // with adlods in CUP.

    /* DISABLED ALUMINUM
    public static final Holder<PlacedFeature> COMPACTED_ALUMINUM_ORE_PLACED = PlacementUtils.register("compacted_aluminum_ore_placed",
            ModConfiguredFeatures.COMPACTED_ALUMINUM_ORE, ModOrePlacement.rareOrePlacement(10, // Once every x chunks
                    HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(-64), VerticalAnchor.aboveBottom(600))));
    */
    public static final Holder<PlacedFeature> COMPACTED_COAL_ORE_PLACED = PlacementUtils.register("compacted_coal_ore_placed",
            ModConfiguredFeatures.COMPACTED_COAL_ORE, ModOrePlacement.rareOrePlacement(12, // Once every x chunks
                    HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(-64), VerticalAnchor.aboveBottom(600))));

    public static final Holder<PlacedFeature> COMPACTED_COPPER_ORE_PLACED = PlacementUtils.register("compacted_copper_ore_placed",
            ModConfiguredFeatures.COMPACTED_COPPER_ORE, ModOrePlacement.rareOrePlacement(12, // Once every x chunks
                    HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(-64), VerticalAnchor.aboveBottom(600))));

    public static final Holder<PlacedFeature> COMPACTED_GOLD_ORE_PLACED = PlacementUtils.register("compacted_gold_ore_placed",
            ModConfiguredFeatures.COMPACTED_GOLD_ORE, ModOrePlacement.rareOrePlacement(10, // Once every x chunks
                    HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(-64), VerticalAnchor.aboveBottom(600))));

    public static final Holder<PlacedFeature> COMPACTED_IRON_ORE_PLACED = PlacementUtils.register("compacted_iron_ore_placed",
            ModConfiguredFeatures.COMPACTED_IRON_ORE, ModOrePlacement.rareOrePlacement(12, // Once every x chunks
                    HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(-64), VerticalAnchor.aboveBottom(600))));

    public static final Holder<PlacedFeature> COMPACTED_TIN_ORE_PLACED = PlacementUtils.register("compacted_tin_ore_placed",
            ModConfiguredFeatures.COMPACTED_TIN_ORE, ModOrePlacement.rareOrePlacement(10, // Once every x chunks
                    HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(-64), VerticalAnchor.aboveBottom(600))));

    public static final Holder<PlacedFeature> COMPACTED_ZINC_ORE_PLACED = PlacementUtils.register("compacted_zinc_ore_placed",
            ModConfiguredFeatures.COMPACTED_ZINC_ORE, ModOrePlacement.rareOrePlacement(10, // Once every x chunks
                    HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(-64), VerticalAnchor.aboveBottom(600))));

}
