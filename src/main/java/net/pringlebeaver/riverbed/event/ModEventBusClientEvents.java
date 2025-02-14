package net.pringlebeaver.riverbed.event;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.pringlebeaver.riverbed.RiverbedMain;
import net.pringlebeaver.riverbed.entity.client.ManateeModel;
import net.pringlebeaver.riverbed.entity.client.ModModelLayers;
import net.pringlebeaver.riverbed.entity.client.TroutModel;
import net.pringlebeaver.riverbed.particle.AlgaeParticles;
import net.pringlebeaver.riverbed.particle.ModParticles;

@Mod.EventBusSubscriber(modid = RiverbedMain.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {

    @SubscribeEvent
    public static void registerParticleFactories(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ModParticles.ALGAE_PARTICLES.get(), AlgaeParticles.Provider::new);
    }

    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.TROUT_LAYER, TroutModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.MANATEE_LAYER, ManateeModel::createBodyLayer);

    }


}
