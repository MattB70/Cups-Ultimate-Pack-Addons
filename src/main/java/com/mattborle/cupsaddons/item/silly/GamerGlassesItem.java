package com.mattborle.cupsaddons.item.silly;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
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


public class GamerGlassesItem extends GeoArmorItem implements IAnimatable {

    public AnimationFactory factory = GeckoLibUtil.createFactory(this);
    public String IDLE_ANIMATION_NAME = "animation.gamer_glasses.idle";

    public GamerGlassesItem(ArmorMaterial material, EquipmentSlot slot, Properties settings) {
        super(material, slot, settings);
    }
    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {
        if(!world.isClientSide()) {

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
