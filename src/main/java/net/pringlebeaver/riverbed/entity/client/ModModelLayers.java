package net.pringlebeaver.riverbed.entity.client;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.pringlebeaver.riverbed.RiverbedMain;

public class ModModelLayers {
    public static final ModelLayerLocation TROUT_LAYER = new ModelLayerLocation(
            new ResourceLocation(RiverbedMain.MOD_ID, "trout_layer"), "main");

    public static final ModelLayerLocation MANATEE_LAYER = new ModelLayerLocation(
            new ResourceLocation(RiverbedMain.MOD_ID, "manatee_layer"), "main");

    public static final ModelLayerLocation GRASS_BASKET_DECORATION_LAYER = new ModelLayerLocation(
            new ResourceLocation(RiverbedMain.MOD_ID, "grass_basket_decoration_layer"), "main");

}
