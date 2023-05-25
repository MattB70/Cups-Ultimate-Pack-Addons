package com.mattborle.cupsaddons.item.armor;

import com.mattborle.cupsaddons.init.MobEffectRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.item.GeoArmorItem;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.List;


public class GamerGlassesItem extends GeoArmorItem implements IAnimatable {

    public AnimationFactory factory = GeckoLibUtil.createFactory(this);
    public String IDLE_ANIMATION_NAME = "animation.gamer_glasses.idle";

    public GamerGlassesItem(ArmorMaterial material, EquipmentSlot slot, Properties settings) {
        super(material, slot, settings
                .durability(-1)
                .rarity(Rarity.RARE)
                .fireResistant()
                .setNoRepair());
    }
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(new TextComponent("§8Improves §6§oGaming §8abilities"));
        super.appendHoverText(stack, level, tooltip, flag);
    }
    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {
        if(!world.isClientSide()) {
            player.addEffect(new MobEffectInstance(MobEffectRegistry.GAMING.get(), 10, 0, (true), (false)));
        }
    }


    // ANIMATIONS ======================================================================================================

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<GamerGlassesItem>(this, "controller",
                20, this::predicate));
    }
    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation(IDLE_ANIMATION_NAME, ILoopType.EDefaultLoopTypes.LOOP));
        return PlayState.CONTINUE;
    }
}
