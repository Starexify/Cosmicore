package net.nova.cosmicore.client.renderer.block;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.nova.cosmicore.blockentity.CrusherTile;

@OnlyIn(Dist.CLIENT)
public class CrusherTileRenderer implements BlockEntityRenderer<CrusherTile> {
    private final BlockRenderDispatcher blockRenderer;

    public CrusherTileRenderer(BlockEntityRendererProvider.Context context) {
        this.blockRenderer = context.getBlockRenderDispatcher();
    }

    @Override
    public void render(CrusherTile crusherTile, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        poseStack.pushPose();
        // Render the item being crushed
        renderCrushedItem(crusherTile, poseStack, bufferSource, packedLight);

        poseStack.popPose();
    }

    private float calculateVerticalOffset(CrusherTile crusherTile, float partialTick) {
        // You can customize this method to calculate the offset based on the crusher's state
        // For example, you might use the crushing progress or a simple sine wave for continuous motion
        float time = (crusherTile.getLevel().getGameTime() + partialTick) / 20.0f;
        return (float) Math.sin(time * Math.PI) * 0.1f; // Adjust the multiplier to change the animation range
    }

    private void renderCrushedItem(CrusherTile crusherTile, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        ItemStack itemStack = crusherTile.getRenderedStack();

        poseStack.pushPose();
        poseStack.translate(0.5f, 0.4f, 0.5f);
        poseStack.scale(0.6f, 0.6f, 0.6f);
        itemRenderer.renderStatic(itemStack, ItemDisplayContext.FIXED, getLightLevel(crusherTile.getLevel(), crusherTile.getBlockPos()),
                OverlayTexture.NO_OVERLAY, poseStack, bufferSource, crusherTile.getLevel(), 1);
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
