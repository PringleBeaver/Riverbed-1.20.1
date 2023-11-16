package net.pringlebeaver.riverbed.entity.client;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.pringlebeaver.riverbed.RiverbedMain;
import net.pringlebeaver.riverbed.entity.custom.ManateeEntity;

public class ManateeRenderer extends MobRenderer<ManateeEntity, ManateeModel<ManateeEntity>> {
    private static final ResourceLocation MANATEE_LOCATION = new ResourceLocation(RiverbedMain.MOD_ID, "textures/entity/manatee/manatee.png");

    public ManateeRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new ManateeModel<>(pContext.bakeLayer(ModModelLayers.MANATEE_LAYER)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(ManateeEntity pEntity) {
        return MANATEE_LOCATION;
    }
}
