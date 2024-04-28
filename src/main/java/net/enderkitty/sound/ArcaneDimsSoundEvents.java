package net.enderkitty.sound;

import net.enderkitty.ArcaneDimensions;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ArcaneDimsSoundEvents {
    
    public static final SoundEvent ENTITY_SOUL_SWARM_AMBIENT = register("entity.soul_swarm.ambient");
    
    private static SoundEvent register(String name) {
        Identifier id = new Identifier(ArcaneDimensions.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }
    
    public static void init() {}
}
