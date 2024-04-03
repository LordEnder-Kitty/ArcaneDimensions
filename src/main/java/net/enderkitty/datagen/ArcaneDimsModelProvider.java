package net.enderkitty.datagen;

import net.enderkitty.block.ArcaneDimsBlocks;
import net.enderkitty.item.ArcaneDimsItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.data.client.TexturedModel;

public class ArcaneDimsModelProvider extends FabricModelProvider {
    public ArcaneDimsModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerLog(ArcaneDimsBlocks.SENIOREM_LOG).log(ArcaneDimsBlocks.SENIOREM_LOG).wood(ArcaneDimsBlocks.SENIOREM_WOOD);
        blockStateModelGenerator.registerLog(ArcaneDimsBlocks.STRIPPED_SENIOREM_LOG).log(ArcaneDimsBlocks.STRIPPED_SENIOREM_LOG).wood(ArcaneDimsBlocks.STRIPPED_SENIOREM_WOOD);
        blockStateModelGenerator.registerFlowerPotPlant(ArcaneDimsBlocks.SENIOREM_SAPLING, ArcaneDimsBlocks.POTTED_SENIOREM_SAPLING, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerSingleton(ArcaneDimsBlocks.SENIOREM_LEAVES, TexturedModel.LEAVES);

        BlockStateModelGenerator.BlockTexturePool senioremPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ArcaneDimsBlocks.SENIOREM_PLANKS);
        senioremPool.family(ArcaneDimsBlocks.SENIOREM_FAMILY);
        senioremPool.stairs(ArcaneDimsBlocks.SENIOREM_STAIRS);
        senioremPool.slab(ArcaneDimsBlocks.SENIOREM_SLAB);
        senioremPool.fence(ArcaneDimsBlocks.SENIOREM_FENCE);
        senioremPool.fenceGate(ArcaneDimsBlocks.SENIOREM_FENCE_GATE);
        senioremPool.pressurePlate(ArcaneDimsBlocks.SENIOREM_PRESSURE_PLATE);
        senioremPool.button(ArcaneDimsBlocks.SENIOREM_BUTTON);

        blockStateModelGenerator.registerDoor(ArcaneDimsBlocks.SENIOREM_DOOR);
        blockStateModelGenerator.registerTrapdoor(ArcaneDimsBlocks.SENIOREM_TRAPDOOR);

        blockStateModelGenerator.registerSimpleCubeAll(ArcaneDimsBlocks.ELDERSTONE);
        blockStateModelGenerator.registerSimpleCubeAll(ArcaneDimsBlocks.COBBLED_ELDERSTONE);
        blockStateModelGenerator.registerSimpleCubeAll(ArcaneDimsBlocks.ELDER_DIRT);
        
        blockStateModelGenerator.registerTintableCross(ArcaneDimsBlocks.ELDER_GRASS, BlockStateModelGenerator.TintType.TINTED);
    }
    
    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ArcaneDimsItems.SENIOREM_STICK, Models.GENERATED);
        itemModelGenerator.register(ArcaneDimsItems.ELDRITCH_CORE, Models.GENERATED);
        itemModelGenerator.register(ArcaneDimsItems.BLIND_CAGE_BONE, Models.GENERATED);
        itemModelGenerator.register(ArcaneDimsItems.ELDERLANDS_PORTAL_KEY, Models.GENERATED);
        
        itemModelGenerator.register(ArcaneDimsItems.SENIOREM_HANGING_SIGN, Models.GENERATED);
        
        itemModelGenerator.register(ArcaneDimsItems.SENIOREM_BOAT, Models.GENERATED);
        itemModelGenerator.register(ArcaneDimsItems.SENIOREM_CHEST_BOAT, Models.GENERATED);
        
        itemModelGenerator.register(ArcaneDimsItems.SENIOREM_WOODEN_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ArcaneDimsItems.SENIOREM_WOODEN_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ArcaneDimsItems.SENIOREM_WOODEN_AXE, Models.HANDHELD);
        itemModelGenerator.register(ArcaneDimsItems.SENIOREM_WOODEN_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ArcaneDimsItems.SENIOREM_WOODEN_HOE, Models.HANDHELD);
    }
}
