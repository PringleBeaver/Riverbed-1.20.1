package net.pringlebeaver.riverbed.event;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.pringlebeaver.riverbed.RiverbedMain;
import net.pringlebeaver.riverbed.effect.ModEffects;

@Mod.EventBusSubscriber(modid = RiverbedMain.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)

public class ModForgeEventBusEvents {

    @SubscribeEvent
    public static void waterBreakSpeed(PlayerEvent.BreakSpeed event) {
        Player playerEntity = event.getEntity();
        if (playerEntity.isInWater() && playerEntity.hasEffect(ModEffects.MANATEES_TOUCH.get())) {
            if (!playerEntity.onGround() && playerEntity.isUnderWater()) {
                event.setNewSpeed(event.getOriginalSpeed() * 25);
            } else if (!playerEntity.onGround() ^ playerEntity.isUnderWater()) {
                event.setNewSpeed(event.getOriginalSpeed() * 5);
            }
        }
    }

}
