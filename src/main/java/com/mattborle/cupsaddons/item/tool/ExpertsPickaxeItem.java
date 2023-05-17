package com.mattborle.cupsaddons.item.tool;

import com.mattborle.cupsaddons.client.renderer.item.ExpertsPickaxeRenderer;
import com.mattborle.cupsaddons.config.CupsAddonsCommonConfigs;
import com.mattborle.cupsaddons.init.ItemRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.IItemRenderProperties;
import org.apache.commons.lang3.text.WordUtils;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.network.GeckoLibNetwork;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.List;
import java.util.function.Consumer;

public class ExpertsPickaxeItem extends PickaxeItem implements IAnimatable {

    public AnimationFactory factory = GeckoLibUtil.createFactory(this);
    public String playerName = "undefined"; // Name of the first player to receive the item. Placeholder declared.
    public String timesUsed = "0";          // Number of times the item has been used. Placeholder declared.


    // Constructor and Usage ===========================================================================================

    public ExpertsPickaxeItem(Tier tier, int p_42962_, float p_42963_, Properties properties) {
        super(tier, p_42962_, p_42963_, properties
                .tab(ItemRegistry.CreativeTab.instance)     // Show in creative menu tab
                .rarity(Rarity.COMMON)                        // Rarity color for title and loot beams
                .fireResistant()                            // Cannot be destroyed in lava
                .durability(-1)                     // Durability of -1 means unbreakable
        );
    }
    @Override
    public void appendHoverText(ItemStack stack, Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        tooltip.add(new TextComponent("Awarded to "+" "+playerName+"."));
        tooltip.add(new TextComponent("Used "+" "+timesUsed+" times."));
    }
    @Override
    public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {
        return super.onEntityItemUpdate(stack, entity);
    }


    // Animation and Model =============================================================================================

    @Override
    public void initializeClient(Consumer<IItemRenderProperties> consumer) {
        super.initializeClient(consumer);
        consumer.accept(new IItemRenderProperties() {
            private final BlockEntityWithoutLevelRenderer renderer = new ExpertsPickaxeRenderer();

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
