package net.pringlebeaver.riverbed.block.entity.renderer;
import net.minecraft.core.Registry;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.pringlebeaver.riverbed.block.entity.GrassBasketBlockEntity;

public class GrassBasketItemRenderer extends BlockEntityWithoutLevelRenderer {

    private final GrassBasketBlockEntityRenderer blockEntityRenderer;

    public GrassBasketItemRenderer(BlockEntityRenderDispatcher pBlockEntityRenderDispatcher, EntityModelSet pEntityModelSet) {
        super(pBlockEntityRenderDispatcher, pEntityModelSet != null ? pEntityModelSet : Minecraft.getInstance().getEntityModels());
        EntityModelSet modelSet = pEntityModelSet != null ? pEntityModelSet : Minecraft.getInstance().getEntityModels();
        BlockEntityRendererProvider.Context context = new BlockEntityRendererProvider.Context(pBlockEntityRenderDispatcher, Minecraft.getInstance().getBlockRenderer(), Minecraft.getInstance().getItemRenderer(), Minecraft.getInstance().getEntityRenderDispatcher(), modelSet, Minecraft.getInstance().font);
        this.blockEntityRenderer = new GrassBasketBlockEntityRenderer(context);
    }

    @Override
    public void renderByItem(ItemStack stack, ItemDisplayContext displayContext, PoseStack poseStack,
                             MultiBufferSource buffer, int packedLight, int packedOverlay) {


        GrassBasketBlockEntity.Decorations decorations = GrassBasketBlockEntity.Decorations.EMPTY;
        if (stack.hasTag() && stack.getTag().contains("BlockEntityTag")) {
            decorations = GrassBasketBlockEntity.Decorations.load(stack.getTag().getCompound("BlockEntityTag"));
        }

        ItemStack topStack = new ItemStack(decorations.top());
        ItemStack middleStack = new ItemStack(decorations.middle());
        ItemStack bottomStack = new ItemStack(decorations.bottom());

        poseStack.pushPose();

        // Extract decorations from the ItemStack NBT (BlockEntityTag)
        Item topDecoration = Items.AIR;
        Item middleDecoration = Items.AIR;
        Item bottomDecoration = Items.AIR;

        if (stack.hasTag() && stack.getTag().contains("BlockEntityTag")) {
            var tag = stack.getTag().getCompound("BlockEntityTag");
            GrassBasketBlockEntity.Decorations decorations = GrassBasketBlockEntity.Decorations.load(tag);
            topDecoration = decorations.top();
            middleDecoration = decorations.middle();
            bottomDecoration = decorations.bottom();
        }

        // Convert Items to ItemStacks for rendering
        ItemStack topStack = topDecoration != null ? new ItemStack(topDecoration) : ItemStack.EMPTY;
        ItemStack middleStack = middleDecoration != null ? new ItemStack(middleDecoration) : ItemStack.EMPTY;
        ItemStack bottomStack = bottomDecoration != null ? new ItemStack(bottomDecoration) : ItemStack.EMPTY;

        // Render using the existing BlockEntityRenderer
        blockEntityRenderer.render(topStack, middleStack, bottomStack, poseStack, buffer, packedLight, packedOverlay);

        poseStack.popPose();
    }
}
