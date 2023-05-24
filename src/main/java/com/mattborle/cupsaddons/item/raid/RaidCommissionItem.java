package com.mattborle.cupsaddons.item.raid;

import com.mattborle.cupsaddons.init.ItemRegistry;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.Random;

public class RaidCommissionItem extends Item {

    int baseReward = 80000; // Base value used to calculate rewards. an Easy raid with no bad_omens.
    float modMult = 1.3f;   // Reward multiplier for every bad_omen present.
    float goodModMult = 0.85f;// Reward multiplier for every good_omen present.
    float difMult = 2.1f;   // Reward multiplier for every difficulty level above easy.

    public RaidCommissionItem() {
        super(new Properties().tab(ItemRegistry.CreativeTab.instance)
                .stacksTo(1)
                .rarity(Rarity.UNCOMMON));
    }


    @Override
    public void appendHoverText(ItemStack stack, Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        int difficulty =-1; // Difficulty saved as int, (0,1,2),(Easy,Normal,Hard)
        int good_omen1 = -1; // good_omen saved as int, -1 means no good_omen.
        int good_omen2 = -1; // good_omen saved as int, -1 means no good_omen.
        int good_omen3 = -1; // good_omen saved as int, -1 means no good_omen.
        int bad_omen1 = -1; // bad_omen saved as int, -1 means no bad_omen.
        int bad_omen2 = -1; // bad_omen saved as int, -1 means no bad_omen, (0,1,2, etc),(Thick Skin, Speed Demons, Mending Monsters, Low Gravity)
        int bad_omen3 = -1; // bad_omen saved as int, -1 means no bad_omen.
        int reward = -1;    // Reward money saved as int, -1 means something went wrong.
        int location = -1;  // Location saved as int, -1 means something went wrong.
        if(stack.getTag() != null){
            // Get difficulty nbt and put it in difficulty variable if exists
            if(stack.getTag().get("cupsaddons.difficulty") != null) {
                if (stack.getTag().get("cupsaddons.difficulty").getAsString() != null) {
                    difficulty = Integer.parseInt(stack.getTag().get("cupsaddons.difficulty").getAsString());
                }
            }
            // Get location nbt and put it in location variable if exists
            if(stack.getTag().get("cupsaddons.location") != null) {
                if (stack.getTag().get("cupsaddons.location").getAsString() != null) {
                    location = Integer.parseInt(stack.getTag().get("cupsaddons.location").getAsString());
                }
            }
            // Get good_omen1 nbt and put it in good_omen1 variable if exists
            if(stack.getTag().get("cupsaddons.good_omen1") != null) {
                if (stack.getTag().get("cupsaddons.good_omen1").getAsString() != null) {
                    good_omen1 = Integer.parseInt(stack.getTag().get("cupsaddons.good_omen1").getAsString());
                }
            }
            // Get good_omen2 nbt and put it in good_omen2 variable if exists
            if(stack.getTag().get("cupsaddons.good_omen2") != null) {
                if (stack.getTag().get("cupsaddons.good_omen2").getAsString() != null) {
                    good_omen2 = Integer.parseInt(stack.getTag().get("cupsaddons.good_omen2").getAsString());
                }
            }
            // Get good_omen3 nbt and put it in good_omen3 variable if exists
            if(stack.getTag().get("cupsaddons.good_omen3") != null) {
                if (stack.getTag().get("cupsaddons.good_omen3").getAsString() != null) {
                    good_omen3 = Integer.parseInt(stack.getTag().get("cupsaddons.good_omen3").getAsString());
                }
            }
            // Get bad_omen1 nbt and put it in bad_omen1 variable if exists
            if(stack.getTag().get("cupsaddons.bad_omen1") != null) {
                if (stack.getTag().get("cupsaddons.bad_omen1").getAsString() != null) {
                    bad_omen1 = Integer.parseInt(stack.getTag().get("cupsaddons.bad_omen1").getAsString());
                }
            }
            // Get bad_omen2 nbt and put it in bad_omen2 variable if exists
            if(stack.getTag().get("cupsaddons.bad_omen2") != null) {
                if (stack.getTag().get("cupsaddons.bad_omen2").getAsString() != null) {
                    bad_omen2 = Integer.parseInt(stack.getTag().get("cupsaddons.bad_omen2").getAsString());
                }
            }
            // Get bad_omen3 nbt and put it in bad_omen3 variable if exists
            if(stack.getTag().get("cupsaddons.bad_omen3") != null) {
                if (stack.getTag().get("cupsaddons.bad_omen3").getAsString() != null) {
                    bad_omen3 = Integer.parseInt(stack.getTag().get("cupsaddons.bad_omen3").getAsString());
                }
            }
            // Get reward nbt and put it in reward variable if exists
            if(stack.getTag().get("cupsaddons.reward") != null) {
                if (stack.getTag().get("cupsaddons.reward").getAsString() != null) {
                    reward = Integer.parseInt(stack.getTag().get("cupsaddons.reward").getAsString());
                }
            }
        }
        // Set difficulty text:
        if(Screen.hasControlDown()) {
            switch (difficulty) {
                case 0:
                    tooltip.add(new TextComponent("§e§oEasy (☠)§r"));
                    break;
                case 1:
                    tooltip.add(new TextComponent("§e§oNormal (☠ ☠)§r"));
                    break;
                case 2:
                    tooltip.add(new TextComponent("§e§oHard (☠ ☠ ☠)§r"));
                    break;
                default:
                    tooltip.add(new TextComponent("§4§o§k?a?b?c?§r"));
                    break;
            }
        }else{
            switch (difficulty) {
                case 0:
                    tooltip.add(new TextComponent("§e§o                  ☠§r"));
                    break;
                case 1:
                    tooltip.add(new TextComponent("§e§o                 ☠ ☠§r"));
                    break;
                case 2:
                    tooltip.add(new TextComponent("§e§o               ☠ ☠ ☠§r"));
                    break;
                default:
                    tooltip.add(new TextComponent("§4§o§k?a?b?c?§r"));
                    break;
            }
        }
        // Set location text:
        tooltip.add(new TextComponent("§7Location §m⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯§r"));
        if(Screen.hasControlDown()) {
            switch(location)
            {
                case 0:
                    tooltip.add(new TextComponent("§e§oDeep within a §6Chasm §eon§r"));
                    tooltip.add(new TextComponent("§e§othe western region of the§r"));
                    tooltip.add(new TextComponent("§e§oold world.§r"));
                    break;
                case 1:
                    tooltip.add(new TextComponent("§e§oThe §6Streets §eof the old city§r"));
                    tooltip.add(new TextComponent("§e§owithin the western region of§r"));
                    tooltip.add(new TextComponent("§e§othe old world.§r"));
                    break;
                default:
                    tooltip.add(new TextComponent("§4§o§k?a?b?c?§r"));
                    break;
            }
        }
        else
        {
            switch(location)
            {
                case 0:
                    tooltip.add(new TextComponent("§o§6Chasm§r"));
                    break;
                case 1:
                    tooltip.add(new TextComponent("§o§6Streets§r"));
                    break;
                default:
                    tooltip.add(new TextComponent("§4§o§k?a?b?c?§r"));
                    break;
            }
        }


        if(Screen.hasControlDown()) {
            // Spacer
            tooltip.add(new TextComponent(""));
            // Set good_omen text:
            if(good_omen1 > 0 || good_omen2 > 0 || good_omen3 > 0){
                tooltip.add(new TextComponent("§7Good Omens §m⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯§r"));
                // 0 = Thick Skin (tough monsters)
                if(good_omen1 == 0 || good_omen2 == 0 || good_omen3 == 0)
                {
                    tooltip.add(new TextComponent("§7- §a§oMonster HP reduced§r"));
                }
                // 1 = Speed Demons (fast monsters)
                if(good_omen1 == 1 || good_omen2 == 1 || good_omen3 == 1)
                {
                    tooltip.add(new TextComponent("§7- §a§oMonster Speed reduced§r"));
                }
                // 2 = Mending Monsters (healing monsters)
                if(good_omen1 == 2 || good_omen2 == 2 || good_omen3 == 2)
                {
                    tooltip.add(new TextComponent("§7- §a§oPlayer HP regeneration§r"));
                }
                // 3 = Other
                if(good_omen1 == 3 || good_omen2 == 3 || good_omen3 == 3)
                {
                    tooltip.add(new TextComponent("§7- §a§oKnockback reduced§r"));
                }
                // Spacer
                tooltip.add(new TextComponent(""));
            }
            // Set bad_omen text:
            if(bad_omen1 > 0 || bad_omen2 > 0 || bad_omen3 > 0){
                tooltip.add(new TextComponent("§7Bad Omens §m⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯§r"));
                // 0 = Thick Skin (tough monsters)
                if(bad_omen1 == 0 || bad_omen2 == 0 || bad_omen3 == 0)
                {
                    tooltip.add(new TextComponent("§7- §c§oMonster HP increased§r"));
                }
                // 1 = Speed Demons (fast monsters)
                if(bad_omen1 == 1 || bad_omen2 == 1 || bad_omen3 == 1)
                {
                    tooltip.add(new TextComponent("§7- §c§oMonster Speed increased§r"));
                }
                // 2 = Mending Monsters (healing monsters)
                if(bad_omen1 == 2 || bad_omen2 == 2 || bad_omen3 == 2)
                {
                    tooltip.add(new TextComponent("§7- §c§oMonster HP regeneration§r"));
                }
                // 3 = Other
                if(bad_omen1 == 3 || bad_omen2 == 3 || bad_omen3 == 3)
                {
                    tooltip.add(new TextComponent("§7- §c§oKnockback increased§r"));
                }
                // Spacer
                tooltip.add(new TextComponent(""));
            }
        }
        else
        {
            // Spacer
            tooltip.add(new TextComponent(""));
            // Set good_omen text:
            if(good_omen1 > 0 || good_omen2 > 0 || good_omen3 > 0){
                tooltip.add(new TextComponent("§7Good Omens §m⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯§r"));
                // 0 = Thick Skin (tough monsters)
                if(good_omen1 == 0 || good_omen2 == 0 || good_omen3 == 0)
                {
                    tooltip.add(new TextComponent("§7- §a§oWeak Monsters§r"));
                }
                // 1 = Speed Demons (fast monsters)
                if(good_omen1 == 1 || good_omen2 == 1 || good_omen3 == 1)
                {
                    tooltip.add(new TextComponent("§7- §a§oSlow Monsters§r"));
                }
                // 2 = Mending Monsters (healing monsters)
                if(good_omen1 == 2 || good_omen2 == 2 || good_omen3 == 2)
                {
                    tooltip.add(new TextComponent("§7- §a§oMending Players§r"));
                }
                // 3 = Other
                if(good_omen1 == 3 || good_omen2 == 3 || good_omen3 == 3)
                {
                    tooltip.add(new TextComponent("§7- §a§oPlanted Feet§r"));
                }
                // Spacer
                tooltip.add(new TextComponent(""));
            }
            // Set bad_omen text:
            if(bad_omen1 > 0 || bad_omen2 > 0 || bad_omen3 > 0){
                tooltip.add(new TextComponent("§7Bad Omens §m⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯§r"));
                // 0 = Thick Skin (tough monsters)
                if(bad_omen1 == 0 || bad_omen2 == 0 || bad_omen3 == 0)
                {
                    tooltip.add(new TextComponent("§7- §c§oTough Monsters§r"));
                }
                // 1 = Speed Demons (fast monsters)
                if(bad_omen1 == 1 || bad_omen2 == 1 || bad_omen3 == 1)
                {
                    tooltip.add(new TextComponent("§7- §c§oQuick Monsters§r"));
                }
                // 2 = Mending Monsters (healing monsters)
                if(bad_omen1 == 2 || bad_omen2 == 2 || bad_omen3 == 2)
                {
                    tooltip.add(new TextComponent("§7- §c§oMending Monsters§r"));
                }
                // 3 = Other
                if(bad_omen1 == 3 || bad_omen2 == 3 || bad_omen3 == 3)
                {
                    tooltip.add(new TextComponent("§7- §c§oHeavy Hitters§r"));
                }
                // Spacer
                tooltip.add(new TextComponent(""));
            }
        }


        // Set Reward text:
        tooltip.add(new TextComponent("§7Reward: §r§e"+String.format("%,d",reward)+"¢§r"));
        // Spacer
        tooltip.add(new TextComponent(""));
        tooltip.add(new TranslatableComponent("tooltip.cupsaddons.raid_commission_warning_1"));
        tooltip.add(new TranslatableComponent("tooltip.cupsaddons.raid_commission_warning_2"));
        // Spacer
        tooltip.add(new TextComponent(""));
        // Details
        if(Screen.hasControlDown()) {
            tooltip.add(new TranslatableComponent("tooltip.cupsaddons.raid_commission_desc_1"));
            tooltip.add(new TranslatableComponent("tooltip.cupsaddons.raid_commission_desc_2"));
            tooltip.add(new TranslatableComponent("tooltip.cupsaddons.raid_commission_desc_3"));
            tooltip.add(new TranslatableComponent("tooltip.cupsaddons.raid_commission_desc_4"));
            tooltip.add(new TranslatableComponent("tooltip.cupsaddons.raid_commission_desc_5"));
            tooltip.add(new TranslatableComponent("tooltip.cupsaddons.raid_commission_desc_6"));
            tooltip.add(new TextComponent(""));
            tooltip.add(new TranslatableComponent("tooltip.cupsaddons.consumed_on_use"));
        }else{
            tooltip.add(new TranslatableComponent("tooltip.cupsaddons.hold_ctrl_for_details"));
        }
    }


    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int p_41407_, boolean p_41408_) {
        // On the server, generate NBT data once when the item hits an inventory.
        if(!level.isClientSide){
            if(stack.getTag() == null){

                // Define these for calculating rewards
                int difficulty = randomInt(0,2);
                int location = randomInt(0,1);
                int good_omen1 = randomInt(-12,3);
                int good_omen2 = randomInt(-12,3);
                int good_omen3 = randomInt(-12,3);
                int bad_omen1 = randomInt(-12,3); // 0 is the first bad_omen, but we make the range wider such that there
                int bad_omen2 = randomInt(-12,3); // is a lower chance of there being a bad_omen in each slot. Anything
                int bad_omen3 = randomInt(-12,3); // less than 0 is treated as no bad_omen, so this works.
                double reward = baseReward;
                stack.setTag(new CompoundTag());
                CompoundTag nbtData = stack.getTag();
                nbtData.putInt("cupsaddons.difficulty", difficulty);
                nbtData.putInt("cupsaddons.location", location);
                nbtData.putInt("cupsaddons.good_omen1", good_omen1);
                nbtData.putInt("cupsaddons.good_omen2", good_omen2);
                nbtData.putInt("cupsaddons.good_omen3", good_omen3);
                nbtData.putInt("cupsaddons.bad_omen1", bad_omen1);
                nbtData.putInt("cupsaddons.bad_omen2", bad_omen2);
                nbtData.putInt("cupsaddons.bad_omen3", bad_omen3);

                // Reward calc:
                Random r = new Random();
                double randomValue = 0.8 + (1.1 - 0.9) * r.nextDouble();
                reward = reward*randomValue;
                if(difficulty >= 1)
                    reward = (int)(reward*difMult);
                if(difficulty >= 2)
                    reward = (int)(reward*difMult);

                if(good_omen1 > -1)
                    reward = (int)(reward*goodModMult);
                if(good_omen2 > -1 && good_omen2 != good_omen1)
                    reward = (int)(reward*goodModMult);
                if(good_omen3 > -1 && good_omen3 != good_omen1 && good_omen3 != good_omen2)
                    reward = (int)(reward*goodModMult);

                if(bad_omen1 > -1)
                    reward = (int)(reward*modMult);
                if(bad_omen2 > -1 && bad_omen2 != bad_omen1)
                    reward = (int)(reward*modMult);
                if(bad_omen3 > -1 && bad_omen3 != bad_omen1 && bad_omen3 != bad_omen2)
                    reward = (int)(reward*modMult);
                // Put reward value in nbt after casting it to an int to avoid cents.
                nbtData.putInt("cupsaddons.reward", (int)reward);
                stack.setTag(nbtData);
            }
        }
        super.inventoryTick(stack, level, entity, p_41407_, p_41408_);
    }




    // Helpers =========================================================================================================

    // Return a random int in range, inclusive.
    public int randomInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max+1 - min) + min;
    }
}
