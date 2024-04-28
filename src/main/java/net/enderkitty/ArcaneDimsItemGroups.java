package net.enderkitty;

import net.enderkitty.block.ArcaneDimsBlocks;
import net.enderkitty.item.ArcaneDimsItems;
import net.enderkitty.item.items.TheElderlandsKeyItem;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ArcaneDimsItemGroups {
    
    public static final ItemGroup ARCANE_DIMENSIONS = Registry.register(Registries.ITEM_GROUP, new Identifier(ArcaneDimensions.MOD_ID, "arcane_dimensions"),
            FabricItemGroup.builder().displayName(Text.translatable("itemGroup.arcane-dims.arcane_dimensions")).icon(() -> new ItemStack(ArcaneDimsItems.ELDRITCH_CORE))
                    .entries((displayContext, entries) -> {
                        
                        entries.add(ArcaneDimsItems.ELDRITCH_CORE);
                        entries.add(ArcaneDimsItems.BLIND_CAGE_BONE);
                        entries.add(ArcaneDimsItems.WARD_CAGE);
                        entries.add(TheElderlandsKeyItem.keyStack());
                        entries.add(TheElderlandsKeyItem.keyStackCharged());
                        entries.add(ArcaneDimsBlocks.ELDRITCH_SMITHING_TABLE);
                        
                        entries.add(ArcaneDimsBlocks.SENIOREM_PLANKS);
                        entries.add(ArcaneDimsBlocks.SENIOREM_LOG);
                        entries.add(ArcaneDimsBlocks.STRIPPED_SENIOREM_LOG);
                        entries.add(ArcaneDimsBlocks.SENIOREM_WOOD);
                        entries.add(ArcaneDimsBlocks.STRIPPED_SENIOREM_WOOD);
                        entries.add(ArcaneDimsBlocks.SENIOREM_LEAVES);
                        entries.add(ArcaneDimsBlocks.SENIOREM_SAPLING);
                        entries.add(ArcaneDimsBlocks.SENIOREM_STAIRS);
                        entries.add(ArcaneDimsBlocks.SENIOREM_SLAB);
                        entries.add(ArcaneDimsBlocks.SENIOREM_FENCE);
                        entries.add(ArcaneDimsBlocks.SENIOREM_FENCE_GATE);
                        entries.add(ArcaneDimsBlocks.SENIOREM_DOOR);
                        entries.add(ArcaneDimsBlocks.SENIOREM_TRAPDOOR);
                        entries.add(ArcaneDimsBlocks.SENIOREM_PRESSURE_PLATE);
                        entries.add(ArcaneDimsBlocks.SENIOREM_BUTTON);
                        entries.add(ArcaneDimsItems.SENIOREM_SIGN);
                        entries.add(ArcaneDimsItems.SENIOREM_HANGING_SIGN);
                        entries.add(ArcaneDimsItems.SENIOREM_BOAT);
                        entries.add(ArcaneDimsItems.SENIOREM_CHEST_BOAT);
                        entries.add(ArcaneDimsItems.SENIOREM_STICK);
                        entries.add(ArcaneDimsItems.SENIOREM_WOODEN_SWORD);
                        entries.add(ArcaneDimsItems.SENIOREM_WOODEN_PICKAXE);
                        entries.add(ArcaneDimsItems.SENIOREM_WOODEN_AXE);
                        entries.add(ArcaneDimsItems.SENIOREM_WOODEN_SHOVEL);
                        entries.add(ArcaneDimsItems.SENIOREM_WOODEN_HOE);
                        
                        entries.add(ArcaneDimsBlocks.ELDERSTONE);
                        entries.add(ArcaneDimsBlocks.COBBLED_ELDERSTONE);
                        entries.add(ArcaneDimsBlocks.ELDER_GRASS_BLOCK);
                        entries.add(ArcaneDimsBlocks.ELDER_DIRT);
                        
                        
                    }).build());
    
    public static void init() {}
}
