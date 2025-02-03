package net.pringlebeaver.riverbed.item.custom;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PlaceOnWaterBlockItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

public class AlgaeBottleItem extends PlaceOnWaterBlockItem {

    public AlgaeBottleItem(Block block, Properties properties) {
        super(block, properties);
    }


    @Override
    public @NotNull String getDescriptionId() {
        return "item.riverbed.algae_bottle";
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack stack = player.getItemInHand(interactionHand);
        InteractionResult interactionResult = super.use(level, player, interactionHand).getResult();
        ItemStack bottleStack = new ItemStack(Items.GLASS_BOTTLE);
        if (interactionResult.consumesAction()) {
            player.swing(interactionHand);
            if (stack.isEmpty()) {
                player.setItemInHand(interactionHand, bottleStack);
            } else {
                if (!player.isCreative()) {
                    if (!player.getInventory().add(bottleStack)) {
                        player.drop(bottleStack, false);
                    }
                }

            }
        }

        return super.use(level, player, interactionHand);
    }
}
