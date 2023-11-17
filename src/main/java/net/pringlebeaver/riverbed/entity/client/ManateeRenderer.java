package net.pringlebeaver.riverbed.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.pringlebeaver.riverbed.RiverbedMain;
import net.pringlebeaver.riverbed.entity.custom.ManateeEntity;

public class ManateeRenderer extends MobRenderer<ManateeEntity, ManateeModel<ManateeEntity>> {
    private static final ResourceLocation MANATEE_LOCATION = new ResourceLocation(RiverbedMain.MOD_ID, "textures/entity/manatee/manatee.png");
    private static final ResourceLocation ALGAE_LOCATION = new ResourceLocation(RiverbedMain.MOD_ID, "textures/entity/manatee/manatee_algae.png");

    private static final ResourceLocation BABY_LOCATION = new ResourceLocation(RiverbedMain.MOD_ID, "textures/entity/manatee/manatee_baby.png");

    public ManateeRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new ManateeModel<>(pContext.bakeLayer(ModModelLayers.MANATEE_LAYER)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(ManateeEntity pEntity) {
        if (pEntity.isBaby()) {
            return BABY_LOCATION;
        } else {
            if (pEntity.isAlgae()) {
                return ALGAE_LOCATION;
            } else {
                return MANATEE_LOCATION;
            }
        }
    }

    @Override
    public void render(ManateeEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        if (pEntity.isBaby()) {
           pMatrixStack.scale(0.35f, 0.35f, 0.35f);
        }
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}
