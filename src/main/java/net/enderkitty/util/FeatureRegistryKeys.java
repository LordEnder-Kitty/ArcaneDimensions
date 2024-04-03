package net.enderkitty.util;

import net.enderkitty.ArcaneDimensions;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;

public class FeatureRegistryKeys {
    public static final RegistryKey<ConfiguredFeature<?, ?>> SENIOREM_KEY = FeatureRegistryKeys.of("seniorem");
    public static final RegistryKey<ConfiguredFeature<?, ?>> LARGE_SENIOREM_KEY = FeatureRegistryKeys.of("large_seniorem");

    public static RegistryKey<ConfiguredFeature<?, ?>> of(String id) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(ArcaneDimensions.MOD_ID, id));
    }
}
