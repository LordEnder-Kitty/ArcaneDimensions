package net.enderkitty.util;

import net.enderkitty.ArcaneDimensions;
import net.enderkitty.block.ArcaneDimsBlocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.size.ThreeLayersFeatureSize;
import net.minecraft.world.gen.foliage.CherryFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.DarkOakTrunkPlacer;

import java.util.OptionalInt;

public class ArcaneDimsConfiguredFeatures {

    //public static final RegistryKey<ConfiguredFeature<?, ?>> SENIOREM_KEY = registerKey("seniorem");



    public static void boostrap(Registerable<ConfiguredFeature<?, ?>> context) {

/*        register(context, SENIOREM_KEY, Feature.TREE, new TreeFeatureConfig.Builder(
                BlockStateProvider.of(ArcaneDimsBlocks.SENIOREM_LOG),
                new DarkOakTrunkPlacer(8, 6, 3),

                BlockStateProvider.of(ArcaneDimsBlocks.SENIOREM_LEAVES),
                new CherryFoliagePlacer(ConstantIntProvider.create(4), ConstantIntProvider.create(0), ConstantIntProvider.create(5), 0.25f, 0.25f, 0.16666667f, 0.33333334f),

                new ThreeLayersFeatureSize(1, 1, 0, 1, 2, OptionalInt.empty())).build());*/
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(ArcaneDimensions.MOD_ID, name));
    }
    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(
            Registerable<ConfiguredFeature<?, ?>> context, RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
