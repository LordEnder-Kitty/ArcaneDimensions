package net.enderkitty.entity.entities;

import net.enderkitty.entity.ArcaneDimsEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ExplosiveProjectileEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

import java.util.Random;

public class SoulChargeEntity extends ExplosiveProjectileEntity {
    public SoulChargeEntity(EntityType<? extends ExplosiveProjectileEntity> entityType, World world) {
        super(entityType, world);
    }
    public SoulChargeEntity(World world, LivingEntity owner, double directionX, double directionY, double directionZ) {
        super(ArcaneDimsEntities.SOUL_CHARGE, owner, directionX, directionY, directionZ, world);
    }
    
    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        boolean bl;
        LivingEntity livingEntity;
        super.onEntityHit(entityHitResult);
        if (this.getWorld().isClient) {
            return;
        }
        Entity entity = entityHitResult.getEntity();
        Entity entity2 = this.getOwner();
        if (entity2 instanceof LivingEntity) {
            livingEntity = (LivingEntity)entity2;
            bl = entity.damage(this.getDamageSources().magic(), 8.0f);
            if (bl) {
                if (entity.isAlive()) {
                    this.applyDamageEffects(livingEntity, entity);
                } else {
                    livingEntity.heal(5.0f);
                }
            }
        }
    }
    
    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.getWorld().isClient) {
            this.getWorld().createExplosion((Entity)this, this.getX(), this.getY(), this.getZ(), 1.0f, false, World.ExplosionSourceType.MOB);
            this.discard();
        }
    }

    @Override
    public void baseTick() {
        super.baseTick();
        if (this.getWorld().isClient && this.isAlive()) {
            this.getWorld().addParticle(ParticleTypes.SOUL, true, 
                    this.getX(), this.getY() + 0.25, this.getZ(), 
                    new Random().nextDouble(-0.2, 0.2),
                    new Random().nextDouble(-0.2, 0.2),
                    new Random().nextDouble(-0.2, 0.2));
        }
    }
}
