package net.pringlebeaver.riverbed.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.pringlebeaver.riverbed.RiverbedMain;
import net.pringlebeaver.riverbed.entity.ModEntities;
import net.pringlebeaver.riverbed.item.custom.CustomFishBucketItem;
import net.pringlebeaver.riverbed.item.custom.CustomSpawnEggItem;
import net.pringlebeaver.riverbed.item.custom.HyacinthMilkItem;
import net.pringlebeaver.riverbed.sound.ModSounds;
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

    public static final RegistryObject<Item> FISH_CHOWDER = ITEMS.register("fish_chowder", () -> new BowlFoodItem(new Item.Properties().food(ModFoods.FISH_CHOWDER).stacksTo(1)));

    // Drinks

    public static final RegistryObject<Item> HYACINTH_MILK_BUCKET = ITEMS.register("hyacinth_milk_bucket", () -> new HyacinthMilkItem(new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));

    // Mob Buckets
   public static final RegistryObject<Item> TROUT_BUCKET = ITEMS.register("trout_bucket", () -> new CustomFishBucketItem(ModEntities.TROUT, () -> Fluids.WATER, () -> SoundEvents.BUCKET_EMPTY_FISH,(new Item.Properties().stacksTo(1) )));

   // Materials

    public static final RegistryObject<Item> WOVEN_GRASS = ITEMS.register("woven_grass", () -> new Item(new Item.Properties()));

    //Basket Fragments



    public static final RegistryObject<Item> WHITE_STRIPE_BASKET_FRAGMENT = ITEMS.register("white_stripe_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> RED_STRIPE_BASKET_FRAGMENT = ITEMS.register("red_stripe_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> PURPLE_STRIPE_BASKET_FRAGMENT = ITEMS.register("purple_stripe_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> PINK_STRIPE_BASKET_FRAGMENT = ITEMS.register("pink_stripe_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> ORANGE_STRIPE_BASKET_FRAGMENT = ITEMS.register("orange_stripe_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> MAGENTA_STRIPE_BASKET_FRAGMENT = ITEMS.register("magenta_stripe_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> LIME_STRIPE_BASKET_FRAGMENT = ITEMS.register("lime_stripe_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> LIGHT_GRAY_STRIPE_BASKET_FRAGMENT = ITEMS.register("light_gray_stripe_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> LIGHT_BLUE_STRIPE_BASKET_FRAGMENT = ITEMS.register("light_blue_stripe_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> GREEN_STRIPE_BASKET_FRAGMENT = ITEMS.register("green_stripe_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> GRAY_STRIPE_BASKET_FRAGMENT = ITEMS.register("gray_stripe_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> CYAN_STRIPE_BASKET_FRAGMENT = ITEMS.register("cyan_stripe_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> BROWN_STRIPE_BASKET_FRAGMENT = ITEMS.register("brown_stripe_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> BLUE_STRIPE_BASKET_FRAGMENT = ITEMS.register("blue_stripe_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> BLACK_STRIPE_BASKET_FRAGMENT = ITEMS.register("black_stripe_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
            pTooltipComponents.add(getBasketTooltip(pStack));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    });

    public static final RegistryObject<Item> YELLOW_STRIPE_BASKET_FRAGMENT = ITEMS.register("yellow_stripe_basket_fragment", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
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
}
