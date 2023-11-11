package net.pringlebeaver.riverbed.item;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.*;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.pringlebeaver.riverbed.RiverbedMain;
import net.pringlebeaver.riverbed.entity.ModEntities;
import net.pringlebeaver.riverbed.item.custom.CustomFishBucketItem;
import net.pringlebeaver.riverbed.item.custom.CustomSpawnEggItem;
import net.pringlebeaver.riverbed.sound.ModSounds;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, RiverbedMain.MOD_ID);


    // Items

    public static final RegistryObject<Item> MUSIC_DISC_DELTA = ITEMS.register("music_disc_delta", () -> new RecordItem(6, ModSounds.MUSIC_DISC_DELTA, new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 3140));

    // Foods

    public static final RegistryObject<Item> TROUT = ITEMS.register("trout", () -> new Item(new Item.Properties().food(ModFoods.TROUT)));

    public static final RegistryObject<Item> COOKED_TROUT = ITEMS.register("cooked_trout", () -> new Item(new Item.Properties().food(ModFoods.COOKED_TROUT)));

    public static final RegistryObject<Item> FISH_CHOWDER = ITEMS.register("fish_chowder", () -> new BowlFoodItem(new Item.Properties().food(ModFoods.FISH_CHOWDER).stacksTo(1)));

    // Mob Buckets
   public static final RegistryObject<Item> TROUT_BUCKET = ITEMS.register("trout_bucket", () -> new CustomFishBucketItem(ModEntities.TROUT, () -> Fluids.WATER, () -> SoundEvents.BUCKET_EMPTY_FISH,(new Item.Properties().stacksTo(1) )));

   // Spawn Eggs

    public static final RegistryObject<Item> TROUT_SPAWN_EGG = ITEMS.register("trout_spawn_egg", () -> new CustomSpawnEggItem(ModEntities.TROUT, 8227656, 14709092, new Item.Properties()));
    public static void register(IEventBus eventBus) {ITEMS.register(eventBus);
    }
}
