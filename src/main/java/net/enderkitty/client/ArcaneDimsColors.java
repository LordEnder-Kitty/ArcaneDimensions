package net.enderkitty.client;

import net.enderkitty.block.ArcaneDimsBlocks;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.FoliageColors;
import net.minecraft.client.color.world.GrassColors;

public class ArcaneDimsColors {

    public static void registerBlockColors() {
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
            if (world == null || pos == null) return FoliageColors.getDefaultColor();
            return BiomeColors.getFoliageColor(world, pos);
        }, ArcaneDimsBlocks.SENIOREM_LEAVES);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
            if (world == null || pos == null) return GrassColors.getDefaultColor();
            return BiomeColors.getGrassColor(world, pos);
        }, ArcaneDimsBlocks.ELDER_GRASS_BLOCK, ArcaneDimsBlocks.ELDER_GRASS, ArcaneDimsBlocks.ELDER_FLY_SPORE_GRASS);
    }
    public static void registerItemColors() {
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> FoliageColors.getDefaultColor(), ArcaneDimsBlocks.SENIOREM_LEAVES.asItem());
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> GrassColors.getDefaultColor(), 
                ArcaneDimsBlocks.ELDER_GRASS_BLOCK, ArcaneDimsBlocks.ELDER_GRASS, ArcaneDimsBlocks.ELDER_FLY_SPORE_GRASS);
    }
}
