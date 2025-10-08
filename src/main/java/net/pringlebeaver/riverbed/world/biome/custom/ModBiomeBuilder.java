package net.pringlebeaver.riverbed.world.biome.custom;

import com.mojang.datafixers.util.Pair;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import net.pringlebeaver.riverbed.world.biome.ModBiomes;
import terrablender.api.ParameterUtils;

import java.util.function.Consumer;

// This river biome implementation is fully based on Climate Rivers and also River Redux, full credit goes to Fuzs for providing the original code, and Jaskarth for inspiring it.
// Please see: https://github.com/jaskarth/RiverRedux/blob/master/src/main/java/supercoder79/riverredux/mixin/MixinOverworldBiomeBuilder.java, and https://github.com/Fuzss/climaterivers/blob/main/1.20.1/Common/src/main/java/fuzs/climaterivers/mixin/OverworldBiomeBuilderMixin.java
public class ModBiomeBuilder {
    static final ParameterUtils.Temperature[] TEMPERATURES = {
            ParameterUtils.Temperature.ICY,
            ParameterUtils.Temperature.COOL,
            ParameterUtils.Temperature.NEUTRAL,
            ParameterUtils.Temperature.WARM,
            ParameterUtils.Temperature.HOT
    };
    static final ParameterUtils.Humidity[] HUMIDITIES = {
            ParameterUtils.Humidity.ARID,
            ParameterUtils.Humidity.DRY,
            ParameterUtils.Humidity.NEUTRAL,
            ParameterUtils.Humidity.WET,
            ParameterUtils.Humidity.HUMID,
    };
    static final ResourceKey<Biome>[][] UNFROZEN_RIVERS = new ResourceKey[][]{
            {null, null, null, null, null},
            {Biomes.RIVER, Biomes.RIVER, Biomes.RIVER, ModBiomes.COLD_RIVER, ModBiomes.COLD_RIVER},
            {Biomes.RIVER, Biomes.RIVER, Biomes.RIVER, Biomes.RIVER, Biomes.RIVER},
            {ModBiomes.ARID_RIVER, ModBiomes.ARID_RIVER, Biomes.RIVER, ModBiomes.BLOOMING_RIVER, ModBiomes.BLOOMING_RIVER},
            {ModBiomes.ARID_RIVER, ModBiomes.ARID_RIVER, ModBiomes.ARID_RIVER, ModBiomes.ARID_RIVER, ModBiomes.ARID_RIVER}
    };

    public static void addRivers(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper, Climate.Parameter continentalness, Climate.Parameter erosion, Climate.Parameter weirdness, float offset) {
        for (int temperature = 0; temperature < TEMPERATURES.length; temperature++) {
            for (int humidity = 0; humidity < HUMIDITIES.length; humidity++) {
                ResourceKey<Biome> biome = UNFROZEN_RIVERS[temperature][humidity];
                if (biome != null) {
                    addSurfaceBiome(mapper,
                            TEMPERATURES[temperature].parameter(),
                            HUMIDITIES[humidity].parameter(),
                            continentalness,
                            erosion,
                            weirdness,
                            offset,
                            biome);
                }
            }
        }
    }

    static void addSurfaceBiome(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper, Climate.Parameter temperature, Climate.Parameter humidity, Climate.Parameter continentalness, Climate.Parameter erosion, Climate.Parameter weirdness, float offset, ResourceKey<Biome> biome) {
        mapper.accept(Pair.of(Climate.parameters(temperature,
                humidity,
                continentalness,
                erosion,
                ParameterUtils.Depth.SURFACE.parameter(),
                weirdness,
                offset), biome));
        mapper.accept(Pair.of(Climate.parameters(temperature,
                humidity,
                continentalness,
                erosion,
                ParameterUtils.Depth.FLOOR.parameter(),
                weirdness,
                offset), biome));
    }
}
