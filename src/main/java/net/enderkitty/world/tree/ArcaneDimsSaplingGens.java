package net.enderkitty.world.tree;

import net.enderkitty.util.FeatureRegistryKeys;
import net.minecraft.block.SaplingGenerator;
import java.util.Optional;

public class ArcaneDimsSaplingGens {

    public static final SaplingGenerator SENIOREM = new SaplingGenerator("seniorem", 0f,
            Optional.of(FeatureRegistryKeys.LARGE_SENIOREM_KEY), Optional.empty(), Optional.of(FeatureRegistryKeys.SENIOREM_KEY),
            Optional.empty(), Optional.empty(), Optional.empty());


}
