package net.pringlebeaver.riverbed.world.biome;

import net.minecraft.resources.ResourceLocation;
import net.pringlebeaver.riverbed.RiverbedMain;
import net.pringlebeaver.riverbed.world.biome.custom.ModOverworldRegion;
import terrablender.api.Regions;

public class ModTerrablenderAPI {
    public static void registerRegions() {
        Regions.register(new ModOverworldRegion(new ResourceLocation(RiverbedMain.MOD_ID, "overworld"), 1));
    }
}
