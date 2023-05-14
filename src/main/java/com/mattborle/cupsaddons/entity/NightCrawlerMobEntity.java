/*
package com.mattborle.cupsaddons.entity;

import com.mattborle.cupsaddons.handlers.consumable.NightEssenceEatingHandler;
import com.mattborle.cupsaddons.init.EntityRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.DungeonHooks;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Set;

public class NightCrawlerMobEntity extends Monster {
    private static final Set<ResourceLocation> SPAWN_BIOMES = Set.of(new ResourceLocation("frozen_ocean"), new ResourceLocation("frozen_river"),
            new ResourceLocation("forest"), new ResourceLocation("ice_spikes"), new ResourceLocation("windswept_hills"),
            new ResourceLocation("snowy_plains"), new ResourceLocation("snowy_slopes"), new ResourceLocation("cold_ocean"),
            new ResourceLocation("frozen_peaks"), new ResourceLocation("snowy_taiga"), new ResourceLocation("snowy_beach"));

    @SubscribeEvent
    public static void addLivingEntityToBiomes(BiomeLoadingEvent event) {
        if (SPAWN_BIOMES.contains(event.getName()))
            event.getSpawns().getSpawner(MobCategory.MONSTER)
                    .add(new MobSpawnSettings.SpawnerData(EntityRegistry.NIGHT_CRAWLER_MOB.get(), 6, 5, 10));
    }

    public NightCrawlerMobEntity(PlayMessages.SpawnEntity packet, Level world) {
        this(EntityRegistry.NIGHT_CRAWLER_MOB.get(), world);
    }

    public NightCrawlerMobEntity(EntityType<NightCrawlerMobEntity> type, Level world) {
        super(type, world);
        xpReward = 0;
        setNoAi(false);
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.2));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this).setAlertOthers());
        this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 1.2, false) {
            @Override
            protected double getAttackReachSqr(LivingEntity entity) {
                return (double) (4.0 + entity.getBbWidth() * entity.getBbWidth());
            }
        });
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal(this, EnderMan.class, true, true));
        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal(this, ServerPlayer.class, true, true));
        this.goalSelector.addGoal(6, new RandomStrollGoal(this, 1));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, ServerPlayer.class, (float) 6));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(9, new FloatGoal(this));
    }

    @Override
    public MobType getMobType() {
        return MobType.UNDEFINED;
    }

    @Override
    public SoundEvent getAmbientSound() {
        return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("cupsaddons:night_crawler_ambient"));
    }

    @Override
    public void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.powder_snow.step")), 0.15f, 1);
    }

    @Override
    public SoundEvent getHurtSound(DamageSource ds) {
        return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("cupsaddons:night_crawler_hurt"));
    }

    @Override
    public SoundEvent getDeathSound() {
        return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("cupsaddons:night_crawler_death"));
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        NightEssenceEatingHandler.execute(this);
        if (source == DamageSource.FALL)
            return false;
        if (source == DamageSource.LIGHTNING_BOLT)
            return false;
        return super.hurt(source, amount);
    }

    @Override
    public void die(DamageSource source) {
        super.die(source);
        NightEssenceEatingHandler.execute(this);
    }

    public static void init() {
        SpawnPlacements.register(EntityRegistry.NIGHT_CRAWLER_MOB.get(), SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (entityType, world, reason, pos, random) -> (world.getDifficulty() != Difficulty.PEACEFUL
                        && Monster.isDarkEnoughToSpawn(world, pos, random) && Mob.checkMobSpawnRules(entityType, world, reason, pos, random)));
        DungeonHooks.addDungeonMob(EntityRegistry.NIGHT_CRAWLER_MOB.get(), 180);
    }

    public static AttributeSupplier.Builder createAttributes() {
        AttributeSupplier.Builder builder = Mob.createMobAttributes();
        builder = builder.add(Attributes.MOVEMENT_SPEED, 0.4);
        builder = builder.add(Attributes.MAX_HEALTH, 20);
        builder = builder.add(Attributes.ARMOR, 0);
        builder = builder.add(Attributes.ATTACK_DAMAGE, 3);
        builder = builder.add(Attributes.FOLLOW_RANGE, 16);
        return builder;
    }
}
 */