package net.pringlebeaver.riverbed.world;

import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockColumnConfiguration;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.pringlebeaver.riverbed.RiverbedMain;
import net.pringlebeaver.riverbed.world.feature.RiverLogFeature;

public class ModFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, RiverbedMain.MOD_ID);

    public static final RegistryObject<Feature<?>> RIVER_LOG = FEATURES.register("river_log", () -> new RiverLogFeature(BlockColumnConfiguration.CODEC));

    public static void register(IEventBus eventBus) {
        FEATURES.register(eventBus);
    }
}
