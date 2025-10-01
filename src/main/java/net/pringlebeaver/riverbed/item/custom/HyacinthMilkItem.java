package net.pringlebeaver.riverbed.item.custom;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MilkBucketItem;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.common.Mod;
import net.pringlebeaver.riverbed.effect.ModEffects;
import net.pringlebeaver.riverbed.effect.custom.ManateesTouchEffect;
import org.jetbrains.annotations.NotNull;

public class HyacinthMilkItem extends MilkBucketItem {

    public static final MobEffectInstance hyacinthMilkEffect = new MobEffectInstance(ModEffects.MANATEES_TOUCH.get(), 6000, 0);


    public HyacinthMilkItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public @NotNull ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving) {
        if (!pLevel.isClientSide) {
            pEntityLiving.addEffect(hyacinthMilkEffect, pEntityLiving);
        }
        return super.finishUsingItem(pStack, pLevel, pEntityLiving);
    }
}
