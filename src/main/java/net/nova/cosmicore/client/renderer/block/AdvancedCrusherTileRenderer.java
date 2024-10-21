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
import net.nova.cosmicore.blockentity.AdvancedCrusherTile;
import net.nova.cosmicore.client.model.CrusherPistonModel;

@OnlyIn(Dist.CLIENT)
public class AdvancedCrusherTileRenderer extends AbstractCrusherTileRenderer<AdvancedCrusherTile> {
    public AdvancedCrusherTileRenderer(BlockEntityRendererProvider.Context context) {
        super(context, CrusherPistonModel.ADVANCED_TEXTURE, CrusherPistonModel.LAYER_LOCATION,
                new CrusherPistonModel(RenderType::entityCutout, context.bakeLayer(CrusherPistonModel.LAYER_LOCATION)));
    }

    @Override
    public void render(AdvancedCrusherTile advancedCrusherTile, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        // Render the item in crusher
        poseStack.pushPose();
        renderCrushedItems(advancedCrusherTile, poseStack, bufferSource);
        poseStack.popPose();

        super.render(advancedCrusherTile, partialTick, poseStack, bufferSource, packedLight, packedOverlay);
    }

    private void renderCrushedItems(AdvancedCrusherTile advancedCrusherTile, PoseStack poseStack, MultiBufferSource bufferSource) {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        ItemStack mainStack = advancedCrusherTile.getRenderedStack();
        ItemStack additionalStack = advancedCrusherTile.getRenderedAddition();

        if (!mainStack.isEmpty() && additionalStack.isEmpty() || mainStack.isEmpty() && !additionalStack.isEmpty()) {
            ItemStack stackToRender = !mainStack.isEmpty() ? mainStack : additionalStack;
            poseStack.pushPose();
            poseStack.translate(0.5f, 0.4f, 0.5f);
            poseStack.scale(0.6f, 0.6f, 0.6f);
            itemRenderer.renderStatic(stackToRender, ItemDisplayContext.FIXED, getLightLevel(advancedCrusherTile.getLevel(), advancedCrusherTile.getBlockPos()),
                    OverlayTexture.NO_OVERLAY, poseStack, bufferSource, advancedCrusherTile.getLevel(), 1);
            poseStack.popPose();
        } else if (!mainStack.isEmpty() && !additionalStack.isEmpty()) {
            // Render main item
            poseStack.pushPose();
            poseStack.translate(0.65f, 0.4f, 0.65f);
            poseStack.scale(0.6f, 0.6f, 0.6f);
            itemRenderer.renderStatic(mainStack, ItemDisplayContext.FIXED, getLightLevel(advancedCrusherTile.getLevel(), advancedCrusherTile.getBlockPos()),
                    OverlayTexture.NO_OVERLAY, poseStack, bufferSource, advancedCrusherTile.getLevel(), 1);
            poseStack.popPose();

            // Render additional item
            poseStack.pushPose();
            poseStack.translate(0.35f, 0.4f, 0.35f);
            poseStack.scale(0.6f, 0.6f, 0.6f);
            itemRenderer.renderStatic(additionalStack, ItemDisplayContext.FIXED, getLightLevel(advancedCrusherTile.getLevel(), advancedCrusherTile.getBlockPos()),
                    OverlayTexture.NO_OVERLAY, poseStack, bufferSource, advancedCrusherTile.getLevel(), 1);
            poseStack.popPose();
        }
    }

    @Override
    protected int getCrushingProgress(AdvancedCrusherTile advancedCrusherTile) {
        return advancedCrusherTile.getCrushingProgress();
    }

    @Override
    protected boolean hasRecipe(AdvancedCrusherTile advancedCrusherTile) {
        return advancedCrusherTile.hasRecipe();
    }
}