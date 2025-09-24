package net.pringlebeaver.riverbed.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.ArrayList;
import java.util.List;

public class WaterHyacinthBlock extends BushBlock implements BonemealableBlock {
    protected static final float AABB_OFFSET = 3.0F;
    protected static final VoxelShape SHAPE = Block.box(5.0D, -2.0D, 5.0D, 11.0D, 10.0D, 11.0D);

    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Vec3 vec3 = pState.getOffset(pLevel, pPos);
        return SHAPE.move(vec3.x, vec3.y, vec3.z);
    }

    public WaterHyacinthBlock(Properties pProperties) {
        super(pProperties);
    }

    protected boolean mayPlaceOn(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        FluidState fluidstate = pLevel.getFluidState(pPos);
        FluidState fluidstate1 = pLevel.getFluidState(pPos.above());
        return fluidstate.getType() == Fluids.WATER && fluidstate1.getType() == Fluids.EMPTY;
    }

    public boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
            BlockPos blockpos = blockPos.below();
            BlockState blockstate = levelReader.getBlockState(blockpos);
            return this.mayPlaceOn(blockstate, levelReader, blockpos);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader pLevel, BlockPos pPos, BlockState pState, boolean pIsClient) {
        Direction[] possibleDirections = {Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST};
        List<Direction> validDirections = new ArrayList<>();

        for (Direction direction : possibleDirections) {
            if (pLevel.getBlockState(pPos.relative(direction)).is(Blocks.AIR)) {
                validDirections.add(direction);
            }
        }
        return !validDirections.isEmpty();
    }



    @Override
    public boolean isBonemealSuccess(Level pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {

        Direction[] possibleDirections = {Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST};
        List<Direction> validDirections = new ArrayList<>();

        for (Direction direction : possibleDirections) {
            if (pLevel.getBlockState(pPos.relative(direction)).is(Blocks.AIR)) {
                validDirections.add(direction);
            }
        }
        if (!validDirections.isEmpty()) {
            Direction randomDirection = validDirections.get(pRandom.nextInt(validDirections.size()));
            if (pLevel.getBlockState(pPos.relative(randomDirection)).is(Blocks.AIR) && pLevel.getFluidState(pPos.relative(randomDirection).below()).is(Fluids.WATER)) {
                pLevel.setBlock(pPos.relative(randomDirection), this.defaultBlockState(), 2);
            }
        }
    }


}
