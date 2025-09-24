package net.pringlebeaver.riverbed.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.IForgeShearable;
import net.pringlebeaver.riverbed.block.ModBlocks;
import net.pringlebeaver.riverbed.particle.ModParticles;

import javax.annotation.Nullable;
import java.util.OptionalInt;

public class AlgaeBlock extends BushBlock implements SimpleWaterloggedBlock, IForgeShearable {

    public static final int SPREADABLE_DISTANCE = 3;

    public static final int MAX_ALGAE = 3;
    public static final IntegerProperty ALGAE = IntegerProperty.create("algae", 1, 3);

    public static final IntegerProperty DISTANCE = IntegerProperty.create("distance", 1, SPREADABLE_DISTANCE);

    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    int filmSpreadDepth = 2;

    protected static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D);


    public AlgaeBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(ALGAE, 1).setValue(WATERLOGGED, Boolean.TRUE).setValue(DISTANCE, SPREADABLE_DISTANCE));

    }
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(ALGAE, WATERLOGGED, DISTANCE);
    }

    protected boolean isSpreading(BlockState pState) {
        return pState.getValue(DISTANCE) < SPREADABLE_DISTANCE || pState.getValue(ALGAE) == MAX_ALGAE;
    }



    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    private void spawnAlgaeParticles(BlockPos blockpos, Level level, RandomSource randomSource) {
        level.addParticle(ModParticles.ALGAE_PARTICLES.get(), false, (blockpos.getX() + randomSource.nextDouble() * 1.5) - 0.25, blockpos.getY(),(blockpos.getZ() + randomSource.nextDouble() * 1.5) - 0.25, 0.0D, 0.0D, 0.0D);
    }

    private void spreadSurfaceFilm(BlockPos blockPos, Level level, RandomSource randomSource, BlockState state) {
        for (int i = 0; i <= filmSpreadDepth; i++) {
            if (nearbyAlgaeCount(level, blockPos) >= 2) {
                if (randomSource.nextInt(2) < this.getAlgaeValue(state)) {
                    if (level.getBlockState(blockPos.above(i)).is(Blocks.AIR) && level.getFluidState(blockPos.above(i -1)).is(Fluids.WATER)) {
                        level.setBlock(blockPos.above(i), ModBlocks.SURFACE_FILM.get().defaultBlockState(), 2);
                    } else if (level.getBlockState(blockPos.above(i)).is(ModBlocks.SURFACE_FILM.get()) && nearbyAlgaeCount(level, blockPos) >= 3) {
                        level.setBlock(blockPos.above(i), ModBlocks.SURFACE_FILM.get().defaultBlockState().setValue(SurfaceFilmBlock.AGE, 2), 2);
                    }
                }
            }

        }
    }
    public int nearbyAlgaeCount(Level level, BlockPos pos) {
        int nearbyAlgaeCount = 0;
        if (level.getBlockState(pos.east()).is(this)) {nearbyAlgaeCount++;}
        if (level.getBlockState(pos.west()).is(this)) {nearbyAlgaeCount++;}
        if (level.getBlockState(pos.north()).is(this)) {nearbyAlgaeCount++;}
        if (level.getBlockState(pos.south()).is(this)) {nearbyAlgaeCount++;}
        return nearbyAlgaeCount;
    }



    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        BlockState blockstate = pContext.getLevel().getBlockState(pContext.getClickedPos());
        FluidState fluidstate = pContext.getLevel().getFluidState(pContext.getClickedPos());
        boolean flag = fluidstate.getType() == Fluids.WATER;

        if (blockstate.is(this)) {
            return super.getStateForPlacement(pContext).setValue(ALGAE, Math.min(4, blockstate.getValue(ALGAE) + 1)).setValue(WATERLOGGED, flag).setValue(DISTANCE, getDistance(blockstate, pContext.getLevel(), pContext.getClickedPos()));
        } else {

            return super.getStateForPlacement(pContext).setValue(WATERLOGGED, flag).setValue(DISTANCE, getDistance(blockstate, pContext.getLevel(), pContext.getClickedPos()));
        }
    }


    public BlockState getStateForLevel(int pAge) {
        return this.defaultBlockState().setValue(ALGAE, Integer.valueOf(pAge));
    }

    public int getAlgaeValue(BlockState pState) {
        return pState.getValue(ALGAE);
    }

    public boolean isRandomlyTicking(BlockState pState) {
        return pState.getValue(WATERLOGGED);
    }

    @Override
    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        pLevel.setBlock(pPos, updateDistance(pState, pLevel, pPos), 3);
    }

    @Override
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        if (pState.getValue(WATERLOGGED)) {
            if (pState.getValue(ALGAE).equals(3)) {
                spawnAlgaeParticles(pPos, pLevel, pRandom);
            }

        }

        super.animateTick(pState, pLevel, pPos, pRandom);
    }

    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        spreadSurfaceFilm(pPos, pLevel, pRandom, pState);
        if (isSpreading(pState) && pState.getValue(WATERLOGGED) && pLevel.isLoaded(pPos)) {

            int algaeValue = getAlgaeValue(pState);

                BlockState blockstate = defaultBlockState();
                for(int t = 0; t < 4; ++t) {
                    BlockPos blockpos = pPos.offset(pRandom.nextInt(3) - 1, pRandom.nextInt(5) - 3, pRandom.nextInt(3) - 1);
                if (pLevel.getBlockState(blockpos).is(Blocks.WATER) && pLevel.getFluidState(blockpos).isSource() && mayPlaceOn(pLevel.getBlockState(blockpos.below()), pLevel, pPos)) {
                        pLevel.setBlockAndUpdate(blockpos, blockstate.setValue(ALGAE, 1).setValue(DISTANCE, getDistance(pState, pLevel, pPos)));
                 } else if ((pLevel.getBlockState(blockpos).is(ModBlocks.ALGAE.get()) && pLevel.getBlockState(blockpos).getValue(ALGAE) == 1) && pLevel.getBlockState(blockpos).getValue(DISTANCE) < 3) {
                    pLevel.setBlockAndUpdate(blockpos, blockstate.setValue(ALGAE, 2).setValue(DISTANCE, getDistance(pState, pLevel, pPos)));

                }
               }

        }
       super.randomTick(pState, pLevel, pPos, pRandom);

    }





    protected boolean mayPlaceOn(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        FluidState fluidstate = pLevel.getFluidState(pPos);
        return !pState.getCollisionShape(pLevel, pPos).getFaceShape(Direction.UP).isEmpty() || pState.isFaceSturdy(pLevel, pPos, Direction.UP);
    }

    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        BlockPos blockpos = pPos.below();
        return this.mayPlaceOn(pLevel.getBlockState(blockpos), pLevel, blockpos);
    }

    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        if (!pState.canSurvive(pLevel, pCurrentPos)) {
            return Blocks.AIR.defaultBlockState();
        } else {
            if (pState.getValue(WATERLOGGED)) {
                pLevel.scheduleTick(pCurrentPos, Fluids.WATER, Fluids.WATER.getTickDelay(pLevel));
            }
            int i = getDistanceAt(pFacingState) + 1;
            if (i != 1 || pState.getValue(DISTANCE) != i) {
                pLevel.scheduleTick(pCurrentPos, this, 1);
            }

            return super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
        }



    }

    private static BlockState updateDistance(BlockState pState, LevelAccessor pLevel, BlockPos pPos) {
        int i = getDistance(pState, pLevel, pPos);
        return pState.setValue(DISTANCE, Integer.valueOf(i));
    }

    private static int getDistance(BlockState pState, LevelAccessor pLevel, BlockPos pPos) {
        int i = SPREADABLE_DISTANCE;
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

        for(Direction direction : Direction.values()) {
            blockpos$mutableblockpos.setWithOffset(pPos, direction);
            i = Math.min(i, getDistanceAt(pLevel.getBlockState(blockpos$mutableblockpos)) + 1);
            if (i == 1) {
                break;
            }
        }

        return i;
    }

    private static int getDistanceAt(BlockState pNeighbor) {
        return getOptionalDistanceAt(pNeighbor).orElse(SPREADABLE_DISTANCE);
    }

    public static OptionalInt getOptionalDistanceAt(BlockState pState) {
        if (pState.is(ModBlocks.ALGAE.get()) && pState.getValue(ALGAE) == 3) {
            return OptionalInt.of(0);
        } else {
            return pState.hasProperty(DISTANCE) ? OptionalInt.of(pState.getValue(DISTANCE)) : OptionalInt.empty();
        }
    }

    public boolean canBeReplaced(BlockState pState, BlockPlaceContext pUseContext) {
        return !pUseContext.isSecondaryUseActive() && pUseContext.getItemInHand().is(this.asItem()) && pState.getValue(ALGAE) < MAX_ALGAE || super.canBeReplaced(pState, pUseContext);
    }


    public FluidState getFluidState(BlockState pState) {
        return pState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(pState);
    }

    public boolean isPathfindable(BlockState pState, BlockGetter pLevel, BlockPos pPos, PathComputationType pType) {
        return false;
    }
}
