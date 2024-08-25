package net.nova.cosmicore.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class AchondriteRenderer extends EntityRenderer<Achondrite> {
    private final AchondriteModel achondriteModel;

    public AchondriteRenderer(EntityRendererProvider.Context context) {
        super(context);
        achondriteModel = new AchondriteModel(context.bakeLayer(AchondriteModel.LAYER_LOCATION));
    }

    @Override
    public void render(Achondrite entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        poseStack.pushPose();

        VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(getTextureLocation(entity)));
        achondriteModel.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY);
        achondriteModel.setupAnim(entity, 0, 0, entity.tickCount + partialTicks, 0, 0);

        poseStack.popPose();
        super.render(entity, entityYaw, partialTicks, poseStack, bufferSource, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(Achondrite pEntity) {
        return AchondriteModel.TEXTURE;
    }
}
