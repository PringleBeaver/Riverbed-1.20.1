package net.pringlebeaver.riverbed.event;

import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.pringlebeaver.riverbed.RiverbedMain;
import net.pringlebeaver.riverbed.entity.RiverbedEntities;
import net.pringlebeaver.riverbed.entity.custom.TroutEntity;

@Mod.EventBusSubscriber(modid = RiverbedMain.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void entitySpawnRestriction(SpawnPlacementRegisterEvent event) {
        event.register(RiverbedEntities.TROUT.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.OCEAN_FLOOR, TroutEntity::canSpawn, SpawnPlacementRegisterEvent.Operation.REPLACE);
    }
@SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(RiverbedEntities.TROUT.get(), TroutEntity.createAttributes().build());
    }
}
