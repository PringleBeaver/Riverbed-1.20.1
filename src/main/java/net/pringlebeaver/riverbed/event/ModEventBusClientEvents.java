package net.pringlebeaver.riverbed.event;

import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.pringlebeaver.riverbed.RiverbedMain;
import net.pringlebeaver.riverbed.block.entity.ModBlockEntities;
import net.pringlebeaver.riverbed.block.entity.renderer.GrassBasketBlockEntityRenderer;
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
    public static void registerBER(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntities.GRASS_BASKET_BLOCK_ENTITY.get(), GrassBasketBlockEntityRenderer::new);
    }


    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.TROUT_LAYER, TroutModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.MANATEE_LAYER, ManateeModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.GRASS_BASKET_DECORATION_LAYER, GrassBasketBlockEntityRenderer::createDecorationLayer);


    }


}
