package net.enderkitty.datagen;

import net.enderkitty.block.ArcaneDimsBlocks;
import net.enderkitty.item.ArcaneDimsItems;
import net.enderkitty.util.ArcaneDimsTagRegistries;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.tag.ItemTags;

public class ArcaneDimsRecipeProvider extends FabricRecipeProvider {
    public ArcaneDimsRecipeProvider(FabricDataOutput output) {
        super(output);
    }
    
    @Override
    public void generate(RecipeExporter exporter) {
        offerPlanksRecipe(exporter, ArcaneDimsBlocks.SENIOREM_PLANKS, ArcaneDimsTagRegistries.ItemTags.SENIOREM_LOGS, 4);
        offerBarkBlockRecipe(exporter, ArcaneDimsBlocks.SENIOREM_WOOD, ArcaneDimsBlocks.SENIOREM_LOG);
        offerBarkBlockRecipe(exporter, ArcaneDimsBlocks.STRIPPED_SENIOREM_WOOD, ArcaneDimsBlocks.STRIPPED_SENIOREM_LOG);
        createStairsRecipe(ArcaneDimsBlocks.SENIOREM_STAIRS, Ingredient.ofItems(ArcaneDimsBlocks.SENIOREM_PLANKS))
                .criterion(FabricRecipeProvider.hasItem(ArcaneDimsBlocks.SENIOREM_PLANKS), FabricRecipeProvider.conditionsFromItem(ArcaneDimsBlocks.SENIOREM_PLANKS))
                .offerTo(exporter);
        createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, ArcaneDimsBlocks.SENIOREM_SLAB, Ingredient.ofItems(ArcaneDimsBlocks.SENIOREM_PLANKS))
                .criterion(FabricRecipeProvider.hasItem(ArcaneDimsBlocks.SENIOREM_PLANKS), FabricRecipeProvider.conditionsFromItem(ArcaneDimsBlocks.SENIOREM_PLANKS))
                .offerTo(exporter);
        createFenceRecipe(ArcaneDimsBlocks.SENIOREM_FENCE, Ingredient.ofItems(ArcaneDimsBlocks.SENIOREM_PLANKS))
                .criterion(FabricRecipeProvider.hasItem(ArcaneDimsBlocks.SENIOREM_PLANKS), FabricRecipeProvider.conditionsFromItem(ArcaneDimsBlocks.SENIOREM_PLANKS))
                .offerTo(exporter);
        createFenceGateRecipe(ArcaneDimsBlocks.SENIOREM_FENCE_GATE, Ingredient.ofItems(ArcaneDimsBlocks.SENIOREM_PLANKS))
                .criterion(FabricRecipeProvider.hasItem(ArcaneDimsBlocks.SENIOREM_PLANKS), FabricRecipeProvider.conditionsFromItem(ArcaneDimsBlocks.SENIOREM_PLANKS))
                .offerTo(exporter);
        createDoorRecipe(ArcaneDimsBlocks.SENIOREM_DOOR, Ingredient.ofItems(ArcaneDimsBlocks.SENIOREM_PLANKS))
                .criterion(FabricRecipeProvider.hasItem(ArcaneDimsBlocks.SENIOREM_PLANKS), FabricRecipeProvider.conditionsFromItem(ArcaneDimsBlocks.SENIOREM_PLANKS))
                .offerTo(exporter);
        createTrapdoorRecipe(ArcaneDimsBlocks.SENIOREM_TRAPDOOR, Ingredient.ofItems(ArcaneDimsBlocks.SENIOREM_PLANKS))
                .criterion(FabricRecipeProvider.hasItem(ArcaneDimsBlocks.SENIOREM_PLANKS), FabricRecipeProvider.conditionsFromItem(ArcaneDimsBlocks.SENIOREM_PLANKS))
                .offerTo(exporter);
        offerPressurePlateRecipe(exporter, ArcaneDimsBlocks.SENIOREM_PRESSURE_PLATE, ArcaneDimsBlocks.SENIOREM_PLANKS);
        offerSingleOutputShapelessRecipe(exporter, ArcaneDimsBlocks.SENIOREM_BUTTON, ArcaneDimsBlocks.SENIOREM_PLANKS, "wooden_button");
        
