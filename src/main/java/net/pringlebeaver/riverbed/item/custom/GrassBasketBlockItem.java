package net.pringlebeaver.riverbed.item.custom;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.pringlebeaver.riverbed.block.entity.renderer.GrassBasketBlockEntityRenderer;
import net.pringlebeaver.riverbed.item.renderer.GrassBasketItemRenderer;

import java.util.function.Consumer;


public class GrassBasketBlockItem extends BlockItem {
    public GrassBasketBlockItem(Block pBlock, Properties pProperties) {
        super(pBlock, pProperties);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return GrassBasketItemRenderer.INSTANCE;
            }
        });
    }
}