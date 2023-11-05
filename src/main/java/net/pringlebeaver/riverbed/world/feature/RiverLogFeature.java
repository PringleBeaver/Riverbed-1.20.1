package net.pringlebeaver.riverbed.world.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.BlockColumnFeature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.BlockColumnConfiguration;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.pringlebeaver.riverbed.block.RiverbedBlocks;
import net.pringlebeaver.riverbed.block.custom.AlgaeBlock;

public class RiverLogFeature extends BlockColumnFeature {

    public RiverLogFeature(Codec<BlockColumnConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<BlockColumnConfiguration> placeContext) {
        WorldGenLevel worldgenlevel = placeContext.level();
        BlockColumnConfiguration blockcolumnconfiguration = placeContext.config();
        RandomSource randomsource = placeContext.random();
        int i = blockcolumnconfiguration.layers().size();
        int[] aint = new int[i];
        int j = 0;
        for(int k = 0; k < i; ++k) {
            aint[k] = blockcolumnconfiguration.layers().get(k).height().sample(randomsource);
            j += aint[k];
        }
        if (j == 0) {
            return false;
        } else {
            BlockPos.MutableBlockPos blockpos$mutableblockpos1 = placeContext.origin().mutable();
            BlockPos.MutableBlockPos blockpos$mutableblockpos = blockpos$mutableblockpos1.mutable().move(blockcolumnconfiguration.direction());

            for(int l = 0; l < j; ++l) {
                if (!blockcolumnconfiguration.allowedPlacement().test(worldgenlevel, blockpos$mutableblockpos)) {
                    truncate(aint, j, l, blockcolumnconfiguration.prioritizeTip());
                    break;
                }

                blockpos$mutableblockpos.move(blockcolumnconfiguration.direction());
            }

            for(int k1 = 0; k1 < i; ++k1) {
                int i1 = aint[k1];
                if (i1 != 0) {
                    BlockColumnConfiguration.Layer blockcolumnconfiguration$layer = blockcolumnconfiguration.layers().get(k1);

                    for(int j1 = 0; j1 < i1; ++j1) {

                        BlockState stateNext = placeContext.level().getBlockState(blockpos$mutableblockpos1.relative(blockcolumnconfiguration.direction()));
                        BlockState stateNextBelow = placeContext.level().getBlockState(blockpos$mutableblockpos1.relative(blockcolumnconfiguration.direction()).below());


                        logPlaceAttempt(blockpos$mutableblockpos1, worldgenlevel, blockcolumnconfiguration$layer, randomsource, placeContext);

                        if (stateNext.is(BlockTags.REPLACEABLE) && stateNextBelow.isFaceSturdy(worldgenlevel, blockpos$mutableblockpos1, Direction.UP)) {
                                blockpos$mutableblockpos1.move(blockcolumnconfiguration.direction());
                            } else {
                                for (int j2 = 0; j2 < 2; ++j2){
                                    blockpos$mutableblockpos1.move(blockcolumnconfiguration.direction().getOpposite());
                                   logPlaceAttempt(blockpos$mutableblockpos1, worldgenlevel, blockcolumnconfiguration$layer, randomsource, placeContext);
                                }
                                break;
                            }

                            }
                    }
                }
            }

            return true;
    }

    private static void logPlaceAttempt(BlockPos blockPos, WorldGenLevel level, BlockColumnConfiguration.Layer layer, RandomSource randomsource, FeaturePlaceContext placeContext) {

        FluidState fluidstate = placeContext.level().getFluidState(blockPos.above());
        boolean flag = fluidstate.getType() == Fluids.WATER;
        BlockState algaeState = RiverbedBlocks.ALGAE.get().defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, flag).setValue(AlgaeBlock.ALGAE, randomsource.nextInt(2) + 1);
        BlockState stateAbove = placeContext.level().getBlockState(blockPos.above());
        BlockState stateBelow = placeContext.level().getBlockState(blockPos.below());

        if (stateBelow.isFaceSturdy(level.getLevel(), blockPos, Direction.UP)) {
            level.setBlock(blockPos, layer.state().getState(randomsource, blockPos), 2);
            if (stateAbove.is(BlockTags.REPLACEABLE)){
                level.setBlock(blockPos.above(),  algaeState, 2);
            }
        }

    }

    private static void truncate(int[] ints, int i2, int i3, boolean b) {
        int i = i2 - i3;
        int j = b ? 1 : -1;
        int k = b ? 0 : ints.length - 1;
        int l = b ? ints.length : -1;

        for(int i1 = k; i1 != l && i > 0; i1 += j) {
            int j1 = ints[i1];
            int k1 = Math.min(j1, i);
            i -= k1;
            ints[i1] -= k1;
        }

    }

}
