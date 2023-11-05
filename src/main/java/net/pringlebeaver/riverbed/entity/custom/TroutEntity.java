package net.pringlebeaver.riverbed.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.animal.AbstractSchoolingFish;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.pringlebeaver.riverbed.item.RiverbedItems;

import java.util.Random;

public class TroutEntity extends AbstractSchoolingFish {


    public TroutEntity(EntityType<? extends AbstractSchoolingFish> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected SoundEvent getFlopSound() {
        return SoundEvents.COD_FLOP;
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(RiverbedItems.TROUT_BUCKET.get());
    }

    public static boolean canSpawn (EntityType<TroutEntity> entityType, LevelAccessor levelAccessor, MobSpawnType spawnType, BlockPos blockPos, RandomSource random) {
        return checkSurfaceWaterAnimalSpawnRules(entityType, levelAccessor, spawnType, blockPos, random);
    }
}
