package net.pringlebeaver.riverbed.world.biomemodifiers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.pringlebeaver.riverbed.RiverbedMain;

public class ModBiomeModifiers {

    public static final DeferredRegister<Codec<? extends BiomeModifier>> BIOME_MODIFIER_SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, RiverbedMain.MOD_ID);



    public static final RegistryObject<Codec<AddRiverFeatures>> ADD_RIVER_FEATURES_CODEC = BIOME_MODIFIER_SERIALIZERS.register("add_river_features", () ->
            RecordCodecBuilder.create(builder -> builder.group(
                    PlacedFeature.CODEC.fieldOf("feature").forGetter(AddRiverFeatures::feature),
                    GenerationStep.Decoration.CODEC.fieldOf("step").forGetter(AddRiverFeatures::step),
                    Codec.STRING.fieldOf("variant").forGetter(AddRiverFeatures::variant)


            ).apply(builder, AddRiverFeatures::new)));

    public static void register(IEventBus eventBus) {
        BIOME_MODIFIER_SERIALIZERS.register(eventBus);
    }
}
