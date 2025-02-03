package net.pringlebeaver.riverbed.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.pringlebeaver.riverbed.block.ModBlocks;
import net.pringlebeaver.riverbed.item.ModItems;
import net.pringlebeaver.riverbed.particle.ModParticles;

import javax.annotation.Nullable;

public class SurfaceFilmBlock extends BushBlock {
    protected static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D);

    public static final IntegerProperty AGE = IntegerProperty.create("age", 1, 2);


    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Vec3 vec3 = pState.getOffset(pLevel, pPos);
        return SHAPE.move(vec3.x, vec3.y, vec3.z);
    }

    public SurfaceFilmBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(AGE, 1));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder);
        pBuilder.add(AGE);
    }

    @Override
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
                spawnAlgaeParticles(pPos, pLevel, pRandom);
        super.animateTick(pState, pLevel, pPos, pRandom);
    }

    private void spawnAlgaeParticles(BlockPos blockpos, Level level, RandomSource randomSource) {
        if (randomSource.nextInt(4) == 0) {
            level.addParticle(ModParticles.ALGAE_PARTICLES.get(), false, (blockpos.getX() + randomSource.nextDouble() * 1.5) - 0.25, blockpos.getY(),(blockpos.getZ() + randomSource.nextDouble() * 1.5) - 0.25, 0.0D, 0.0D, 0.0D);
        }
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        BlockState blockstate = pContext.getLevel().getBlockState(pContext.getClickedPos());
            return super.getStateForPlacement(pContext);
    }

    protected boolean mayPlaceOn(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        FluidState fluidstate = pLevel.getFluidState(pPos);
        FluidState fluidstate1 = pLevel.getFluidState(pPos.above());
        return fluidstate.getType() == Fluids.WATER  && fluidstate1.getType() == Fluids.EMPTY;
    }

    public boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
            BlockPos blockpos = blockPos.below();
            BlockState blockstate = levelReader.getBlockState(blockpos);
            return this.mayPlaceOn(blockstate, levelReader, blockpos);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        ItemStack usedStack = pPlayer.getItemInHand(pHand);
        ItemStack algaeBottleItemStack = new ItemStack(ModBlocks.SURFACE_FILM.get().asItem());

        if (usedStack.is(Items.GLASS_BOTTLE)) {
            usedStack.shrink(1);
            pLevel.playSound(pPlayer, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);

            pLevel.setBlock(pPos, Blocks.AIR.defaultBlockState(), 2);

            if (usedStack.isEmpty()) {
                pPlayer.setItemInHand(pHand, algaeBottleItemStack);
            } else if (!pPlayer.getInventory().add(algaeBottleItemStack)) {
                pPlayer.drop(algaeBottleItemStack, false);
            }
            return InteractionResult.sidedSuccess(pLevel.isClientSide);
        }
        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }
}
