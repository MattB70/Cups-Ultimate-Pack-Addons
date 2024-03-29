package com.mattborle.cupsaddons.item.curios;

import com.google.common.collect.Multimap;
import com.mattborle.cupsaddons.CupsAddons;
import com.mattborle.cupsaddons.config.CupsAddonsCommonConfigs;
import com.mattborle.cupsaddons.init.ItemRegistry;
import com.mojang.serialization.Codec;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.particles.*;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.registries.ForgeRegistries;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import static com.mattborle.cupsaddons.config.CupsAddonsCommonConfigs.*;

public class FerromagneticObjectItem extends Item implements ICurioItem {

    static final int RANGE = 8; // Range of effect
    static final int COOLDOWN = (int)(COOLDOWN_FERROMAGNETIC_OBJECT.get().floatValue()*20); // Cooldown in ticks
    static final float DAMAGE = 2.0f * TUNE_FERROMAGNETIC_OBJECT.get().floatValue();

    public FerromagneticObjectItem(Properties properties) {
        super(properties
                .tab(ItemRegistry.CreativeTab.instance)
                .rarity(Rarity.RARE)
                .fireResistant()
                .stacksTo(1)
        );
    }
    @Override
    public void appendHoverText(ItemStack stack, Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        DecimalFormat df = new DecimalFormat("#.0");
        String cooldownString = df.format(CupsAddonsCommonConfigs.COOLDOWN_FERROMAGNETIC_OBJECT.get());
        if(Screen.hasShiftDown()){
            tooltip.add(new TranslatableComponent("tooltip.cupsaddons.ferromagnetic_object_active_ability"));
            tooltip.add(new TranslatableComponent("tooltip.cupsaddons.press_shift_to_use"));
            tooltip.add(new TextComponent("\n§dCooldown: §6"+cooldownString+" seconds\n"));
            //tooltip.add(new TranslatableComponent("tooltip.cupsaddons.ferromagnetic_object_passive_ability"));
        }else{
            tooltip.add(new TextComponent(""));
            tooltip.add(new TranslatableComponent("tooltip.cupsaddons.hold_shift_for_details"));
            tooltip.add(new TextComponent(""));
        }
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        LivingEntity player = slotContext.entity();
        Level level = player.getLevel();
        CompoundTag tag = stack.getOrCreateTag();

        // Do active ability if enabled
        if(ACTIVE_ABILITY_FERROMAGNETIC_OBJECT.get()) {

            if (level instanceof ServerLevel serverLevel) {
                // On the server
                // If cooldown has not finished
                if (tag.getInt("cupsaddons.cooldown") >= 0)
                { // decrement cooldown nbt
                    tag.putInt("cupsaddons.cooldown", tag.getInt("cupsaddons.cooldown") - 1);
                } // If cooldown has finished
                else if (tag.getInt("cupsaddons.cooldown") < 1 && player.isShiftKeyDown())
                {
                    tag.putInt("cupsaddons.cooldown", COOLDOWN); // Put fresh cooldown in ticks

                    // Play magnet sound
                    serverLevel.playSound(null, player.getX(), player.getY(), player.getZ(), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("cupsaddons:magnetize")), SoundSource.PLAYERS, 1.2f, 0.9f + player.getRandom().nextFloat() * 0.1f);
                    // Find nearby entities
                    List<LivingEntity> nearbyEntities = serverLevel.getNearbyEntities(LivingEntity.class, TargetingConditions.forCombat(), player, AABB.ofSize(player.getEyePosition(), RANGE, RANGE / 2, RANGE));
                    // Iterate nearbyEntities and knock back non-iron/chainmail wearing entities, and pull in those with the armor
                    for (int i = 0; i < nearbyEntities.size(); i++) {
                        LivingEntity entity = nearbyEntities.get(i);

                        // skip players
                        if (!(entity instanceof Player)) {

                            // If entity is alive and can see the player (don't knockback through walls):
                            if (entity.isAlive() && entity.hasLineOfSight(player)) {
                                try {
                                    // Get armor for checking
                                    ItemStack entityHead = entity.getItemBySlot(EquipmentSlot.HEAD);
                                    ItemStack entityChest = entity.getItemBySlot(EquipmentSlot.CHEST);
                                    ItemStack entityLegs = entity.getItemBySlot(EquipmentSlot.LEGS);
                                    ItemStack entityFeet = entity.getItemBySlot(EquipmentSlot.FEET);
                                    // Check armor
                                    if (entityHead.getItem() == Items.IRON_HELMET || entityHead.getItem() == Items.CHAINMAIL_HELMET ||
                                            entityChest.getItem() == Items.IRON_CHESTPLATE || entityChest.getItem() == Items.CHAINMAIL_CHESTPLATE ||
                                            entityLegs.getItem() == Items.IRON_LEGGINGS || entityLegs.getItem() == Items.CHAINMAIL_LEGGINGS ||
                                            entityFeet.getItem() == Items.IRON_BOOTS || entityFeet.getItem() == Items.CHAINMAIL_BOOTS)
                                    {
                                        // Repel entities that fail any of the above tests
                                        for (int strength = 30; strength > 0; strength--) {
                                            entity.push(player);
                                        }
                                        // Briefly give entity glowing effect
                                        entity.addEffect(new MobEffectInstance(MobEffects.GLOWING, 8, 1, (false), (false)));
                                        entity.playSound(SoundEvents.PLAYER_ATTACK_KNOCKBACK, 0.8f, 1f);
                                        // Spawn particles around entity
                                        // sendParticles to level (ParticleType,x,y,z,numParticles,dx,dy,dz,speed)
                                        serverLevel.sendParticles(ParticleTypes.GLOW, entity.getX(),entity.getY()+1,entity.getZ(),4,0,1,0,0.1);
                                        serverLevel.sendParticles(ParticleTypes.SMOKE, entity.getX(),entity.getY()+1,entity.getZ(),10,0,1,0,0.1);
                                    }
                                } catch (Exception e) {
                                    CupsAddons.LOGGER.error(e.getStackTrace().toString());
                                }
                            }
                        }
                    }
                    // Ability use Particles
                    // Create a circle of particles at about the range of the ability's AOE.
                    float angle = 0.0f;
                    int numParticles = 30;
                    for(int i = 0; i < numParticles; i++) {
                        // sendParticles to level (ParticleType,x,y,z,numParticles,dx,dy,dz,speed)
                        serverLevel.sendParticles(ParticleTypes.GLOW, player.getX()+((RANGE/2)*Math.cos(angle)),player.getY()+0.5,player.getZ()+((RANGE/2)*Math.sin(angle)),0,0,0,0,0);
                        angle += (Math.PI*2)/numParticles;
                    }
                }
            }
        }
    }
}
