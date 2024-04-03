package net.enderkitty.entity.entities;

import net.enderkitty.client.particle.ArcaneDimsParticles;
import net.enderkitty.util.ArcaneDimsTagRegistries;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.RevengeGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.EnumSet;
import java.util.Random;

public class ElderFlyEntity extends HostileEntity {
    
    public ElderFlyEntity(EntityType<? extends ElderFlyEntity> entityType, World world) {
        super(entityType, world);
        this.moveControl = new FlyMoveControl(this);
    }
    
    @Override
    protected void initGoals() {
        this.goalSelector.add(5, new FlyRandomlyGoal(this));
        this.goalSelector.add(5, new LookAtEntityGoal(this, PlayerEntity.class, 10, 1));
        
        this.targetSelector.add(1, new RevengeGoal(this, new Class[0])); 
        this.goalSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
    }
    
    public static DefaultAttributeContainer.Builder createAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 10)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1);
    }
    
    @Override
    public boolean damage(DamageSource source, float amount) {
        if (source.getAttacker() instanceof LivingEntity entity && entity.getMainHandStack().isIn(ArcaneDimsTagRegistries.ItemTags.ELDER_TOOLS)) {
            return super.damage(source, amount * 1.5f);
        }
        return super.damage(source, amount);
    }
    
    @Override
    public void travel(Vec3d movementInput) {
        if (this.isLogicalSideForUpdatingMovement()) {
            if (this.isTouchingWater()) {
                this.updateVelocity(0.2f, movementInput);
                this.move(MovementType.SELF, this.getVelocity());
                this.setVelocity(this.getVelocity().multiply(0.8f));
            } else if (this.isInLava()) {
                this.updateVelocity(0.2f, movementInput);
                this.move(MovementType.SELF, this.getVelocity());
                this.setVelocity(this.getVelocity().multiply(0.5));
            } else {
                float f = 0.91f;
                if (this.isOnGround()) {
                    f = this.getWorld().getBlockState(this.getVelocityAffectingPos()).getBlock().getSlipperiness() * 0.91f;
                }
                float g = 0.16277137f / (f * f * f);
                f = 0.91f;
                if (this.isOnGround()) {
                    f = this.getWorld().getBlockState(this.getVelocityAffectingPos()).getBlock().getSlipperiness() * 0.91f;
                }
                this.updateVelocity(this.isOnGround() ? 0.1f * g : 0.02f, movementInput);
                this.move(MovementType.SELF, this.getVelocity());
                this.setVelocity(this.getVelocity().multiply(f));
            }
        }
        this.updateLimbs(false);
    }
    
    
    @Override
    public void baseTick() {
        super.baseTick();
        if (this.getWorld().isClient && !this.isDead()) {
            for (int i = 0; i < 6; ++i) {
                this.getWorld().addParticle(ArcaneDimsParticles.ELDER_FLY_SPORE, true, 
                        this.getX(), this.getY() + 0.25, this.getZ(),
                        0, 0, 0
                );
            }
            this.getWorld().addParticle(ParticleTypes.WHITE_ASH, true, 
                    this.getX(), this.getY() + 0.75, this.getZ(),
                    0, -0.15, 0
            );
        }
    }
    
    
    
    
    static class FlyMoveControl extends MoveControl {
        private final ElderFlyEntity elderFly;
        private int collisionCheckCooldown;
        
        public FlyMoveControl(ElderFlyEntity fly) {
            super(fly);
            this.elderFly = fly;
        }
        
        @Override
        public void tick() {
            if (this.state != State.MOVE_TO) {
                return;
            }
            if (this.collisionCheckCooldown-- <= 0) {
                this.collisionCheckCooldown += this.elderFly.getRandom().nextInt(5) + 2;
                Vec3d vec3d = new Vec3d(this.targetX - this.elderFly.getX(), this.targetY - this.elderFly.getY(), this.targetZ - this.elderFly.getZ());
                double d = vec3d.length();
                if (this.willCollide(vec3d = vec3d.normalize(), MathHelper.ceil(d))) {
                    this.elderFly.setVelocity(this.elderFly.getVelocity().add(vec3d.multiply(0.1)));
                } else {
                    this.state = State.WAIT;
                }
            }
        }
        
        private boolean willCollide(Vec3d direction, int steps) {
            Box box = this.elderFly.getBoundingBox();
            for (int i = 1; i < steps; ++i) {
                box = box.offset(direction);
                if (this.elderFly.getWorld().isSpaceEmpty(this.elderFly, box)) continue;
                return false;
            }
            return true;
        }
    }
    
    //Flight
    static class FlyRandomlyGoal extends Goal {
        private final ElderFlyEntity elderFly;
        
        public FlyRandomlyGoal(ElderFlyEntity fly) {
            this.elderFly = fly;
            this.setControls(EnumSet.of(Control.MOVE));
        }
        
        @Override
        public boolean canStart() {
            double f;
            double e;
            MoveControl moveControl = this.elderFly.getMoveControl();
            if (!moveControl.isMoving()) {
                return true;
            }
            double d = moveControl.getTargetX() - this.elderFly.getX();
            double g = d * d + (e = moveControl.getTargetY() - this.elderFly.getY()) * e + (f = moveControl.getTargetZ() - this.elderFly.getZ()) * f;
            return g < 1.0 || g > 3600.0;
        }
        
        @Override
        public boolean shouldContinue() {
            return false;
        }
        
        @Override
        public void start() {
            double x = this.elderFly.getX() + (double) ((elderFly.getRandom().nextFloat() * 2.0f - 1.0f) * 16.0f);
            double y = this.elderFly.getY() + (double) ((elderFly.getRandom().nextFloat() * 2.0f - 1.0f) * 16.0f);
            double z = this.elderFly.getZ() + (double) ((elderFly.getRandom().nextFloat() * 2.0f - 1.0f) * 16.0f);
            this.elderFly.getMoveControl().moveTo(x, y, z, 1.0);
        }
    }
    
}
