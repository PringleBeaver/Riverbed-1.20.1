package net.pringlebeaver.riverbed.world.biome.custom;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.*;
import net.pringlebeaver.riverbed.world.biome.ModBiomes;
import terrablender.api.ModifiedVanillaOverworldBuilder;
import terrablender.api.ParameterUtils;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.awt.*;
import java.util.List;
import java.util.function.Consumer;

public class ModOverworldRegion extends Region {
    public ModOverworldRegion(ResourceLocation name, int weight) {
        super(name, RegionType.OVERWORLD, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        this.addModifiedVanillaOverworldBiomes(mapper, builder -> {


           //builder.replaceBiome(Biomes.RIVER, ModBiomes.LUSH_RIVER);

           // List<Climate.ParameterPoint> lushRiverPoints = new ParameterUtils.ParameterPointListBuilder()
            //       .build();

         //   lushRiverPoints.forEach(point -> addBiome(mapper, point, ModBiomes.LUSH_RIVER));

        });
    }

}
