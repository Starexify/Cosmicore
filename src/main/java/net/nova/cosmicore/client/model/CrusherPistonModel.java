package net.nova.cosmicore.client.model;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.nova.cosmicore.Cosmicore;

import java.util.function.Function;

public class CrusherPistonModel extends BaseModel {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Cosmicore.rl("crusher_piston"), "main");
    public static final ResourceLocation TEXTURE = Cosmicore.rl("textures/entity/crusher_piston/crusher_piston.png");

    public CrusherPistonModel(Function<ResourceLocation, RenderType> pRenderType, ModelPart root) {
        super(pRenderType, root);
    }
}
