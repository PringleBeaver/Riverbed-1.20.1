package net.pringlebeaver.riverbed.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.pringlebeaver.riverbed.RiverbedMain;
import net.pringlebeaver.riverbed.entity.custom.TroutEntity;

@OnlyIn(Dist.CLIENT)
public class TroutRenderer extends MobRenderer<TroutEntity, TroutModel<TroutEntity>> {
    private static final ResourceLocation RAINBOW = new ResourceLocation(RiverbedMain.MOD_ID, "textures/entity/trout/rainbow_trout.png");
    private static final ResourceLocation BROOK = new ResourceLocation(RiverbedMain.MOD_ID, "textures/entity/trout/brook_trout.png");
    private static final ResourceLocation BULL = new ResourceLocation(RiverbedMain.MOD_ID, "textures/entity/trout/bull_trout.png");
    private static final ResourceLocation GOLDEN = new ResourceLocation(RiverbedMain.MOD_ID, "textures/entity/trout/golden_trout.png");
    private static final ResourceLocation BROWN = new ResourceLocation(RiverbedMain.MOD_ID, "textures/entity/trout/brown_trout.png");



    public TroutRenderer(EntityRendererProvider.Context context) {
        super(context, new TroutModel<>(context.bakeLayer(ModModelLayers.TROUT_LAYER)), 0.3F);
    }

    public ResourceLocation getTextureLocation(TroutEntity pEntity)
    {
        ResourceLocation resourcelocation;
        switch (pEntity.getVariant()) {
            case RAINBOW:
                resourcelocation = RAINBOW;
                break;
            case BROOK:
                resourcelocation = BROOK;
                break;
            case BULL:
                resourcelocation = BULL;
                break;
            case BROWN:
                resourcelocation = BROWN;
                break;
            case GOLDEN:
                resourcelocation = GOLDEN;
                break;
            default:
                throw new IncompatibleClassChangeError();
        }

        return resourcelocation;
    }

    protected void setupRotations(TroutEntity entityLiving, PoseStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        super.setupRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
        float f = 4.3F * Mth.sin(0.6F * ageInTicks);
        matrixStackIn.mulPose(Axis.YP.rotationDegrees(f));
        if (!entityLiving.isInWater()) {
            matrixStackIn.translate(0.1F, 0.1F, -0.1F);
            matrixStackIn.mulPose(Axis.ZP.rotationDegrees(90.0F));
        }
    }

}
