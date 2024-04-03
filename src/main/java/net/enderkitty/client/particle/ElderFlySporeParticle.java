package net.enderkitty.client.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;
import org.jetbrains.annotations.Nullable;

public class ElderFlySporeParticle extends SpriteBillboardParticle {
    public ElderFlySporeParticle(ClientWorld clientWorld, double x, double y, double z, double xVelocity, 
                                 double yVelocity, double zVelocity, SpriteProvider spriteProvider) {
        
        super(clientWorld, x, y, z, xVelocity, yVelocity, zVelocity);
        
        this.velocityMultiplier = 0.2f;
        this.x = xVelocity;
        this.y = yVelocity;
        this.z = zVelocity;
        this.scale *= 0.75f;
        this.maxAge = 10;
        this.setSpriteForAge(spriteProvider);
        
        this.red = 1;
        this.green = 1;
        this.blue = 1;
    }

    @Override
    public void tick() {
        super.tick();
        fadeOut();
    }
    
    private void fadeOut() {
        this.alpha = (-1 / (float)maxAge) * age + 1;
    }
    

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }
    
    
    public static class Factory implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider spriteProvider;
        
        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Nullable
        @Override
        public Particle createParticle(DefaultParticleType parameters, ClientWorld world, double x, double y, double z, 
                                       double velocityX, double velocityY, double velocityZ) {
            return new ElderFlySporeParticle(world, x, y, z, velocityX, velocityY, velocityZ, spriteProvider);
        }
    }
}
