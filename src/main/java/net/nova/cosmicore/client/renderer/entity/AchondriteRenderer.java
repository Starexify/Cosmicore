package net.nova.cosmicore.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
//import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.nova.cosmicore.Cosmicore;
import net.nova.cosmicore.client.model.BaseMeteorModel;
import net.nova.cosmicore.client.renderer.entity.state.MeteoriteRenderState;
import net.nova.cosmicore.entity.Achondrite;
import net.nova.cosmicore.init.CModelLayers;

public class AchondriteRenderer extends EntityRenderer<Achondrite, MeteoriteRenderState> {
  private final BaseMeteorModel meteorModel;
  public static final Identifier ACHONDRITE_LOCATION = Cosmicore.rl("textures/entity/meteors/achondrite.png");

  public AchondriteRenderer(EntityRendererProvider.Context context) {
    super(context);
    meteorModel = new BaseMeteorModel(context.bakeLayer(CModelLayers.ACHONDRITE));
  }

//    public void render(MeteoriteRenderState renderState, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
//        poseStack.pushPose();
//        VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderTypes.entityCutout(getTextureLocation(renderState)));
//        meteorModel.setupAnim(renderState);
//        meteorModel.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY);
//        poseStack.popPose();
//        super.render(renderState, poseStack, bufferSource, packedLight);
//    }

  public Identifier getTextureLocation(MeteoriteRenderState renderState) {
    return ACHONDRITE_LOCATION;
  }

  @Override
  public MeteoriteRenderState createRenderState() {
    return new MeteoriteRenderState();
  }

  @Override
  public void extractRenderState(Achondrite p_entity, MeteoriteRenderState reusedState, float partialTick) {
    super.extractRenderState(p_entity, reusedState, partialTick);
    reusedState.fallingAnimationState.copyFrom(p_entity.fallingAnimationState);
    reusedState.explodedAnimationState.copyFrom(p_entity.explodedAnimationState);
  }
}
