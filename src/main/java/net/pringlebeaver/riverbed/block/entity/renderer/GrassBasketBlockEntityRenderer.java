package net.pringlebeaver.riverbed.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.mojang.serialization.Lifecycle;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.BellBlockEntity;
import net.minecraft.world.level.block.entity.DecoratedPotBlockEntity;
import net.minecraft.world.level.block.entity.DecoratedPotPatterns;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.pringlebeaver.riverbed.RiverbedMain;
import net.pringlebeaver.riverbed.block.ModBlocks;
import net.pringlebeaver.riverbed.block.custom.GrassBasketBlock;
import net.pringlebeaver.riverbed.block.entity.GrassBasketBlockEntity;
import net.pringlebeaver.riverbed.block.entity.ModBlockEntities;
import net.pringlebeaver.riverbed.entity.client.ModModelLayers;
import net.pringlebeaver.riverbed.item.ModItems;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;


@OnlyIn(Dist.CLIENT)
public class GrassBasketBlockEntityRenderer implements BlockEntityRenderer<GrassBasketBlockEntity> {
    public static final ResourceLocation BLANK_TEXTURE = new ResourceLocation(RiverbedMain.MOD_ID, "block/grass_basket_pattern/blank_basket_pattern");

    private final ModelPart decoration_bottom;
    private final ModelPart decoration_middle;
    private final ModelPart decoration_top;

    public static final Material DEFAULT_MATERIAL = new Material(TextureAtlas.LOCATION_BLOCKS, BLANK_TEXTURE);

    private static Material getMaterialForItem(Item item) {
     if (item != null) {
         ResourceLocation textureLocation;

         if (item == (ModItems.WOVEN_GRASS).get()) {
             textureLocation = new ResourceLocation(RiverbedMain.MOD_ID, "block/grass_basket_pattern/blank_basket_pattern");

         } else {
             textureLocation = new ResourceLocation(RiverbedMain.MOD_ID, "block/grass_basket_pattern/" + item.toString());
         }


         return new Material(TextureAtlas.LOCATION_BLOCKS, textureLocation);
     } else {
           return DEFAULT_MATERIAL;}

    }



    public GrassBasketBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        ModelPart decorationLayer = context.bakeLayer(ModModelLayers.GRASS_BASKET_DECORATION_LAYER);




        this.decoration_bottom = decorationLayer.getChild("decoration_bottom");
        this.decoration_middle = decorationLayer.getChild("decoration_middle");
        this.decoration_top = decorationLayer.getChild("decoration_top");

    }


    public static LayerDefinition createDecorationLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition decoration_bottom = partdefinition.addOrReplaceChild("decoration_bottom", CubeListBuilder.create().texOffs(0, 11).addBox(-11.0F, -4.0F, -1.0F, 12.0F, 4.0F, 12.0F, new CubeDeformation(0.01F)), PartPose.offset(13.0F, 15.0F, 3.0F));

        PartDefinition decoration_middle = partdefinition.addOrReplaceChild("decoration_middle", CubeListBuilder.create().texOffs(0, 7).addBox(-11.0F, -4.0F, -1.0F, 12.0F, 4.0F, 12.0F, new CubeDeformation(0.01F)), PartPose.offset(13.0F, 11.0F, 3.0F));

        PartDefinition decoration_top = partdefinition.addOrReplaceChild("decoration_top", CubeListBuilder.create().texOffs(0, 3).addBox(-11.0F, -4.0F, -1.0F, 12.0F, 4.0F, 12.0F, new CubeDeformation(0.01F)), PartPose.offset(13.0F, 7.0F, 3.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    public void render(GrassBasketBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
        // Direction Stuff
        pPoseStack.pushPose();
        Direction direction = pBlockEntity.getDirection();
        pPoseStack.translate(0.5D, 0.5D, 0.5D);
        pPoseStack.mulPose(Axis.YP.rotationDegrees(180.0F - direction.toYRot()));
        pPoseStack.mulPose(Axis.XP.rotationDegrees(180.0F));

        pPoseStack.translate(-0.5D, -0.5D, -0.5D);

        // Decorations

        // Other stuff
        VertexConsumer vertexconsumer = DEFAULT_MATERIAL.buffer(pBuffer, RenderType::entityCutout);
        Item TOP_DECORATION = pBlockEntity.getDecorations().top();
        Item MIDDLE_DECORATION = pBlockEntity.getDecorations().middle();
        Item BOTTOM_DECORATION = pBlockEntity.getDecorations().bottom();




        this.renderDecoration(this.decoration_bottom, pPoseStack, pBuffer, pPackedLight, pPackedOverlay, getMaterialForItem(TOP_DECORATION), DyeColor.getColor(BOTTOM_DECORATION.asItem().getDefaultInstance()));
        this.renderDecoration(this.decoration_middle, pPoseStack, pBuffer, pPackedLight, pPackedOverlay, getMaterialForItem(MIDDLE_DECORATION), DyeColor.getColor(BOTTOM_DECORATION.asItem().getDefaultInstance()));
        this.renderDecoration(this.decoration_top, pPoseStack, pBuffer, pPackedLight, pPackedOverlay, getMaterialForItem(BOTTOM_DECORATION), DyeColor.getColor(BOTTOM_DECORATION.asItem().getDefaultInstance()));
        pPoseStack.popPose();


    }

    private void renderDecoration(ModelPart pModelPart, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay, @Nullable Material pMaterial, DyeColor dyeColor) {
        if (pMaterial == null) {
            pMaterial = DEFAULT_MATERIAL;
        }
        float[] afloat;
        if (dyeColor == null) {
            afloat = new float[]{1.0f, 1.0f, 1.0f};

        } else {
            afloat = dyeColor.getTextureDiffuseColors();

        }

        pModelPart.render(pPoseStack, pMaterial.buffer(pBuffer, RenderType::entityCutout), pPackedLight, pPackedOverlay, afloat[0], afloat[1], afloat[2], 1.0F);

    }
}
