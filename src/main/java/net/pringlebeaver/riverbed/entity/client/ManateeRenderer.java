package net.pringlebeaver.riverbed.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.pringlebeaver.riverbed.RiverbedMain;
import net.pringlebeaver.riverbed.entity.custom.ManateeEntity;
import org.jetbrains.annotations.NotNull;

public class ManateeRenderer extends MobRenderer<ManateeEntity, ManateeModel<ManateeEntity>> {
    private static final ResourceLocation MANATEE_LOCATION = new ResourceLocation(RiverbedMain.MOD_ID, "textures/entity/manatee/manatee.png");
    private static final ResourceLocation BLOOMING_LOCATION = new ResourceLocation(RiverbedMain.MOD_ID, "textures/entity/manatee/manatee_blooming.png");
    private static final ResourceLocation ALGAE_LOCATION = new ResourceLocation(RiverbedMain.MOD_ID, "textures/entity/manatee/manatee_algae.png");
    private static final ResourceLocation MEDIUM_ALGAE_LOCATION = new ResourceLocation(RiverbedMain.MOD_ID, "textures/entity/manatee/manatee_medium_algae.png");


    private static final ResourceLocation BABY_LOCATION = new ResourceLocation(RiverbedMain.MOD_ID, "textures/entity/manatee/manatee_baby.png");

    public ManateeRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new ManateeModel<>(pContext.bakeLayer(ModModelLayers.MANATEE_LAYER)), 0.5f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ManateeEntity pEntity) {
        if (pEntity.isBaby()) {
            return BABY_LOCATION;
        } else {
            if (pEntity.isBlooming()) {
                return BLOOMING_LOCATION;
            } else {
                if (pEntity.isAlgae()) {
                    return ALGAE_LOCATION;
                } else {
                    if (pEntity.getAlgaeGrowthTime() > pEntity.getTotalGrowthTime() * 0.75) {
                        return MEDIUM_ALGAE_LOCATION;
                    } else {
                        return MANATEE_LOCATION;
                    }
                }
            }

        }
    }

    @Override
    public void render(ManateeEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}
