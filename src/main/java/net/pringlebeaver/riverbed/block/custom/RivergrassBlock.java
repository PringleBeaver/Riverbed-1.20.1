package net.pringlebeaver.riverbed.block.custom;

import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.GrassColor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.pringlebeaver.riverbed.block.ModBlocks;
import net.pringlebeaver.riverbed.util.ModTags;

import javax.annotation.Nullable;

public class RivergrassBlock extends DoublePlantBlock implements SimpleWaterloggedBlock {

    protected static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);

    private static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public RivergrassBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(HALF, DoubleBlockHalf.LOWER).setValue(WATERLOGGED, Boolean.valueOf(false)));

    }

    @OnlyIn(Dist.CLIENT)
    public static void loadBlockTint(RegisterColorHandlersEvent.Block event) {
        event.getBlockColors().register((bs, world, pos, index) -> {
            return world != null && pos != null ? BiomeColors.getAverageGrassColor(world, pos) : GrassColor.get(0.5D, 1.0D);
        }, ModBlocks.RIVER_GRASS.get());
    }

    @OnlyIn(Dist.CLIENT)
    public static void loadItemTint(RegisterColorHandlersEvent.Item event) {
        event.getItemColors().register((stack, index) -> {
            return GrassColor.get(0.5D, 1.0D);
        }, ModBlocks.RIVER_GRASS.get());
    }

    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return SHAPE;
    }

    protected boolean mayPlaceOn(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return (blockState.isFaceSturdy(blockGetter, blockPos, Direction.UP) && blockGetter.getFluidState(blockPos.above()).isSourceOfType(Fluids.WATER) || blockState.is(ModTags.Blocks.RIVER_GRASS_PLACEABLE) ) && !blockGetter.getFluidState(blockPos.above().above()).isSourceOfType(Fluids.WATER);
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        BlockState blockstate = super.getStateForPlacement(pContext);
        return blockstate != null ? copyWaterloggedFrom(pContext.getLevel(), pContext.getClickedPos(), blockstate) : null;
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
        p_154632_.add(HALF, WATERLOGGED);
    }
}
