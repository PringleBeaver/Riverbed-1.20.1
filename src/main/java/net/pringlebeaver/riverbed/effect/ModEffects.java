package net.pringlebeaver.riverbed.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.pringlebeaver.riverbed.RiverbedMain;
import net.pringlebeaver.riverbed.effect.custom.AnglingEffect;
import net.pringlebeaver.riverbed.effect.custom.ManateesMightEffect;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, RiverbedMain.MOD_ID);

    public static final RegistryObject<MobEffect> ANGLING = MOB_EFFECTS.register("angling", () -> new AnglingEffect(MobEffectCategory.BENEFICIAL, 4294509));

    public static final RegistryObject<MobEffect> MANATEES_MIGHT = MOB_EFFECTS.register("manatees_might", () -> new ManateesMightEffect(MobEffectCategory.BENEFICIAL, 8490127));

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
