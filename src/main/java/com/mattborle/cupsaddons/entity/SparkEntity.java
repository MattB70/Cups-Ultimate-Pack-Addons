package com.mattborle.cupsaddons.entity;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.registries.ForgeRegistries;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.ParticleKeyFrameEvent;
import software.bernie.geckolib3.core.event.SoundKeyframeEvent;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.List;


public class SparkEntity extends Monster implements IAnimatable {

    public AnimationFactory factory = GeckoLibUtil.createFactory(this);
    public String IDLE_ANIMATION_NAME = "animation.spark.idle";
    private int attackTimer = -1; // time in ticks for attack to play out, ending in mob's death. Default to -1 for not attacking.

    public SparkEntity(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
        xpReward = 2;
        this.moveControl = new FlyingMoveControl(this, 25, true);
    }
    @Override
    protected PathNavigation createNavigation(Level world) {

        return new FlyingPathNavigation(this, world);
    }
    @Override
    public void setNoGravity(boolean ignored) {

        super.setNoGravity(true);
    }
    public void aiStep() {
        super.aiStep();
        this.setNoGravity(true);
    }
    public static AttributeSupplier setAttributes() {
        return Monster.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 8.0f)
                .add(Attributes.ATTACK_DAMAGE, 0.0f)
                .add(Attributes.ATTACK_SPEED, 0.0f)
                .add(Attributes.FLYING_SPEED, 0.5f)
                .add(Attributes.MOVEMENT_SPEED, 0.3f).build();
    }
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2, false));
        this.goalSelector.addGoal(2, new FloatGoal(this)); // Don't go underwater.
        this.goalSelector.addGoal(3, (new HurtByTargetGoal(this)).setAlertOthers()); // Alert others when damaged.
        this.goalSelector.addGoal(4, (new NearestAttackableTargetGoal<>(this, Player.class, true)).setUnseenMemoryTicks(300)); // Attack Player
        this.goalSelector.addGoal(5, new WaterAvoidingRandomFlyingGoal(this, 1.0));
    }
    // Activate spark attack if it touches a player
    @Override
    public void playerTouch(Player player) {
        super.playerTouch(player);
        if(this.attackTimer == -1){
            this.attackTimer = 60; // set attack timer. This will trigger ticking damage and eventual death.
        }
    }
    // Activate spark attack if it takes damage
    @Override
    public boolean hurt(DamageSource damageSource, float x) {
        if(this.attackTimer == -1){
            this.attackTimer = 60; // set attack timer. This will trigger ticking damage and eventual death.
        }
        return super.hurt(damageSource, x);
    }

    protected SoundEvent getAmbientSound() {
        return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("cupsaddons:magic_pop"));
    }
    protected float getSoundVolume() {
        return 0.7f;
    }
    @Override
    public void tick() {
        if(level instanceof ServerLevel serverLevel){
            if (this.attackTimer > -1) {
                this.attackTimer--;// reduce timer
                // tick damage every 10 ticks
                if(this.attackTimer % 10 == 0) {
                    // get nearby entities
                    List<LivingEntity> nearbyEntities = serverLevel.getNearbyEntities(LivingEntity.class, TargetingConditions.forCombat(), this, AABB.ofSize(this.getEyePosition(), 10, 10 / 2, 10));
                    // walk entities
                    for (int i = 0; i < nearbyEntities.size(); i++) {
                        LivingEntity entity = nearbyEntities.get(i);
                        // only damage players
                        if (entity instanceof Player) {
                            // If entity is alive and can see the spark (don't attack through walls):
                            if (entity.isAlive() && entity.hasLineOfSight(this)) {
                                entity.hurt(DamageSource.MAGIC,1.0f); // hurt nearby entities
                                this.push(entity); // push nearby entities a small amount to keep things moving
                            }
                        }
                    }
                    // do sound and extra particles every 10 ticks on damage tick
                    serverLevel.playSound(null, this.getX(), this.getY(), this.getZ(), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("cupsaddons:magnetize")), SoundSource.PLAYERS, 0.6f, 1.5f);
                    serverLevel.sendParticles(ParticleTypes.ELECTRIC_SPARK, this.getX(),this.getY()+1,this.getZ(),10,0,0,0,2);
                    serverLevel.sendParticles(ParticleTypes.SMOKE, this.getX(),this.getY()+1,this.getZ(),10,0,0,0,2);
                }
                // do particles every tick
                serverLevel.sendParticles(ParticleTypes.ELECTRIC_SPARK, this.getX(),this.getY()+1,this.getZ(),5,0,0,0,2);
                if (this.attackTimer == 0) {
                    // do particles and sound
                    serverLevel.sendParticles(ParticleTypes.SMOKE, this.getX(),this.getY()+1,this.getZ(),5,0,0,0,0.5);
                    serverLevel.playSound(null, this.getX(), this.getY(), this.getZ(), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("cupsaddons:magic_pop")), SoundSource.PLAYERS, 0.6f, 1.5f);
                    this.kill(); // kill entity
                }
            }
        }
        super.tick();
    }


    // ANIMATION =======================================================================================================
    private <P extends IAnimatable> PlayState predicate(AnimationEvent<P> event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation(IDLE_ANIMATION_NAME));
        return PlayState.CONTINUE;
    }
    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(
                this,
                "controller",
                0,
                this::predicate));
    }
    @Override
    public AnimationFactory getFactory() {
        return factory;
    }
}
