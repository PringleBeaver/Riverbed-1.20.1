package net.pringlebeaver.riverbed.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.CodModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.animal.Cod;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.pringlebeaver.riverbed.RiverbedMain;
import net.pringlebeaver.riverbed.entity.custom.TroutEntity;

@OnlyIn(Dist.CLIENT)
public class TroutRenderer extends MobRenderer<TroutEntity, TroutModel<TroutEntity>> {
    private static final ResourceLocation TROUT_LOCATION = new ResourceLocation(RiverbedMain.MOD_ID, "textures/entity/rainbow_trout.png");

    public TroutRenderer(EntityRendererProvider.Context context) {
        super(context, new TroutModel<TroutEntity>(context.bakeLayer(RiverbedModelLayers.TROUT_LAYER)), 0.3F);
    }

    public ResourceLocation getTextureLocation(TroutEntity pEntity) {
        return TROUT_LOCATION;
    }

    protected void setupRotations(TroutEntity pEntityLiving, PoseStack pMatrixStack, float pAgeInTicks, float pRotationYaw, float pPartialTicks) {
        super.setupRotations(pEntityLiving, pMatrixStack, pAgeInTicks, pRotationYaw, pPartialTicks);
        float f = 4.3F * Mth.sin(0.6F * pAgeInTicks);
        pMatrixStack.mulPose(Axis.YP.rotationDegrees(f));
        if (!pEntityLiving.isInWater()) {
            pMatrixStack.translate(0.1F, 0.1F, -0.1F);
            pMatrixStack.mulPose(Axis.ZP.rotationDegrees(90.0F));
        }

    }
}
