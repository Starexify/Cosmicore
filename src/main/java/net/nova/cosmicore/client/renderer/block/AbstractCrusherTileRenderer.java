package net.nova.cosmicore.client.renderer.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.nova.cosmicore.client.model.CrusherPistonModel;

public abstract class AbstractCrusherTileRenderer<T extends BlockEntity> implements BlockEntityRenderer<T> {
    public final ResourceLocation pistonTexture;
    public final ModelLayerLocation pistonLayer;
    public final BlockRenderDispatcher blockRenderer;
    public final CrusherPistonModel pistonModel;

    protected AbstractCrusherTileRenderer(BlockEntityRendererProvider.Context context, ResourceLocation pistonTexture, ModelLayerLocation pistonLayer, CrusherPistonModel pistonModel) {
        this.blockRenderer = context.getBlockRenderDispatcher();
        this.pistonTexture = pistonTexture;
        this.pistonLayer = pistonLayer;
        this.pistonModel = pistonModel;
    }

    @Override
    public void render(T crusherTile, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        poseStack.pushPose();
        renderAnimatedPiston(crusherTile, partialTick, poseStack, bufferSource, packedLight, packedOverlay);
        poseStack.popPose();
    }

    protected void renderAnimatedPiston(T crusherTile, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        float[] offsets = calculateVerticalOffsets(crusherTile, partialTick);
        float neckOffset = offsets[0];
        float headOffset = offsets[1];

        VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutout(pistonTexture));

        // Render neck
        poseStack.pushPose();
        poseStack.translate(0.5, 1 + neckOffset, 0.5);
        poseStack.scale(1, -1, -1);
        pistonModel.neck.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        poseStack.popPose();

        // Render head
        poseStack.pushPose();
        poseStack.translate(0.5, 1 + headOffset, 0.5);
        poseStack.scale(1, -1, -1);
        pistonModel.head.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        poseStack.popPose();
    }

    protected float[] calculateVerticalOffsets(T crusherTile, float partialTick) {
        int crushingProgress = getCrushingProgress(crusherTile);
        int maxCrushingProgress = 400;

        float progress = (crushingProgress + partialTick) / maxCrushingProgress;

        float neckOffset = 0;
        float headOffset = 0;

        if (progress < 0.95f) { // First 95%: Move down
            neckOffset = -0.125f * Math.min(progress / 0.5f, 1); // Reaches max at 95 ticks 0.2375f
            headOffset = -0.3f * Math.min(progress / 0.95f, 1);  // Reaches max at 380 ticks
        } else { // Last 5%: Move up quickly
            float returnProgress = (progress - 0.95f) / 0.05f;
            neckOffset = -0.125f * (1 - returnProgress);
            headOffset = -0.3f * (1 - returnProgress);
        }

        if (!hasRecipe(crusherTile)) {
            neckOffset = 0;
            headOffset = 0;
        }

        return new float[]{neckOffset, headOffset};
    }

    public int getLightLevel(Level level, BlockPos pos) {
        int bLight = level.getBrightness(LightLayer.BLOCK, pos);
        int sLight = level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(bLight, sLight);
    }

    @Override
    public int getViewDistance() {
        return 68;
    }

    // Abstract methods for implementation
    protected abstract int getCrushingProgress(T crusherTile);
    protected abstract boolean hasRecipe(T crusherTile);
}
