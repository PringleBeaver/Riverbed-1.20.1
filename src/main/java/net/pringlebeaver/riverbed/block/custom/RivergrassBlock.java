package net.pringlebeaver.riverbed.block.custom;

import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.chunk.LevelChunkSection;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.pringlebeaver.riverbed.block.ModBlocks;
import net.pringlebeaver.riverbed.util.ModTags;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class RivergrassBlock extends DoublePlantBlock implements SimpleWaterloggedBlock, BonemealableBlock {

    protected static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);

    private static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    private static final BooleanProperty DENSE = BooleanProperty.create("dense");

    public RivergrassBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(HALF, DoubleBlockHalf.LOWER).setValue(WATERLOGGED, Boolean.valueOf(false)).setValue(DENSE, Boolean.valueOf(false)));

    }

    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return SHAPE;
    }

    protected boolean mayPlaceOn(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return (blockState.isFaceSturdy(blockGetter, blockPos, Direction.UP) && blockGetter.getFluidState(blockPos.above()).isSourceOfType(Fluids.WATER) || blockState.is(ModTags.Blocks.RIVER_GRASS_PLACEABLE) ) && !blockGetter.getFluidState(blockPos.above().above()).isSourceOfType(Fluids.WATER);
    }

    @Nullable
    public BlockState getStateForPlacement(@NotNull BlockPlaceContext pContext) {
        BlockState blockstate = super.getStateForPlacement(pContext);
        return blockstate != null ? copyWaterloggedFrom(pContext.getLevel(), pContext.getClickedPos(), blockstate.setValue(DENSE, false)) : null;
    }

    public FluidState getFluidState(BlockState blockState) {
        return blockState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(blockState);
    }

    public boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        if (blockState.getValue(HALF) == DoubleBlockHalf.UPPER) {
            return super.canSurvive(blockState, levelReader, blockPos);
        } else {
            BlockPos blockpos = blockPos.below();
            BlockState blockstate = levelReader.getBlockState(blockpos);
            return this.mayPlaceOn(blockstate, levelReader, blockpos);
        }
    }



    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_154632_) {
        p_154632_.add(HALF, WATERLOGGED, DENSE);
    }


    public Boolean isDense(BlockState pState) {
        return pState.getValue(DENSE);
    }


    @Override
    public boolean isValidBonemealTarget(LevelReader pLevel, BlockPos pPos, BlockState pState, boolean pIsClient) {
        return !isDense(pState);
    }

    @Override
    public boolean isBonemealSuccess(Level pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
        return true;
    }

    private static void updateDenseState(ServerLevel level, BlockPos pos, BlockState state) {
        if (state.getValue(HALF) == DoubleBlockHalf.LOWER)
        {
            level.setBlock(pos.above(), level.getBlockState(pos.above()).setValue(DENSE, true), 2);
        }
        else if (state.getValue(HALF) == DoubleBlockHalf.UPPER)
        {
            level.setBlock(pos.below(), level.getBlockState(pos.below()).setValue(DENSE, true), 2);
        }
    }



    @Override
    public void performBonemeal(ServerLevel pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
        pLevel.setBlock(pPos, pState.setValue(DENSE, true), 2);
        updateDenseState(pLevel, pPos, pState);
    }
}
