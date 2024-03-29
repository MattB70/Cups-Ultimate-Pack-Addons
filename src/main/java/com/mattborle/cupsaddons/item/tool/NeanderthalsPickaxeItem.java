package com.mattborle.cupsaddons.item.tool;

import com.mattborle.cupsaddons.client.renderer.item.NeanderthalsPickaxeRenderer;
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
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.IItemRenderProperties;
import net.minecraftforge.common.Tags;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.registries.ForgeRegistries;
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


public class NeanderthalsPickaxeItem extends PickaxeItem implements IAnimatable, ISyncable {
    /*
        NeanderthalsPickaxe or Neanderthal's Pickaxe

        Reward:
        A reward item is rewarded to the player for completion of a quest, task, purchased, or otherwise rewarded
        to the player through modpack defined methods. These items may not always be unique, but that can never be
        discovered outside completing the task required by the modpack designer.
    */

    // Functionality Variables
    public String fuelItem = CupsAddonsCommonConfigs.FUEL_NEANDERTHALS_PICKAXE.get();
    public int maxFuel = CupsAddonsCommonConfigs.MAX_FUEL_NEANDERTHALS_PICKAXE.get();   // Max fuel, typically the number of uses an item has before needing to be refueled

    // Animation Variables
    public String IDLE_ANIMATION_NAME = "animation.neanderthals_pickaxe.idle";
    public String USE_ANIMATION_NAME = "animation.neanderthals_pickaxe.use";
    private static final String CONTROLLER_NAME = "useController";
    private static final int ANIM_USE = 0;
    public AnimationFactory factory = GeckoLibUtil.createFactory(this);




    // Constructor and Usage ===========================================================================================

