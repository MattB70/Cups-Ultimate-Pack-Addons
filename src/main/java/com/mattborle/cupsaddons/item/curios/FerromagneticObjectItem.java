package com.mattborle.cupsaddons.item.curios;

import com.mattborle.cupsaddons.CupsAddons;
import com.mattborle.cupsaddons.config.CupsAddonsCommonConfigs;
import com.mattborle.cupsaddons.init.ItemRegistry;
import com.mattborle.cupsaddons.init.MobEffectRegistry;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.client.EffectRenderer;
import net.minecraftforge.registries.ForgeRegistries;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.text.DecimalFormat;
import java.util.List;

public class FerromagneticObjectItem extends Item implements ICurioItem {

    static final int RANGE = 10; // Range of effect

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
            tooltip.add(new TextComponent("§dCooldown: §6"+cooldownString+" seconds\n"));
            tooltip.add(new TranslatableComponent("tooltip.cupsaddons.ferromagnetic_object_passive_ability"));
        }else{
            tooltip.add(new TextComponent(""));
            tooltip.add(new TranslatableComponent("tooltip.cupsaddons.hold_shift_for_details"));
            tooltip.add(new TextComponent(""));
        }
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        LivingEntity livingEntity = slotContext.entity();
        // On the server,
        if (!livingEntity.level.isClientSide()) {
            CompoundTag tag = stack.getOrCreateTag();
            if (tag.getBoolean("cupsaddons.has_used") && !livingEntity.isShiftKeyDown()) {
                tag.putBoolean("cupsaddons.has_used", false);
            } else if (!tag.getBoolean("cupsaddons.has_used") && livingEntity.isShiftKeyDown()) {
                tag.putBoolean("cupsaddons.has_used", true);
                // Do active ability

                // Play magnet sound
                livingEntity.level.playSound(null, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("cupsaddons:magnetize")), SoundSource.PLAYERS, 1, 0.5F + livingEntity.getRandom().nextFloat() * 0.1F);

                // Find nearby entites
                List<LivingEntity> nearbyEntities = livingEntity.level.getNearbyEntities(LivingEntity.class, TargetingConditions.forCombat(), livingEntity, AABB.ofSize(livingEntity.getEyePosition(),RANGE,RANGE/2,RANGE));




                // List nearby entities to console for debug.
                CupsAddons.LOGGER.debug("nearbyEntities:");
                for(int i = 0; i < nearbyEntities.size(); i++){
                    CupsAddons.LOGGER.debug("   Entity: "+nearbyEntities.get(i).getDisplayName().getString());
                }
            }
        }
    }
}
