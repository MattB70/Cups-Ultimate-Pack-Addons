package com.mattborle.cupsaddons.init;

import com.mattborle.cupsaddons.CupsAddons;
import com.mattborle.cupsaddons.world.feature.tree.OreTreeGrower;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;


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
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG))
    );
    public static final RegistryObject<Block> IRON_OAK_LOG = MOD_BLOCKS.register(
            "iron_oak_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG))
    );
    public static final RegistryObject<Block> COPPER_OAK_LOG = MOD_BLOCKS.register(
            "copper_oak_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG))
    );
    public static final RegistryObject<Block> ZINC_OAK_LOG = MOD_BLOCKS.register(
            "zinc_oak_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG))
    );
    public static final RegistryObject<Block> RICH_OAK_LOG = MOD_BLOCKS.register(
            "rich_oak_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG))
    );



    public static void register(IEventBus eventBus) {
        MOD_BLOCKS.register(eventBus);
    }

    // Register blocks as items
    @SubscribeEvent
    public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
        final IForgeRegistry<Item> registry = event.getRegistry();
        MOD_BLOCKS.getEntries().stream().map(RegistryObject::get).forEach( (block) -> {
            final Item.Properties properties = new Item.Properties().tab(ItemRegistry.CreativeTab.instance);
            final BlockItem blockItem = new BlockItem(block, properties);
            blockItem.setRegistryName(block.getRegistryName());
            registry.register(blockItem);
        });
    }
}
