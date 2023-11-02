package net.pringlebeaver.riverbed.item;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.pringlebeaver.riverbed.RiverbedMain;
import net.pringlebeaver.riverbed.entity.RiverbedEntities;
import net.pringlebeaver.riverbed.item.custom.CustomFishBucketItem;
import net.pringlebeaver.riverbed.sound.RiverbedSounds;

public class RiverbedItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, RiverbedMain.MOD_ID);


    // Items

    public static final RegistryObject<Item> MUSIC_DISC_DELTA = ITEMS.register("music_disc_delta",
            () -> new RecordItem(6, RiverbedSounds.MUSIC_DISC_DELTA, new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 3140));

    // Foods

    public static final RegistryObject<Item> TROUT = ITEMS.register("trout",
            () -> new Item(new Item.Properties().food(RiverbedFoods.TROUT)));

    public static final RegistryObject<Item> COOKED_TROUT = ITEMS.register("cooked_trout",
            () -> new Item(new Item.Properties().food(RiverbedFoods.COOKED_TROUT)));

    public static final RegistryObject<Item> FISH_CHOWDER = ITEMS.register("fish_chowder",
            () -> new BowlFoodItem(new Item.Properties().food(RiverbedFoods.FISH_CHOWDER).stacksTo(1)));

    // Mob Buckets
   public static final RegistryObject<Item> TROUT_BUCKET = ITEMS.register("trout_bucket",
            () -> new CustomFishBucketItem(RiverbedEntities.TROUT, () -> Fluids.WATER, () -> SoundEvents.BUCKET_EMPTY_FISH,(new Item.Properties().stacksTo(1) )));

    public static void register(IEventBus eventBus) {ITEMS.register(eventBus);
    }
}
