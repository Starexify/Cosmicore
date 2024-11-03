package net.nova.cosmicore.client.renderer.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.nova.cosmicore.blockentity.CosmicShieldTile;
import net.nova.cosmicore.client.model.CosmicShieldTierIModel;

@OnlyIn(Dist.CLIENT)
public class CosmicShieldTileRenderer implements BlockEntityRenderer<CosmicShieldTile> {
    private final ResourceLocation shieldTierITexture = CosmicShieldTierIModel.TEXTURE;
    private final ModelLayerLocation shieldTierILayer = CosmicShieldTierIModel.LAYER_LOCATION;
    private final CosmicShieldTierIModel tierIModel;

    public CosmicShieldTileRenderer(BlockEntityRendererProvider.Context context) {
        ModelPart modelPart = context.bakeLayer(shieldTierILayer);
        this.tierIModel = new CosmicShieldTierIModel(RenderType::entityCutout, modelPart);
    }

    @Override
    public void render(CosmicShieldTile cosmicShieldTile, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        poseStack.pushPose();

        // Render the crystal
        if (!cosmicShieldTile.isEmpty()) {
            renderCrystal(cosmicShieldTile, partialTick, poseStack, bufferSource, packedLight, packedOverlay);
        }

        poseStack.popPose();
    }

    public void renderCrystal(CosmicShieldTile cosmicShieldTile, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutout(shieldTierITexture));

        poseStack.pushPose();
        float baseHeight = 1.5F;

        // Calculate floating offset using sine wave
        float gameTime = Minecraft.getInstance().level.getGameTime() + partialTick;
        float floatOffset = (float) Math.sin(gameTime * 0.05F) * 0.05F; // Adjust 0.05F for speed and 0.1F for height

        // Apply position with floating
        poseStack.translate(0.5, baseHeight + floatOffset, 0.5);

        // Apply rotation
        float rotationTime = gameTime * 2;
        poseStack.mulPose(Axis.YP.rotation(rotationTime * ((float) Math.PI / 180F)));

        poseStack.scale(1, -1, -1);
        tierIModel.renderToBuffer(poseStack, vertexConsumer, packedLight, packedOverlay);
        poseStack.popPose();
    }

    @Override
    public int getViewDistance() {
        return 68;
    }
}
