package net.pringlebeaver.riverbed.item.custom;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.pringlebeaver.riverbed.block.entity.renderer.GrassBasketItemRenderer;

import java.util.function.Consumer;

public class GrassBasketBlockItem extends BlockItem {


    public GrassBasketBlockItem(Block pBlock, Properties pProperties) {
        super(pBlock, pProperties);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private BlockEntityWithoutLevelRenderer renderer;

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                if (renderer == null) {
                    // This is safe because it's called on the client thread, after client init
                    renderer = new GrassBasketItemRenderer(
                            Minecraft.getInstance().getBlockEntityRenderDispatcher(),
                            Minecraft.getInstance().getEntityModels()
                    );
                }
                return renderer;
            }
        });
    }
}
