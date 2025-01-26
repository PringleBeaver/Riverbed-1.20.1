package net.pringlebeaver.riverbed.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;
import net.pringlebeaver.riverbed.RiverbedMain;

import java.rmi.registry.Registry;

public class ModTags {

    public static class Blocks {
        public static final TagKey<Block> RIVER_GRASS_PLACEABLE = tag("river_grass_placeable");
        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(RiverbedMain.MOD_ID, name));
        }
    }
    public static class Items {


    }
    public static class Biomes {

    }

}
