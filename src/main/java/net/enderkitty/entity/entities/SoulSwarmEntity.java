package net.enderkitty.entity.entities;

import net.enderkitty.sound.ArcaneDimsSoundEvents;
import net.enderkitty.util.ArcaneDimsTagRegistries;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.BirdNavigation;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;
import java.util.Random;

public class SoulSwarmEntity extends HostileEntity implements RangedAttackMob {
    private static final TrackedData<Integer> SOULS = DataTracker.registerData(SoulSwarmEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private final ServerBossBar bossBar = (ServerBossBar)new ServerBossBar(this.getDisplayName(), BossBar.Color.BLUE, BossBar.Style.PROGRESS).setDarkenSky(true);
    
    public SoulSwarmEntity(EntityType<? extends SoulSwarmEntity> entityType, World world) {
        super(entityType, world);
        this.moveControl = new SwarmMoveControl(this);
        this.experiencePoints = SOULS.getId() * 2;
    }
    
    @Override
    protected void initGoals() {
        this.goalSelector.add(4, new ProjectileAttackGoal(this, 1, 40, 20));
        this.goalSelector.add(4, new FlyGoal(this, 10));
        this.goalSelector.add(7, new ChargeTargetGoal(this));
        this.goalSelector.add(5, new LookAtEntityGoal(this, PlayerEntity.class, 80));
        
        this.targetSelector.add(1, new RevengeGoal(this, new Class[0])); 
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, false));
    }
    
    @Override
    protected EntityNavigation createNavigation(World world) {
        BirdNavigation birdNavigation = new BirdNavigation(this, world);
        birdNavigation.setCanPathThroughDoors(false);
        birdNavigation.setCanSwim(false);
        birdNavigation.setCanEnterOpenDoors(true);
        return birdNavigation;
    }
    
    public static DefaultAttributeContainer.Builder createAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 200)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1);
    }
    
    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(SOULS, 1);
    }
    
    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("Souls", this.getSouls());
    }
    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.setSouls(nbt.getInt("Souls") + 1);
        if (this.hasCustomName()) this.bossBar.setName(this.getDisplayName());
    }
    
    @Override
    public void setCustomName(@Nullable Text name) {
        super.setCustomName(name);
        this.bossBar.setName(this.getDisplayName());
    }
    
    public int getSouls() {
        return this.dataTracker.get(SOULS);
    }
    public void setSouls(int souls) {
        int soulsClamped = MathHelper.clamp(souls, 1, 10);
        this.dataTracker.set(SOULS, soulsClamped);
    }
    
    @Override
    public boolean damage(DamageSource source, float amount) {
        if (source.getAttacker() instanceof LivingEntity entity && entity.getMainHandStack().isIn(ArcaneDimsTagRegistries.ItemTags.ELDER_TOOLS)) {
            return super.damage(source, amount * 1.5f);
        }
        if (source.isOf(DamageTypes.FALL)) return false;
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
            for (int i = 0; i < this.getSouls() * 2; ++i) {
                this.getWorld().addParticle(ParticleTypes.SOUL, true, 
                        this.getX(), this.getY() + 0.75, this.getZ(),
                        
                        new Random().nextDouble(-0.08, 0.08),
                        new Random().nextDouble(-0.08, 0.08),
                        new Random().nextDouble(-0.08, 0.08)
                );
            }
        }
        LivingEntity target = this.getTarget();
        if (target != null && this.squaredDistanceTo(target) <= 5) {
            float damage = (float) (this.getSouls() * 1.3);
            target.damage(target.getWorld().getDamageSources().mobAttack(this), damage);
        }
    }
    
    @Override
    protected void mobTick() {
        this.bossBar.setPercent(this.getHealth() / this.getMaxHealth());
    }
    
    @Override
    public void onStartedTrackingBy(ServerPlayerEntity player) {
        super.onStartedTrackingBy(player);
        this.bossBar.addPlayer(player);
    }
    @Override
    public void onStoppedTrackingBy(ServerPlayerEntity player) {
        super.onStoppedTrackingBy(player);
        this.bossBar.removePlayer(player);
    }
    
    @Nullable @Override
    protected SoundEvent getAmbientSound() {
        return ArcaneDimsSoundEvents.ENTITY_SOUL_SWARM_AMBIENT;
    }

    @Override
    public boolean cannotDespawn() {
        return true;
    }

    @Override
    public void shootAt(LivingEntity target, float pullProgress) {
        SoulChargeEntity soulCharge = new SoulChargeEntity(this.getWorld(), this, target.getX(), target.getEyeY(), target.getZ());
        soulCharge.setOwner(this);
        soulCharge.setPos(
                this.getX() + (4 * Math.sin(Math.toRadians(this.getYaw())) * Math.cos(Math.toRadians(this.getPitch() - 180))),
                this.getY() + 0.75,
                this.getZ() + (4 * Math.cos(Math.toRadians(this.getYaw() + 180)) * Math.cos(Math.toRadians(this.getPitch() - 180)))
                );
        this.getWorld().spawnEntity(soulCharge);
    }

    /* Temporary! For debugging only! */

    @Deprecated(forRemoval = true)
    @Override
    protected ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        if (stack.isOf(Items.NETHERITE_SWORD)) this.kill(); stack.damage(4, player, player1 -> player1.sendToolBreakStatus(hand));
        return super.interactMob(player, hand);
    }


    /* GOALS AND MOVEMENT CONTROL */
    
    // Move Control
    static class SwarmMoveControl extends MoveControl {
        private final SoulSwarmEntity swarm;
        private int collisionCheckCooldown;
        
        public SwarmMoveControl(SoulSwarmEntity swarm) {
            super(swarm);
            this.swarm = swarm;
        }
        
        @Override
        public void tick() {
            if (this.state != MoveControl.State.MOVE_TO) {
                return;
            }
            if (this.collisionCheckCooldown-- <= 0) {
                this.collisionCheckCooldown += this.swarm.getRandom().nextInt(5) + 2;
                Vec3d vec3d = new Vec3d(this.targetX - this.swarm.getX(), this.targetY - this.swarm.getY(), this.targetZ - this.swarm.getZ());
                double d = vec3d.length();
                if (this.willCollide(vec3d = vec3d.normalize(), MathHelper.ceil(d))) {
                    this.swarm.setVelocity(this.swarm.getVelocity().add(vec3d.multiply(0.1)));
                } else {
                    this.state = MoveControl.State.WAIT;
                }
            }
        }
        
        private boolean willCollide(Vec3d direction, int steps) {
            Box box = this.swarm.getBoundingBox();
            for (int i = 1; i < steps; ++i) {
                box = box.offset(direction);
                if (this.swarm.getWorld().isSpaceEmpty(this.swarm, box)) continue;
                return false;
            }
            return true;
        }
    }
    
    // AI
    /*static class FlyToTargetGoal extends Goal {
        private final SoulSwarmEntity swarm;
        private LivingEntity target;
        
        public FlyToTargetGoal(SoulSwarmEntity swarm) {
            this.swarm = swarm;
            this.setControls(EnumSet.of(Goal.Control.MOVE));
        }
        
        @Override
        public boolean canStart() {
            if (swarm.getTarget() != null) target = swarm.getTarget();
            return target != null && swarm.squaredDistanceTo(target) > 25;
        }
        
        @Override
        public boolean shouldContinue() {
            return false;
        }
        
        @Override
        public void start() {
            LivingEntity target = swarm.getTarget();
            
            if (target != null && swarm.getHealth() > swarm.getMaxHealth() / 3) {
                double posX = target.getPos().getX() + new Random().nextDouble(-5, 5);
                double posY = target.getEyeY() + new Random().nextDouble(0.0, 3.0);
                double posZ = target.getPos().getZ() + new Random().nextDouble(-5, 5);
                swarm.getMoveControl().moveTo(posX, posY, posZ, 1.5);
            }
        }
    }*/
    /*static class FlyAroundGoal extends Goal {
        private final SoulSwarmEntity swarm;
        private LivingEntity target;
        
        public FlyAroundGoal(SoulSwarmEntity swarm) {
            this.swarm = swarm;
            this.setControls(EnumSet.of(Control.MOVE));
        }
        
        @Override
        public boolean canStart() {
            target = swarm.getTarget();
            double f;
            double e;
            MoveControl moveControl = this.swarm.getMoveControl();
            if (!moveControl.isMoving()) {
                return target != null;
            }
            double d = moveControl.getTargetX() - this.swarm.getX();
            double g = d * d + (e = moveControl.getTargetY() - this.swarm.getY()) * e + (f = moveControl.getTargetZ() - this.swarm.getZ()) * f;
            return g < 1.0 || g > 3600.0 && target != null;
        }
        
        @Override
        public void start() {
            if (target != null && swarm.canSee(target)) {
                target.sendMessage(Text.literal("FlyAroundGoal"));
                double posX = target.getX() + (new Random().nextBoolean() ? ((new Random().nextBoolean()) ? 10 : -10) : ((new Random().nextBoolean()) ? 5 : -5));
                double posY = target.getEyeY() + 1;
                double posZ = target.getZ() + (new Random().nextBoolean() ? ((new Random().nextBoolean()) ? 10 : -10) : ((new Random().nextBoolean()) ? 5 : -5));
                swarm.moveControl.moveTo(posX, posY, posZ, 1);
            }
        }
        
    }*/
    static class ChargeTargetGoal extends Goal {
        private final SoulSwarmEntity swarm;
        private LivingEntity target;
        
        public ChargeTargetGoal(SoulSwarmEntity swarm) {
            this.swarm = swarm;
            this.setControls(EnumSet.of(Control.MOVE));
            this.getTickCount(300);
        }
        
        @Override
        public boolean canStart() {
            target = swarm.getTarget();
            double f;
            double e;
            MoveControl moveControl = this.swarm.getMoveControl();
            if (!moveControl.isMoving()) {
                return target != null;
            }
            double d = moveControl.getTargetX() - this.swarm.getX();
            double g = d * d + (e = moveControl.getTargetY() - this.swarm.getY()) * e + (f = moveControl.getTargetZ() - this.swarm.getZ()) * f;
            return g < 1.0 || g > 3600.0 && target != null;
        }
        
        @Override
        public void start() {
            if (target != null && swarm.squaredDistanceTo(target) < 4000 && swarm.canSee(target) && swarm.getHealth() > swarm.getMaxHealth() / 4) {
                target.sendMessage(Text.literal("ChargeTargetGoal"));
                
                swarm.lookControl.lookAt(target);
                swarm.moveControl.moveTo(target.getX(), target.getEyeY(), target.getZ(), 200);
            }
        }
    }
}
