package net.nova.cosmicore.client.renderer.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.nova.cosmicore.blockentity.CosmicShieldTile;
import net.nova.cosmicore.client.model.CosmicShieldTopModel;

import java.util.Random;

@OnlyIn(Dist.CLIENT)
public class CosmicShieldTileRenderer implements BlockEntityRenderer<CosmicShieldTile> {
    private final ResourceLocation shieldTopTexture = CosmicShieldTopModel.TEXTURE;
    private final ModelLayerLocation shieldTopLayer = CosmicShieldTopModel.LAYER_LOCATION;
    private final BlockRenderDispatcher blockRenderer;
    private final CosmicShieldTopModel shieldTopModel;

    public CosmicShieldTileRenderer(BlockEntityRendererProvider.Context context) {
        this.blockRenderer = context.getBlockRenderDispatcher();
        ModelPart modelPart = context.bakeLayer(shieldTopLayer);
        this.shieldTopModel = new CosmicShieldTopModel(RenderType::entityCutout, modelPart);
    }

    @Override
    public void render(CosmicShieldTile cosmicShieldTile, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        poseStack.pushPose();

        // Render the animated antenna
        renderAnimatedAntenna(cosmicShieldTile, partialTick, poseStack, bufferSource, packedLight, packedOverlay);

        poseStack.popPose();
    }

    private void renderAnimatedAntenna(CosmicShieldTile cosmicShieldTile, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        // Render Antenna
        VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutout(shieldTopTexture));

        poseStack.pushPose();
        poseStack.translate(0.8, 0, 0.8);


        long gameTime = cosmicShieldTile.getLevel().getGameTime();
        float rotationSpeed = 0.05f; // Adjust this value to change rotation speed
        float rotationProgress = (gameTime * rotationSpeed) % 1.0f;
        float yRotation = rotationProgress * 90f; // Rotate from 0 to 90 degrees

        poseStack.mulPose(Axis.YP.rotationDegrees(yRotation));

        poseStack.scale(1, -1, -1);
        shieldTopModel.antenna.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        poseStack.popPose();
    }

    @Override
    public int getViewDistance() {
        return 68;
    }
}
