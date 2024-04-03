package net.enderkitty.client.particle;

import net.enderkitty.ArcaneDimensions;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ArcaneDimsParticles {

    public static final DefaultParticleType ELDER_FLY_SPORE = FabricParticleTypes.simple();


    public static void register() {
        Registry.register(Registries.PARTICLE_TYPE, new Identifier(ArcaneDimensions.MOD_ID, "elder_fly_spore"), ELDER_FLY_SPORE);
    }
}
