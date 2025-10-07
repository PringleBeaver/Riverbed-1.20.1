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
                        pOutput.accept(ModBlocks.RIVER_STONE_TILES.get());
                        pOutput.accept(ModBlocks.RIVER_STONE_TILE_STAIRS.get());
                        pOutput.accept(ModBlocks.RIVER_STONE_TILE_SLAB.get());
                        pOutput.accept(ModBlocks.RIVER_STONE_TILE_WALL.get());

                        pOutput.accept(ModBlocks.ALGAE_BLOCK.get());

                        pOutput.accept(ModBlocks.ALGAE.get());


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

    public static final RegistryObject<CreativeModeTab> S_RIVERBED_BASKET_TAB = CREATIVE_MOD_TABS.register("s_riverbed_basket_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.STRIPE_BASKET_FRAGMENT.get()))
                    .title(Component.translatable("creativetab.riverbed_basket_tab"))
                    .displayItems((pParameters, pOutput) -> {

                        pOutput.accept(ModItems.STRIPE_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.DOTS_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.DOUBLE_STRIPE_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.ZIGZAG_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.SOLID_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.RED_STRIPE_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.RED_DOTS_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.RED_DOUBLE_STRIPE_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.RED_ZIGZAG_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.RED_SOLID_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.ORANGE_STRIPE_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.ORANGE_DOTS_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.ORANGE_DOUBLE_STRIPE_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.ORANGE_ZIGZAG_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.ORANGE_SOLID_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.YELLOW_STRIPE_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.YELLOW_DOTS_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.YELLOW_DOUBLE_STRIPE_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.YELLOW_ZIGZAG_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.YELLOW_SOLID_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.LIME_STRIPE_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.LIME_DOTS_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.LIME_DOUBLE_STRIPE_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.LIME_ZIGZAG_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.LIME_SOLID_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.GREEN_STRIPE_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.GREEN_DOTS_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.GREEN_DOUBLE_STRIPE_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.GREEN_ZIGZAG_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.GREEN_SOLID_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.CYAN_STRIPE_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.CYAN_DOTS_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.CYAN_DOUBLE_STRIPE_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.CYAN_ZIGZAG_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.CYAN_SOLID_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.LIGHT_BLUE_STRIPE_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.LIGHT_BLUE_DOTS_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.LIGHT_BLUE_DOUBLE_STRIPE_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.LIGHT_BLUE_ZIGZAG_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.LIGHT_BLUE_SOLID_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.BLUE_STRIPE_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.BLUE_DOTS_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.BLUE_DOUBLE_STRIPE_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.BLUE_ZIGZAG_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.BLUE_SOLID_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.PURPLE_STRIPE_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.PURPLE_DOTS_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.PURPLE_DOUBLE_STRIPE_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.PURPLE_ZIGZAG_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.PURPLE_SOLID_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.MAGENTA_STRIPE_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.MAGENTA_DOTS_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.MAGENTA_DOUBLE_STRIPE_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.MAGENTA_ZIGZAG_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.MAGENTA_SOLID_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.PINK_STRIPE_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.PINK_DOTS_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.PINK_DOUBLE_STRIPE_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.PINK_ZIGZAG_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.PINK_SOLID_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.BROWN_STRIPE_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.BROWN_DOTS_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.BROWN_DOUBLE_STRIPE_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.BROWN_ZIGZAG_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.BROWN_SOLID_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.BLACK_STRIPE_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.BLACK_DOTS_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.BLACK_DOUBLE_STRIPE_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.BLACK_ZIGZAG_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.BLACK_SOLID_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.GRAY_STRIPE_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.GRAY_DOTS_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.GRAY_DOUBLE_STRIPE_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.GRAY_ZIGZAG_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.GRAY_SOLID_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.LIGHT_GRAY_STRIPE_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.LIGHT_GRAY_DOTS_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.LIGHT_GRAY_DOUBLE_STRIPE_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.LIGHT_GRAY_ZIGZAG_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.LIGHT_GRAY_SOLID_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.WHITE_STRIPE_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.WHITE_DOTS_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.WHITE_DOUBLE_STRIPE_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.WHITE_ZIGZAG_BASKET_FRAGMENT.get());
                        pOutput.accept(ModItems.WHITE_SOLID_BASKET_FRAGMENT.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MOD_TABS.register(eventBus);
    }
}
