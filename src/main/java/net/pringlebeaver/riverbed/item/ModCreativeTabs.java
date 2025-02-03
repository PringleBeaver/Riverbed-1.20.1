package net.pringlebeaver.riverbed.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.pringlebeaver.riverbed.RiverbedMain;
import net.pringlebeaver.riverbed.block.ModBlocks;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MOD_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, RiverbedMain.MOD_ID);

    public static final RegistryObject<CreativeModeTab> RIVERBED_TAB = CREATIVE_MOD_TABS.register("riverbed_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.TROUT.get()))
                    .title(Component.translatable("creativetab.riverbed_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        

                        pOutput.accept(ModBlocks.RIVER_STONES.get());
                        pOutput.accept(ModBlocks.RIVER_STONE_STAIRS.get());
                        pOutput.accept(ModBlocks.RIVER_STONE_SLAB.get());

                        pOutput.accept(ModBlocks.CHISELED_RIVER_STONE.get());
                        pOutput.accept(ModBlocks.RIVER_STONE_BRICKS.get());
                        pOutput.accept(ModBlocks.RIVER_STONE_BRICK_STAIRS.get());
                        pOutput.accept(ModBlocks.RIVER_STONE_BRICK_SLAB.get());
                        pOutput.accept(ModBlocks.RIVER_STONE_BRICK_WALL.get());
                        pOutput.accept(ModBlocks.ALGAL_RIVER_STONE_BRICKS.get());
                        pOutput.accept(ModBlocks.ALGAL_RIVER_STONE_BRICK_STAIRS.get());
                        pOutput.accept(ModBlocks.ALGAL_RIVER_STONE_BRICK_SLAB.get());
                        pOutput.accept(ModBlocks.ALGAL_RIVER_STONE_BRICK_WALL.get());

                        pOutput.accept(ModBlocks.ALGAE_BLOCK.get());

                        pOutput.accept(ModBlocks.ALGAE.get());
                        pOutput.accept(ModBlocks.TWIG_PILE.get());
                        pOutput.accept(ModBlocks.BEAVER_NEST.get());


                        pOutput.accept(ModBlocks.WATER_HYACINTH.get());
                        pOutput.accept(ModBlocks.RIVER_GRASS.get());
                        pOutput.accept(ModBlocks.REED_GRASS.get());

                        pOutput.accept(ModBlocks.DRY_RIVER_GRASS.get());

                        pOutput.accept(ModBlocks.WOVEN_GRASS_BLOCK.get());
                        pOutput.accept(ModBlocks.WOVEN_GRASS_MAT.get());


                        pOutput.accept(ModBlocks.GRASS_BASKET.get());


                        pOutput.accept(ModItems.WOVEN_GRASS.get());



                        pOutput.accept(ModItems.TROUT.get());
                        pOutput.accept(ModItems.COOKED_TROUT.get());
                        pOutput.accept(ModItems.FISH_CHOWDER.get());

                        pOutput.accept(ModItems.HYACINTH_MILK_BUCKET.get());

                        pOutput.accept(ModItems.TROUT_BUCKET.get());

                        pOutput.accept(ModItems.MUSIC_DISC_DELTA.get());

                        pOutput.accept(ModItems.TROUT_SPAWN_EGG.get());
                        pOutput.accept(ModItems.MANATEE_SPAWN_EGG.get());





                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MOD_TABS.register(eventBus);
    }
}
