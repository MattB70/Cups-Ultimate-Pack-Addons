package com.mattborle.cupsaddons.world.feature.tree;

import com.mattborle.cupsaddons.world.feature.ModConfiguredFeatures;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class OreTreeGrower extends AbstractTreeGrower {

    String type;
    public OreTreeGrower(String type) {
        this.type = type;
    }

    @Nullable
    @Override
    protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(Random p_204307_, boolean p_204308_) {
        switch (type) {
            case "gold":
                return ModConfiguredFeatures.GOLDEN_OAK_TREE;
            case "iron":
                return ModConfiguredFeatures.IRON_OAK_TREE;
            case "copper":
                return ModConfiguredFeatures.COPPER_OAK_TREE;
            case "zinc":
                return ModConfiguredFeatures.ZINC_OAK_TREE;
            case "rich":
                return ModConfiguredFeatures.RICH_OAK_TREE;
        }

        // fallback to iron
        return ModConfiguredFeatures.IRON_OAK_TREE;
    }
}
