package net.pringlebeaver.riverbed.item.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.pringlebeaver.riverbed.block.entity.GrassBasketBlockEntity;
import net.pringlebeaver.riverbed.block.entity.renderer.GrassBasketBlockEntityRenderer;

public class GrassBasketItemRenderer extends BlockEntityWithoutLevelRenderer {
    
    public static final GrassBasketItemRenderer INSTANCE = new GrassBasketItemRenderer();
    private final GrassBasketBlockEntity dummyBasket = new GrassBasketBlockEntity();

    public GrassBasketItemRenderer() {
        super(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
    }

    @Override
    public void renderByItem(ItemStack stack, ItemDisplayContext pContext, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
        BlockEntityRenderDispatcher dispatcher = Minecraft.getInstance().getBlockEntityRenderDispatcher();
        GrassBasketBlockEntityRenderer renderer = (GrassBasketBlockEntityRenderer) dispatcher.getRenderer(this.dummyBasket);

        if (renderer != null) {
            GrassBasketBlockEntity.Decorations decorations = GrassBasketBlockEntity.Decorations.load(BlockItem.getBlockEntityData(stack));
            if (decorations == null) {
                decorations = GrassBasketBlockEntity.Decorations.EMPTY;
            }

            GrassBasketBlockEntityRenderer.renderInventory(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, decorations, renderer);
        }
    }
}