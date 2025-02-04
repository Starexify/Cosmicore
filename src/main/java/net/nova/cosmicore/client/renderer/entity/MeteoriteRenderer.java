package net.nova.cosmicore.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.nova.cosmicore.Cosmicore;
import net.nova.cosmicore.client.model.BaseMeteorModel;
import net.nova.cosmicore.client.renderer.entity.state.MeteoriteRenderState;
import net.nova.cosmicore.entity.Meteorite;
import net.nova.cosmicore.init.CModelLayers;

public class MeteoriteRenderer extends EntityRenderer<Meteorite, MeteoriteRenderState> {
    public final BaseMeteorModel meteorModel;
    public static final ResourceLocation METEORITE_LOCATION = Cosmicore.rl("textures/entity/meteors/meteorite.png");

    public MeteoriteRenderer(EntityRendererProvider.Context context) {
        super(context);
        meteorModel = new BaseMeteorModel(context.bakeLayer(CModelLayers.METEORITE));
    }

    @Override
    public void render(MeteoriteRenderState renderState, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        poseStack.pushPose();
        VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(getTextureLocation(renderState)));
        meteorModel.setupAnim(renderState);
        meteorModel.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY);
        poseStack.popPose();
        super.render(renderState, poseStack, bufferSource, packedLight);
    }

    public ResourceLocation getTextureLocation(MeteoriteRenderState renderState) {
        return METEORITE_LOCATION;
    }

    @Override
    public MeteoriteRenderState createRenderState() {
        return new MeteoriteRenderState();
    }

    @Override
    public void extractRenderState(Meteorite p_entity, MeteoriteRenderState reusedState, float partialTick) {
        super.extractRenderState(p_entity, reusedState, partialTick);
        reusedState.fallingAnimationState.copyFrom(p_entity.fallingAnimationState);
        reusedState.explodedAnimationState.copyFrom(p_entity.explodedAnimationState);
    }
}