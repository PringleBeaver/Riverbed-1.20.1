package net.pringlebeaver.riverbed.sound;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.pringlebeaver.riverbed.RiverbedMain;

public class ModSounds {

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, RiverbedMain.MOD_ID);

    public static final RegistryObject<SoundEvent> MUSIC_DISC_DELTA = registerSoundEvents("music_disc_delta");

    public static final RegistryObject<SoundEvent> MANATEE_EAT = registerSoundEvents("manatee_eat");

    public static final RegistryObject<SoundEvent> MANATEE_EAT_SUCCESS = registerSoundEvents("manatee_eat_success");


    private static RegistryObject<SoundEvent> registerSoundEvents(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(RiverbedMain.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
