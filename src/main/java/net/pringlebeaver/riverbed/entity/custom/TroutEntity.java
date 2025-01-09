package net.pringlebeaver.riverbed.entity.custom;

import com.google.errorprone.annotations.Var;
import com.mojang.serialization.Codec;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BiomeTags;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.animal.AbstractSchoolingFish;
import net.minecraft.world.entity.animal.FrogVariant;
import net.minecraft.world.entity.animal.frog.Frog;
import net.minecraft.world.entity.animal.horse.Llama;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.pringlebeaver.riverbed.item.ModItems;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.IntFunction;

public class TroutEntity extends AbstractSchoolingFish {


    private static final EntityDataAccessor<Integer> VARIANT_ID = SynchedEntityData.defineId(Frog.class, EntityDataSerializers.INT);

    public static final String BUCKET_VARIANT_TAG = "BucketVariantTag";



    public TroutEntity(EntityType<? extends AbstractSchoolingFish> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected @NotNull SoundEvent getFlopSound() {
        return SoundEvents.COD_FLOP;
    }

    @Override
    public @NotNull ItemStack getBucketItemStack() {
        return new ItemStack(ModItems.TROUT_BUCKET.get());
    }

    public void saveToBucketTag(ItemStack bucket) {
        super.saveToBucketTag(bucket);
        CompoundTag compoundNbt = bucket.getOrCreateTag();
        compoundNbt.putInt("BucketVariantTag", this.getVariant().id);


    }

    // Variations


    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(VARIANT_ID, 1);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("Variant", this.getVariant().id);

    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setVariant(TroutEntity.Variant.byId(pCompound.getInt("Variant")));

    }


    public TroutEntity.Variant getVariant() {
        return TroutEntity.Variant.byId(this.entityData.get(VARIANT_ID));
    }


    public void setVariant(TroutEntity.Variant variant) {
        this.entityData.set(VARIANT_ID, variant.id);

    }




    public static enum Variant implements StringRepresentable {
        RAINBOW(0, "rainbow"),
        BROOK(1, "brook"),
        BULL(2, "bull"),
        GOLDEN(3, "golden");


        public static final Codec<TroutEntity.Variant> CODEC = StringRepresentable.fromEnum(TroutEntity.Variant::values);
        private static final IntFunction<TroutEntity.Variant> BY_ID = ByIdMap.continuous(TroutEntity.Variant::getId, values(), ByIdMap.OutOfBoundsStrategy.CLAMP);
        final int id;
        private final String name;

        private Variant(int pId, String pName) {
            this.id = pId;
            this.name = pName;
        }

        public int getId() {
            return this.id;
        }

        public static TroutEntity.Variant byId(int pId) {
            return BY_ID.apply(pId);
        }

        public String getSerializedName() {
            return this.name;
        }
    }


    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData, @Nullable CompoundTag pDataTag) {
        List<Variant> riverVariants = List.of(Variant.RAINBOW, Variant.RAINBOW, Variant.BROOK);

        List<Variant> frozenRiverVariants = List.of(Variant.RAINBOW, Variant.RAINBOW, Variant.BULL);

        double goldenChance = 0.05;



        if (pReason == MobSpawnType.BUCKET && pDataTag != null && pDataTag.contains("BucketVariantTag")) {
            this.setVariant(Variant.byId(pDataTag.getInt("BucketVariantTag")));
            return pSpawnData;
        } else {
            RandomSource RANDOM_SOURCE = pLevel.getRandom();
            Holder<Biome> holder = pLevel.getBiome(this.blockPosition());
            TroutEntity.Variant TROUT_VARIANT;

            if (random.nextDouble() < goldenChance) {
                TROUT_VARIANT = Variant.GOLDEN;
            } else {
                if (holder.is(Biomes.FROZEN_RIVER)) {
                    TROUT_VARIANT = Util.getRandom(frozenRiverVariants, RANDOM_SOURCE);
                } else {
                    TROUT_VARIANT = Util.getRandom(riverVariants, RANDOM_SOURCE);
                }
            }

            this.setVariant(TROUT_VARIANT);
        }

        return super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
    }

    public static boolean canSpawn(EntityType<TroutEntity> entityType, LevelAccessor levelAccessor, MobSpawnType spawnType, BlockPos blockPos, RandomSource random) {
        return checkSurfaceWaterAnimalSpawnRules(entityType, levelAccessor, spawnType, blockPos, random);
    }
}