        //Eldritch Core
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ArcaneDimsItems.ELDRITCH_CORE)
                .pattern(" L ")
                .pattern("EPE")
                .pattern(" L ")
                .input('L', Blocks.BIRCH_LOG).input('E', Items.ECHO_SHARD).input('P', ItemTags.DECORATED_POT_SHERDS)
                .criterion(FabricRecipeProvider.hasItem(Items.ECHO_SHARD), FabricRecipeProvider.conditionsFromItem(Items.ECHO_SHARD))
                .offerTo(exporter);
        //Elderlands Portal Key
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ArcaneDimsItems.ELDERLANDS_PORTAL_KEY)
                .pattern(" C ")
                .pattern("E#E")
                .pattern(" B ")
                .input('C', Items.COPPER_BLOCK).input('E', Items.EMERALD)
                .input('#', ArcaneDimsItems.ELDRITCH_CORE).input('B', ArcaneDimsItems.BLIND_CAGE_BONE)
                .criterion(FabricRecipeProvider.hasItem(ArcaneDimsItems.ELDRITCH_CORE), FabricRecipeProvider.conditionsFromItem(ArcaneDimsItems.ELDRITCH_CORE))
                .offerTo(exporter);
        //Ward Cage
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ArcaneDimsBlocks.WARD_CAGE)
                .pattern("BSB")
                .pattern("BEB")
                .pattern("B#B")
                .input('B', ArcaneDimsItems.BLIND_CAGE_BONE).input('S', Items.STRING).input('E', Items.ECHO_SHARD).input('#', Blocks.SOUL_SOIL)
                .criterion(FabricRecipeProvider.hasItem(ArcaneDimsItems.BLIND_CAGE_BONE), FabricRecipeProvider.conditionsFromItem(ArcaneDimsItems.BLIND_CAGE_BONE))
                .offerTo(exporter);
        //Seniorem Wood Tools
        offerSwordRecipe(exporter, ArcaneDimsItems.SENIOREM_WOODEN_SWORD, ArcaneDimsBlocks.SENIOREM_PLANKS, ArcaneDimsItems.SENIOREM_STICK);
        offerPickaxeRecipe(exporter, ArcaneDimsItems.SENIOREM_WOODEN_PICKAXE, ArcaneDimsBlocks.SENIOREM_PLANKS, ArcaneDimsItems.SENIOREM_STICK);
        offerAxeRecipe(exporter, ArcaneDimsItems.SENIOREM_WOODEN_AXE, ArcaneDimsBlocks.SENIOREM_PLANKS, ArcaneDimsItems.SENIOREM_STICK);
        offerShovelRecipe(exporter, ArcaneDimsItems.SENIOREM_WOODEN_SHOVEL, ArcaneDimsBlocks.SENIOREM_PLANKS, ArcaneDimsItems.SENIOREM_STICK);
        offerHoeRecipe(exporter, ArcaneDimsItems.SENIOREM_WOODEN_HOE, ArcaneDimsBlocks.SENIOREM_PLANKS, ArcaneDimsItems.SENIOREM_STICK);
        //Seniorem Stick
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ArcaneDimsItems.SENIOREM_STICK, 4)
                .pattern("#")
                .pattern("#")
                .input('#', ArcaneDimsBlocks.SENIOREM_PLANKS)
                .criterion(FabricRecipeProvider.hasItem(ArcaneDimsBlocks.SENIOREM_PLANKS), FabricRecipeProvider.conditionsFromItem(ArcaneDimsBlocks.SENIOREM_PLANKS))
                .offerTo(exporter);
        //Boats
        offerBoatRecipe(exporter, ArcaneDimsItems.SENIOREM_BOAT, ArcaneDimsBlocks.SENIOREM_PLANKS);
        offerChestBoatRecipe(exporter, ArcaneDimsItems.SENIOREM_CHEST_BOAT, ArcaneDimsItems.SENIOREM_BOAT);
    }
    
    
    public static void offerSwordRecipe(RecipeExporter exporter, ItemConvertible output, ItemConvertible input, ItemConvertible handle) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, output).pattern("#").pattern("#").pattern("H")
                .input('#', input).input('H', handle).criterion(FabricRecipeProvider.hasItem(input), FabricRecipeProvider.conditionsFromItem(input))
                .offerTo(exporter);
    }
    public static void offerPickaxeRecipe(RecipeExporter exporter, ItemConvertible output, ItemConvertible input, ItemConvertible handle) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, output).pattern("###").pattern(" H ").pattern(" H ")
                .input('#', input).input('H', handle).criterion(FabricRecipeProvider.hasItem(input), FabricRecipeProvider.conditionsFromItem(input))
                .offerTo(exporter);
    }
    public static void offerAxeRecipe(RecipeExporter exporter, ItemConvertible output, ItemConvertible input, ItemConvertible handle) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, output).pattern("##").pattern("H#").pattern("H ")
                .input('#', input).input('H', handle).criterion(FabricRecipeProvider.hasItem(input), FabricRecipeProvider.conditionsFromItem(input))
                .offerTo(exporter);
    }
    public static void offerShovelRecipe(RecipeExporter exporter, ItemConvertible output, ItemConvertible input, ItemConvertible handle) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, output).pattern("#").pattern("H").pattern("H")
                .input('#', input).input('H', handle).criterion(FabricRecipeProvider.hasItem(input), FabricRecipeProvider.conditionsFromItem(input))
                .offerTo(exporter);
    }
    public static void offerHoeRecipe(RecipeExporter exporter, ItemConvertible output, ItemConvertible input, ItemConvertible handle) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, output).pattern("##").pattern("H ").pattern("H ")
                .input('#', input).input('H', handle).criterion(FabricRecipeProvider.hasItem(input), FabricRecipeProvider.conditionsFromItem(input))
                .offerTo(exporter);
    }
}
