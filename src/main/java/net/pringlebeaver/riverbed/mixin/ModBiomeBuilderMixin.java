package net.pringlebeaver.riverbed.mixin;

import com.mojang.datafixers.util.Pair;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.OverworldBiomeBuilder;
import net.pringlebeaver.riverbed.RiverbedMain;
import net.pringlebeaver.riverbed.world.biome.ModBiomes;
import net.pringlebeaver.riverbed.world.biome.custom.ModBiomeBuilder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import terrablender.api.ParameterUtils;

import java.util.function.Consumer;

// This river biome implementation is fully based on Climate Rivers and also River Redux, full credit goes to Fuzs for providing the original code, and Jaskarth for inspiring it.
// Please see: https://github.com/jaskarth/RiverRedux/blob/master/src/main/java/supercoder79/riverredux/mixin/MixinOverworldBiomeBuilder.java, and https://github.com/Fuzss/climaterivers/blob/main/1.20.1/Common/src/main/java/fuzs/climaterivers/mixin/OverworldBiomeBuilderMixin.java
@Mixin(OverworldBiomeBuilder.class)
abstract class ModBiomeBuilderMixin {

    @Inject(method = "addSurfaceBiome", at = @At("HEAD"), cancellable = true)
    private void addSurfaceBiome(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer, Climate.Parameter temperature, Climate.Parameter humidity, Climate.Parameter continentalness, Climate.Parameter erosion, Climate.Parameter depth, float weirdness, ResourceKey<Biome> key, CallbackInfo callback) {

        if (RiverbedMain.isLoadComplete) {

            if (key == Biomes.RIVER && ParameterUtils.Temperature.UNFROZEN.parameter().equals(temperature) &&
                    ParameterUtils.Humidity.FULL_RANGE.parameter().equals(humidity)) {
                ModBiomeBuilder.addRivers(consumer, continentalness, erosion, depth, weirdness);
                callback.cancel();
            }
        }
    }
}
