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
import net.nova.cosmicore.blockentity.AdvancedCrusherTile;

@OnlyIn(Dist.CLIENT)
public class AdvancedCrusherTileRenderer implements BlockEntityRenderer<AdvancedCrusherTile> {
    private final BlockRenderDispatcher blockRenderer;

    public AdvancedCrusherTileRenderer(BlockEntityRendererProvider.Context context) {
        this.blockRenderer = context.getBlockRenderDispatcher();
    }

    @Override
    public void render(AdvancedCrusherTile advancedCrusherTile, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        poseStack.pushPose();
        // Render the item being crushed
        renderCrushedItem(advancedCrusherTile, poseStack, bufferSource, packedLight);

        poseStack.popPose();
    }

    private void renderCrushedItem(AdvancedCrusherTile advancedCrusherTile, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
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
