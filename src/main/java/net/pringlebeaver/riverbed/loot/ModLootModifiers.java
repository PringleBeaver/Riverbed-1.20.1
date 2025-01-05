package net.pringlebeaver.riverbed.loot;

import com.mojang.serialization.Codec;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.pringlebeaver.riverbed.RiverbedMain;

public class ModLootModifiers {
public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIER_SERIALIZERS =
        DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, RiverbedMain.MOD_ID);

public static final RegistryObject<Codec<? extends IGlobalLootModifier>> ANGLING_EFFECT_MODIFIER =
        LOOT_MODIFIER_SERIALIZERS.register("angling_effect_modifier", AnglingEffectModifier.CODEC);

        public static void register(IEventBus eventBus) {
            LOOT_MODIFIER_SERIALIZERS.register(eventBus);
        }
}