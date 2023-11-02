package net.pringlebeaver.riverbed.event;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.pringlebeaver.riverbed.RiverbedMain;
import net.pringlebeaver.riverbed.entity.RiverbedEntities;
import net.pringlebeaver.riverbed.entity.custom.TroutEntity;

@Mod.EventBusSubscriber(modid = RiverbedMain.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
@SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(RiverbedEntities.TROUT.get(), TroutEntity.createAttributes().build());
    }
}
