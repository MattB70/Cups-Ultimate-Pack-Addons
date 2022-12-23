package com.mattborle.cupsaddons.item;

import com.mattborle.cupsaddons.client.renderer.item.ChrysophilistsPickaxeRenderer;
import com.mattborle.cupsaddons.config.CupsAddonsCommonConfigs;
import com.mattborle.cupsaddons.init.ItemRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.IItemRenderProperties;
import net.minecraftforge.common.Tags;
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

    /*  TODO:   Re-write, especially nested if statements, and create a implementable class out of this. Something like
        TODO:   a "FuelableItem" so it can be applied to new items easily.
    */

    /*
        ChrysophilistsPickaxe or Chrysophilist's Pickaxe

        Unique:
        A unique item is rewarded to the player for completion of a quest, task, purchased, or otherwise rewarded
        to the player through modpack defined methods. These items may not always be totally "unique", but that can
        never be discovered outside completing the task required by the modpack designer.
    */


    // Functionality Variables
    public String fuelItem = CupsAddonsCommonConfigs.FUEL_CHRYSOPHILISTS_PICKAXE.get();
    public int maxFuel = CupsAddonsCommonConfigs.MAX_FUEL_CHRYSOPHILISTS_PICKAXE.get();   // Max fuel, typically the number of uses an item has before needing to be refueled


    // Animation Variables
    public String IDLE_ANIMATION_NAME = "animation.chrysophilists_pickaxe.idle";
    public String USE_ANIMATION_NAME = "animation.chrysophilists_pickaxe.use";
    private static final String CONTROLLER_NAME = "useController";
    private static final int ANIM_USE = 0;
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
            int fuel = 0;
            // Get fuel nbt and put it in fuel variable if exists
            if(stack.getTag() != null){
                if(stack.getTag().get("cupsaddons.fuel") != null) {
                    if (stack.getTag().get("cupsaddons.fuel").getAsString() != null) {
                        fuel = Integer.parseInt(stack.getTag().get("cupsaddons.fuel").getAsString());
                    }
                }
            }
            // Display fuel tooltip
            tooltip.add(new TextComponent("Fuel: "+fuel+"/"+maxFuel+ " ("+ WordUtils.capitalize(fuelItem.replace("_", " "))+")")
                    .withStyle(ChatFormatting.BLUE));
            if(Screen.hasAltDown()){
                tooltip.add(new TranslatableComponent("tooltip.cupsaddons.chrysophilists_pickaxe_description"));
                tooltip.add(new TranslatableComponent("tooltip.cupsaddons.fuel_item_instructions"));
            }else{
                tooltip.add(new TranslatableComponent("tooltip.cupsaddons.hold_alt_for_details"));
            }
            tooltip.add(new TranslatableComponent("tooltip.cupsaddons.reward_item"));
        }
    }
    @Override
    public boolean mineBlock(ItemStack stack, Level level, BlockState blockState, BlockPos blockPos, LivingEntity livingEntity) {
        // Check if the mined block is of Tag STONE or ORE
        if (blockState.is(Tags.Blocks.STONE) || blockState.is(Tags.Blocks.ORES)) {
            // Check if the item has fuel and do something special if it does.
            if (stack.getTag() != null) {
                if (stack.getTag().get("cupsaddons.fuel") != null) {
                    if (stack.getTag().get("cupsaddons.fuel").getAsString() != null) {
                        if (Integer.parseInt(stack.getTag().get("cupsaddons.fuel").getAsString()) > 0) {
                            if (!level.isClientSide) {
                                // On the server side:

                                // Special action
                                ItemEntity droppedItem = new ItemEntity(level, blockPos.getX() + 0.5, blockPos.getY() + 0.5, blockPos.getZ() + 0.5, new ItemStack(Items.RAW_GOLD));
                                level.addFreshEntity(droppedItem);

                                // Reduce the item's fuel by 1.
                                int fuel = Integer.parseInt(stack.getTag().get("cupsaddons.fuel").getAsString());
                                CompoundTag nbtData = new CompoundTag();
                                nbtData.putInt("cupsaddons.fuel", fuel - 1);
                                livingEntity.getMainHandItem().setTag(nbtData);
                            }
                        }
                    }
                }
            }
        }
        return super.mineBlock(stack, level, blockState, blockPos, livingEntity);
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

    private <E extends IAnimatable> PlayState idleAnimationPredicate(AnimationEvent<E> event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation(IDLE_ANIMATION_NAME, ILoopType.EDefaultLoopTypes.LOOP));
        return PlayState.CONTINUE;
    }
    private <P extends Item & IAnimatable> PlayState useAnimationPredicate(AnimationEvent<P> event) {
        // Not setting an animation here as that's handled below
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        AnimationController controller = new AnimationController(this, CONTROLLER_NAME, 1, this::useAnimationPredicate);
        // Registering a sound listener just makes it so when any sound keyframe is hit
        // the method will be called.
        // To register a particle listener or custom event listener you do the exact
        // same thing, just with registerParticleListener and
        // registerCustomInstructionListener, respectively.
        controller.registerSoundListener(this::soundListener);
        controller.registerParticleListener(this::particleListener);
        data.addAnimationController(controller);
        // Idle animation controller
        data.addAnimationController(new AnimationController(this, "idle", 1, this::idleAnimationPredicate));
    }

    private <ENTITY extends IAnimatable> void soundListener(SoundKeyframeEvent<ENTITY> event) {
        // At the sound keyframe:
    }
    private <ENTITY extends IAnimatable> void particleListener(ParticleKeyFrameEvent<ENTITY> event) {
        // At the particle keyframe:
        LocalPlayer player = Minecraft.getInstance().player;
        if (player != null) {
            player.playSound(SoundEvents.TOTEM_USE, 0.7f, 1.5f);
            // Particles
            for(int i = 0; i < 20; i++){
                Random r = new Random();
                player.level.addParticle(ParticleTypes.ENCHANT, player.getX(), player.getY()+player.getEyeHeight()*1.5, player.getZ(),
                        (-3f + r.nextFloat() * (6f)),
                        (-3f + r.nextFloat() * (6f)),
                        (-3f + r.nextFloat() * (6f)));
                player.level.addParticle(ParticleTypes.SMOKE, player.getX(), player.getY()+player.getEyeHeight(), player.getZ(),
                        (-0.5f + r.nextFloat() * (1f)),
                        (-0.5f + r.nextFloat() * (1f)),
                        (-0.5f + r.nextFloat() * (1f)));
            }
        }
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        // Return if client side
        if (world.isClientSide) {
            return super.use(world, player, hand);
        }

        // Here we check if the item can be used/fueled on the server side, the client side player animation is
        // handled in UseSpecialItem.java and is similar.
        Item mainHandItem = player.getMainHandItem().getItem();
        Item offHandItem = player.getOffhandItem().getItem();
        // Return if not correct item pair
        if (!(mainHandItem.toString().equals("chrysophilists_pickaxe") && offHandItem.toString().equals(CupsAddonsCommonConfigs.FUEL_CHRYSOPHILISTS_PICKAXE.get()))) {
            return super.use(world, player, hand);
        }
        // Return if item already fully fueled
        if(player.getMainHandItem().getTag() != null) {
            if (player.getMainHandItem().getTag().get("cupsaddons.fuel") != null) {
                if (player.getMainHandItem().getTag().get("cupsaddons.fuel").getAsString() != null) {
                    if (Integer.parseInt(player.getMainHandItem().getTag().get("cupsaddons.fuel").getAsString()) == maxFuel) {
                        player.displayClientMessage(new TextComponent("Already Fully Fueled").withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.RED), true);
                        return super.use(world, player, hand);
                    }
                }
            }
        }

        // Remove one item from the offhand stack
        player.getOffhandItem().setCount(player.getOffhandItem().getCount()-1);

        // Make a compound tag and set our fuel data in it.
        CompoundTag nbtData = new CompoundTag();
        nbtData.putInt("cupsaddons.fuel", maxFuel);
        player.getMainHandItem().setTag(nbtData);

        // Animation Sync:
        // Gets the itemStack that the player is holding.
        final ItemStack stack = player.getItemInHand(hand);
        final int id = GeckoLibUtil.guaranteeIDForStack(stack, (ServerLevel) world);
        // Tell all nearby clients to trigger this itemStack.
        final PacketDistributor.PacketTarget target = PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> player);
        GeckoLibNetwork.syncAnimation(target, this, id, ANIM_USE);

        return super.use(world, player, hand);
    }

    @Override
    public void onAnimationSync(int id, int state) {
        if (state == ANIM_USE) {
            // Always use GeckoLibUtil to get AnimationControllers when you don't have
            // access to an AnimationEvent
            final AnimationController controller = GeckoLibUtil.getControllerForID(this.factory, id, CONTROLLER_NAME);

            if (controller.getAnimationState() == AnimationState.Stopped) {
                final LocalPlayer player = Minecraft.getInstance().player;
                if (player != null) {
                    player.displayClientMessage(new TextComponent("Fueling Item...").withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.GOLD), true);
                }
                // It's okay to cache the animation, so this may be commented out.
                // controller.markNeedsReload();
                // eventually do the actual animation. Also sets it to not loop
                controller.setAnimation(new AnimationBuilder().addAnimation(USE_ANIMATION_NAME, ILoopType.EDefaultLoopTypes.PLAY_ONCE));
            }
        }
    }
}