package net.pringlebeaver.riverbed.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.TropicalFish;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.pringlebeaver.riverbed.entity.ModEntities;
import net.pringlebeaver.riverbed.entity.custom.TroutEntity;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;

public class CustomFishBucketItem extends MobBucketItem {

    private final java.util.function.Supplier<? extends EntityType<?>> entityTypeSupplier;

    private final java.util.function.Supplier<? extends SoundEvent> emptySoundSupplier;

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        ChatFormatting[] chatFormatting = new ChatFormatting[]{ChatFormatting.ITALIC, ChatFormatting.GRAY};

        if (getFishType() == ModEntities.TROUT.get()) {
            CompoundTag compoundtag = pStack.getTag();
            if (compoundtag != null && compoundtag.contains("BucketVariantTag", 3)) {
               TroutEntity.Variant variant = TroutEntity.Variant.byId(compoundtag.getInt("BucketVariantTag"));
                pTooltipComponents.add(Component.translatable("entity.riverbed.trout.variant." + variant.toString().toLowerCase()).withStyle(chatFormatting));
            }
        }
    }

    public CustomFishBucketItem(java.util.function.Supplier<? extends EntityType<?>> entitySupplier, java.util.function.Supplier<? extends Fluid> fluidSupplier, java.util.function.Supplier<? extends SoundEvent> soundSupplier, Item.Properties properties) {
        super(entitySupplier, fluidSupplier,soundSupplier, properties);

        this.emptySoundSupplier = soundSupplier;
        this.entityTypeSupplier = entitySupplier;
    }
}
