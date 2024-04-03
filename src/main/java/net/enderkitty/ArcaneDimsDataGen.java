package net.enderkitty;

import net.enderkitty.datagen.*;
import net.enderkitty.util.ArcaneDimsConfiguredFeatures;
import net.enderkitty.util.ArcaneDimsPlacedFeatures;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public class ArcaneDimsDataGen implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(ArcaneDimsTagProviders.ArcaneDimsItemTags::new);
		pack.addProvider(ArcaneDimsTagProviders.ArcaneDimsBlockTags::new);
		pack.addProvider(ArcaneDimsLootTableProvider::new);
		pack.addProvider(ArcaneDimsModelProvider::new);
		pack.addProvider(ArcaneDimsRecipeProvider::new);
		pack.addProvider(ArcaneDimsWorldGen::new);

	}

	@Override
	public void buildRegistry(RegistryBuilder registryBuilder) {
		registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, ArcaneDimsConfiguredFeatures::boostrap);
		registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, ArcaneDimsPlacedFeatures::bootstrap);
		//registryBuilder.addRegistry(RegistryKeys.BIOME, ModBiomes::boostrap);
		//registryBuilder.addRegistry(RegistryKeys.DIMENSION_TYPE, ModDimensions::bootstrapType);
	}
}
