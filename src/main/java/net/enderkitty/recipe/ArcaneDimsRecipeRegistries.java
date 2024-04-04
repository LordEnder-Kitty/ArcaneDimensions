package net.enderkitty.recipe;

import net.enderkitty.ArcaneDimensions;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ArcaneDimsRecipeRegistries {
    public static void register() {
        Registry.register(Registries.RECIPE_SERIALIZER, EldritchSmithingRecipe.Serializer.ID, EldritchSmithingRecipe.Serializer.INSTANCE);
        Registry.register(Registries.RECIPE_TYPE, new Identifier(ArcaneDimensions.MOD_ID, EldritchSmithingRecipe.Type.ID), EldritchSmithingRecipe.Type.INSTANCE);
    }
}
