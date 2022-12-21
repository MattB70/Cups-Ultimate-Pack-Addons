package com.mattborle.cupsaddons.item;

import com.mattborle.cupsaddons.CupsAddons;
import com.mattborle.cupsaddons.client.renderer.item.ChrysophilistsPickaxeRenderer;
import com.mattborle.cupsaddons.config.CupsAddonsCommonConfigs;
import com.mattborle.cupsaddons.init.ItemRegistry;
import com.mattborle.cupsaddons.init.SoundRegistry;
import com.simibubi.create.foundation.sound.SoundScapes;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.IItemRenderProperties;
import net.minecraftforge.network.PacketDistributor;
import org.apache.commons.lang3.text.WordUtils;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.ParticleKeyFrameEvent;
import software.bernie.geckolib3.core.event.SoundKeyframeEvent;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.network.GeckoLibNetwork;
import software.bernie.geckolib3.network.ISyncable;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.List;
import java.util.Random;
import java.util.function.Consumer;


public class ChrysophilistsPickaxeItem extends PickaxeItem implements IAnimatable, ISyncable {

    /*
        ChrysophilistsPickaxe or Chrysophilist's Pickaxe

        Unique:
        A unique item is rewarded to the player for completion of a quest, task, purchased, or otherwise rewarded
        to the player through modpack defined methods. These items may not always be totally "unique", but that can
        never be discovered outside completing the task required by the modpack designer.
    */

    // Functionality Variables
    public String fuelItem = CupsAddonsCommonConfigs.FUEL_CHRYSOPHILISTS_PICKAXE.get();
    public int fuel = 0;
    public int maxFuel = 100;

    // Animation Variables
    public String idleAnimationName = "animation.chrysophilists_pickaxe.idle";
    public String useAnimationName = "animation.chrysophilists_pickaxe.use";
    public String activeAnimationName = "animation.chrysophilists_pickaxe.active";
    private static final String CONTROLLER_NAME = "useController";
    private static final int ANIM_OPEN = 0;
    public AnimationFactory factory = GeckoLibUtil.createFactory(this);

    // Constructor and Usage ===========================================================================================
    public ChrysophilistsPickaxeItem(Tier tier, int p_42962_, float p_42963_, Properties properties) {
        super(tier, p_42962_, p_42963_, properties
                .tab(ItemRegistry.CreativeTab.instance)     // Show in creative menu tab
                .rarity(Rarity.EPIC)                        // Rarity color for title and loot beams
                .fireResistant()                            // Cannot be destroyed in lava
                .durability(-1)                     // Durability of -1 means unbreakable
        );
        GeckoLibNetwork.registerSyncable(this);
    }
    @Override
    public void appendHoverText(ItemStack stack, Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        if(CupsAddonsCommonConfigs.ABILITY_CHRYSOPHILISTS_PICKAXE.get()){
            tooltip.add(new TextComponent("Fuel ("+ WordUtils.capitalize(fuelItem.replace("_", " "))+"): "+fuel+"/"+maxFuel)
                    .withStyle(ChatFormatting.BLUE));
            if(Screen.hasAltDown()){
                tooltip.add(new TranslatableComponent("tooltip.cupsaddons.chrysophilists_pickaxe_description"));
            }else{
                tooltip.add(new TranslatableComponent("tooltip.cupsaddons.hold_alt_for_details"));
            }
            tooltip.add(new TranslatableComponent("tooltip.cupsaddons.unique_item"));
        }
    }

    // Animation and Model =============================================================================================

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

    private <E extends IAnimatable> PlayState idleAnimation(AnimationEvent<E> event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation(idleAnimationName, ILoopType.EDefaultLoopTypes.LOOP));
        return PlayState.CONTINUE;
    }
    private <P extends Item & IAnimatable> PlayState predicate(AnimationEvent<P> event) {
        // Not setting an animation here as that's handled below
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        AnimationController controller = new AnimationController(this, CONTROLLER_NAME, 20, this::predicate);
        // Registering a sound listener just makes it so when any sound keyframe is hit
        // the method will be called.
        // To register a particle listener or custom event listener you do the exact
        // same thing, just with registerParticleListener and
        // registerCustomInstructionListener, respectively.
        controller.registerSoundListener(this::soundListener);
        controller.registerParticleListener(this::particleListener);
        data.addAnimationController(controller);
        // Idle animation controller
        data.addAnimationController(new AnimationController(this, "idle", 20, this::idleAnimation));
    }

    private <ENTITY extends IAnimatable> void soundListener(SoundKeyframeEvent<ENTITY> event) {
        // Immediately on use, do nothing extra.
    }
    private <ENTITY extends IAnimatable> void particleListener(ParticleKeyFrameEvent<ENTITY> event) {
        // As soon as the particles play, Do nothing.
        LocalPlayer player = Minecraft.getInstance().player;
        if (player != null) {
            player.playSound(SoundEvents.TOTEM_USE, 0.8f, 1.3f);
        }
        // Particles
        for(int i = 0; i < 20; i++){
            Random r = new Random();
            player.level.addParticle(ParticleTypes.ENCHANT, player.getX(), player.getY()+player.getEyeHeight()*1.5, player.getZ(),
                    (-3f + r.nextFloat() * (6f)),
                    (-3f + r.nextFloat() * (6f)),
                    (-3f + r.nextFloat() * (6f)));
            player.level.addParticle(ParticleTypes.SMOKE, player.getX(), player.getY()+player.getEyeHeight()*1.5, player.getZ(),
                    (-3f + r.nextFloat() * (6f)),
                    (-3f + r.nextFloat() * (6f)),
                    (-3f + r.nextFloat() * (6f)));
        }
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        if (!world.isClientSide) {
            // Gets the item that the player is holding, should be this item
            final ItemStack stack = player.getItemInHand(hand);
            final int id = GeckoLibUtil.guaranteeIDForStack(stack, (ServerLevel) world);
            // Tell all nearby clients to trigger this Item
            final PacketDistributor.PacketTarget target = PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> player);
            GeckoLibNetwork.syncAnimation(target, this, id, ANIM_OPEN);
        }
        return super.use(world, player, hand);
    }

    @Override
    public void onAnimationSync(int id, int state) {
        if (state == ANIM_OPEN) {
            // Always use GeckoLibUtil to get AnimationControllers when you don't have
            // access to an AnimationEvent
            final AnimationController controller = GeckoLibUtil.getControllerForID(this.factory, id, CONTROLLER_NAME);

            if (controller.getAnimationState() == AnimationState.Stopped) {
                final LocalPlayer player = Minecraft.getInstance().player;
                if (player != null) {
                    player.displayClientMessage(new TextComponent("Fueling Item...").withStyle(ChatFormatting.DARK_BLUE,ChatFormatting.ITALIC), true);
                }
                // If you don't do this, the popup animation will only play once because the
                // animation will be cached.
                controller.markNeedsReload();
                // eventually do the actual animation. Also sets it to not loop
                controller.setAnimation(new AnimationBuilder().addAnimation(useAnimationName, ILoopType.EDefaultLoopTypes.PLAY_ONCE));
            }
        }
    }
}