package net.nova.cosmicore.client.renderer.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.nova.cosmicore.Cosmicore;
import net.nova.cosmicore.blockentity.CosmicShieldTile;
import net.nova.cosmicore.client.model.CosmicShieldTierIModel;

public class CosmicShieldTileRenderer implements BlockEntityRenderer<CosmicShieldTile> {
    public static ResourceLocation SHIELD_TIER_I_LOCATION = Cosmicore.rl("textures/entity/cosmic_shield/cosmic_shield_tier_1.png");
    public final CosmicShieldTierIModel tierIModel;

    public CosmicShieldTileRenderer(BlockEntityRendererProvider.Context context) {
        this(context.getModelSet());
    }

    public CosmicShieldTileRenderer(EntityModelSet modelSet) {
        this.tierIModel = new CosmicShieldTierIModel(modelSet.bakeLayer(CosmicShieldTierIModel.LAYER_LOCATION));
    }

    public void render(CosmicShieldTile cosmicShieldTile, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutout(SHIELD_TIER_I_LOCATION));
        // Render the crystal
        if (!cosmicShieldTile.inventory.getStackInSlot(0).isEmpty())
            renderCrystal(partialTick, poseStack, vertexConsumer, packedLight, packedOverlay);
    }

    public void renderCrystal(float partialTick, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay) {
        poseStack.pushPose();
        float baseHeight = 1.5F;

        // Calculate floating offset using sine wave
        float gameTime = Minecraft.getInstance().level.getGameTime() + partialTick;
        float floatOffset = (float) Math.sin(gameTime * 0.05F) * 0.05F; // 0.05F speed & 0.1F height

        // Position with floating
        poseStack.translate(0.5, baseHeight + floatOffset, 0.5);

        // Rotation
        float rotationTime = gameTime * 2;
        poseStack.mulPose(Axis.YP.rotation(rotationTime * ((float) Math.PI / 180F)));

        poseStack.scale(1, -1, -1);
        tierIModel.renderToBuffer(poseStack, vertexConsumer, packedLight, packedOverlay);
        poseStack.popPose();
    }
}
