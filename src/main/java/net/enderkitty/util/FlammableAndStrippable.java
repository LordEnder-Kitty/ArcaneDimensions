package net.enderkitty.util;

import net.enderkitty.block.ArcaneDimsBlocks;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;

public class FlammableAndStrippable {

    public static void registerFlammable() {
        FlammableBlockRegistry.getDefaultInstance().add(ArcaneDimsBlocks.SENIOREM_PLANKS, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(ArcaneDimsBlocks.SENIOREM_LOG, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(ArcaneDimsBlocks.SENIOREM_WOOD, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(ArcaneDimsBlocks.STRIPPED_SENIOREM_LOG, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(ArcaneDimsBlocks.STRIPPED_SENIOREM_WOOD, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(ArcaneDimsBlocks.SENIOREM_LEAVES, 30, 60);
    }
    public static void registerStrippable() {
        StrippableBlockRegistry.register(ArcaneDimsBlocks.SENIOREM_LOG, ArcaneDimsBlocks.STRIPPED_SENIOREM_LOG);
        StrippableBlockRegistry.register(ArcaneDimsBlocks.SENIOREM_WOOD, ArcaneDimsBlocks.STRIPPED_SENIOREM_WOOD);
    }
}
