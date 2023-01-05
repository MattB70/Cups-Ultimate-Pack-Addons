package com.mattborle.cupsaddons.item.curios;

import com.mattborle.cupsaddons.config.CupsAddonsCommonConfigs;
import com.mattborle.cupsaddons.init.ItemRegistry;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.text.DecimalFormat;
import java.util.List;

public class FerromagneticObjectItem extends Item implements ICurioItem {
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
        LivingEntity entity = slotContext.entity();
        // On the server,
        if (!entity.level.isClientSide()) {
            CompoundTag tag = stack.getOrCreateTag();
            if (tag.getBoolean("cupsaddons.has_used") && !entity.isShiftKeyDown()) {
                tag.putBoolean("cupsaddons.has_used", false);
            } else if (!tag.getBoolean("cupsaddons.has_used") && entity.isShiftKeyDown()) {
                tag.putBoolean("cupsaddons.has_used", true);
                // Do active ability
                entity.level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.GENERIC_EXPLODE, SoundSource.PLAYERS, 1, 0.5F + entity.getRandom().nextFloat() * 0.1F);
            }
        }
    }
}
