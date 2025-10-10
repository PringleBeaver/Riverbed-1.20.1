package net.pringlebeaver.riverbed;

import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.pringlebeaver.riverbed.block.ModBlocks;
import net.pringlebeaver.riverbed.block.entity.ModBlockEntities;
import net.pringlebeaver.riverbed.effect.ModEffects;
import net.pringlebeaver.riverbed.entity.ModEntities;
import net.pringlebeaver.riverbed.entity.client.ManateeRenderer;
import net.pringlebeaver.riverbed.entity.client.TroutRenderer;
import net.pringlebeaver.riverbed.item.ModItems;
import net.pringlebeaver.riverbed.item.ModCreativeTabs;
import net.pringlebeaver.riverbed.loot.ModLootModifiers;
import net.pringlebeaver.riverbed.particle.ModParticles;
import net.pringlebeaver.riverbed.recipes.ModRecipes;
import net.pringlebeaver.riverbed.sound.ModSounds;
import net.pringlebeaver.riverbed.world.ModFeatures;
import net.pringlebeaver.riverbed.world.biome.surface.ModSurfaceRules;
import net.pringlebeaver.riverbed.world.biomemodifiers.ModBiomeModifiers;
import org.slf4j.Logger;
import terrablender.api.SurfaceRuleManager;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(RiverbedMain.MOD_ID)
public class RiverbedMain
{
    public static final String MOD_ID = "riverbed";
    private static final Logger LOGGER = LogUtils.getLogger();

    public static boolean isLoadComplete = false;


    public RiverbedMain()
    {

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register Stuff


        ModEntities.register(modEventBus);
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModLootModifiers.register(modEventBus);

        ModSounds.register(modEventBus);
        ModEffects.register(modEventBus);
        ModParticles.register(modEventBus);


        ModCreativeTabs.register(modEventBus);

        ModLootModifiers.register(modEventBus);

        ModFeatures.register(modEventBus);

        ModBiomeModifiers.register(modEventBus);

        ModRecipes.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);
    }


    @SubscribeEvent
    public void commonSetup(final FMLCommonSetupEvent event)
    {

            isLoadComplete = true;

        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MOD_ID, ModSurfaceRules.makeRules());

        event.enqueueWork(() -> {
            // Compostable
            ComposterBlock.COMPOSTABLES.put(ModBlocks.RIVER_GRASS.get().asItem(), 0.3f);
            ComposterBlock.COMPOSTABLES.put(ModBlocks.WATER_HYACINTH.get().asItem(), 0.65f);
            ComposterBlock.COMPOSTABLES.put(ModBlocks.ALGAE.get().asItem(), 0.5f);
            ComposterBlock.COMPOSTABLES.put(ModBlocks.ALGAE_BLOCK.get().asItem(), 0.75f);


            // Flower Pots
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.WATER_HYACINTH.getId(),ModBlocks.POTTED_HYACINTH);
        });
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }



    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            EntityRenderers.register(ModEntities.TROUT.get(), TroutRenderer::new);
            EntityRenderers.register(ModEntities.MANATEE.get(), ManateeRenderer::new);
        }

        @SubscribeEvent
        public static void itemTintSetup(RegisterColorHandlersEvent.Item event)
        {
        }

        @SubscribeEvent
        public static void blockTintSetup(RegisterColorHandlersEvent.Block event)
        {
        }
    }
}
