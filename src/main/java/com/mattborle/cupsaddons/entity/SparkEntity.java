package com.mattborle.cupsaddons.entity;

import com.mattborle.cupsaddons.init.ModParticles;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
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
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.List;


public class SparkEntity extends Monster implements IAnimatable {

    public AnimationFactory factory = GeckoLibUtil.createFactory(this);
    public final String IDLE_ANIMATION_NAME = "animation.spark.idle";
    private static int ATTACK_TIMER = 40;
    private int attackTimer = -1; // time in ticks for attack to play out, ending in mob's death. Default to -1 for not attacking.
    private final int HOVER_SOUND_TIMER = 19; // How many ticks before looping sound. 2.636s, 2636ms,
    private int hoverSoundTimer = HOVER_SOUND_TIMER;
    private float PLAYER_DAMAGE = 2.5f; // How much damage should a spark tick on the player.

    public SparkEntity(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
        xpReward = 2;
        this.moveControl = new FlyingMoveControl(this, 15, false);
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
                .add(Attributes.FLYING_SPEED, 0.8f)
                .add(Attributes.MOVEMENT_SPEED, 0.3f).build();
    }
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this)); // Don't go underwater.
        this.goalSelector.addGoal(2, (new HurtByTargetGoal(this)).setAlertOthers()); // Alert others when damaged.
        this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 1.2, false));
        this.goalSelector.addGoal(4, (new NearestAttackableTargetGoal<>(this, Player.class, true)).setUnseenMemoryTicks(1000)); // Attack Player
        this.goalSelector.addGoal(5, new WaterAvoidingRandomFlyingGoal(this, 1.0));
    }
    @Override
    protected int calculateFallDamage(float p_21237_, float p_21238_) {
        return 0; // no fall damage possible
    }
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("cupsaddons:spark_impact"));
    }
    @Override
    public Fallsounds getFallSounds() {
        return new Fallsounds(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("cupsaddons:spark_impact")),ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("cupsaddons:spark_impact")));
    }
    // Activate spark attack if it touches a player
    @Override
    public void playerTouch(Player player) {
        super.playerTouch(player);
        if(this.getLevel() instanceof ServerLevel serverLevel) {
            if (this.attackTimer == -1) {
                this.attackTimer = ATTACK_TIMER; // set attack timer. This will trigger ticking damage and eventual death.
                // play impact sound
                serverLevel.playSound(null, this.getX(), this.getY(), this.getZ(), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("cupsaddons:spark_impact")), SoundSource.HOSTILE, 1f, 1f);
                // play attack sound once
                serverLevel.playSound(null, this.getX(), this.getY(), this.getZ(), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("cupsaddons:spark_attack")), SoundSource.HOSTILE, 1f, 1f);
            }
        }
    }
    // Activate spark attack if it takes damage
    @Override
    public boolean hurt(DamageSource damageSource, float x) {
        if(this.getLevel() instanceof ServerLevel serverLevel) {
            if (this.attackTimer == -1) {
                this.attackTimer = ATTACK_TIMER; // set attack timer. This will trigger ticking damage and eventual death.
                // play attack sound once
                serverLevel.playSound(null, this.getX(), this.getY(), this.getZ(), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("cupsaddons:spark_attack")), SoundSource.HOSTILE, 1f, 1f);
            }
            // do hurt particles
            serverLevel.sendParticles(ParticleTypes.SMOKE, this.getX(), this.getY() + 0.8, this.getZ(), 20, 0, 0, 0, 2);
        }
        return super.hurt(damageSource, x);
    }
    @Override
    public void kill() {
        if(this.getLevel() instanceof ServerLevel serverLevel){
            // do death particles
            serverLevel.sendParticles(ParticleTypes.CAMPFIRE_COSY_SMOKE, this.getX(),this.getY()+0.8,this.getZ(),10,0,0,0,0.3);
            // do death sound
            serverLevel.playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.GENERIC_BURN, SoundSource.HOSTILE, 0.5f, 0.7f);
        }
        super.kill();
    }
    @Override
    public void tick() {
        // Only do any of this if entity is alive
        if (this.getHealth() <= 0) {
            super.tick();
        }
        // HOVER EFFECTS
        // Only do hover sfx if the spark is not attacking/self-destructing. This doesn't mean it won't make the
        // sound at all, but rather just won't loop it again once it begins attacking.
        if (this.attackTimer <= 0) {
            if (level instanceof ClientLevel clientLevel) {
                if (this.hoverSoundTimer == HOVER_SOUND_TIMER) {
                    clientLevel.playLocalSound(
                            this.getX(),
                            this.getY(),
                            this.getZ(),
                            ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("cupsaddons:spark_hover")),
                            SoundSource.HOSTILE,
                            0.8f,
                            1.0f,
                            true);
                    this.hoverSoundTimer = 0;
                } else {
                    this.hoverSoundTimer++; // decrement a tick
                }
            }
        } else {
            // ATTACK
            if (level instanceof ServerLevel serverLevel) {
                this.attackTimer--;// reduce timer
                // tick damage every 10 ticks
                if (this.attackTimer % 10 == 0) {
                    // get nearby entities
                    List<LivingEntity> nearbyEntities = serverLevel.getNearbyEntities(LivingEntity.class, TargetingConditions.forCombat(), this, AABB.ofSize(this.getEyePosition(), 5, 5, 5));
                    // walk entities
                    for (int i = 0; i < nearbyEntities.size(); i++) {
                        LivingEntity entity = nearbyEntities.get(i);
                        // damage players
                        if (entity instanceof Player) {
                            // If entity is alive and can see the spark (don't attack through walls):
                            if (entity.isAlive() && entity.hasLineOfSight(this)) {
                                entity.hurt(DamageSource.MAGIC, PLAYER_DAMAGE); // hurt nearby entities
                                this.hurt(DamageSource.MAGIC, 0.1f); // hurt this spark
                                this.push(entity); // push nearby entities a small amount to keep things moving
                            }
                        }
                        // damage sparks
                        if (entity instanceof SparkEntity) {
                            // If entity is alive and can see the spark (don't attack through walls):
                            if (entity.isAlive() && entity.hasLineOfSight(this)) {
                                entity.hurt(DamageSource.MAGIC, 0.1f); // hurt nearby entities
                            }
                        }
                    }
                    // do sound and extra particles every 10 ticks on damage tick
                    serverLevel.playSound(null, this.getX(), this.getY(), this.getZ(), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("cupsaddons:magnetize")), SoundSource.HOSTILE, 0.6f, 1.5f);
                    serverLevel.sendParticles(ModParticles.ELECTRICITY_PARTICLES.get(), this.getX(), this.getY() + 0.8, this.getZ(), 15, 0.5, 0.5, 0.5, 0.8);
                    serverLevel.sendParticles(ParticleTypes.SMOKE, this.getX(), this.getY() + 0.8, this.getZ(), 10, 0, 0, 0, 0.5);
                }
                if (this.attackTimer == 0) {
                    // overcharged to death.
                    serverLevel.sendParticles(ParticleTypes.CAMPFIRE_COSY_SMOKE, this.getX(), this.getY() + 0.8, this.getZ(), 15, 0, 0, 0, 0.6);
                    serverLevel.playSound(null, this.getX(), this.getY(), this.getZ(), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("cupsaddons:spark_death")), SoundSource.HOSTILE, 1f, 1f);
                    this.kill(); // kill entity
                }
                // do particles every tick
                serverLevel.sendParticles(ModParticles.ELECTRICITY_PARTICLES.get(), this.getX(), this.getY() + 0.8, this.getZ(), 5, 0.5, 0.5, 0.5, 0.3);
            }
        }
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
