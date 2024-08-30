package net.nova.cosmicore.client.renderer.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.nova.cosmicore.blockentity.AdvancedCrusherTile;
import net.nova.cosmicore.client.model.AdvancedCrusherPistonModel;

@OnlyIn(Dist.CLIENT)
public class AdvancedCrusherTileRenderer implements BlockEntityRenderer<AdvancedCrusherTile> {
    private final ResourceLocation pistonTexture = AdvancedCrusherPistonModel.TEXTURE;
    private final ModelLayerLocation pistonLayer = AdvancedCrusherPistonModel.LAYER_LOCATION;
    private final BlockRenderDispatcher blockRenderer;
    private final AdvancedCrusherPistonModel pistonModel;

    public AdvancedCrusherTileRenderer(BlockEntityRendererProvider.Context context) {
        this.blockRenderer = context.getBlockRenderDispatcher();
        ModelPart modelPart = context.bakeLayer(pistonLayer);
        this.pistonModel = new AdvancedCrusherPistonModel(RenderType::entityCutout, modelPart);
    }

    @Override
    public void render(AdvancedCrusherTile advancedCrusherTile, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        poseStack.pushPose();

        // Render the animated piston
        renderAnimatedPiston(advancedCrusherTile, partialTick, poseStack, bufferSource, packedLight, packedOverlay);

        // Render the item being crushed
        renderCrushedItem(advancedCrusherTile, poseStack, bufferSource);

        poseStack.popPose();
    }

    private void renderAnimatedPiston(AdvancedCrusherTile advancedCrusherTile, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        float[] offsets = calculateVerticalOffsets(advancedCrusherTile, partialTick);
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

    private float[] calculateVerticalOffsets(AdvancedCrusherTile advancedCrusherTile, float partialTick) {
        int crushingProgress = advancedCrusherTile.getCrushingProgress();
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

        if (!advancedCrusherTile.hasRecipe()) {
            neckOffset = 0;
            headOffset = 0;
        }

        return new float[]{neckOffset, headOffset};
    }

    private void renderCrushedItem(AdvancedCrusherTile advancedCrusherTile, PoseStack poseStack, MultiBufferSource bufferSource) {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        ItemStack itemStack = advancedCrusherTile.getRenderedStack();

        poseStack.pushPose();
        poseStack.translate(0.5f, 0.4f, 0.5f);
        poseStack.scale(0.6f, 0.6f, 0.6f);
        itemRenderer.renderStatic(itemStack, ItemDisplayContext.FIXED, getLightLevel(advancedCrusherTile.getLevel(), advancedCrusherTile.getBlockPos()),
                OverlayTexture.NO_OVERLAY, poseStack, bufferSource, advancedCrusherTile.getLevel(), 1);
        poseStack.popPose();
    }

    private int getLightLevel(Level level, BlockPos pos) {
        int bLight = level.getBrightness(LightLayer.BLOCK, pos);
        int sLight = level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(bLight, sLight);
    }

    @Override
    public int getViewDistance() {
        return 68;
    }
}