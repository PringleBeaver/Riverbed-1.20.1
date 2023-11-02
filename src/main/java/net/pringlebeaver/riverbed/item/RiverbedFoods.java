package net.pringlebeaver.riverbed.item;

import net.minecraft.world.food.FoodProperties;

public class RiverbedFoods {
    public static final FoodProperties TROUT = new FoodProperties.Builder().nutrition(2).saturationMod(0.1F).build();
    public static final FoodProperties COOKED_TROUT = new FoodProperties.Builder().nutrition(6).saturationMod(0.8F).build();
}
