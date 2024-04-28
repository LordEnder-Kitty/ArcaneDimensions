package net.enderkitty;

import net.enderkitty.block.ArcaneDimsBlockEntities;
import net.enderkitty.block.ArcaneDimsBlocks;
import net.enderkitty.client.particle.ArcaneDimsParticles;
import net.enderkitty.entity.ArcaneDimsBoats;
import net.enderkitty.entity.ArcaneDimsEntities;
import net.enderkitty.entity.entities.ElderFlyEntity;
import net.enderkitty.entity.entities.SoulSwarmEntity;
import net.enderkitty.item.ArcaneDimsItems;
import net.enderkitty.recipe.ArcaneDimsRecipeRegistries;
import net.enderkitty.screen.ArcaneDimsScreenHandlers;
import net.enderkitty.sound.ArcaneDimsSoundEvents;
import net.enderkitty.util.LootTableModifiers;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArcaneDimensions implements ModInitializer {
	public static final String MOD_ID = "arcanedims";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	
	@Override
	public void onInitialize() {
		ArcaneDimsItems.init();
		ArcaneDimsBlocks.init();
		ArcaneDimsItemGroups.init();
		ArcaneDimsBlockEntities.init();
		ArcaneDimsSoundEvents.init();
		ArcaneDimsBoats.registerBoats();
		
		LootTableModifiers.modifyLootTables();
		
		ArcaneDimsParticles.register();
		
		ArcaneDimsScreenHandlers.init();
		ArcaneDimsRecipeRegistries.register();
		
		FabricDefaultAttributeRegistry.register(ArcaneDimsEntities.SOUL_SWARM, SoulSwarmEntity.createAttributes());
		FabricDefaultAttributeRegistry.register(ArcaneDimsEntities.ELDER_FLY, ElderFlyEntity.createAttributes());
	}
}
