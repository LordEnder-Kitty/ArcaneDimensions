package net.enderkitty.item;

import com.terraformersmc.terraform.boat.api.item.TerraformBoatItemHelper;
import net.enderkitty.ArcaneDimensions;
import net.enderkitty.block.ArcaneDimsBlocks;
import net.enderkitty.entity.ArcaneDimsBoats;
import net.enderkitty.item.items.TheElderlandsKeyItem;
import net.enderkitty.item.items.WardCageItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ArcaneDimsItems {
    
    public static final Item ELDRITCH_CORE = register("eldritch_core", new Item(new FabricItemSettings()));
    public static final Item BLIND_CAGE_BONE = register("blind_cage_bone", new Item(new FabricItemSettings()));
    public static final Item WARD_CAGE = register("ward_cage", new WardCageItem(ArcaneDimsBlocks.WARD_CAGE, new FabricItemSettings().maxCount(1)));
    public static final Item ELDERLANDS_PORTAL_KEY = register("elderlands_portal_key", new TheElderlandsKeyItem(new FabricItemSettings().maxCount(1).fireproof()));
    
    public static final Item SENIOREM_STICK = register("seniorem_stick", new Item(new FabricItemSettings()));
    
    public static final Item SENIOREM_SIGN = register("seniorem_sign", 
            new SignItem(new FabricItemSettings().maxCount(16), ArcaneDimsBlocks.SENIOREM_SIGN, ArcaneDimsBlocks.SENIOREM_WALL_SIGN));
    public static final Item SENIOREM_HANGING_SIGN = register("seniorem_hanging_sign", 
            new HangingSignItem(ArcaneDimsBlocks.SENIOREM_HANGING_SIGN, ArcaneDimsBlocks.SENIOREM_WALL_HANGING_SIGN, new FabricItemSettings().maxCount(16)));
    
    public static final Item SENIOREM_BOAT = TerraformBoatItemHelper.registerBoatItem(ArcaneDimsBoats.SENIOREM_BOAT_ID, ArcaneDimsBoats.SENIOREM_BOAT, false);
    public static final Item SENIOREM_CHEST_BOAT = TerraformBoatItemHelper.registerBoatItem(ArcaneDimsBoats.SENIOREM_CHEST_BOAT_ID, ArcaneDimsBoats.SENIOREM_BOAT, true);
    
    //Seniorem Tools
    public static final Item SENIOREM_WOODEN_SWORD = register("seniorem_wooden_sword", new SwordItem(
            ArcaneDimsToolMaterials.SENIOREM_WOOD, 3, -2.4f, new FabricItemSettings()));
    public static final Item SENIOREM_WOODEN_PICKAXE = register("seniorem_wooden_pickaxe", new PickaxeItem(
            ArcaneDimsToolMaterials.SENIOREM_WOOD, 1, -2.8f, new FabricItemSettings()));
    public static final Item SENIOREM_WOODEN_AXE = register("seniorem_wooden_axe", new AxeItem(
            ArcaneDimsToolMaterials.SENIOREM_WOOD, 6, -3.2f, new FabricItemSettings()));
    public static final Item SENIOREM_WOODEN_SHOVEL = register("seniorem_wooden_shovel", new ShovelItem(
            ArcaneDimsToolMaterials.SENIOREM_WOOD, 1.5f, -3, new FabricItemSettings()));
    public static final Item SENIOREM_WOODEN_HOE = register("seniorem_wooden_hoe", new HoeItem(
            ArcaneDimsToolMaterials.SENIOREM_WOOD, 0, -3, new FabricItemSettings()));
    
    
    
    
    
    private static Item register(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(ArcaneDimensions.MOD_ID, name), item);
    }
    
    public static void registerClass() {}
}
