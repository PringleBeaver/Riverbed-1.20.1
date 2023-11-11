package net.pringlebeaver.riverbed.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.pringlebeaver.riverbed.RiverbedMain;

public class ModTags {

    public static class Blocks {
        public static final TagKey<Block> RIVER_GRASS_PLACEABLE = tag("river_grass_placeable");

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(RiverbedMain.MOD_ID, name));
        }
    }
    public static class Items {

    }
}
