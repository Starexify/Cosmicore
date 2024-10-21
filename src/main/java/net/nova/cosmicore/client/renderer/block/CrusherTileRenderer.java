package net.nova.cosmicore.client.renderer.block;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.nova.cosmicore.blockentity.CrusherTile;
import net.nova.cosmicore.client.model.CrusherPistonModel;

@OnlyIn(Dist.CLIENT)
public class CrusherTileRenderer extends AbstractCrusherTileRenderer<CrusherTile> {
    public CrusherTileRenderer(BlockEntityRendererProvider.Context context) {
        super(context, CrusherPistonModel.TEXTURE, CrusherPistonModel.LAYER_LOCATION,
                new CrusherPistonModel(RenderType::entityCutout, context.bakeLayer(CrusherPistonModel.LAYER_LOCATION)));
    }

    @Override
    public void render(CrusherTile crusherTile, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        // Render the item in crusher
        poseStack.pushPose();
        renderCrushedItem(crusherTile, poseStack, bufferSource);
        poseStack.popPose();

        super.render(crusherTile, partialTick, poseStack, bufferSource, packedLight, packedOverlay);
    }

    public void renderCrushedItem(CrusherTile crusherTile, PoseStack poseStack, MultiBufferSource bufferSource) {
        ItemStack itemStack = crusherTile.getRenderedStack();

        if (!itemStack.isEmpty()) {
            ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
            poseStack.pushPose();
            poseStack.translate(0.5f, 0.4f, 0.5f);
            poseStack.scale(0.6f, 0.6f, 0.6f);
            itemRenderer.renderStatic(itemStack, ItemDisplayContext.FIXED, getLightLevel(crusherTile.getLevel(), crusherTile.getBlockPos()),
                    OverlayTexture.NO_OVERLAY, poseStack, bufferSource, crusherTile.getLevel(), 1);
            poseStack.popPose();
        }
    }

    @Override
    protected int getCrushingProgress(CrusherTile crusherTile) {
        return crusherTile.getCrushingProgress();
    }

    @Override
    protected boolean hasRecipe(CrusherTile crusherTile) {
        return crusherTile.hasRecipe();
    }
}
