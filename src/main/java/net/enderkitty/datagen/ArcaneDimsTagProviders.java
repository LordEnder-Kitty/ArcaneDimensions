package net.enderkitty.datagen;

import net.enderkitty.block.ArcaneDimsBlocks;
import net.enderkitty.item.ArcaneDimsItems;
import net.enderkitty.util.ArcaneDimsTagRegistries;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ArcaneDimsTagProviders {

    public static class ArcaneDimsItemTags extends FabricTagProvider.ItemTagProvider {
        public ArcaneDimsItemTags(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
            super(output, completableFuture);
        }

        @Override
        protected void configure(RegistryWrapper.WrapperLookup arg) {
            getOrCreateTagBuilder(ArcaneDimsTagRegistries.ItemTags.SENIOREM_LOGS)
                    .add(ArcaneDimsBlocks.SENIOREM_LOG.asItem())
                    .add(ArcaneDimsBlocks.SENIOREM_WOOD.asItem())
                    .add(ArcaneDimsBlocks.STRIPPED_SENIOREM_LOG.asItem())
                    .add(ArcaneDimsBlocks.STRIPPED_SENIOREM_WOOD.asItem());
            getOrCreateTagBuilder(ArcaneDimsTagRegistries.ItemTags.STICKS)
                    .add(Items.STICK)
                    .add(ArcaneDimsItems.SENIOREM_STICK);
            getOrCreateTagBuilder(ArcaneDimsTagRegistries.ItemTags.ELDER_TOOLS)
                    .add(ArcaneDimsItems.SENIOREM_WOODEN_SWORD)
                    .add(ArcaneDimsItems.SENIOREM_WOODEN_PICKAXE)
                    .add(ArcaneDimsItems.SENIOREM_WOODEN_AXE)
                    .add(ArcaneDimsItems.SENIOREM_WOODEN_SHOVEL)
                    .add(ArcaneDimsItems.SENIOREM_WOODEN_HOE);
            
            getOrCreateTagBuilder(ItemTags.PLANKS).add(ArcaneDimsBlocks.SENIOREM_PLANKS.asItem());
            getOrCreateTagBuilder(ItemTags.LOGS_THAT_BURN).addTag(ArcaneDimsTagRegistries.ItemTags.SENIOREM_LOGS);
            getOrCreateTagBuilder(ItemTags.LEAVES).add(ArcaneDimsBlocks.SENIOREM_LEAVES.asItem());
            getOrCreateTagBuilder(ItemTags.SAPLINGS).add(ArcaneDimsBlocks.SENIOREM_SAPLING.asItem());
            getOrCreateTagBuilder(ItemTags.WOODEN_STAIRS).add(ArcaneDimsBlocks.SENIOREM_STAIRS.asItem());
            getOrCreateTagBuilder(ItemTags.WOODEN_SLABS).add(ArcaneDimsBlocks.SENIOREM_SLAB.asItem());
            getOrCreateTagBuilder(ItemTags.WOODEN_FENCES).add(ArcaneDimsBlocks.SENIOREM_FENCE.asItem());
            getOrCreateTagBuilder(ItemTags.FENCE_GATES).add(ArcaneDimsBlocks.SENIOREM_FENCE_GATE.asItem());
            getOrCreateTagBuilder(ItemTags.WOODEN_DOORS).add(ArcaneDimsBlocks.SENIOREM_DOOR.asItem());
            getOrCreateTagBuilder(ItemTags.WOODEN_TRAPDOORS).add(ArcaneDimsBlocks.SENIOREM_TRAPDOOR.asItem());
            getOrCreateTagBuilder(ItemTags.WOODEN_PRESSURE_PLATES).add(ArcaneDimsBlocks.SENIOREM_PRESSURE_PLATE.asItem());
            getOrCreateTagBuilder(ItemTags.WOODEN_BUTTONS).add(ArcaneDimsBlocks.SENIOREM_BUTTON.asItem());
            
            getOrCreateTagBuilder(ItemTags.SIGNS).add(ArcaneDimsItems.SENIOREM_SIGN);
            getOrCreateTagBuilder(ItemTags.HANGING_SIGNS).add(ArcaneDimsItems.SENIOREM_HANGING_SIGN);
            
            getOrCreateTagBuilder(ItemTags.BOATS).add(ArcaneDimsItems.SENIOREM_BOAT);
            getOrCreateTagBuilder(ItemTags.CHEST_BOATS).add(ArcaneDimsItems.SENIOREM_CHEST_BOAT);
        }
    }

    public static class ArcaneDimsBlockTags extends FabricTagProvider.BlockTagProvider {
        public ArcaneDimsBlockTags(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
            super(output, registriesFuture);
        }

        @Override
        protected void configure(RegistryWrapper.WrapperLookup arg) {
            getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
                    .add(ArcaneDimsBlocks.SENIOREM_PLANKS)
                    .add(ArcaneDimsBlocks.SENIOREM_LOG)
                    .add(ArcaneDimsBlocks.SENIOREM_WOOD)
                    .add(ArcaneDimsBlocks.STRIPPED_SENIOREM_LOG)
                    .add(ArcaneDimsBlocks.STRIPPED_SENIOREM_WOOD)
                    .add(ArcaneDimsBlocks.SENIOREM_STAIRS)
                    .add(ArcaneDimsBlocks.SENIOREM_SLAB)
                    .add(ArcaneDimsBlocks.SENIOREM_FENCE)
                    .add(ArcaneDimsBlocks.SENIOREM_FENCE_GATE)
                    .add(ArcaneDimsBlocks.SENIOREM_DOOR)
                    .add(ArcaneDimsBlocks.SENIOREM_TRAPDOOR)
                    .add(ArcaneDimsBlocks.SENIOREM_PRESSURE_PLATE)
                    .add(ArcaneDimsBlocks.SENIOREM_BUTTON)
                    .add(ArcaneDimsBlocks.SENIOREM_SIGN)
                    .add(ArcaneDimsBlocks.SENIOREM_WALL_SIGN)
                    .add(ArcaneDimsBlocks.SENIOREM_HANGING_SIGN)
                    .add(ArcaneDimsBlocks.SENIOREM_WALL_HANGING_SIGN)
            ;
            getOrCreateTagBuilder(BlockTags.HOE_MINEABLE)
                    .add(ArcaneDimsBlocks.SENIOREM_LEAVES);
            getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE)
                    .add(ArcaneDimsBlocks.ELDER_GRASS_BLOCK)
                    .add(ArcaneDimsBlocks.ELDER_DIRT);
            getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                    .add(ArcaneDimsBlocks.ELDERSTONE)
                    .add(ArcaneDimsBlocks.COBBLED_ELDERSTONE);
            
            getOrCreateTagBuilder(ArcaneDimsTagRegistries.BlockTags.SENIOREM_LOGS)
                    .add(ArcaneDimsBlocks.SENIOREM_LOG)
                    .add(ArcaneDimsBlocks.SENIOREM_WOOD)
                    .add(ArcaneDimsBlocks.STRIPPED_SENIOREM_LOG)
                    .add(ArcaneDimsBlocks.STRIPPED_SENIOREM_WOOD);
            
            getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN).addTag(ArcaneDimsTagRegistries.BlockTags.SENIOREM_LOGS);
            getOrCreateTagBuilder(BlockTags.LEAVES).add(ArcaneDimsBlocks.SENIOREM_LEAVES);
            getOrCreateTagBuilder(BlockTags.PLANKS).add(ArcaneDimsBlocks.SENIOREM_PLANKS);
            getOrCreateTagBuilder(BlockTags.SAPLINGS).add(ArcaneDimsBlocks.SENIOREM_SAPLING);
            getOrCreateTagBuilder(BlockTags.WOODEN_STAIRS).add(ArcaneDimsBlocks.SENIOREM_STAIRS);
            getOrCreateTagBuilder(BlockTags.WOODEN_SLABS).add(ArcaneDimsBlocks.SENIOREM_SLAB);
            getOrCreateTagBuilder(BlockTags.WOODEN_FENCES).add(ArcaneDimsBlocks.SENIOREM_FENCE);
            getOrCreateTagBuilder(BlockTags.FENCE_GATES).add(ArcaneDimsBlocks.SENIOREM_FENCE_GATE);
            getOrCreateTagBuilder(BlockTags.WOODEN_DOORS).add(ArcaneDimsBlocks.SENIOREM_DOOR);
            getOrCreateTagBuilder(BlockTags.WOODEN_TRAPDOORS).add(ArcaneDimsBlocks.SENIOREM_TRAPDOOR);
            getOrCreateTagBuilder(BlockTags.WOODEN_PRESSURE_PLATES).add(ArcaneDimsBlocks.SENIOREM_PRESSURE_PLATE);
            getOrCreateTagBuilder(BlockTags.WOODEN_BUTTONS).add(ArcaneDimsBlocks.SENIOREM_BUTTON);
            
            getOrCreateTagBuilder(BlockTags.STANDING_SIGNS).add(ArcaneDimsBlocks.SENIOREM_SIGN);
            getOrCreateTagBuilder(BlockTags.WALL_SIGNS).add(ArcaneDimsBlocks.SENIOREM_WALL_SIGN);
            getOrCreateTagBuilder(BlockTags.CEILING_HANGING_SIGNS).add(ArcaneDimsBlocks.SENIOREM_HANGING_SIGN);
            getOrCreateTagBuilder(BlockTags.WALL_HANGING_SIGNS).add(ArcaneDimsBlocks.SENIOREM_WALL_HANGING_SIGN);
            
            getOrCreateTagBuilder(BlockTags.VALID_SPAWN).add(ArcaneDimsBlocks.ELDER_GRASS_BLOCK);
            getOrCreateTagBuilder(BlockTags.SCULK_REPLACEABLE)
                    .add(ArcaneDimsBlocks.ELDER_GRASS_BLOCK)
                    .add(ArcaneDimsBlocks.ELDER_DIRT)
                    .add(ArcaneDimsBlocks.ELDERSTONE);
            getOrCreateTagBuilder(BlockTags.GOATS_SPAWNABLE_ON)
                    .add(ArcaneDimsBlocks.ELDER_GRASS_BLOCK)
                    .add(ArcaneDimsBlocks.ELDERSTONE);
            getOrCreateTagBuilder(BlockTags.FROGS_SPAWNABLE_ON).add(ArcaneDimsBlocks.ELDER_GRASS_BLOCK);
            getOrCreateTagBuilder(BlockTags.BAMBOO_PLANTABLE_ON)
                    .add(ArcaneDimsBlocks.ELDER_GRASS_BLOCK)
                    .add(ArcaneDimsBlocks.ELDER_DIRT);
            getOrCreateTagBuilder(BlockTags.AZALEA_GROWS_ON)
                    .add(ArcaneDimsBlocks.ELDER_GRASS_BLOCK)
                    .add(ArcaneDimsBlocks.ELDER_DIRT);
            getOrCreateTagBuilder(BlockTags.LUSH_GROUND_REPLACEABLE)
                    .add(ArcaneDimsBlocks.ELDER_GRASS_BLOCK)
                    .add(ArcaneDimsBlocks.ELDER_DIRT)
                    .add(ArcaneDimsBlocks.ELDERSTONE);
            getOrCreateTagBuilder(BlockTags.NETHER_CARVER_REPLACEABLES)
                    .add(ArcaneDimsBlocks.ELDER_GRASS_BLOCK)
                    .add(ArcaneDimsBlocks.ELDER_DIRT)
                    .add(ArcaneDimsBlocks.ELDERSTONE);
            getOrCreateTagBuilder(BlockTags.SCULK_REPLACEABLE_WORLD_GEN)
                    .add(ArcaneDimsBlocks.ELDER_GRASS_BLOCK)
                    .add(ArcaneDimsBlocks.ELDER_DIRT)
                    .add(ArcaneDimsBlocks.ELDERSTONE);
            getOrCreateTagBuilder(BlockTags.FOXES_SPAWNABLE_ON).add(ArcaneDimsBlocks.ELDER_GRASS_BLOCK);
            getOrCreateTagBuilder(BlockTags.ENDERMAN_HOLDABLE)
                    .add(ArcaneDimsBlocks.ELDER_GRASS_BLOCK)
                    .add(ArcaneDimsBlocks.ELDER_DIRT);
            getOrCreateTagBuilder(BlockTags.AZALEA_ROOT_REPLACEABLE)
                    .add(ArcaneDimsBlocks.ELDER_GRASS_BLOCK)
                    .add(ArcaneDimsBlocks.ELDER_DIRT)
                    .add(ArcaneDimsBlocks.ELDERSTONE);
            getOrCreateTagBuilder(BlockTags.ANIMALS_SPAWNABLE_ON).add(ArcaneDimsBlocks.ELDER_GRASS_BLOCK);
            getOrCreateTagBuilder(BlockTags.DEAD_BUSH_MAY_PLACE_ON)
                    .add(ArcaneDimsBlocks.ELDER_GRASS_BLOCK)
                    .add(ArcaneDimsBlocks.ELDER_DIRT);
            getOrCreateTagBuilder(BlockTags.DIRT)
                    .add(ArcaneDimsBlocks.ELDER_GRASS_BLOCK)
                    .add(ArcaneDimsBlocks.ELDER_DIRT);
            getOrCreateTagBuilder(BlockTags.SNIFFER_DIGGABLE_BLOCK)
                    .add(ArcaneDimsBlocks.ELDER_GRASS_BLOCK)
                    .add(ArcaneDimsBlocks.ELDER_DIRT);
            getOrCreateTagBuilder(BlockTags.RABBITS_SPAWNABLE_ON).add(ArcaneDimsBlocks.ELDER_GRASS_BLOCK);
            getOrCreateTagBuilder(BlockTags.WOLVES_SPAWNABLE_ON).add(ArcaneDimsBlocks.ELDER_GRASS_BLOCK);
            getOrCreateTagBuilder(BlockTags.PARROTS_SPAWNABLE_ON).add(ArcaneDimsBlocks.ELDER_GRASS_BLOCK);
            getOrCreateTagBuilder(BlockTags.OVERWORLD_CARVER_REPLACEABLES)
                    .add(ArcaneDimsBlocks.ELDER_GRASS_BLOCK)
                    .add(ArcaneDimsBlocks.ELDER_DIRT)
                    .add(ArcaneDimsBlocks.ELDERSTONE);
            getOrCreateTagBuilder(BlockTags.BIG_DRIPLEAF_PLACEABLE)
                    .add(ArcaneDimsBlocks.ELDER_GRASS_BLOCK)
                    .add(ArcaneDimsBlocks.ELDER_DIRT);
            getOrCreateTagBuilder(BlockTags.MOSS_REPLACEABLE)
                    .add(ArcaneDimsBlocks.ELDER_GRASS_BLOCK)
                    .add(ArcaneDimsBlocks.ELDER_DIRT)
                    .add(ArcaneDimsBlocks.ELDERSTONE);
            getOrCreateTagBuilder(BlockTags.CONVERTABLE_TO_MUD).add(ArcaneDimsBlocks.ELDER_DIRT);
            
            getOrCreateTagBuilder(BlockTags.STONE_ORE_REPLACEABLES).add(ArcaneDimsBlocks.ELDERSTONE);
            getOrCreateTagBuilder(BlockTags.SNAPS_GOAT_HORN).add(ArcaneDimsBlocks.ELDERSTONE);
            getOrCreateTagBuilder(BlockTags.DRIPSTONE_REPLACEABLE_BLOCKS).add(ArcaneDimsBlocks.ELDERSTONE);
        }
    }
}
