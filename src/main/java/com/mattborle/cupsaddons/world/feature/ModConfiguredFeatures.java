package com.mattborle.cupsaddons.world.feature;

import com.mattborle.cupsaddons.init.BlockRegistry;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

public class ModConfiguredFeatures {


    // Ore trees =======================================================================================================
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> GOLDEN_OAK_TREE =
            FeatureUtils.register("golden_oak", Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(BlockRegistry.GOLDEN_OAK_LOG.get()),
                    new StraightTrunkPlacer(3,4,1),
                    BlockStateProvider.simple(BlockRegistry.METALLIC_OAK_LEAVES.get()),
                    new BlobFoliagePlacer(ConstantInt.of(2),ConstantInt.of(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1)
            ).build());
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> IRON_OAK_TREE =
            FeatureUtils.register("iron_oak", Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(BlockRegistry.IRON_OAK_LOG.get()),
                    new StraightTrunkPlacer(3,4,1),
                    BlockStateProvider.simple(BlockRegistry.METALLIC_OAK_LEAVES.get()),
                    new BlobFoliagePlacer(ConstantInt.of(2),ConstantInt.of(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1)
            ).build());
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> COPPER_OAK_TREE =
            FeatureUtils.register("copper_oak", Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(BlockRegistry.COPPER_OAK_LOG.get()),
                    new StraightTrunkPlacer(3,4,1),
                    BlockStateProvider.simple(BlockRegistry.METALLIC_OAK_LEAVES.get()),
                    new BlobFoliagePlacer(ConstantInt.of(2),ConstantInt.of(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1)
            ).build());
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> ZINC_OAK_TREE =
            FeatureUtils.register("zinc_oak", Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(BlockRegistry.ZINC_OAK_LOG.get()),
                    new StraightTrunkPlacer(3,4,1),
                    BlockStateProvider.simple(BlockRegistry.METALLIC_OAK_LEAVES.get()),
                    new BlobFoliagePlacer(ConstantInt.of(2),ConstantInt.of(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1)
            ).build());
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> RICH_OAK_TREE =
            FeatureUtils.register("rich_oak", Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(BlockRegistry.RICH_OAK_LOG.get()),
                    new StraightTrunkPlacer(3,4,1),
                    BlockStateProvider.simple(BlockRegistry.METALLIC_OAK_LEAVES.get()),
                    new BlobFoliagePlacer(ConstantInt.of(2),ConstantInt.of(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1)
            ).build());
}
