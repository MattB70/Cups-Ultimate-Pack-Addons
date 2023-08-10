package com.mattborle.cupsaddons.init;

import com.mattborle.cupsaddons.CupsAddons;
import com.mattborle.cupsaddons.block.MetallicLeavesBlock;
import com.mattborle.cupsaddons.block.RichLeavesBlock;
import com.mattborle.cupsaddons.block.RotatedPillarOreBlock;
import com.mattborle.cupsaddons.item.generic.GlowingBlockItem;
import com.mattborle.cupsaddons.world.feature.tree.OreTreeGrower;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.ToIntFunction;


@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BlockRegistry {

    // Block registry
    public static final DeferredRegister<Block> MOD_BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, CupsAddons.MOD_ID);

    // Ore Trees =======================================================================================================
    // Saplings
    public static final RegistryObject<Block> GOLDEN_OAK_SAPLING = MOD_BLOCKS.register(
            "golden_oak_sapling",
            () -> new SaplingBlock(new OreTreeGrower("gold"),
                    BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING))
    );
    public static final RegistryObject<Block> IRON_OAK_SAPLING = MOD_BLOCKS.register(
            "iron_oak_sapling",
            () -> new SaplingBlock(new OreTreeGrower("iron"),
                    BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING))
    );
    public static final RegistryObject<Block> COPPER_OAK_SAPLING = MOD_BLOCKS.register(
            "copper_oak_sapling",
            () -> new SaplingBlock(new OreTreeGrower("copper"),
                    BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING))
    );
    public static final RegistryObject<Block> ZINC_OAK_SAPLING = MOD_BLOCKS.register(
            "zinc_oak_sapling",
            () -> new SaplingBlock(new OreTreeGrower("zinc"),
                    BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING))
    );
    public static final RegistryObject<Block> RICH_OAK_SAPLING = MOD_BLOCKS.register(
            "rich_oak_sapling",
            () -> new SaplingBlock(new OreTreeGrower("rich"),
                    BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING))
    );
    // Logs
    public static final RegistryObject<Block> GOLDEN_OAK_LOG = MOD_BLOCKS.register(
            "golden_oak_log",
            () -> new RotatedPillarOreBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG))
    );
    public static final RegistryObject<Block> IRON_OAK_LOG = MOD_BLOCKS.register(
            "iron_oak_log",
            () -> new RotatedPillarOreBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG))
    );
    public static final RegistryObject<Block> COPPER_OAK_LOG = MOD_BLOCKS.register(
            "copper_oak_log",
            () -> new RotatedPillarOreBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG))
    );
    public static final RegistryObject<Block> ZINC_OAK_LOG = MOD_BLOCKS.register(
            "zinc_oak_log",
            () -> new RotatedPillarOreBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG))
    );
    public static final RegistryObject<Block> RICH_OAK_LOG = MOD_BLOCKS.register(
            "rich_oak_log",
            () -> new RotatedPillarOreBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG))
    );
    // Leaves
    public static final RegistryObject<Block> METALLIC_OAK_LEAVES = MOD_BLOCKS.register(
            "metallic_oak_leaves",
            () -> new MetallicLeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES))
    );
    public static final RegistryObject<Block> RICH_OAK_LEAVES = MOD_BLOCKS.register(
            "rich_oak_leaves",
            () -> new RichLeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES))
    );


    public static void register(IEventBus eventBus) {
        MOD_BLOCKS.register(eventBus);
    }


    // Register blocks as items
    @SubscribeEvent
    public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
        final IForgeRegistry<Item> registry = event.getRegistry();
        MOD_BLOCKS.getEntries().stream().map(RegistryObject::get).forEach( (block) -> {
            // Rich blocks
            if(block.getRegistryName().toString().contains("rich_"))
            {
                final Item.Properties properties = new Item.Properties().tab(ItemRegistry.CreativeTab.instance);
                final GlowingBlockItem glowingBlockItem = new GlowingBlockItem(block, properties);
                glowingBlockItem.setRegistryName(block.getRegistryName());
                registry.register(glowingBlockItem);
            }
            else
            // Normal blocks
            {
                final Item.Properties properties = new Item.Properties().tab(ItemRegistry.CreativeTab.instance);
                final BlockItem blockItem = new BlockItem(block, properties);
                blockItem.setRegistryName(block.getRegistryName());
                registry.register(blockItem);
            }
        });
    }
}
