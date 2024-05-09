package com.mattborle.cupsaddons.world.gen;

import com.mattborle.cupsaddons.world.feature.ModPlacedFeatures;
import net.minecraft.core.Holder;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.List;

public class ModOreGeneration {
    public static void generateOres(final BiomeLoadingEvent event) {
        List<Holder<PlacedFeature>> base =
                event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES);

        base.add(ModPlacedFeatures.COMPACTED_ALUMINUM_ORE_PLACED);
        base.add(ModPlacedFeatures.COMPACTED_COAL_ORE_PLACED);
        base.add(ModPlacedFeatures.COMPACTED_COPPER_ORE_PLACED);
        base.add(ModPlacedFeatures.COMPACTED_GOLD_ORE_PLACED);
        base.add(ModPlacedFeatures.COMPACTED_IRON_ORE_PLACED);
        base.add(ModPlacedFeatures.COMPACTED_TIN_ORE_PLACED);
        base.add(ModPlacedFeatures.COMPACTED_ZINC_ORE_PLACED);
    }
}
