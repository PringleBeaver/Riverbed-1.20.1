package net.pringlebeaver.riverbed.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.RecordItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.pringlebeaver.riverbed.RiverbedMain;
import net.pringlebeaver.riverbed.sound.RiverbedSounds;

public class RiverbedItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, RiverbedMain.MOD_ID);


    // Items

    public static final RegistryObject<Item> COPPER_PAN = ITEMS.register("copper_pan",
            () -> new Item(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> MUSIC_DISC_DELTA = ITEMS.register("music_disc_delta",
            () -> new RecordItem(6, RiverbedSounds.MUSIC_DISC_DELTA, new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 3140));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
