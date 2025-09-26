package net.pringlebeaver.riverbed.world.biome.custom;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.*;
import net.pringlebeaver.riverbed.world.biome.ModBiomes;
import terrablender.api.*;

import java.awt.*;
import java.util.List;
import java.util.function.Consumer;

public class ModOverworldRegion extends Region {
    public ModOverworldRegion(ResourceLocation name, int weight)
    {
        super(name, RegionType.OVERWORLD, weight);
    }
    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
/*
        this.addModifiedVanillaOverworldBiomes(mapper, builder -> {
            builder.replaceBiome(Biomes.RIVER, ModBiomes.LUSH_RIVER);
        });

         List<Climate.ParameterPoint> LushRiverParameters = new ParameterUtils.ParameterPointListBuilder().temperature(ParameterUtils.Temperature.HOT)
               .humidity(ParameterUtils.Humidity.HUMID)
              .continentalness(ParameterUtils.Continentalness.span(ParameterUtils.Continentalness.COAST, ParameterUtils.Continentalness.FAR_INLAND), ParameterUtils.Continentalness.span(ParameterUtils.Continentalness.MID_INLAND, ParameterUtils.Continentalness.FAR_INLAND))
               .erosion(ParameterUtils.Erosion.span(ParameterUtils.Erosion.EROSION_2, ParameterUtils.Erosion.EROSION_5))
              .depth(ParameterUtils.Depth.SURFACE, ParameterUtils.Depth.FLOOR)
             .weirdness(ParameterUtils.Weirdness.VALLEY)
             .build();



        LushRiverParameters.forEach(point -> addBiome(mapper, point, ModBiomes.LUSH_RIVER)); */
    }

}
