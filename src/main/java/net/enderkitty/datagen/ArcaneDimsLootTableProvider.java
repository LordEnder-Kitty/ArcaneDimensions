package net.enderkitty.datagen;

import net.enderkitty.block.ArcaneDimsBlocks;
import net.enderkitty.item.ArcaneDimsItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.loottable.BlockLootTableGenerator;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.TableBonusLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;

public class ArcaneDimsLootTableProvider extends FabricBlockLootTableProvider {
    public ArcaneDimsLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }
    
    @Override
    public void generate() {
        addDrop(ArcaneDimsBlocks.SENIOREM_PLANKS);
        addDrop(ArcaneDimsBlocks.SENIOREM_LOG);
        addDrop(ArcaneDimsBlocks.SENIOREM_WOOD);
        addDrop(ArcaneDimsBlocks.STRIPPED_SENIOREM_LOG);
        addDrop(ArcaneDimsBlocks.STRIPPED_SENIOREM_WOOD);
        addDrop(ArcaneDimsBlocks.SENIOREM_SAPLING);
        addPottedPlantDrops(ArcaneDimsBlocks.POTTED_SENIOREM_SAPLING);
        
        addDrop(ArcaneDimsBlocks.SENIOREM_LEAVES, senioremLeavesDrops(ArcaneDimsBlocks.SENIOREM_LEAVES, 
                ArcaneDimsBlocks.SENIOREM_SAPLING, ArcaneDimsItems.SENIOREM_STICK, BlockLootTableGenerator.SAPLING_DROP_CHANCE));
        
        addDrop(ArcaneDimsBlocks.SENIOREM_STAIRS);
        addDrop(ArcaneDimsBlocks.SENIOREM_SLAB, slabDrops(ArcaneDimsBlocks.SENIOREM_SLAB));
        addDrop(ArcaneDimsBlocks.SENIOREM_FENCE);
        addDrop(ArcaneDimsBlocks.SENIOREM_FENCE_GATE);
        addDrop(ArcaneDimsBlocks.SENIOREM_DOOR, doorDrops(ArcaneDimsBlocks.SENIOREM_DOOR));
        addDrop(ArcaneDimsBlocks.SENIOREM_TRAPDOOR);
        addDrop(ArcaneDimsBlocks.SENIOREM_PRESSURE_PLATE);
        addDrop(ArcaneDimsBlocks.SENIOREM_BUTTON);
        addDrop(ArcaneDimsBlocks.SENIOREM_SIGN);
        addDrop(ArcaneDimsBlocks.SENIOREM_WALL_SIGN);
        addDrop(ArcaneDimsBlocks.SENIOREM_HANGING_SIGN);
        addDrop(ArcaneDimsBlocks.SENIOREM_WALL_HANGING_SIGN);

        addDrop(ArcaneDimsBlocks.ELDERSTONE, drops(ArcaneDimsBlocks.ELDERSTONE, ArcaneDimsBlocks.COBBLED_ELDERSTONE));
        addDrop(ArcaneDimsBlocks.COBBLED_ELDERSTONE);
        addDrop(ArcaneDimsBlocks.ELDER_GRASS_BLOCK, drops(ArcaneDimsBlocks.ELDER_GRASS_BLOCK, ArcaneDimsBlocks.ELDER_DIRT));
        addDrop(ArcaneDimsBlocks.ELDER_DIRT);
    }
    
    public LootTable.Builder senioremLeavesDrops(Block leaves, Block drop, Item stick, float ... saplingChance) {
        return BlockLootTableGenerator.dropsWithSilkTouchOrShears(
                leaves, ((LeafEntry.Builder)this.addSurvivesExplosionCondition(leaves, ItemEntry.builder(drop)))
                        .conditionally(TableBonusLootCondition.builder(Enchantments.FORTUNE, saplingChance)))
                .pool(LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0f))
                        .conditionally(WITHOUT_SILK_TOUCH_NOR_SHEARS)
                        .with((LootPoolEntry.Builder<?>)((LeafEntry.Builder)this.applyExplosionDecay(leaves, ItemEntry.builder(stick)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f)))))
                                .conditionally(TableBonusLootCondition.builder(Enchantments.FORTUNE, LEAVES_STICK_DROP_CHANCE))));
    }
}
