package net.pringlebeaver.riverbed.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
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
import net.minecraft.world.level.material.EmptyFluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.WaterFluid;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.IForgeShearable;

import javax.annotation.Nullable;

public class AlgaeBlock extends BushBlock implements SimpleWaterloggedBlock, IForgeShearable {

    public static final int MAX_LEVEL = 3;
    public static final IntegerProperty LEVEL = BlockStateProperties.LEVEL;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    protected static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D);


    public AlgaeBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(LEVEL, Integer.valueOf(1)).setValue(WATERLOGGED, Boolean.valueOf(true)));

    }

    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        BlockState blockstate = pContext.getLevel().getBlockState(pContext.getClickedPos());
        if (blockstate.is(this)) {
            return blockstate.setValue(LEVEL, Integer.valueOf(Math.min(4, blockstate.getValue(LEVEL) + 1)));
        } else {
            FluidState fluidstate = pContext.getLevel().getFluidState(pContext.getClickedPos());
            boolean flag = fluidstate.getType() == Fluids.WATER;
            return super.getStateForPlacement(pContext).setValue(WATERLOGGED, Boolean.valueOf(flag));
        }
    }

    protected IntegerProperty getLevelProperty() {
        return LEVEL;
    }

    public BlockState getStateForLevel(int pAge) {
        return this.defaultBlockState().setValue(this.getLevelProperty(), Integer.valueOf(pAge));
    }

    public int getAlgaeLevel(BlockState pState) {
        return pState.getValue(this.getLevelProperty());
    }

    public boolean isRandomlyTicking(BlockState pState) {
        return pState.getValue(WATERLOGGED);
    }

  //  @Override
   // public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {

      //  int i = this.getAlgaeLevel(pState);
      // if (pLevel.random.nextInt(16) == 0 && pLevel.isAreaLoaded(pPos, 4)) {
      //      if (pState.getValue(LEVEL) == 1 && pState.getValue(WATERLOGGED)) {
           //     pLevel.setBlock(pPos, this.getStateForLevel(i + 1), 2);
       //     }
      //      if (pLevel.random.nextInt(4) == 0 && pState.getValue(LEVEL) == 2 && pState.getValue(WATERLOGGED)) {
     //           pLevel.setBlock(pPos, this.getStateForLevel(i + 1), 2);
      //      }
      //  }

      //      if (pState.getValue(LEVEL) > 1 && pState.getValue(WATERLOGGED)) {
    //            BlockState blockstate = this.defaultBlockState();

      //          for(int t = 0; t < 4; ++t) {
     //               BlockPos blockpos = pPos.offset(pRandom.nextInt(3) - 1, pRandom.nextInt(5) - 3, pRandom.nextInt(3) - 1);
     //               if (pLevel.getBlockState(blockpos).is(Blocks.WATER) && pLevel.getFluidState(blockpos).isSource() && mayPlaceOn(pLevel.getBlockState(blockpos.below()), pLevel, pPos)) {
     //                   pLevel.setBlockAndUpdate(blockpos, blockstate.setValue(LEVEL, 1));
    //                }
    //            }

    //    }
   //     super.randomTick(pState, pLevel, pPos, pRandom);
  //  }

    protected boolean mayPlaceOn(BlockState pState, BlockGetter pLevel, BlockPos pPos) {

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

            return super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
        }
    }

    public boolean canBeReplaced(BlockState pState, BlockPlaceContext pUseContext) {
        return !pUseContext.isSecondaryUseActive() && pUseContext.getItemInHand().is(this.asItem()) && pState.getValue(LEVEL) < MAX_LEVEL ? true : super.canBeReplaced(pState, pUseContext);
    }


    public FluidState getFluidState(BlockState pState) {
        return pState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(pState);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(LEVEL, WATERLOGGED);
    }

    public boolean isPathfindable(BlockState pState, BlockGetter pLevel, BlockPos pPos, PathComputationType pType) {
        return false;
    }
}
