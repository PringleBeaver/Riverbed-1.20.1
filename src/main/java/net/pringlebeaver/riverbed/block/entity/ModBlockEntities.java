package net.pringlebeaver.riverbed.block.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.pringlebeaver.riverbed.RiverbedMain;
import net.pringlebeaver.riverbed.block.ModBlocks;
import org.jetbrains.annotations.Nullable;

public class ModBlockEntities {


    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, RiverbedMain.MOD_ID);

    public static final RegistryObject<BlockEntityType<GrassBasketBlockEntity>> GRASS_BASKET_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("grass_basket_block_entity", () -> BlockEntityType.Builder.of(GrassBasketBlockEntity::new, ModBlocks.GRASS_BASKET.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }

}
