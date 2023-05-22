package com.mattborle.cupsaddons.item.raid;

import com.mattborle.cupsaddons.init.ItemRegistry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.Random;

public class RaidCommissionItem extends Item {

    int baseReward = 50000; // Base value used to calculate rewards. an Easy raid with no modifiers.
    float modMult = 1.3f;   // Reward multiplier for every modifier present.
    float difMult = 2.2f;   // Reward multiplier for every difficulty level above easy.

    public RaidCommissionItem() {
        super(new Properties().tab(ItemRegistry.CreativeTab.instance)
                .stacksTo(1)
                .rarity(Rarity.UNCOMMON));
    }


    @Override
    public void appendHoverText(ItemStack stack, Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        int difficulty =-1; // Difficulty saved as int, (0,1,2),(Easy,Normal,Hard)
        int modifier1 = -1; // Modifier saved as int, -1 means no modifier.
        int modifier2 = -1; // Modifier saved as int, -1 means no modifier, (0,1,2, etc),(Thick Skin, Speed Demons, Mending Monsters, Low Gravity)
        int modifier3 = -1; // Modifier saved as int, -1 means no modifier.
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
            // Get modifier1 nbt and put it in modifier1 variable if exists
            if(stack.getTag().get("cupsaddons.modifier1") != null) {
                if (stack.getTag().get("cupsaddons.modifier1").getAsString() != null) {
                    modifier1 = Integer.parseInt(stack.getTag().get("cupsaddons.modifier1").getAsString());
                }
            }
            // Get modifier2 nbt and put it in modifier2 variable if exists
            if(stack.getTag().get("cupsaddons.modifier2") != null) {
                if (stack.getTag().get("cupsaddons.modifier2").getAsString() != null) {
                    modifier2 = Integer.parseInt(stack.getTag().get("cupsaddons.modifier2").getAsString());
                }
            }
            // Get modifier3 nbt and put it in modifier3 variable if exists
            if(stack.getTag().get("cupsaddons.modifier3") != null) {
                if (stack.getTag().get("cupsaddons.modifier3").getAsString() != null) {
                    modifier3 = Integer.parseInt(stack.getTag().get("cupsaddons.modifier3").getAsString());
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
        switch(difficulty)
        {
            case 0:
                tooltip.add(new TextComponent("§a§oEasy§r"));
                break;
            case 1:
                tooltip.add(new TextComponent("§e§oNormal§r"));
                break;
            case 2:
                tooltip.add(new TextComponent("§6§oHard§r"));
                break;
            default:
                tooltip.add(new TextComponent("§4§o§k?a?b?c?§r"));
                break;
        }
        // Set location text:
        tooltip.add(new TextComponent("§7Location §m⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯§r"));
        switch(location)
        {
            case 0:
                tooltip.add(new TextComponent("§e§oDeep within a §6Chasm within§r"));
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
        // Set Modifier text:
        tooltip.add(new TextComponent("§7Omens §m⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯§r"));
        // 0 = Thick Skin (tough monsters)
        if(modifier1 == 0 || modifier2 == 0 || modifier3 == 0)
        {
            tooltip.add(new TextComponent("§7- §6§oThick Skin§r"));
        }
        // 1 = Speed Demons (fast monsters)
        if(modifier1 == 1 || modifier2 == 1 || modifier3 == 1)
        {
            tooltip.add(new TextComponent("§7- §3§oSpeed Demons§r"));
        }
        // 2 = Mending Monsters (healing monsters)
        if(modifier1 == 2 || modifier2 == 2 || modifier3 == 2)
        {
            tooltip.add(new TextComponent("§7- §c§oMending Monsters§r"));
        }
        // 3 = Other
        if(modifier1 == 3 || modifier2 == 3 || modifier3 == 3)
        {
            tooltip.add(new TextComponent("§7- §b§oLow Gravity§r"));
        }
        tooltip.add(new TextComponent("§7§m⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯§r"));
        // Set Reward text:
        tooltip.add(new TextComponent("§7Reward: §r§e"+String.format("%,d",reward)+"¢§r"));
        tooltip.add(new TextComponent("§7§m⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯§r"));
    }


    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int p_41407_, boolean p_41408_) {
        // On the server, generate NBT data once when the item hits an inventory.
        if(!level.isClientSide){
            if(stack.getTag() == null){

                // Define these for calculating rewards
                int difficulty = randomInt(0,2);
                int location = randomInt(0,1);
                int modifier1 = randomInt(-1,3);
                int modifier2 = randomInt(-1,3);
                int modifier3 = randomInt(-1,3);
                double reward = baseReward;
                stack.setTag(new CompoundTag());
                CompoundTag nbtData = stack.getTag();
                nbtData.putInt("cupsaddons.difficulty", difficulty);
                nbtData.putInt("cupsaddons.location", location);
                nbtData.putInt("cupsaddons.modifier1", modifier1);
                nbtData.putInt("cupsaddons.modifier2", modifier2);
                nbtData.putInt("cupsaddons.modifier3", modifier3);

                // Reward calc:
                Random r = new Random();
                double randomValue = 0.8 + (1.1 - 0.9) * r.nextDouble();
                reward = reward*randomValue;
                if(difficulty >= 1)
                    reward = (int)(reward*difMult);
                if(difficulty >= 2)
                    reward = (int)(reward*difMult);
                if(modifier1 > -1)
                    reward = (int)(reward*modMult);
                if(modifier2 > -1 && modifier2 != modifier1)
                    reward = (int)(reward*modMult);
                if(modifier3 > -1 && modifier3 != modifier1 && modifier3 != modifier2)
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
