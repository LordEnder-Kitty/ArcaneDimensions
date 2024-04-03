package net.enderkitty.entity;

import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import com.terraformersmc.terraform.boat.api.TerraformBoatTypeRegistry;
import net.enderkitty.ArcaneDimensions;
import net.enderkitty.block.ArcaneDimsBlocks;
import net.enderkitty.item.ArcaneDimsItems;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class ArcaneDimsBoats {
    public static final Identifier SENIOREM_BOAT_ID = new Identifier(ArcaneDimensions.MOD_ID, "seniorem_boat");
    public static final Identifier SENIOREM_CHEST_BOAT_ID = new Identifier(ArcaneDimensions.MOD_ID, "seniorem_chest_boat");
    
    public static final RegistryKey<TerraformBoatType> SENIOREM_BOAT = TerraformBoatTypeRegistry.createKey(SENIOREM_BOAT_ID);
    
    public static void registerBoats() {
        TerraformBoatType senioremBoat = new TerraformBoatType.Builder()
                .item(ArcaneDimsItems.SENIOREM_BOAT)
                .chestItem(ArcaneDimsItems.SENIOREM_CHEST_BOAT)
                .planks(ArcaneDimsBlocks.SENIOREM_PLANKS.asItem())
                .build();

        Registry.register(TerraformBoatTypeRegistry.INSTANCE, SENIOREM_BOAT, senioremBoat);
    }
}