    public NeanderthalsPickaxeItem(Tier tier, int p_42962_, float p_42963_, Properties properties) {
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
        int fuel = 0;
        int uses = 0;
        String playerName = "";

        if(stack.getTag() != null){
            // Get fuel nbt and put it in fuel variable if exists
            if(stack.getTag().get("cupsaddons.fuel") != null) {
                if (stack.getTag().get("cupsaddons.fuel").getAsString() != null) {
                    fuel = Integer.parseInt(stack.getTag().get("cupsaddons.fuel").getAsString());
                }
            }
            // Get uses nbt and put it in uses variable if exists
            if(stack.getTag().get("cupsaddons.uses") != null) {
                if (stack.getTag().get("cupsaddons.uses").getAsString() != null) {
                    uses = Integer.parseInt(stack.getTag().get("cupsaddons.uses").getAsString());
                }
            }
            // Get playerName nbt and put it in playerName variable if exists
            if(stack.getTag().get("cupsaddons.playerName") != null) {
                if (stack.getTag().get("cupsaddons.playerName").getAsString() != null) {
                    playerName = stack.getTag().get("cupsaddons.playerName").getAsString();
                }
            }
        }
        // Display fuel tooltip
        tooltip.add(new TextComponent("Fuel: "+fuel+"/"+maxFuel+ " ("+ WordUtils.capitalize(fuelItem.replace("_", " "))+")")
                .withStyle(ChatFormatting.BLUE));
        if(Screen.hasControlDown()){
            tooltip.add(new TranslatableComponent("tooltip.cupsaddons.neanderthals_pickaxe_description"));
            if(Screen.hasAltDown()){
                tooltip.add(new TranslatableComponent("tooltip.cupsaddons.neanderthals_pickaxe_description_extra"));
            }else{
                tooltip.add(new TranslatableComponent("tooltip.cupsaddons.hold_ctrl_alt_for_details"));
            }
        }else{
            tooltip.add(new TranslatableComponent("tooltip.cupsaddons.hold_ctrl_for_details"));
        }
        tooltip.add(new TextComponent("§8Awarded to §6§o"+playerName));
        tooltip.add(new TextComponent("§8Mined §6§o"+uses+"§r§8 blocks"));
        tooltip.add(new TranslatableComponent("tooltip.cupsaddons.fuel_item_instructions"));
    }
    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int p_41407_, boolean p_41408_) {
        if(!level.isClientSide){
            if(stack.getTag() == null){
                // Set playerName and uses nbt if it doesn't exist, if it does exist, skip all of this.
                stack.setTag(new CompoundTag());
                CompoundTag nbtData = stack.getTag();
                nbtData.putString("cupsaddons.playerName", entity.getScoreboardName());
                nbtData.putString("cupsaddons.uses", "0");
                stack.setTag(nbtData);
            }
        }
        super.inventoryTick(stack, level, entity, p_41407_, p_41408_);
    }
    @Override
    public boolean mineBlock(ItemStack stack, Level level, BlockState blockState, BlockPos blockPos, LivingEntity livingEntity) {
        // Increment uses counter
        if (stack.getTag().get("cupsaddons.uses") != null) {
            if (stack.getTag().get("cupsaddons.uses").getAsString() != null) {
                int uses = Integer.parseInt(stack.getTag().get("cupsaddons.uses").getAsString());
                stack.getTag().remove("cupsaddons.uses");
                CompoundTag nbtData = stack.getTag();
                nbtData.putInt("cupsaddons.uses", uses + 1);
                livingEntity.getMainHandItem().setTag(nbtData);
            }
        }
        // Check if the mined block is of Tag STONE or ORE
        if (blockState.is(Tags.Blocks.ORE_BEARING_GROUND_STONE) ||
                blockState.is(Tags.Blocks.ORE_BEARING_GROUND_NETHERRACK) ||
                blockState.is(Tags.Blocks.ORE_BEARING_GROUND_DEEPSLATE) ||
                blockState.is(Tags.Blocks.ORES_IN_GROUND_STONE) ||
                blockState.is(Tags.Blocks.ORES_IN_GROUND_DEEPSLATE) ||
                blockState.is(Tags.Blocks.ORES_IN_GROUND_NETHERRACK)) {
            // Check if the item has fuel and do something special if it does.
            if (stack.getTag() != null) {
                if (stack.getTag().get("cupsaddons.fuel") != null) {
                    if (stack.getTag().get("cupsaddons.fuel").getAsString() != null) {

                        // display fuel level:
                        int fuel = Integer.parseInt(stack.getTag().get("cupsaddons.fuel").getAsString());
                        displayFuelLevel(fuel); // display fuel

                        if (Integer.parseInt(stack.getTag().get("cupsaddons.fuel").getAsString()) > 0) {

                            // Play sound on client
                            Minecraft.getInstance().player.playSound(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("cupsaddons:magic_pop")), 0.2f, (float)((Math.random() * (1.6 - 1.25)) + 1.25));

                            if (level instanceof ServerLevel serverLevel) {
                                // On the server side:

                                // Special action
                                ItemEntity droppedIron = new ItemEntity(serverLevel, blockPos.getX() + 0.5, blockPos.getY() + 0.5, blockPos.getZ() + 0.5, new ItemStack(Items.RAW_IRON));
                                serverLevel.addFreshEntity(droppedIron);
                                ItemEntity droppedCoal = new ItemEntity(serverLevel, blockPos.getX() + 0.5, blockPos.getY() + 0.5, blockPos.getZ() + 0.5, new ItemStack(Items.COAL));
                                serverLevel.addFreshEntity(droppedCoal);
                                // One extra gold per fortune level:
                                for(int i = 0; i < EnchantmentHelper.getItemEnchantmentLevel(Enchantments.BLOCK_FORTUNE,stack); i++) {
                                    droppedIron = new ItemEntity(serverLevel, blockPos.getX() + 0.5, blockPos.getY() + 0.5, blockPos.getZ() + 0.5, new ItemStack(Items.RAW_IRON));
                                    serverLevel.addFreshEntity(droppedIron);
                                    droppedCoal = new ItemEntity(serverLevel, blockPos.getX() + 0.5, blockPos.getY() + 0.5, blockPos.getZ() + 0.5, new ItemStack(Items.COAL));
                                    serverLevel.addFreshEntity(droppedCoal);
                                }
                                // Reduce the item's fuel by 1.
                                fuel = Integer.parseInt(stack.getTag().get("cupsaddons.fuel").getAsString());
                                stack.getTag().remove("cupsaddons.fuel");
                                CompoundTag nbtData = stack.getTag();
                                nbtData.putInt("cupsaddons.fuel", fuel - 1);
                                livingEntity.getMainHandItem().setTag(nbtData);

                                // Display particles on server side
                                serverLevel.sendParticles(ParticleTypes.CRIT, blockPos.getX()+0.5,blockPos.getY()+0.5,blockPos.getZ()+0.5,5,0,0,0,0.5);

                                // If stone type block, don't return a stack as to ensure no stone is dropped.
                                if (blockState.is(Tags.Blocks.ORE_BEARING_GROUND_STONE) ||
                                        blockState.is(Tags.Blocks.ORE_BEARING_GROUND_NETHERRACK) ||
                                        blockState.is(Tags.Blocks.ORE_BEARING_GROUND_DEEPSLATE)) {
                                    serverLevel.removeBlock(blockPos, true);
                                    return super.mineBlock(null, serverLevel, blockState, blockPos, livingEntity);
                                }
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
            private final BlockEntityWithoutLevelRenderer renderer = new NeanderthalsPickaxeRenderer();

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
        // Idle animation controller, delay for the return to idle smoothness.
        data.addAnimationController(new AnimationController(this, "idle", 1, this::idleAnimationPredicate));
    }

    private <ENTITY extends IAnimatable> void soundListener(SoundKeyframeEvent<ENTITY> event) {
        // At the sound keyframe:
    }
    private <ENTITY extends IAnimatable> void particleListener(ParticleKeyFrameEvent<ENTITY> event) {
        // At the particle keyframe:
        LocalPlayer player = Minecraft.getInstance().player;
        if (player != null) {
            // Text
            displayFuelLevel(maxFuel); // display max fuel
            // Sound
            player.playSound(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("cupsaddons:magic_pop")), 0.9f, 1.0f);
            player.playSound(SoundEvents.TOTEM_USE, 0.1f, 1.4f);
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
        if (!(mainHandItem.toString().equals("neanderthals_pickaxe") && offHandItem.toString().equals(CupsAddonsCommonConfigs.FUEL_NEANDERTHALS_PICKAXE.get()))) {
            return super.use(world, player, hand);
        }
        // Return if item already fully fueled
        if(player.getMainHandItem().getTag() != null) {
            if (player.getMainHandItem().getTag().get("cupsaddons.fuel") != null) {
                if (player.getMainHandItem().getTag().get("cupsaddons.fuel").getAsString() != null) {
                    if (Integer.parseInt(player.getMainHandItem().getTag().get("cupsaddons.fuel").getAsString()) == maxFuel) {
                        displayFuelLevel(maxFuel); // display max fuel
                        return super.use(world, player, hand);
                    }
                }
            }
        }

        // Remove one item from the offhand stack
        player.getOffhandItem().setCount(player.getOffhandItem().getCount()-1);

        // Check if nbt data already exists
        if(player.getMainHandItem().hasTag()) {
            // Get existing nbt and set the fuel to max.
            player.getMainHandItem().getTag().remove("cupsaddons.fuel"); // Remove fuel entry
            CompoundTag nbtData = player.getMainHandItem().getTag(); // Get nbt
            nbtData.putInt("cupsaddons.fuel", maxFuel); // Set fuel entry to maxFuel and put
            player.getMainHandItem().setTag(nbtData); // Set nbt
        }else{
            // Make a compound tag and set our fuel data in it.
            CompoundTag nbtData = new CompoundTag(); // Create new nbt
            nbtData.putInt("cupsaddons.fuel", maxFuel); // Set fuel entry to maxFuel and put
            player.getMainHandItem().setTag(nbtData); // Set nbt
        }

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
                // It's okay to cache the animation, so this may be commented out.
                controller.markNeedsReload();
                // eventually do the actual animation. Also sets it to not loop
                controller.setAnimation(new AnimationBuilder().addAnimation(USE_ANIMATION_NAME, ILoopType.EDefaultLoopTypes.PLAY_ONCE));
            }
        }
    }





    // Helpers =========================================================================================================

    // Call this every time the fuel level changes, or if that is too often, when it makes sense.
    private void displayFuelLevel(int currentFuel){
        LocalPlayer player = Minecraft.getInstance().player;
        if(player == null) return;

        if(currentFuel == maxFuel){ // If the item is fully fueled:
            player.displayClientMessage(new TextComponent("Fuel: FULL").withStyle(ChatFormatting.GREEN), true);
            return;
        }
        if(currentFuel == 0){       // If the item fuel is empty
            player.displayClientMessage(new TextComponent("Fuel: EMPTY").withStyle(ChatFormatting.RED), true);
            player.playSound(SoundEvents.GENERIC_BURN, 0.1f, 1.6f); // play out of fuel sound
            return;
        }
        // else, display fuel as a percentage
        player.displayClientMessage(new TextComponent("Fuel: "+(int)(((float)currentFuel/(float)maxFuel)*100)+"%").withStyle(ChatFormatting.GOLD), true);
    }

}