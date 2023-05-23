package com.mattborle.cupsaddons.item.tool;

import com.mattborle.cupsaddons.client.renderer.item.GodsPickaxeRenderer;
import com.mattborle.cupsaddons.init.ItemRegistry;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.IItemRenderProperties;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.List;
import java.util.function.Consumer;

public class GodsPickaxeItem extends PickaxeItem implements IAnimatable {

    public AnimationFactory factory = GeckoLibUtil.createFactory(this);


    // Constructor and Usage ===========================================================================================

    public GodsPickaxeItem(Tier tier, int p_42962_, float p_42963_, Properties properties) {
        super(tier, p_42962_, p_42963_, properties
                .tab(ItemRegistry.CreativeTab.instance)     // Show in creative menu tab
                .rarity(Rarity.EPIC)                        // Rarity color for title and loot beams
                .fireResistant()                            // Cannot be destroyed in lava
                .durability(-1)                     // Durability of -1 means unbreakable
        );
    }
    @Override
    public void appendHoverText(ItemStack stack, Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        int uses = 0;
        String playerName = "";
        if(stack.getTag() != null){
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
        tooltip.add(new TranslatableComponent("tooltip.cupsaddons.tool_unbreakable"));
        tooltip.add(new TextComponent(""));
        tooltip.add(new TextComponent("§8Awarded to §6§o"+playerName));
        tooltip.add(new TextComponent("§8Mined §6§o"+uses+"§r§8 blocks"));
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
    public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity entity) {
        if(!level.isClientSide){
            // Increase uses nbt
            if (stack.getTag() != null) {
                if (stack.getTag().get("cupsaddons.uses") != null) {
                    if (stack.getTag().get("cupsaddons.uses").getAsString() != null) {
                        int uses = Integer.parseInt(stack.getTag().get("cupsaddons.uses").getAsString());
                        stack.getTag().remove("cupsaddons.uses");
                        CompoundTag nbtData = stack.getTag();
                        nbtData.putInt("cupsaddons.uses", uses + 1);
                        entity.getMainHandItem().setTag(nbtData);
                    }
                }
            }
        }
        return super.mineBlock(stack, level, state, pos, entity);
    }

    // Animation and Model =============================================================================================

    @Override
    public void initializeClient(Consumer<IItemRenderProperties> consumer) {
        super.initializeClient(consumer);
        consumer.accept(new IItemRenderProperties() {
            private final BlockEntityWithoutLevelRenderer renderer = new GodsPickaxeRenderer();

            @Override
            public BlockEntityWithoutLevelRenderer getItemStackRenderer() {
                return renderer;
            }
        });
    }
    @Override
    public void registerControllers(AnimationData data) {

    }
    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
}
