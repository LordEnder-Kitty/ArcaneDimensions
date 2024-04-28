package net.enderkitty.block;

import net.enderkitty.ArcaneDimensions;
import net.enderkitty.block.block_entities.EldritchSmithingTableBlockEntity;
import net.enderkitty.block.block_entities.WardCageBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ArcaneDimsBlockEntities {

    public static final BlockEntityType<WardCageBlockEntity> WARD_CAGE_BLOCK_ENTITY = Registry.register(
            Registries.BLOCK_ENTITY_TYPE, new Identifier(ArcaneDimensions.MOD_ID, "ward_cage_block_entity"),
            FabricBlockEntityTypeBuilder.create(WardCageBlockEntity::new, ArcaneDimsBlocks.WARD_CAGE).build()
    );
    public static final BlockEntityType<EldritchSmithingTableBlockEntity> ELDRITCH_SMITHING_TABLE_BLOCK_ENTITY = Registry.register(
            Registries.BLOCK_ENTITY_TYPE, new Identifier(ArcaneDimensions.MOD_ID, "eldritch_smithing_table"),
            FabricBlockEntityTypeBuilder.create(EldritchSmithingTableBlockEntity::new, ArcaneDimsBlocks.ELDRITCH_SMITHING_TABLE).build());


    public static void init() {}
}
