package net.enderkitty.block;

import com.terraformersmc.terraform.sign.block.TerraformHangingSignBlock;
import com.terraformersmc.terraform.sign.block.TerraformSignBlock;
import com.terraformersmc.terraform.sign.block.TerraformWallHangingSignBlock;
import com.terraformersmc.terraform.sign.block.TerraformWallSignBlock;
import net.enderkitty.ArcaneDimensions;
import net.enderkitty.block.blocks.*;
import net.enderkitty.util.MiscIdentifiers;
import net.enderkitty.world.tree.ArcaneDimsSaplingGens;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.enums.Instrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.data.family.BlockFamilies;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ArcaneDimsBlocks {
    
    public static final Block WARD_CAGE = register("ward_cage", false, new WardCageBlock(FabricBlockSettings.create()
            .strength(3, 5).nonOpaque().pistonBehavior(PistonBehavior.BLOCK).sounds(BlockSoundGroup.BONE)));
    
    public static final Block SENIOREM_PLANKS = register("seniorem_planks", new Block(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS)));
    public static final Block SENIOREM_LOG = register("seniorem_log", Blocks.createLogBlock(MapColor.DARK_RED, MapColor.SPRUCE_BROWN));
    public static final Block SENIOREM_WOOD = register("seniorem_wood", new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_WOOD).mapColor(MapColor.SPRUCE_BROWN)));
    public static final Block STRIPPED_SENIOREM_LOG = register("stripped_seniorem_log", Blocks.createLogBlock(MapColor.DARK_RED, MapColor.DARK_RED));
    public static final Block STRIPPED_SENIOREM_WOOD = register("stripped_seniorem_wood", new PillarBlock(FabricBlockSettings.copyOf(Blocks.STRIPPED_OAK_WOOD).mapColor(MapColor.DARK_RED)));
    public static final Block SENIOREM_LEAVES = register("seniorem_leaves", Blocks.createLeavesBlock(BlockSoundGroup.AZALEA_LEAVES));
    public static final Block SENIOREM_SAPLING = register("seniorem_sapling", new SaplingBlock(ArcaneDimsSaplingGens.SENIOREM, FabricBlockSettings.copyOf(Blocks.OAK_SAPLING)));
    public static final Block POTTED_SENIOREM_SAPLING = register("potted_seniorem_sapling", false, new FlowerPotBlock(SENIOREM_SAPLING, FabricBlockSettings.copyOf(Blocks.POTTED_OAK_SAPLING)));
    public static final Block SENIOREM_STAIRS = register("seniorem_stairs", createStairsBlock(SENIOREM_PLANKS));
    public static final Block SENIOREM_SLAB = register("seniorem_slab", new SlabBlock(FabricBlockSettings.copyOf(SENIOREM_PLANKS)));
    public static final Block SENIOREM_FENCE = register("seniorem_fence", new FenceBlock(FabricBlockSettings.copyOf(SENIOREM_PLANKS)));
    public static final Block SENIOREM_FENCE_GATE = register("seniorem_fence_gate", new FenceGateBlock(WoodType.ACACIA, FabricBlockSettings.copyOf(SENIOREM_PLANKS)));
    public static final Block SENIOREM_DOOR = register("seniorem_door", new DoorBlock(BlockSetType.ACACIA, FabricBlockSettings.copyOf(SENIOREM_PLANKS)));
    public static final Block SENIOREM_TRAPDOOR = register("seniorem_trapdoor", new TrapdoorBlock(BlockSetType.ACACIA, FabricBlockSettings.copyOf(SENIOREM_PLANKS)));
    public static final Block SENIOREM_PRESSURE_PLATE = register("seniorem_pressure_plate", new PressurePlateBlock(BlockSetType.ACACIA, FabricBlockSettings.copyOf(SENIOREM_PLANKS)));
    public static final Block SENIOREM_BUTTON = register("seniorem_button", Blocks.createWoodenButtonBlock(BlockSetType.ACACIA));
    
    public static final Block SENIOREM_SIGN = register("seniorem_sign", false, 
            new TerraformSignBlock(MiscIdentifiers.SENIOREM_SIGN_TEXTURE, FabricBlockSettings.copyOf(Blocks.OAK_SIGN)));
    public static final Block SENIOREM_WALL_SIGN = register("seniorem_wall_sign", false, 
            new TerraformWallSignBlock(MiscIdentifiers.SENIOREM_SIGN_TEXTURE, FabricBlockSettings.copyOf(Blocks.OAK_WALL_SIGN)));
    
    public static final Block SENIOREM_HANGING_SIGN = register("seniorem_hanging_sign", false, new TerraformHangingSignBlock(
            MiscIdentifiers.SENIOREM_HANGING_SIGN_TEXTURE, MiscIdentifiers.SENIOREM_HANGING_SIGN_GUI_TEXTURE, 
            FabricBlockSettings.copyOf(Blocks.OAK_HANGING_SIGN)));
    public static final Block SENIOREM_WALL_HANGING_SIGN = register("seniorem_wall_hanging_sign", false, new TerraformWallHangingSignBlock(
            MiscIdentifiers.SENIOREM_HANGING_SIGN_TEXTURE, MiscIdentifiers.SENIOREM_HANGING_SIGN_GUI_TEXTURE, 
            FabricBlockSettings.copyOf(Blocks.OAK_WALL_HANGING_SIGN)));
    
    
    public static final Block ELDERSTONE = register("elderstone", new Block(FabricBlockSettings.copyOf(Blocks.STONE).strength(2, 6)));
    public static final Block COBBLED_ELDERSTONE = register("cobbled_elderstone", new Block(FabricBlockSettings.copyOf(Blocks.COBBLESTONE).strength(2, 6)));
    
    public static final Block ELDER_GRASS_BLOCK = register("elder_grass_block", new ElderGrassBlock(FabricBlockSettings.copyOf(Blocks.GRASS_BLOCK).strength(1, 1)));
    public static final Block ELDER_DIRT = register("elder_dirt", new Block(FabricBlockSettings.copyOf(Blocks.DIRT)
            .strength(0.8f, 0.5f).sounds(BlockSoundGroup.ROOTED_DIRT)));
    
    public static final Block ELDER_GRASS = register("elder_grass", new ShortElderGrassBlock(FabricBlockSettings.copyOf(Blocks.SHORT_GRASS).sounds(BlockSoundGroup.AZALEA_LEAVES)));
    public static final Block ELDER_FLY_SPORE_GRASS = register("elder_fly_spore_grass",
            new ElderFlySporeGrassBlock(FabricBlockSettings.copyOf(Blocks.TALL_GRASS).sounds(BlockSoundGroup.AZALEA_LEAVES)));
    
    public static final Block ELDRITCH_SMITHING_TABLE = register("eldritch_smithing_table", new EldritchSmithingTableBlock(FabricBlockSettings.create()
            .strength(2, 6).sounds(BlockSoundGroup.WOOD).mapColor(MapColor.SPRUCE_BROWN).instrument(Instrument.XYLOPHONE)));
    
    
    
    
    
    public static final BlockFamily SENIOREM_FAMILY = BlockFamilies.register(SENIOREM_PLANKS)
            .sign(SENIOREM_SIGN, SENIOREM_WALL_SIGN)
            .group("wooden").unlockCriterionName("has_planks").build();
    
    public static Block createStairsBlock(Block base) {
        return new StairsBlock(base.getDefaultState(), AbstractBlock.Settings.copy(base));
    }
    
    
    private static Block register(String name, Block block) {
        registerItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(ArcaneDimensions.MOD_ID, name), block);
    }
    private static Block register(String name, boolean hasItem, Block block) {
        if (hasItem) { registerItem(name, block); }
        return Registry.register(Registries.BLOCK, new Identifier(ArcaneDimensions.MOD_ID, name), block);
    }
    private static Item registerItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(ArcaneDimensions.MOD_ID, name), new BlockItem(block, new FabricItemSettings()));
    }
    
    public static void registerClass() {}
}
