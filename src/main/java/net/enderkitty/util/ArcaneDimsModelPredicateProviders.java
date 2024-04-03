package net.enderkitty.util;

import net.enderkitty.item.ArcaneDimsItems;
import net.enderkitty.item.items.WardCageItem;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.util.Identifier;

public class ArcaneDimsModelPredicateProviders {
    public static void registerPredicates() {
        ModelPredicateProviderRegistry.register(ArcaneDimsItems.WARD_CAGE, new Identifier("has_souls"), 
                (stack, world, entity, seed) -> WardCageItem.hasSouls(stack) ? 1.0f : 0.0f);
    }
}
