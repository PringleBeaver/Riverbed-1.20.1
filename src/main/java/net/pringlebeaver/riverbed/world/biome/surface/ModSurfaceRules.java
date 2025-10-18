package net.pringlebeaver.riverbed.world.biome.surface;


import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.pringlebeaver.riverbed.world.biome.ModBiomes;

public class ModSurfaceRules {
    private static final SurfaceRules.RuleSource DIRT = makeStateRule(Blocks.DIRT);
    private static final SurfaceRules.RuleSource COARSE_DIRT = makeStateRule(Blocks.COARSE_DIRT);
    private static final SurfaceRules.RuleSource GRASS_BLOCK = makeStateRule(Blocks.GRASS_BLOCK);
    private static final SurfaceRules.RuleSource SAND = makeStateRule(Blocks.SAND);
    private static final SurfaceRules.RuleSource SANDSTONE = makeStateRule(Blocks.SANDSTONE);
    private static final SurfaceRules.RuleSource GRAVEL = makeStateRule(Blocks.GRAVEL);


    public static SurfaceRules.RuleSource makeRules() {
        SurfaceRules.ConditionSource isAtOrAboveWaterLevel = SurfaceRules.waterBlockCheck(-1, 0);
        SurfaceRules.ConditionSource isUnderWaterLevel = SurfaceRules.not(isAtOrAboveWaterLevel);
        SurfaceRules.ConditionSource coarseDirtNoiseSource = SurfaceRules.noiseCondition(
                Noises.PATCH, -0.05, 0.05);


        SurfaceRules.RuleSource grassSurface = SurfaceRules.sequence(SurfaceRules.ifTrue(isAtOrAboveWaterLevel, GRASS_BLOCK), DIRT);

        SurfaceRules.RuleSource aridRiverDefault = SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, SANDSTONE),
                SAND
        );

        SurfaceRules.RuleSource aridRiverBedBlocks = SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, aridRiverDefault),

                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, SAND),

                SurfaceRules.ifTrue(SurfaceRules.DEEP_UNDER_FLOOR, SAND),

                SurfaceRules.ifTrue(SurfaceRules.VERY_DEEP_UNDER_FLOOR, SANDSTONE)

        );

        SurfaceRules.RuleSource aridRiverBankBlocks = SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(coarseDirtNoiseSource, COARSE_DIRT),
                                GRASS_BLOCK
                        )
                ),
                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, DIRT),
                SurfaceRules.ifTrue(SurfaceRules.DEEP_UNDER_FLOOR, DIRT)
        );


        SurfaceRules.RuleSource aridRiverSurfaceRules = SurfaceRules.sequence(

                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.ARID_RIVER),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(isUnderWaterLevel, aridRiverBedBlocks),

                                aridRiverBankBlocks
                        )
                ),

                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, grassSurface)
        );

        return SurfaceRules.ifTrue(SurfaceRules.abovePreliminarySurface(), aridRiverSurfaceRules);
    }

    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }
}
