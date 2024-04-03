package net.enderkitty.entity;

import net.enderkitty.ArcaneDimensions;
import net.enderkitty.entity.entities.ElderFlyEntity;
import net.enderkitty.entity.entities.SoulChargeEntity;
import net.enderkitty.entity.entities.SoulSwarmEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ArcaneDimsEntities {
    
    public static final EntityType<SoulSwarmEntity> SOUL_SWARM = Registry.register(Registries.ENTITY_TYPE, 
            new Identifier(ArcaneDimensions.MOD_ID, "soul_swarm"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, SoulSwarmEntity::new)
                    .dimensions(EntityDimensions.fixed(1.5f, 1.5f)).build());
    public static final EntityType<ElderFlyEntity> ELDER_FLY = Registry.register(Registries.ENTITY_TYPE, 
            new Identifier(ArcaneDimensions.MOD_ID, "elder_fly"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, ElderFlyEntity::new)
                    .dimensions(EntityDimensions.fixed(0.5f, 0.5f)).build());
    public static final EntityType<SoulChargeEntity> SOUL_CHARGE = Registry.register(Registries.ENTITY_TYPE, 
            new Identifier(ArcaneDimensions.MOD_ID, "soul_charge"),
            FabricEntityTypeBuilder.<SoulChargeEntity>create(SpawnGroup.MISC, SoulChargeEntity::new)
                    .dimensions(EntityDimensions.fixed(0.5f, 0.5f)).build());
}
