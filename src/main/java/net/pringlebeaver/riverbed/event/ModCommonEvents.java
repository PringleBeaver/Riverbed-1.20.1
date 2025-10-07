package net.pringlebeaver.riverbed.event;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.pringlebeaver.riverbed.RiverbedMain;
import net.pringlebeaver.riverbed.block.custom.GrassBasketBlock;
import net.pringlebeaver.riverbed.block.entity.GrassBasketBlockEntity;
import net.pringlebeaver.riverbed.util.ModTags;

@Mod.EventBusSubscriber(modid = RiverbedMain.MOD_ID)
public class ModCommonEvents {

    @SubscribeEvent
    public static void onBreak(BlockEvent.BreakEvent event) {

        // Breaking for Grass Basket Fragment Drops
        if (event.getState().getBlock() instanceof GrassBasketBlock && event.getPlayer().getMainHandItem().is(ModTags.Items.BREAKS_GRASS_BASKET) && !event.getPlayer().isCreative()) {

            Level level = (Level) event.getLevel();
            BlockPos pos = event.getPos();
            BlockEntity blockEntity = level.getBlockEntity(pos);


            if (blockEntity instanceof GrassBasketBlockEntity grassBasketEntity) {
                grassBasketEntity.getDecorations().sorted().forEach(item -> {
                    Block.popResource(level, pos, item.getDefaultInstance());
                });
            }
            event.setCanceled(true);
            level.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);

        }
    }

}
