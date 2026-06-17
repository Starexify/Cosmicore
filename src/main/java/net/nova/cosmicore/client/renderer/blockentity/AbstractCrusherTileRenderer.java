package net.nova.cosmicore.client.renderer.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.util.LightCoordsUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.Vec3;
import net.nova.cosmicore.client.model.CrusherPistonModel;
import net.nova.cosmicore.client.renderer.blockentity.state.CrusherRenderState;
import org.jspecify.annotations.Nullable;

public abstract class AbstractCrusherTileRenderer<T extends BlockEntity, S extends CrusherRenderState> implements BlockEntityRenderer<T, S> {
  public final BlockEntityRenderDispatcher blockRenderer;
  public final Identifier pistonTexture;
  public final ModelLayerLocation pistonLayer;
  public final CrusherPistonModel pistonModel;

  protected AbstractCrusherTileRenderer(BlockEntityRendererProvider.Context context, Identifier pistonTexture, ModelLayerLocation pistonLayer, CrusherPistonModel pistonModel) {
    this.blockRenderer = context.blockEntityRenderDispatcher();
    this.pistonTexture = pistonTexture;
    this.pistonLayer = pistonLayer;
    this.pistonModel = pistonModel;
  }

  @Override
  public void extractRenderState(T blockEntity, S state, float partialTicks, Vec3 cameraPosition, ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress) {
    BlockEntityRenderer.super.extractRenderState(blockEntity, state, partialTicks, cameraPosition, breakProgress);

    float[] offsets = this.calculateVerticalOffsets(blockEntity, partialTicks);
    state.neckOffset = offsets[0];
    state.headOffset = offsets[1];

    Level level = blockEntity.getLevel();
    if (level != null) state.lightCoords = LightCoordsUtil.getLightCoords(level, blockEntity.getBlockPos());
  }

  @Override
  public void submit(S state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState) {
    poseStack.pushPose();
    poseStack.translate(0.5, 1.5, 0.5);
    poseStack.scale(1.0F, -1.0F, -1.0F);
    submitNodeCollector.submitModel(
        this.pistonModel,
        state,
        poseStack,
        this.pistonTexture,
        state.lightCoords, OverlayTexture.NO_OVERLAY,
        0,
        null
    );
    poseStack.popPose();
  }

  protected float[] calculateVerticalOffsets(T crusherTile, float partialTick) {
    int crushingProgress = getCrushingProgress(crusherTile);
    int maxCrushingProgress = 400;

    float progress = (crushingProgress + partialTick) / maxCrushingProgress;

    float neckOffset;
    float headOffset;

    if (progress < 0.95f) { // First 95%: Move down
      neckOffset = -0.125f * Math.min(progress / 0.5f, 1); // Reaches max at 95 ticks 0.2375f
      headOffset = -0.3f * Math.min(progress / 0.95f, 1);  // Reaches max at 380 ticks
    }
    else { // Last 5%: Move up quickly
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

  // Abstract methods for implementation
  protected abstract int getCrushingProgress(T crusherTile);

  protected abstract boolean hasRecipe(T crusherTile);
}
