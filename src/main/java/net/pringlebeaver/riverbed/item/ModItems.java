package net.pringlebeaver.riverbed.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.pringlebeaver.riverbed.RiverbedMain;
import net.pringlebeaver.riverbed.effect.ModEffects;
import net.pringlebeaver.riverbed.entity.ModEntities;
import net.pringlebeaver.riverbed.item.custom.CustomFishBucketItem;
import net.pringlebeaver.riverbed.item.custom.CustomSpawnEggItem;
import net.pringlebeaver.riverbed.item.custom.HyacinthMilkItem;
import net.pringlebeaver.riverbed.sound.ModSounds;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, RiverbedMain.MOD_ID);


    // Items

    public static final RegistryObject<Item> MUSIC_DISC_DELTA = ITEMS.register("music_disc_delta", () -> new RecordItem(6, ModSounds.MUSIC_DISC_DELTA, new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 3140));

    // Foods

    public static final RegistryObject<Item> TROUT = ITEMS.register("trout", () -> new Item(new Item.Properties().food(ModFoods.TROUT)));

    public static final RegistryObject<Item> COOKED_TROUT = ITEMS.register("cooked_trout", () -> new Item(new Item.Properties().food(ModFoods.COOKED_TROUT)));

    public static final RegistryObject<Item> FISH_CHOWDER = ITEMS.register("fish_chowder", () -> new BowlFoodItem(new Item.Properties().food(ModFoods.FISH_CHOWDER).stacksTo(getSoupStackSize())){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            PotionUtils.addPotionTooltip(List.of(ModFoods.chowderEffect), pTooltipComponents, 1.0F);
        }
    });



    // Drinks

    public static final RegistryObject<Item> HYACINTH_MILK_BUCKET = ITEMS.register("hyacinth_milk_bucket", () -> new HyacinthMilkItem(new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            PotionUtils.addPotionTooltip(List.of(HyacinthMilkItem.hyacinthMilkEffect), pTooltipComponents, 1.0F);
        }
    });

    // Mob Buckets
   public static final RegistryObject<Item> TROUT_BUCKET = ITEMS.register("trout_bucket", () -> new CustomFishBucketItem(ModEntities.TROUT, () -> Fluids.WATER, () -> SoundEvents.BUCKET_EMPTY_FISH,(new Item.Properties().stacksTo(1) )));

   // Materials

    public static final RegistryObject<Item> WOVEN_GRASS = ITEMS.register("woven_grass", () -> new Item(new Item.Properties()));

    //Basket Fragments

    public static final RegistryObject<Item> STRIPE_BASKET_FRAGMENT = ITEMS.register("stripe_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> DOTS_BASKET_FRAGMENT = ITEMS.register("dots_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> DOUBLE_STRIPE_BASKET_FRAGMENT = ITEMS.register("double_stripe_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> ZIGZAG_BASKET_FRAGMENT = ITEMS.register("zigzag_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> SOLID_BASKET_FRAGMENT = ITEMS.register("solid_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> RED_STRIPE_BASKET_FRAGMENT = ITEMS.register("red_stripe_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> RED_DOTS_BASKET_FRAGMENT = ITEMS.register("red_dots_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> RED_DOUBLE_STRIPE_BASKET_FRAGMENT = ITEMS.register("red_double_stripe_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> RED_ZIGZAG_BASKET_FRAGMENT = ITEMS.register("red_zigzag_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> RED_SOLID_BASKET_FRAGMENT = ITEMS.register("red_solid_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> ORANGE_STRIPE_BASKET_FRAGMENT = ITEMS.register("orange_stripe_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> ORANGE_DOTS_BASKET_FRAGMENT = ITEMS.register("orange_dots_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> ORANGE_DOUBLE_STRIPE_BASKET_FRAGMENT = ITEMS.register("orange_double_stripe_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> ORANGE_ZIGZAG_BASKET_FRAGMENT = ITEMS.register("orange_zigzag_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> ORANGE_SOLID_BASKET_FRAGMENT = ITEMS.register("orange_solid_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> YELLOW_STRIPE_BASKET_FRAGMENT = ITEMS.register("yellow_stripe_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> YELLOW_DOTS_BASKET_FRAGMENT = ITEMS.register("yellow_dots_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> YELLOW_DOUBLE_STRIPE_BASKET_FRAGMENT = ITEMS.register("yellow_double_stripe_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> YELLOW_ZIGZAG_BASKET_FRAGMENT = ITEMS.register("yellow_zigzag_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> YELLOW_SOLID_BASKET_FRAGMENT = ITEMS.register("yellow_solid_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> LIME_STRIPE_BASKET_FRAGMENT = ITEMS.register("lime_stripe_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> LIME_DOTS_BASKET_FRAGMENT = ITEMS.register("lime_dots_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> LIME_DOUBLE_STRIPE_BASKET_FRAGMENT = ITEMS.register("lime_double_stripe_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> LIME_ZIGZAG_BASKET_FRAGMENT = ITEMS.register("lime_zigzag_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> LIME_SOLID_BASKET_FRAGMENT = ITEMS.register("lime_solid_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> CYAN_STRIPE_BASKET_FRAGMENT = ITEMS.register("cyan_stripe_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> CYAN_DOTS_BASKET_FRAGMENT = ITEMS.register("cyan_dots_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> CYAN_DOUBLE_STRIPE_BASKET_FRAGMENT = ITEMS.register("cyan_double_stripe_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> CYAN_ZIGZAG_BASKET_FRAGMENT = ITEMS.register("cyan_zigzag_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> CYAN_SOLID_BASKET_FRAGMENT = ITEMS.register("cyan_solid_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> LIGHT_BLUE_STRIPE_BASKET_FRAGMENT = ITEMS.register("light_blue_stripe_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> LIGHT_BLUE_DOTS_BASKET_FRAGMENT = ITEMS.register("light_blue_dots_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> LIGHT_BLUE_DOUBLE_STRIPE_BASKET_FRAGMENT = ITEMS.register("light_blue_double_stripe_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> LIGHT_BLUE_ZIGZAG_BASKET_FRAGMENT = ITEMS.register("light_blue_zigzag_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> LIGHT_BLUE_SOLID_BASKET_FRAGMENT = ITEMS.register("light_blue_solid_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> BLUE_STRIPE_BASKET_FRAGMENT = ITEMS.register("blue_stripe_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> BLUE_DOTS_BASKET_FRAGMENT = ITEMS.register("blue_dots_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> BLUE_DOUBLE_STRIPE_BASKET_FRAGMENT = ITEMS.register("blue_double_stripe_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> BLUE_ZIGZAG_BASKET_FRAGMENT = ITEMS.register("blue_zigzag_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> BLUE_SOLID_BASKET_FRAGMENT = ITEMS.register("blue_solid_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> PURPLE_STRIPE_BASKET_FRAGMENT = ITEMS.register("purple_stripe_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> PURPLE_DOTS_BASKET_FRAGMENT = ITEMS.register("purple_dots_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> PURPLE_DOUBLE_STRIPE_BASKET_FRAGMENT = ITEMS.register("purple_double_stripe_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> PURPLE_ZIGZAG_BASKET_FRAGMENT = ITEMS.register("purple_zigzag_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> PURPLE_SOLID_BASKET_FRAGMENT = ITEMS.register("purple_solid_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> MAGENTA_STRIPE_BASKET_FRAGMENT = ITEMS.register("magenta_stripe_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> MAGENTA_DOTS_BASKET_FRAGMENT = ITEMS.register("magenta_dots_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> MAGENTA_DOUBLE_STRIPE_BASKET_FRAGMENT = ITEMS.register("magenta_double_stripe_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> MAGENTA_ZIGZAG_BASKET_FRAGMENT = ITEMS.register("magenta_zigzag_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> MAGENTA_SOLID_BASKET_FRAGMENT = ITEMS.register("magenta_solid_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> PINK_STRIPE_BASKET_FRAGMENT = ITEMS.register("pink_stripe_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> PINK_DOTS_BASKET_FRAGMENT = ITEMS.register("pink_dots_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> PINK_DOUBLE_STRIPE_BASKET_FRAGMENT = ITEMS.register("pink_double_stripe_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> PINK_ZIGZAG_BASKET_FRAGMENT = ITEMS.register("pink_zigzag_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> PINK_SOLID_BASKET_FRAGMENT = ITEMS.register("pink_solid_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> BROWN_STRIPE_BASKET_FRAGMENT = ITEMS.register("brown_stripe_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> BROWN_DOTS_BASKET_FRAGMENT = ITEMS.register("brown_dots_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> BROWN_DOUBLE_STRIPE_BASKET_FRAGMENT = ITEMS.register("brown_double_stripe_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> BROWN_ZIGZAG_BASKET_FRAGMENT = ITEMS.register("brown_zigzag_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> BROWN_SOLID_BASKET_FRAGMENT = ITEMS.register("brown_solid_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> BLACK_STRIPE_BASKET_FRAGMENT = ITEMS.register("black_stripe_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> BLACK_DOTS_BASKET_FRAGMENT = ITEMS.register("black_dots_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> BLACK_DOUBLE_STRIPE_BASKET_FRAGMENT = ITEMS.register("black_double_stripe_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> BLACK_ZIGZAG_BASKET_FRAGMENT = ITEMS.register("black_zigzag_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> BLACK_SOLID_BASKET_FRAGMENT = ITEMS.register("black_solid_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> GRAY_STRIPE_BASKET_FRAGMENT = ITEMS.register("gray_stripe_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> GRAY_DOTS_BASKET_FRAGMENT = ITEMS.register("gray_dots_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> GRAY_DOUBLE_STRIPE_BASKET_FRAGMENT = ITEMS.register("gray_double_stripe_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> GRAY_ZIGZAG_BASKET_FRAGMENT = ITEMS.register("gray_zigzag_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> GRAY_SOLID_BASKET_FRAGMENT = ITEMS.register("gray_solid_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> LIGHT_GRAY_STRIPE_BASKET_FRAGMENT = ITEMS.register("light_gray_stripe_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> LIGHT_GRAY_DOTS_BASKET_FRAGMENT = ITEMS.register("light_gray_dots_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> LIGHT_GRAY_DOUBLE_STRIPE_BASKET_FRAGMENT = ITEMS.register("light_gray_double_stripe_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> LIGHT_GRAY_ZIGZAG_BASKET_FRAGMENT = ITEMS.register("light_gray_zigzag_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> LIGHT_GRAY_SOLID_BASKET_FRAGMENT = ITEMS.register("light_gray_solid_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> WHITE_STRIPE_BASKET_FRAGMENT = ITEMS.register("white_stripe_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> WHITE_DOTS_BASKET_FRAGMENT = ITEMS.register("white_dots_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> WHITE_DOUBLE_STRIPE_BASKET_FRAGMENT = ITEMS.register("white_double_stripe_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> WHITE_ZIGZAG_BASKET_FRAGMENT = ITEMS.register("white_zigzag_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> WHITE_SOLID_BASKET_FRAGMENT = ITEMS.register("white_solid_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> GREEN_STRIPE_BASKET_FRAGMENT = ITEMS.register("green_stripe_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> GREEN_DOTS_BASKET_FRAGMENT = ITEMS.register("green_dots_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> GREEN_DOUBLE_STRIPE_BASKET_FRAGMENT = ITEMS.register("green_double_stripe_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> GREEN_ZIGZAG_BASKET_FRAGMENT = ITEMS.register("green_zigzag_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> GREEN_SOLID_BASKET_FRAGMENT = ITEMS.register("green_solid_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });













    // Spawn Eggs

    public static final RegistryObject<Item> TROUT_SPAWN_EGG = ITEMS.register("trout_spawn_egg", () -> new CustomSpawnEggItem(ModEntities.TROUT, 8227656, 14709092, new Item.Properties()));

    public static final RegistryObject<Item> MANATEE_SPAWN_EGG = ITEMS.register("manatee_spawn_egg", () -> new CustomSpawnEggItem(ModEntities.MANATEE, 10002851, 6456106, new Item.Properties()));
    public static void register(IEventBus eventBus) {ITEMS.register(eventBus);
    }
    
    static Component getBasketTooltip(ItemStack stack) {
        return Component.translatable("tooltip.riverbed." + stack.getItem().toString()).withStyle(style -> style.withColor(ChatFormatting.GRAY));
    }

    static int getSoupStackSize() {
        if(ModList.get().isLoaded("farmersdelight")){
            return 16;
        } else {
            return 1;
        }
    }
}
