package com.mattborle.cupsaddons.item;

import com.mattborle.cupsaddons.CupsAddons;
import com.mattborle.cupsaddons.client.renderer.item.ChrysophilistsPickaxeRenderer;
import com.mattborle.cupsaddons.init.ItemRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.IItemRenderProperties;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.List;
import java.util.function.Consumer;

public class ChrysophilistsPickaxeItem extends PickaxeItem implements IAnimatable {

    /*
        ChrysophilistsPickaxe or Chrysophilist's Pickaxe

        Unique:
        A unique item is rewarded to the player for completion of a quest, task, purchased, or otherwise rewarded
        to the player through modpack defined methods. These items may not always be totally "unique", but that can
        never be discovered outside completing the task required by the modpack designer.
    */

    public AnimationFactory factory = GeckoLibUtil.createFactory(this);
    public String idleAnimationName = "animation.chrysophilists_pickaxe.idle";
    public Item fuelItem = Items.LAPIS_LAZULI;


    // Constructor and Usage ===========================================================================================
    public ChrysophilistsPickaxeItem(Tier tier, int p_42962_, float p_42963_, Properties properties) {
        super(tier, p_42962_, p_42963_, properties
                .tab(ItemRegistry.CreativeTab.instance)
                .rarity(Rarity.EPIC));
    }
    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        ItemStack offhandItem = player.getOffhandItem();
        if(offhandItem.getItem() == fuelItem) {

        }

        return super.use(world, player, hand);
    }
    @Override
    public void appendHoverText(ItemStack stack, Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        tooltip.add(new TranslatableComponent(
                "Fuel (Lapis Lazuli): " + (stack.getMaxDamage() - stack.getDamageValue() - 1) + " / " + (stack.getMaxDamage() - 1))
                .withStyle(ChatFormatting.ITALIC,ChatFormatting.LIGHT_PURPLE));
    }


    // Model and Animation =============================================================================================
    @Override
    public void initializeClient(Consumer<IItemRenderProperties> consumer) {
        super.initializeClient(consumer);
        consumer.accept(new IItemRenderProperties() {
            private final BlockEntityWithoutLevelRenderer renderer = new ChrysophilistsPickaxeRenderer();

            @Override
            public BlockEntityWithoutLevelRenderer getItemStackRenderer() {
                return renderer;
            }
        });
    }
    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation(idleAnimationName, ILoopType.EDefaultLoopTypes.LOOP));
        return PlayState.CONTINUE;
    }
    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
    }
    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
    // Give the item the shiny "foil" or "enchanted" effect.
    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }
}
