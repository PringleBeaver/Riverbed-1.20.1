package net.pringlebeaver.riverbed.entity.custom;


import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.*;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;

import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;

import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;

import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.animal.Animal;

import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.IForgeShearable;

import net.pringlebeaver.riverbed.block.ModBlocks;

import net.pringlebeaver.riverbed.entity.ModEntities;
import net.pringlebeaver.riverbed.item.ModItems;
import net.pringlebeaver.riverbed.particle.ModParticles;
import net.pringlebeaver.riverbed.sound.ModSounds;
import net.pringlebeaver.riverbed.util.ModTags;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class ManateeEntity extends Animal implements IForgeShearable  {
    private static final EntityDataAccessor<Boolean> ALGAE = SynchedEntityData.defineId(ManateeEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> BLOOMING = SynchedEntityData.defineId(ManateeEntity.class, EntityDataSerializers.BOOLEAN);

    private static final EntityDataAccessor<Integer> MOISTNESS_LEVEL = SynchedEntityData.defineId(ManateeEntity.class, EntityDataSerializers.INT);

    private static final EntityDataAccessor<Integer> HUNGRY = SynchedEntityData.defineId(ManateeEntity.class, EntityDataSerializers.INT);

    private static final EntityDataAccessor<Integer> ALGAE_GROWTH_TIME = SynchedEntityData.defineId(ManateeEntity.class, EntityDataSerializers.INT);

    private static final int TOTAL_AIR_SUPPLY = 5000;

    private static final int TOTAL_GROWTH_TIME = 10000;

    public ManateeEntity(EntityType<? extends Animal> pEntityType, Level pLevel) {

        super(pEntityType, pLevel);
        this.moveControl = new SmoothSwimmingMoveControl(this, 65, 35, 0.02F, 0.1F, true);
        this.lookControl = new SmoothSwimmingLookControl(this, 5);
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
    }

    public MobType getMobType() {
        return MobType.WATER;
    }

    public boolean checkSpawnObstruction(LevelReader pLevel) {
        return pLevel.isUnobstructed(this);
    }


    // Sounds


    @Override
    protected SoundEvent getAmbientSound() {
        return ModSounds.MANATEE_CHIRP.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return ModSounds.MANATEE_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.MANATEE_DEATH.get();
    }

    public boolean canBreatheUnderwater() {
        return false;
    }

    public boolean isPushedByFluid() {
        return false;
    }



    public void shear(SoundSource pCategory) {
        this.level().playSound((Player)null, this, SoundEvents.SHEEP_SHEAR, pCategory, 1.0F, 1.0F);
        spawnParticles(this.blockPosition(), this.level(), 200, ModParticles.ALGAE_PARTICLES.get());
        int i = this.random.nextInt(3);

        if(this.isBlooming()) {
            for(int j = 0; j < (1 + i); ++j) {
                ItemEntity hyacinthitementity = this.spawnAtLocation(ModBlocks.WATER_HYACINTH.get().asItem());
                if (hyacinthitementity != null) {
                    hyacinthitementity.setDeltaMovement(hyacinthitementity.getDeltaMovement().add((double)((this.random.nextFloat() - this.random.nextFloat()) * 0.1F), (double)(this.random.nextFloat() * 0.05F), (double)((this.random.nextFloat() - this.random.nextFloat()) * 0.1F)));
                }

            }
        }

        for(int j = 0; j < (6 + i); ++j) {


            ItemEntity algaeitementity = this.spawnAtLocation(ModBlocks.ALGAE.get().asItem());
            if (algaeitementity != null) {
                algaeitementity.setDeltaMovement(algaeitementity.getDeltaMovement().add((double)((this.random.nextFloat() - this.random.nextFloat()) * 0.1F), (double)(this.random.nextFloat() * 0.05F), (double)((this.random.nextFloat() - this.random.nextFloat()) * 0.1F)));
            }
        }
        this.setAlgae(false);
        this.setBlooming(false);
    }





    public @NotNull InteractionResult mobInteract(Player pPlayer, @NotNull InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);

        // Milking
        if (itemstack.is(Items.BUCKET) && !this.isBaby()) {
            pPlayer.playSound(SoundEvents.COW_MILK, 1.0F, 1.0F);
            ItemStack milkResult;
            if (this.isBlooming()) {
                if (!level().isClientSide) {
                    level().playSound(this, this.blockPosition(), ModSounds.MANATEE_BLOOM_REMOVE.get(), this.getSoundSource(), 1.0F, 1.0F);
                    spawnParticles(this.blockPosition(), this.level(),100, ModParticles.ALGAE_PARTICLES.get());
                    spawnParticles(this.blockPosition(), this.level(), 50, ParticleTypes.EFFECT);

                    this.setBlooming(false);
                }
                milkResult = ItemUtils.createFilledResult(itemstack, pPlayer, ModItems.HYACINTH_MILK_BUCKET.get().getDefaultInstance());
            } else {
                milkResult = ItemUtils.createFilledResult(itemstack, pPlayer, Items.MILK_BUCKET.getDefaultInstance());
            }
            pPlayer.setItemInHand(pHand, milkResult);
            return InteractionResult.sidedSuccess(this.level().isClientSide);
            // Convert to Flowers
        } else if (itemstack.is(ModBlocks.WATER_HYACINTH.get().asItem()) && !isBlooming() && !isBaby()) {
            itemstack.shrink(1);
            int successChance = this.random.nextInt(6);

            if (successChance == 0) {
                if (!level().isClientSide) {
                    level().playSound(this, this.blockPosition(), ModSounds.MANATEE_EAT_SUCCESS.get(), this.getSoundSource(), 1.0F, 1.0F);
                    spawnParticles(this.blockPosition(), this.level(),100, ModParticles.ALGAE_PARTICLES.get());
                    spawnParticles(this.blockPosition(), this.level(), 50, ParticleTypes.EFFECT);
                    this.setBlooming(true);
                    this.setAlgae(true);
                }
                return InteractionResult.sidedSuccess(this.level().isClientSide);

            } else {
                if (!level().isClientSide) {
                    level().playSound(this, this.blockPosition(), ModSounds.MANATEE_EAT.get(), this.getSoundSource(), 1.0F, 1.0F);
                    spawnParticles(this.blockPosition(), this.level(), 50, ParticleTypes.SMOKE);
                }
                return InteractionResult.sidedSuccess(this.level().isClientSide);
            }

        } else {

            return super.mobInteract(pPlayer, pHand);
        }
    }




        public boolean readyForShearing() {
        return (this.isAlive() && this.isAlgae()) && !isBaby();
    }
    @Override
    public @NotNull List<ItemStack> onSheared(@org.jetbrains.annotations.Nullable Player player, @NotNull ItemStack item, Level level, BlockPos pos, int fortune) {
        if (readyForShearing()) {
            shear(SoundSource.NEUTRAL);
            return IForgeShearable.super.onSheared(player, item, level, pos, fortune);
        } else {
            return Collections.emptyList();
        }
    }

    private void spawnParticles(BlockPos blockPos, Level level, Integer amount, ParticleOptions particleOptions) {
        if (level instanceof ServerLevel serverLevel) {


            serverLevel.sendParticles(
                    particleOptions,
                        blockPos.getX() + 0.5,
                        blockPos.getY()+ 0.75,
                        blockPos.getZ()+ 0.5,
                        amount, 0.5, 0.5, 0.5,
                        0.01D);
        }
    }


    public int getMaxAirSupply() {
        return TOTAL_AIR_SUPPLY;
    }
    public boolean isAlgae() {
    return this.entityData.get(ALGAE);
    }

    public boolean isBlooming() {
        return this.entityData.get(BLOOMING);
    }

    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData, @Nullable CompoundTag pDataTag) {
        this.setAirSupply(this.getMaxAirSupply());
        this.setXRot(0.0F);
        this.setAlgaeGrowthTime(random.nextInt(8000));
        this.setHungryLevel(random.nextInt(1000));
        setBlooming(false);
        if (isBaby()) {
            setAlgae(false);
        } else {
            setAlgae(random.nextBoolean());

        }

        return super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
    }

    @org.jetbrains.annotations.Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        return ModEntities.MANATEE.get().create(pLevel);
    }


    public int getMoistnessLevel() {
        return this.entityData.get(MOISTNESS_LEVEL);
    }

    public int getHungryLevel() {
        return this.entityData.get(HUNGRY);
    }

    public int getAlgaeGrowthTime() {
        return this.entityData.get(ALGAE_GROWTH_TIME);
    }
    public int getTotalGrowthTime() {
        return TOTAL_GROWTH_TIME;
    }

    @Override
    public boolean isPersistenceRequired() {
        return true;
    }

    public void setMoistnessLevel(int pMoistnessLevel) {
        this.entityData.set(MOISTNESS_LEVEL, pMoistnessLevel);
    }

    public void setHungryLevel(int hungryLevel) {
        this.entityData.set(HUNGRY, hungryLevel);
    }

    public void setAlgae(boolean algae) {
        this.entityData.set(ALGAE, algae);
    }

    public void setBlooming(boolean blooming) {
        this.entityData.set(BLOOMING, blooming);
    }

    public void setAlgaeGrowthTime(int algaeGrowthTime) {
        this.entityData.set(ALGAE_GROWTH_TIME, algaeGrowthTime);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(MOISTNESS_LEVEL, 2400);
        this.entityData.define(HUNGRY, 0);
        this.entityData.define(ALGAE, true);
        this.entityData.define(BLOOMING, false);
        this.entityData.define(ALGAE_GROWTH_TIME, 0);
    }
    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("Moistness", this.getMoistnessLevel());
        pCompound.putInt("Hungry", this.getHungryLevel());
        pCompound.putBoolean("Algae", this.isAlgae());
        pCompound.putBoolean("Blooming", this.isBlooming());
        pCompound.putInt("AlgaeGrowthTime", this.getAlgaeGrowthTime());
        
    }
    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setMoistnessLevel(pCompound.getInt("Moistness"));
        this.setAlgae(pCompound.getBoolean("Algae"));
        this.setBlooming(pCompound.getBoolean("Blooming"));
        this.setAlgaeGrowthTime(pCompound.getInt("AlgaeGrowthTime"));

    }


    

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new BreathAirGoal(this));
        this.goalSelector.addGoal(1, new TryFindWaterGoal(this));



        this.goalSelector.addGoal(3, new BreedGoal(this, 1.2D));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.1D, Ingredient.of(Items.SEAGRASS), false));

        this.goalSelector.addGoal(5, new GrazeGoal(this));
        this.goalSelector.addGoal(6, new FollowParentGoal(this, 1.1D));

        this.goalSelector.addGoal(7, new RandomSwimmingGoal(this, 1.0D, 10));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 6.0F));
    }

    protected PathNavigation createNavigation( Level pLevel) {
        return new WaterBoundPathNavigation(this, pLevel);
    }



    public void travel(Vec3 pTravelVector) {
        if (this.isEffectiveAi() && this.isInWater() && !this.onGround()) {
            this.moveRelative(this.getSpeed(), pTravelVector);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
            if (this.getTarget() == null) {
                if (this.getAirSupply() < 150) {
                    this.setDeltaMovement(this.getDeltaMovement().add(0.0D, 0.0D, 0.0D));
                } else {
                    if(!this.isEyeInFluid(FluidTags.WATER)) {
                        this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.01D, 0.0D));

                    }
                    this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.005D, 0.0D));
                }
            }
        } else {
            super.travel(pTravelVector);
        }

    }


    public int getMaxHeadXRot() {
        return 1;
    }


    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 16.0D).add(Attributes.MOVEMENT_SPEED, (double)0.5F);
    }

    @Override
    protected int increaseAirSupply(int pCurrentAir) {
        return this.getMaxAirSupply();
    }



    public void tick() {
        super.tick();
        if (this.level().isClientSide()) {
            if (this.eatAnimationTick > 0) {
                this.eatAnimationTick--;
            }
        }

        if (this.isNoAi()) {
            this.setAirSupply(this.getMaxAirSupply());
        } else {
            if(this.getHungryLevel() < 2400) {
                this.setHungryLevel(this.getHungryLevel() +1);

            }

            if (this.isBaby()) {
                this.setAlgaeGrowthTime(0);
                this.setAlgae(false);
            } else {
            if (this.isAlgae()) {
                this.setAlgaeGrowthTime(0);
            } else {
                this.setAlgaeGrowthTime(this.getAlgaeGrowthTime() + 1);
                if (this.getAlgaeGrowthTime() > TOTAL_GROWTH_TIME) {
                    this.setAlgae(true);
                }
            }
                }

            if (this.isInWaterRainOrBubble()) {
                this.setMoistnessLevel(2400);
            } else {
                this.setMoistnessLevel(this.getMoistnessLevel() - 1);
                if (this.getMoistnessLevel() <= 0) {
                    this.hurt(this.damageSources().dryOut(), 1.0F);
                }

                if (this.onGround() && !this.isInWater()) {
                    this.setDeltaMovement(this.getDeltaMovement().add((double)((this.random.nextFloat() * 2.0F - 1.0F) * 0.2F), 0.5D, (double)((this.random.nextFloat() * 2.0F - 1.0F) * 0.2F)));
                    this.setYRot(this.random.nextFloat() * 360.0F);
                    this.setOnGround(false);
                    this.hasImpulse = true;
                }
            }

            if (this.level().isClientSide && this.isInWater() && this.getDeltaMovement().lengthSqr() > 0.03D) {
                Vec3 vec3 = this.getViewVector(0.0F);
                float f = Mth.cos(this.getYRot() * ((float)Math.PI / 180F)) * 0.1F;
                float f1 = Mth.sin(this.getYRot() * ((float)Math.PI / 180F)) * 0.1F;
                float f2 = 1.2F - this.random.nextFloat() * 0.7F;
            }

        }
    }

    public boolean isFood(ItemStack pStack) {
        return pStack.is(Items.SEAGRASS);
    }


    public boolean canBeLeashed(@NotNull Player pPlayer) {
        return true;
    }

    private int eatAnimationTick;



    public float getHeadEatAngleScale(float pPartialTick) {
        if (this.eatAnimationTick > 1 && this.eatAnimationTick <= 40) {
            float f = ((float)(this.eatAnimationTick - 4) - pPartialTick) / 32.0F;
            return ((float)Math.PI / 5F) + 0.21991149F * Mth.sin(f * 28.7F);
        } else {
            return this.eatAnimationTick > 0 ? ((float)Math.PI / 5F) : this.getXRot() * ((float)Math.PI / 180F);
        }
    }

    public void handleEntityEvent(byte pId) {
        if (pId == 10) {
            this.eatAnimationTick = 40;
        } else {
            super.handleEntityEvent(pId);
        }

    }

    public static boolean canSpawn(EntityType<ManateeEntity> entityType, LevelAccessor levelAccessor, MobSpawnType spawnType, BlockPos blockPos, RandomSource random) {
        return checkManateeSpawnRules(entityType, levelAccessor, spawnType, blockPos, random);
    }

    public static boolean checkManateeSpawnRules(EntityType<? extends Animal> pAnimal, LevelAccessor pLevel, MobSpawnType pSpawnType, BlockPos pPos, RandomSource pRandom) {
        int searchRange = 80;

        int i = pLevel.getSeaLevel();
        int j = i - 13;
        boolean hasEnoughWater = pPos.getY() >= j && pPos.getY() <= i && pLevel.getFluidState(pPos.below()).is(FluidTags.WATER) && pLevel.getBlockState(pPos.above()).is(Blocks.WATER);

        if (!hasEnoughWater) {
            return false;
        }

        AABB nearbySearchArea = new AABB(pPos).inflate(searchRange);

        return pLevel.getEntities((Entity) null, nearbySearchArea, (Entity entity) -> entity.getType() == pAnimal).size() < 5;
    }

    @Override
    public boolean canRide(Entity pVehicle) {
        return false;
    }

    static class GrazeGoal extends Goal{
        private final ManateeEntity manatee;
        private final Level level;
        private int eatingTime;

        private final int searchRange = 8;
        @Nullable
        private BlockPos targetPos;

        GrazeGoal(ManateeEntity manatee) {
            this.manatee = manatee;
            this.level = manatee.level();
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            if (manatee.getHungryLevel() < 2400) {
                return false;
            }
            Optional<BlockPos> optional = this.findNearbySeagrass();

            List<BlockPos> potentialTargets = BlockPos.betweenClosedStream(this.manatee.blockPosition().offset(-searchRange, -searchRange, -searchRange), this.manatee.blockPosition().offset(searchRange, searchRange, searchRange))
                    .filter(pos -> this.level.getBlockState(pos).is(ModTags.Blocks.MANATEE_CAN_EAT))
                    .map(BlockPos::immutable)
                    .collect(java.util.stream.Collectors.toList());

            if (potentialTargets.isEmpty()) {
                return false;
            }
            java.util.Collections.shuffle(potentialTargets);

            for (BlockPos pos : potentialTargets) {
                net.minecraft.world.level.pathfinder.Path path = this.manatee.getNavigation().createPath(pos, 1);
                if (path != null) {
                    this.targetPos = pos;
                    return true;
                }
            }

            return false;
        }

        @Override
        public boolean canContinueToUse(){
            if (this.eatingTime > 0 && targetPos != null) {
                return this.manatee.distanceToSqr(targetPos.getX() + 0.5D, targetPos.getY() + 0.5D, targetPos.getZ() + 0.5D) < 2.0D;
            }
            return (eatingTime > 0 || (targetPos != null && !this.manatee.getNavigation().isDone()) && level.getBlockState(targetPos).is(ModTags.Blocks.MANATEE_CAN_EAT));
        }

        @Override
        public void start() {
            if (targetPos != null) {
                this.manatee.getNavigation().moveTo(targetPos.getX() + 0.5D, targetPos.getY() + 1.0D, targetPos.getZ() + 0.5D, 1.0D);
            }
            this.eatingTime = 0;
        }

        @Override
        public void stop() {
            this.manatee.getNavigation().stop();
            targetPos = null;
        }

        public int getEatingTime(){
            return eatingTime;
        }


        @Override
        public void tick() {
            if (targetPos == null) {
                return;
            }


            if (manatee.distanceToSqr(targetPos.getX() + 0.5D, targetPos.getY() + 0.5D, targetPos.getZ() + 0.5D) < 2.0D) {
                if (this.eatingTime == 0) {
                    this.eatingTime = 20;
                    this.manatee.level().broadcastEntityEvent(this.manatee, (byte) 10);
                    this.manatee.getNavigation().stop();

                } else {
                    this.eatingTime--;
                }
                if (this.eatingTime % 5 == 0) {
                    this.manatee.playSound(ModSounds.MANATEE_EAT.get(), 1.3F, 1.0F);
                }
                this.manatee.spawnParticles(targetPos, level, 2, new BlockParticleOption(ParticleTypes.BLOCK, level.getBlockState(targetPos)));
                this.manatee.getLookControl().setLookAt(targetPos.getX() + 0.5D, targetPos.getY() + 0.5D, targetPos.getZ() + 0.5D);

                if (this.eatingTime == 0) {
                    if (!this.level.isClientSide()) {
                        if (level.getBlockState(targetPos).is(ModTags.Blocks.MANATEE_CAN_EAT)) {
                            this.eatSuccess();
                        }

                    }
                }
            }
        }

        public void eatSuccess(){
            if (this.manatee.isBaby()){
                this.manatee.setAge(this.manatee.getAge() + 200);
            }
            this.manatee.playSound(ModSounds.MANATEE_EAT.get(), 1.0F, 1.0F);
            this.manatee.setHungryLevel(manatee.random.nextInt(1000));
            this.manatee.spawnParticles(targetPos, level, 25, new BlockParticleOption(ParticleTypes.BLOCK, level.getBlockState(targetPos)));

        }

        private final Predicate<BlockState> MANATEE_CAN_EAT = (pState) -> {
            if (pState.is(ModTags.Blocks.MANATEE_CAN_EAT)) {
                return true;
            } else {
                return false;
            }
        };
        private Optional<BlockPos> findNearbySeagrass() {
            return this.findNearestBlock(MANATEE_CAN_EAT, searchRange);
        }

        private Optional<BlockPos> findNearestBlock(Predicate<BlockState> pPredicate, double pDistance) {
            BlockPos blockpos = manatee.blockPosition();
            BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

            for(int i = 0; (double)i <= pDistance; i = i > 0 ? -i : 1 - i) {
                for(int j = 0; (double)j < pDistance; ++j) {
                    for(int k = 0; k <= j; k = k > 0 ? -k : 1 - k) {
                        for(int l = k < j && k > -j ? j : 0; l <= j; l = l > 0 ? -l : 1 - l) {
                            blockpos$mutableblockpos.setWithOffset(blockpos, k, i - 1, l);
                            if (blockpos.closerThan(blockpos$mutableblockpos, pDistance) && pPredicate.test(level.getBlockState(blockpos$mutableblockpos))) {
                                return Optional.of(blockpos$mutableblockpos);
                            }
                        }
                    }
                }
            }

            return Optional.empty();
        }
    }
}
