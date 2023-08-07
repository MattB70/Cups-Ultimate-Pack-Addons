package com.mattborle.cupsaddons.world.feature;

import com.mattborle.cupsaddons.init.BlockRegistry;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;

public class ModConfiguredFeatures {


    // Ore trees =======================================================================================================
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> GOLDEN_OAK_TREE =
            FeatureUtils.register("golden_oak", Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(BlockRegistry.GOLDEN_OAK_LOG.get()),
                    new StraightTrunkPlacer(3,1,1),
                    BlockStateProvider.simple(BlockRegistry.METALLIC_OAK_LEAVES.get()),
                    new BlobFoliagePlacer(ConstantInt.of(2),ConstantInt.of(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1)
            ).build());
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> IRON_OAK_TREE =
            FeatureUtils.register("iron_oak", Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(BlockRegistry.IRON_OAK_LOG.get()),
                    new StraightTrunkPlacer(3,1,1),
                    BlockStateProvider.simple(BlockRegistry.METALLIC_OAK_LEAVES.get()),
                    new BlobFoliagePlacer(ConstantInt.of(2),ConstantInt.of(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1)
            ).build());
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> COPPER_OAK_TREE =
            FeatureUtils.register("copper_oak", Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(BlockRegistry.COPPER_OAK_LOG.get()),
                    new StraightTrunkPlacer(3,1,1),
                    BlockStateProvider.simple(BlockRegistry.METALLIC_OAK_LEAVES.get()),
                    new BlobFoliagePlacer(ConstantInt.of(2),ConstantInt.of(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1)
            ).build());
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> ZINC_OAK_TREE =
            FeatureUtils.register("zinc_oak", Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(BlockRegistry.ZINC_OAK_LOG.get()),
                    new StraightTrunkPlacer(3,1,1),
                    BlockStateProvider.simple(BlockRegistry.METALLIC_OAK_LEAVES.get()),
                    new BlobFoliagePlacer(ConstantInt.of(2),ConstantInt.of(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1)
            ).build());
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> RICH_OAK_TREE =
            FeatureUtils.register("rich_oak", Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(BlockRegistry.RICH_OAK_LOG.get()),
                    new StraightTrunkPlacer(3,1,1),
                    BlockStateProvider.simple(BlockRegistry.METALLIC_OAK_LEAVES.get()),
                    new BlobFoliagePlacer(ConstantInt.of(2),ConstantInt.of(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1)
            ).build());


    // ore tree spawners ===============================================================================================
    public static final Holder<PlacedFeature> GOLDEN_OAK_CHECKED = PlacementUtils.register("golden_oak_checked", GOLDEN_OAK_TREE,
            PlacementUtils.filteredByBlockSurvival(BlockRegistry.GOLDEN_OAK_SAPLING.get()));
    public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> GOLDEN_OAK_SPAWN =
            FeatureUtils.register("golden_oak_spawn", Feature.RANDOM_SELECTOR,
                    new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(GOLDEN_OAK_CHECKED,
                            0.005F)), GOLDEN_OAK_CHECKED));

    public static final Holder<PlacedFeature> IRON_OAK_CHECKED = PlacementUtils.register("iron_oak_checked", IRON_OAK_TREE,
            PlacementUtils.filteredByBlockSurvival(BlockRegistry.IRON_OAK_SAPLING.get()));
    public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> IRON_OAK_SPAWN =
            FeatureUtils.register("iron_oak_spawn", Feature.RANDOM_SELECTOR,
                    new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(IRON_OAK_CHECKED,
                            0.01F)), IRON_OAK_CHECKED));

    public static final Holder<PlacedFeature> COPPER_OAK_CHECKED = PlacementUtils.register("copper_oak_checked", COPPER_OAK_TREE,
            PlacementUtils.filteredByBlockSurvival(BlockRegistry.COPPER_OAK_SAPLING.get()));
    public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> COPPER_OAK_SPAWN =
            FeatureUtils.register("copper_oak_spawn", Feature.RANDOM_SELECTOR,
                    new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(COPPER_OAK_CHECKED,
                            0.01F)), COPPER_OAK_CHECKED));

    public static final Holder<PlacedFeature> ZINC_OAK_CHECKED = PlacementUtils.register("zinc_oak_checked", ZINC_OAK_TREE,
            PlacementUtils.filteredByBlockSurvival(BlockRegistry.ZINC_OAK_SAPLING.get()));
    public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> ZINC_OAK_SPAWN =
            FeatureUtils.register("zinc_oak_spawn", Feature.RANDOM_SELECTOR,
                    new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(ZINC_OAK_CHECKED,
                            0.01F)), ZINC_OAK_CHECKED));

    public static final Holder<PlacedFeature> RICH_OAK_CHECKED = PlacementUtils.register("rich_oak_checked", RICH_OAK_TREE,
            PlacementUtils.filteredByBlockSurvival(BlockRegistry.RICH_OAK_SAPLING.get()));
    public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> RICH_OAK_SPAWN =
            FeatureUtils.register("rich_oak_spawn", Feature.RANDOM_SELECTOR,
                    new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(RICH_OAK_CHECKED,
                            0.001F)), RICH_OAK_CHECKED));
}
