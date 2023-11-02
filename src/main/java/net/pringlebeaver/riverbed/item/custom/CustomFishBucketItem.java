package net.pringlebeaver.riverbed.item.custom;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.level.material.Fluid;

import java.util.function.Supplier;

public class CustomFishBucketItem extends MobBucketItem {

    private final java.util.function.Supplier<? extends EntityType<?>> entityTypeSupplier;

    private final java.util.function.Supplier<? extends SoundEvent> emptySoundSupplier;


    public CustomFishBucketItem(java.util.function.Supplier<? extends EntityType<?>> entitySupplier, java.util.function.Supplier<? extends Fluid> fluidSupplier, java.util.function.Supplier<? extends SoundEvent> soundSupplier, Item.Properties properties) {
        super(entitySupplier, fluidSupplier,soundSupplier, properties);

        this.emptySoundSupplier = soundSupplier;
        this.entityTypeSupplier = entitySupplier;
    }
}
