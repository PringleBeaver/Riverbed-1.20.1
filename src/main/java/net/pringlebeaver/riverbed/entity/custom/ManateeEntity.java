package net.pringlebeaver.riverbed.entity.custom;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
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
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.IForgeShearable;
import net.minecraftforge.fml.common.Mod;
import net.pringlebeaver.riverbed.block.ModBlocks;
import net.pringlebeaver.riverbed.effect.ModEffects;
import net.pringlebeaver.riverbed.entity.ModEntities;
import net.pringlebeaver.riverbed.item.ModItems;
import net.pringlebeaver.riverbed.particle.ModParticles;
import net.pringlebeaver.riverbed.sound.ModSounds;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

public class ManateeEntity extends Animal implements IForgeShearable  {
    private static final EntityDataAccessor<Boolean> ALGAE = SynchedEntityData.defineId(ManateeEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> BLOOMING = SynchedEntityData.defineId(ManateeEntity.class, EntityDataSerializers.BOOLEAN);

    private static final EntityDataAccessor<Integer> MOISTNESS_LEVEL = SynchedEntityData.defineId(ManateeEntity.class, EntityDataSerializers.INT);

    private static final EntityDataAccessor<Integer> ALGAE_GROWTH_TIME = SynchedEntityData.defineId(ManateeEntity.class, EntityDataSerializers.INT);

    private static final int TOTAL_AIR_SUPPLY = 6000;

    private static final int TOTAL_GROWTH_TIME = 10000;

    public ManateeEntity(EntityType<? extends Animal> pEntityType, Level pLevel) {

        super(pEntityType, pLevel);
        this.moveControl = new SmoothSwimmingMoveControl(this, 85, 10, 0.02F, 0.1F, true);
        this.lookControl = new SmoothSwimmingLookControl(this, 5);
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
    }

    public MobType getMobType() {
        return MobType.WATER;
    }

    // Sounds


    @org.jetbrains.annotations.Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return ModSounds.MANATEE_CHIRP.get();
    }

    @org.jetbrains.annotations.Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return ModSounds.MANATEE_HURT.get();
    }

    @org.jetbrains.annotations.Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.MANATEE_DEATH.get();
    }

    public boolean canBreatheUnderwater() {
        return true;
    }

    public boolean isPushedByFluid() {
        return false;
    }



    public void shear(SoundSource pCategory) {
        this.level().playSound((Player)null, this, SoundEvents.SHEEP_SHEAR, pCategory, 1.0F, 1.0F);
        spawnParticles(this.blockPosition(), this.level(), 200, ModParticles.ALGAE_PARTICLES.get());
        this.setAlgae(false);
        this.setBlooming(false);
        int i = 6 + this.random.nextInt(3);

        for(int j = 0; j < i; ++j) {
            ItemEntity itementity = this.spawnAtLocation(ModBlocks.ALGAE.get().asItem());
            if (itementity != null) {
                itementity.setDeltaMovement(itementity.getDeltaMovement().add((double)((this.random.nextFloat() - this.random.nextFloat()) * 0.1F), (double)(this.random.nextFloat() * 0.05F), (double)((this.random.nextFloat() - this.random.nextFloat()) * 0.1F)));
            }
        }


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
        } else if (itemstack.is(ModBlocks.WATER_HYACINTH.get().asItem()) && isAlgae() && !isBlooming()) {
            itemstack.shrink(1);
            int successChance = this.random.nextInt(6);

            if (successChance == 0) {
                if (!level().isClientSide) {
                    level().playSound(this, this.blockPosition(), ModSounds.MANATEE_EAT_SUCCESS.get(), this.getSoundSource(), 1.0F, 1.0F);
                    spawnParticles(this.blockPosition(), this.level(),100, ModParticles.ALGAE_PARTICLES.get());
                    spawnParticles(this.blockPosition(), this.level(), 50, ParticleTypes.EFFECT);
                    this.setBlooming(true);
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

    private void spawnParticles(BlockPos blockPos, Level level, Integer amount, ParticleType<?> particleType) {
        if (level instanceof ServerLevel serverLevel) {


            serverLevel.sendParticles(
                    (SimpleParticleType) particleType,
                        blockPos.getX() + 0.5,
                        blockPos.getY()+ 0.75,
                        blockPos.getZ()+ 0.5,
                        amount, 0.5, 0.5, 0.5,
                        0.01D);
        }
    }

    protected void handleAirSupply(int pAirSupply) {
        if (this.isAlive() && !this.isInWaterRainOrBubble()) {
            this.setAirSupply(pAirSupply - 1);
            if (this.getAirSupply() == -20) {
                this.setAirSupply(0);
                this.hurt(this.damageSources().dryOut(), 2.0F);
            }
        } else {
            this.setAirSupply(this.getMaxAirSupply());
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
        this.entityData.define(ALGAE, true);
        this.entityData.define(BLOOMING, false);
        this.entityData.define(ALGAE_GROWTH_TIME, 0);
    }
    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("Moistness", this.getMoistnessLevel());
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

        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(2, new BreedGoal(this, 0.8D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.1D, Ingredient.of(Items.SEAGRASS), false));


        this.goalSelector.addGoal(4, new RandomSwimmingGoal(this, 1.0D, 10));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
    }

    protected PathNavigation createNavigation( Level pLevel) {
        return new WaterBoundPathNavigation(this, pLevel);
    }


    public int getMaxHeadXRot() {
        return 1;
    }


    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 16.0D).add(Attributes.MOVEMENT_SPEED, (double)0.5F);
    }


    protected int increaseAirSupply(int pCurrentAir) {
        return this.getMaxAirSupply();
    }

    public void tick() {
        super.tick();
        if (this.isNoAi()) {
            this.setAirSupply(this.getMaxAirSupply());
        } else {
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

                if (this.onGround()) {
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
}
