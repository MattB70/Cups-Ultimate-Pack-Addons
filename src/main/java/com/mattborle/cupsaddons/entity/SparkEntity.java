package com.mattborle.cupsaddons.entity;

import com.mattborle.cupsaddons.init.SoundRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class SparkEntity extends Monster implements IAnimatable {

    public AnimationFactory factory = GeckoLibUtil.createFactory(this);
    public String IDLE_ANIMATION_NAME = "animation.spark.idle";

    public SparkEntity(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
        xpReward = 2;
        this.moveControl = new FlyingMoveControl(this, 5, true);
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
    @Override
    public void playerTouch(Player player) {
        // If the mob touches a player, it should explode.
        super.playerTouch(player);
        explodeAttack(player);
    }

    protected SoundEvent getAmbientSound() { return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("cupsaddons:magnetize")); }
    protected float getSoundVolume() { return 0.7f; }



    // ANIMATION =======================================================================================================
    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
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
        return this.factory;
    }



    // HELPERS =========================================================================================================
    private void explodeAttack(Player player) {
        this.dead = true;
        level.explode(this, this.getX(), this.getY(), this.getZ(), 1f, Explosion.BlockInteraction.NONE);
        this.discard();
        level.playSound(player,this, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("cupsaddons:magnetize")), SoundSource.HOSTILE, 1.0f, 1.0f);
    }
}
