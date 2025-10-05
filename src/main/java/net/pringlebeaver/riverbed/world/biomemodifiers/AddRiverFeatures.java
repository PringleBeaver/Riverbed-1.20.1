package net.pringlebeaver.riverbed.world.biomemodifiers;

import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;

import java.util.Objects;

public record AddRiverFeatures(Holder<PlacedFeature> feature, GenerationStep.Decoration step, String variant) implements BiomeModifier {
    
        boolean hasTemperateRiver(Holder<Biome> biome) {
            return (   biome.is(Biomes.RIVER)
                    || biome.is(BiomeTags.IS_FOREST)
                    || biome.is(Tags.Biomes.IS_PLAINS)
                    || biome.is(Tags.Biomes.IS_CONIFEROUS)
                    )
                    &&
                    (
                            !biome.is(Tags.Biomes.IS_SNOWY)
                            && biome.is(BiomeTags.IS_OVERWORLD)
                            && !biome.is(Tags.Biomes.IS_UNDERGROUND)
                            && !biome.is(Tags.Biomes.IS_HOT)
                            && !biome.is(Tags.Biomes.IS_SANDY)
                            && !biome.is(BiomeTags.IS_BEACH)
                    );
        }

    boolean hasAridRiver(Holder<Biome> biome) {
        return
                ( biome.is(Tags.Biomes.IS_DRY_OVERWORLD)
                || biome.is(BiomeTags.IS_SAVANNA) )
                && biome.is(BiomeTags.IS_OVERWORLD)
                && !biome.is(Tags.Biomes.IS_UNDERGROUND)
                && !biome.is(BiomeTags.IS_BEACH);
    }

    boolean hasHumidRiver(Holder<Biome> biome) {
        return  (
                biome.is(Tags.Biomes.IS_WET_OVERWORLD)
                )
                && biome.is(BiomeTags.IS_OVERWORLD)
                && !biome.is(Tags.Biomes.IS_UNDERGROUND)
                && !biome.is(BiomeTags.IS_BEACH);
    }

    boolean hasSnowyRiver(Holder<Biome> biome) {
        return  (
                biome.is(Tags.Biomes.IS_SNOWY)
        )
                && biome.is(BiomeTags.IS_OVERWORLD)
                && !biome.is(Tags.Biomes.IS_UNDERGROUND)
                && !biome.is(BiomeTags.IS_BEACH);
    }

    boolean hasNonriverAlgae(Holder<Biome> biome) {
        return (biome.is(Tags.Biomes.IS_SWAMP)) && (!biome.is(Tags.Biomes.IS_SNOWY));
    }



        public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder)
        {
            if (phase == Phase.ADD) {
                if ((hasTemperateRiver(biome) || hasSnowyRiver(biome) || hasHumidRiver(biome)) && Objects.equals(variant, "has_rocks")) {
                    builder.getGenerationSettings().addFeature(step, feature);
                } else if(hasNonriverAlgae(biome) && Objects.equals(variant, "has_nonriver_algae")) {
                    builder.getGenerationSettings().addFeature(step, feature);
                } else {
                    if (hasTemperateRiver(biome) && Objects.equals(variant, "temperate")) {
                        builder.getGenerationSettings().addFeature(step, feature);
                    } else if (hasAridRiver(biome) && Objects.equals(variant, "arid")) {
                        builder.getGenerationSettings().addFeature(step, feature);
                    }
                }

            }

        }

        public Codec<? extends BiomeModifier> codec() {
            return ModBiomeModifiers.ADD_RIVER_FEATURES_CODEC.get();
        }
    }
