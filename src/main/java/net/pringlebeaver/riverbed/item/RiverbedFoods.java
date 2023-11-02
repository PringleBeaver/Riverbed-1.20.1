package net.pringlebeaver.riverbed.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class RiverbedFoods {
    public static final FoodProperties TROUT = new FoodProperties.Builder().nutrition(2).saturationMod(0.1F).build();
    public static final FoodProperties COOKED_TROUT = new FoodProperties.Builder().nutrition(6).saturationMod(0.8F).build();

    public static final FoodProperties FISH_CHOWDER = stew(10).effect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 100, 1), 1.0F).alwaysEat().build();

    private static FoodProperties.Builder stew(int pNutrition) {
        return (new FoodProperties.Builder()).nutrition(pNutrition).saturationMod(0.6F);
    }

}
