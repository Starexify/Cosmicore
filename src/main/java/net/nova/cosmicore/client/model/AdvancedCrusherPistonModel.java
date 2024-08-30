package net.nova.cosmicore.client.model;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.nova.cosmicore.Cosmicore;

import java.util.function.Function;

public class AdvancedCrusherPistonModel extends BaseModel {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Cosmicore.rl("advanced_crusher_piston"), "main");
    public static final ResourceLocation TEXTURE = Cosmicore.rl("textures/entity/crusher_piston/advanced_crusher_piston.png");

    public AdvancedCrusherPistonModel(Function<ResourceLocation, RenderType> pRenderType, ModelPart root) {
        super(pRenderType, root);
    }
}
