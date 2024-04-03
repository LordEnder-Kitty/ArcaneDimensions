package net.enderkitty.recipe;

import net.enderkitty.ArcaneDimensions;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ArcaneDimsRecipeRegistries {
    public static final RecipeSerializer<EldritchSmithingRecipe> ELDRITCH_SMITHING_SERIALIZER = Registry.register(Registries.RECIPE_SERIALIZER, 
            new Identifier(ArcaneDimensions.MOD_ID, "eldritch_smithing"), new EldritchSmithingRecipe.Serializer());
    
    public static final RecipeType<EldritchSmithingRecipe> ELDRITCH_SMITHING_TYPE = Registry.register(Registries.RECIPE_TYPE,
            new Identifier(ArcaneDimensions.MOD_ID, "eldritch_smithing"), new RecipeType<EldritchSmithingRecipe>() {
                @Override public String toString() { return "eldritch_smithing"; }
            });
    
    
    public static void register() {}
}
