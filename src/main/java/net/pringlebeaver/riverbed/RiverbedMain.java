package net.pringlebeaver.riverbed;

import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.pringlebeaver.riverbed.block.RiverbedBlocks;
import net.pringlebeaver.riverbed.entity.RiverbedEntities;
import net.pringlebeaver.riverbed.entity.client.TroutRenderer;
import net.pringlebeaver.riverbed.item.RiverbedCreativeTabs;
import net.pringlebeaver.riverbed.item.RiverbedItems;
import net.pringlebeaver.riverbed.sound.RiverbedSounds;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(RiverbedMain.MOD_ID)
public class RiverbedMain
{
    public static final String MOD_ID = "riverbed";
    private static final Logger LOGGER = LogUtils.getLogger();
    public RiverbedMain()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register Stuff

        RiverbedItems.register(modEventBus);

        RiverbedBlocks.register(modEventBus);

        RiverbedCreativeTabs.register(modEventBus);

        RiverbedSounds.register(modEventBus);

        RiverbedEntities.register(modEventBus);





        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }

    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            EntityRenderers.register(RiverbedEntities.TROUT.get(), TroutRenderer::new);
        }
    }
}
