package net.nova.cosmicore.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.nova.cosmicore.client.model.MeteoriteModel;
import net.nova.cosmicore.entity.Achondrite;
import net.nova.cosmicore.entity.Meteorite;

public class MeteoriteRenderer extends EntityRenderer<Meteorite> {
    private final MeteoriteModel meteoriteModel;

    public MeteoriteRenderer(EntityRendererProvider.Context context) {
        super(context);
        meteoriteModel = new MeteoriteModel(context.bakeLayer(MeteoriteModel.LAYER_LOCATION));
    }

    @Override
    public void render(Meteorite entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        poseStack.pushPose();

        VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(getTextureLocation(entity)));
        meteoriteModel.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY);
        meteoriteModel.setupAnim(entity, 0, 0, entity.tickCount + partialTicks, 0, 0);

        poseStack.popPose();
        super.render(entity, entityYaw, partialTicks, poseStack, bufferSource, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(Meteorite pEntity) {
        return MeteoriteModel.TEXTURE;
    }
}