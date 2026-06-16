package net.nova.cosmicore.client.model;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.nova.cosmicore.animations.MeteorFallingAnimation;
import net.nova.cosmicore.client.renderer.entity.state.MeteoriteRenderState;

public class BaseMeteorModel extends EntityModel<MeteoriteRenderState> {
  private final ModelPart root;

  public BaseMeteorModel(ModelPart root) {
    super(root);
    this.root = root.getChild("root");
  }

  public static LayerDefinition createBodyLayer() {
    MeshDefinition meshdefinition = new MeshDefinition();
    PartDefinition partdefinition = meshdefinition.getRoot();

    PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offsetAndRotation(1.0F, 10.0F, 2.5F, 0.0F, -2.1817F, 1.5708F));

    PartDefinition main = root.addOrReplaceChild("main", CubeListBuilder.create().texOffs(0, 0).addBox(-19.0F, -26.0F, 0.0F, 22.0F, 16.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(5.7808F, 18.4129F, -7.3726F));
    PartDefinition NS = root.addOrReplaceChild("NS", CubeListBuilder.create(), PartPose.offset(8.7808F, 8.4129F, -7.3726F));
    PartDefinition NS_1 = NS.addOrReplaceChild("NS_1", CubeListBuilder.create().texOffs(76, 70).addBox(-25.0F, -12.0F, 3.0F, 28.0F, 8.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
    PartDefinition main_NS = NS.addOrReplaceChild("main_NS", CubeListBuilder.create().texOffs(76, 0).addBox(-17.0F, -12.0F, -1.0F, 18.0F, 12.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -2.0F, 0.0F));
    PartDefinition EW = root.addOrReplaceChild("EW", CubeListBuilder.create(), PartPose.offset(-2.2192F, 18.4129F, 0.6274F));
    PartDefinition main_EW = EW.addOrReplaceChild("main_EW", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
    PartDefinition main_EW_r1 = main_EW.addOrReplaceChild("main_EW_r1", CubeListBuilder.create().texOffs(0, 70).addBox(-23.0F, -12.0F, -1.0F, 24.0F, 12.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.0F, -12.0F, -6.0F, 0.0F, 0.0F, 0.0F));
    PartDefinition EW_1 = EW.addOrReplaceChild("EW_1", CubeListBuilder.create().texOffs(50, 40).addBox(-7.0F, -22.0F, -11.0F, 14.0F, 8.0F, 22.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
    PartDefinition UD = root.addOrReplaceChild("UD", CubeListBuilder.create(), PartPose.offset(-2.2192F, 18.4129F, 0.6274F));
    PartDefinition main_UD = UD.addOrReplaceChild("main_UD", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
    PartDefinition main_UD_r1 = main_UD.addOrReplaceChild("main_UD_r1", CubeListBuilder.create().texOffs(0, 32).addBox(-17.0F, -12.0F, -1.0F, 18.0F, 12.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.0F, -10.0F, -6.0F, -1.5708F, 0.0F, 1.5708F));
    PartDefinition UD_1 = UD.addOrReplaceChild("UD_1", CubeListBuilder.create().texOffs(68, 88).addBox(-7.0F, -29.0F, -4.0F, 14.0F, 22.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

    return LayerDefinition.create(meshdefinition, 160, 160);
  }

  @Override
  public void setupAnim(MeteoriteRenderState renderState) {
    super.setupAnim(renderState);
//        this.animate(renderState.fallingAnimationState, MeteorFallingAnimation.FALLING_ROTATION_ANIMATION, renderState.ageInTicks, 1.0F);
//        this.animate(renderState.explodedAnimationState, MeteorFallingAnimation.DEATH_ANIMATION, renderState.ageInTicks, 1.0F);
  }
}
