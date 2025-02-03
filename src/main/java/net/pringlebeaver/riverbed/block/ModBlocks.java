package net.pringlebeaver.riverbed.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PlaceOnWaterBlockItem;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.pringlebeaver.riverbed.RiverbedMain;
import net.pringlebeaver.riverbed.block.custom.*;
import net.pringlebeaver.riverbed.item.ModItems;
import net.pringlebeaver.riverbed.item.custom.AlgaeBottleItem;
import net.pringlebeaver.riverbed.item.custom.ModFuelBlockItem;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, RiverbedMain.MOD_ID);


    //Blocks

    //River Stones

    public static final RegistryObject<Block> RIVER_STONES = registerBlock("river_stones",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE).sound(SoundType.TUFF)));

    public static final RegistryObject<Block> RIVER_STONE_SLAB = registerBlock("river_stone_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE).sound(SoundType.TUFF)));

    public static final RegistryObject<Block> RIVER_STONE_STAIRS = registerBlock("river_stone_stairs",
            () -> new StairBlock(() -> ModBlocks.RIVER_STONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.COBBLESTONE).sound(SoundType.TUFF)));



    // River Bricks
    public static final RegistryObject<Block> RIVER_STONE_BRICKS = registerBlock("river_stone_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS).sound(SoundType.NETHER_BRICKS)));

    public static final RegistryObject<Block> CHISELED_RIVER_STONE = registerBlock("chiseled_river_stone",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS).sound(SoundType.NETHER_BRICKS)));

    public static final RegistryObject<Block> RIVER_STONE_BRICK_SLAB = registerBlock("river_stone_brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS).sound(SoundType.NETHER_BRICKS)));

    public static final RegistryObject<Block> RIVER_STONE_BRICK_STAIRS = registerBlock("river_stone_brick_stairs",
            () -> new StairBlock(() -> ModBlocks.RIVER_STONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS).sound(SoundType.NETHER_BRICKS)));

    public static final RegistryObject<Block> RIVER_STONE_BRICK_WALL = registerBlock("river_stone_brick_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS).sound(SoundType.NETHER_BRICKS)));


    public static final RegistryObject<Block> ALGAL_RIVER_STONE_BRICKS = registerBlock("algal_river_stone_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS).sound(SoundType.NETHER_BRICKS)));

    public static final RegistryObject<Block> ALGAL_RIVER_STONE_BRICK_SLAB = registerBlock("algal_river_stone_brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS).sound(SoundType.NETHER_BRICKS)));

    public static final RegistryObject<Block> ALGAL_RIVER_STONE_BRICK_STAIRS = registerBlock("algal_river_stone_brick_stairs",
            () -> new StairBlock(() -> ModBlocks.RIVER_STONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS).sound(SoundType.NETHER_BRICKS)));

    public static final RegistryObject<Block> ALGAL_RIVER_STONE_BRICK_WALL = registerBlock("algal_river_stone_brick_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS).sound(SoundType.NETHER_BRICKS)));

    // Algae
    public static final RegistryObject<Block> ALGAE = registerBlock("algae",
            () -> new AlgaeBlock(BlockBehaviour.Properties.copy(Blocks.MOSS_CARPET).sound(SoundType.WET_GRASS).noCollission()));

    public static final RegistryObject<Block> ALGAE_BLOCK = registerBlock("algae_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.SPONGE).sound(SoundType.WET_GRASS)));

    public static final RegistryObject<Block> SURFACE_FILM = registerBlock("surface_film",
            () -> new SurfaceFilmBlock(BlockBehaviour.Properties.copy(Blocks.LILY_PAD).sound(SoundType.HONEY_BLOCK).noCollission()));
    // Plants

    public static final RegistryObject<Block> RIVER_GRASS = registerBlock("river_grass",
            () -> new RivergrassBlock(BlockBehaviour.Properties.copy(Blocks.SMALL_DRIPLEAF).sound(SoundType.WET_GRASS)));

    public static final RegistryObject<Block> REED_GRASS = registerBlock("reed_grass",
            () -> new RivergrassBlock(BlockBehaviour.Properties.copy(Blocks.SMALL_DRIPLEAF).sound(SoundType.WET_GRASS)));

    public static final RegistryObject<Block> DRY_RIVER_GRASS = registerBlock("dry_river_grass",
            () -> new RivergrassBlock(BlockBehaviour.Properties.copy(Blocks.SMALL_DRIPLEAF).sound(SoundType.GRASS)));

    public static final RegistryObject<Block> WATER_HYACINTH = registerBlock("water_hyacinth",
            () -> new WaterHyacinthBlock(BlockBehaviour.Properties.copy(Blocks.DANDELION).sound(SoundType.WET_GRASS)));

    public static final RegistryObject<Block> POTTED_HYACINTH = BLOCKS.register("potted_hyacinth",
            () -> new FlowerPotBlock((() -> (FlowerPotBlock) Blocks.FLOWER_POT), WATER_HYACINTH ,BlockBehaviour.Properties.copy(Blocks.POTTED_DANDELION)));


    // Woven Grass

    public static final RegistryObject<Block> WOVEN_GRASS_BLOCK = registerBlock("woven_grass_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.SPONGE).sound(SoundType.AZALEA_LEAVES)));

    public static final RegistryObject<Block> WOVEN_GRASS_MAT = registerBlock("woven_grass_mat",
            () -> new CarpetBlock(BlockBehaviour.Properties.copy(Blocks.SPONGE).sound(SoundType.AZALEA_LEAVES)));

    // Misc

    public static final RegistryObject<Block> TWIG_PILE = registerBlock("twig_pile",
            () -> new MangroveRootsBlock(BlockBehaviour.Properties.copy(Blocks.MANGROVE_ROOTS).sound(SoundType.GRASS)));

    // Special

    public static final RegistryObject<Block> GRASS_BASKET = registerBlock("grass_basket",
            () -> new GrassBasketBlock(BlockBehaviour.Properties.copy(Blocks.DIRT).sound(SoundType.AZALEA_LEAVES).noOcclusion()));


    public static final RegistryObject<Block> BEAVER_NEST = registerBlock("beaver_nest",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.WOOD)));



    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }
    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block){
        switch (name) {
            case "algae_block":
                return ModItems.ITEMS.register(name, () -> new ModFuelBlockItem(block.get(), new Item.Properties(), 200));

            case "water_hyacinth":
                return ModItems.ITEMS.register(name, () -> new PlaceOnWaterBlockItem(block.get(), new Item.Properties()));

            case "surface_film":
                return null;

            case "grass_basket":
                return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().stacksTo(1)));


        }

        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
