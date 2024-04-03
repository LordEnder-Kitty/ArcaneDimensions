package net.enderkitty.world.dimension;

import net.enderkitty.ArcaneDimensions;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;

public class ArcaneDimsDimensionTypes {
    public static final RegistryKey<DimensionType> THE_ELDERLANDS = RegistryKey.of(RegistryKeys.DIMENSION_TYPE, new Identifier(ArcaneDimensions.MOD_ID, "the_elderlands"));
    public static final RegistryKey<World> THE_ELDERLANDS_WORLD = RegistryKey.of(RegistryKeys.WORLD, new Identifier(ArcaneDimensions.MOD_ID, "the_elderlands"));
    public static final Identifier THE_ELDERLANDS_ID = new Identifier(ArcaneDimensions.MOD_ID, "the_elderlands");
}
