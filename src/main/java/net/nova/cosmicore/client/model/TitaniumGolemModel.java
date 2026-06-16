package net.nova.cosmicore.client.model;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.nova.cosmicore.animations.TitaniumGolemAnimation;
import net.nova.cosmicore.client.renderer.entity.state.TitaniumGolemRenderState;

public class TitaniumGolemModel extends EntityModel<TitaniumGolemRenderState> {
  private final ModelPart body;
  private final ModelPart head;

  public TitaniumGolemModel(ModelPart root) {
    super(root);
    this.body = root.getChild("body");
    this.head = body.getChild("head");
  }

  public static LayerDefinition createBodyLayer() {
    MeshDefinition meshdefinition = new MeshDefinition();
    PartDefinition partdefinition = meshdefinition.getRoot();
    PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, -4.0F, 0.0F));
    PartDefinition arms = body.addOrReplaceChild("arms", CubeListBuilder.create(), PartPose.offset(0.0F, -13.0F, 0.0F));
    PartDefinition arm0 = arms.addOrReplaceChild("arm0", CubeListBuilder.create(), PartPose.offset(13.5F, 0.0F, 1.0F));
    PartDefinition up0 = arm0.addOrReplaceChild("up0", CubeListBuilder.create().texOffs(52, 27).addBox(-4.0F, -7.0F, -4.0F, 8.0F, 19.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
    PartDefinition details_up0 = up0.addOrReplaceChild("details_up0", CubeListBuilder.create().texOffs(72, 121).addBox(-2.0F, -1.0F, -4.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
        .texOffs(72, 121).addBox(-2.0F, -1.0F, 2.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
        .texOffs(72, 112).addBox(2.0F, -1.0F, -2.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
        .texOffs(72, 112).addBox(-4.0F, -1.0F, -2.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 13.0F, 0.0F));
    PartDefinition down_and_piston0 = arm0.addOrReplaceChild("down_and_piston0", CubeListBuilder.create(), PartPose.offset(0.0F, 16.0F, 0.0F));
    PartDefinition piston0 = down_and_piston0.addOrReplaceChild("piston0", CubeListBuilder.create().texOffs(50, 92).addBox(-2.0F, -22.0F, -2.0F, 4.0F, 31.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
    PartDefinition down0 = down_and_piston0.addOrReplaceChild("down0", CubeListBuilder.create(), PartPose.offset(0.0F, 2.0F, 0.0F));
    PartDefinition down_main0 = down0.addOrReplaceChild("down_main0", CubeListBuilder.create().texOffs(66, 58).addBox(-4.0F, -22.0F, -3.0F, 8.0F, 15.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 22.0F, -1.0F));
    PartDefinition details_down0 = down0.addOrReplaceChild("details_down0", CubeListBuilder.create().texOffs(86, 114).addBox(2.0F, -1.0F, -4.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
        .texOffs(86, 114).addBox(2.0F, -1.0F, 2.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
        .texOffs(86, 114).addBox(-4.0F, -1.0F, 2.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
        .texOffs(86, 114).addBox(-4.0F, -1.0F, -4.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 0.0F));
    PartDefinition arm1 = arms.addOrReplaceChild("arm1", CubeListBuilder.create(), PartPose.offset(-13.5F, 0.0F, 1.0F));
    PartDefinition up1 = arm1.addOrReplaceChild("up1", CubeListBuilder.create().texOffs(89, 26).addBox(-4.0F, -7.0F, -4.0F, 8.0F, 19.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
    PartDefinition details_up1 = up1.addOrReplaceChild("details_up1", CubeListBuilder.create().texOffs(72, 112).addBox(-4.0F, -1.0F, -2.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
        .texOffs(72, 121).addBox(-2.0F, -1.0F, -4.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
        .texOffs(72, 121).addBox(-2.0F, -1.0F, 2.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
        .texOffs(72, 112).addBox(2.0F, -1.0F, -2.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 13.0F, 0.0F));
    PartDefinition down_and_piston1 = arm1.addOrReplaceChild("down_and_piston1", CubeListBuilder.create(), PartPose.offset(0.0F, 16.0F, 0.0F));
    PartDefinition piston1 = down_and_piston1.addOrReplaceChild("piston1", CubeListBuilder.create().texOffs(108, 92).addBox(-2.0F, -22.0F, -2.0F, 4.0F, 31.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
    PartDefinition down1 = down_and_piston1.addOrReplaceChild("down1", CubeListBuilder.create(), PartPose.offset(0.0F, 2.0F, 0.0F));
    PartDefinition down_main1 = down1.addOrReplaceChild("down_main1", CubeListBuilder.create().texOffs(70, 84).addBox(-4.0F, -22.0F, -3.0F, 8.0F, 15.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 22.0F, -1.0F));
    PartDefinition details_down1 = down1.addOrReplaceChild("details_down1", CubeListBuilder.create().texOffs(86, 114).addBox(2.0F, -1.0F, -4.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
        .texOffs(86, 114).addBox(2.0F, -1.0F, 2.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
        .texOffs(86, 114).addBox(-4.0F, -1.0F, 2.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
        .texOffs(86, 114).addBox(-4.0F, -1.0F, -4.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 0.0F));
    PartDefinition torso_and_legs = body.addOrReplaceChild("torso_and_legs", CubeListBuilder.create(), PartPose.offset(0.0F, -1.5F, 0.5F));
    PartDefinition torso_main = torso_and_legs.addOrReplaceChild("torso_main", CubeListBuilder.create().texOffs(0, 55).addBox(-9.5F, -11.0F, -6.5F, 19.0F, 22.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.5F, 0.0F));
    PartDefinition torso_down_and_legs = torso_and_legs.addOrReplaceChild("torso_down_and_legs", CubeListBuilder.create(), PartPose.offset(0.0F, 10.0F, 0.0F));
    PartDefinition torso_down = torso_down_and_legs.addOrReplaceChild("torso_down", CubeListBuilder.create().texOffs(0, 101).addBox(-7.0F, -2.5F, -4.5F, 14.0F, 5.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 0.0F));
    PartDefinition legs = torso_down_and_legs.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offset(-1.5F, 22.5F, -0.5F));
    PartDefinition leg0 = legs.addOrReplaceChild("leg0", CubeListBuilder.create().texOffs(43, 1).addBox(-3.5F, -1.0F, -3.5F, 7.0F, 16.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.5F, -18.0F, 0.5F));
    PartDefinition leg1 = legs.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(74, 1).addBox(-3.5F, -1.0F, -3.5F, 7.0F, 16.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(6.5F, -18.0F, 0.5F));
    PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, -9.5F, -6.0F));
    PartDefinition main_head = head.addOrReplaceChild("main_head", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -4.5F, -10.0F, 10.0F, 9.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
    PartDefinition mouth = head.addOrReplaceChild("mouth", CubeListBuilder.create().texOffs(0, 24).addBox(-4.5F, -5.5F, -1.001F, 9.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
        .texOffs(0, 35).addBox(-6.0F, -0.5F, -11.0F, 12.0F, 3.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.0F, 0.0F));

    return LayerDefinition.create(meshdefinition, 128, 128);
  }

  @Override
  public void setupAnim(TitaniumGolemRenderState renderState) {
    super.setupAnim(renderState);

//        this.animateWalk(TitaniumGolemAnimation.WALK, renderState.walkAnimationPos, renderState.walkAnimationSpeed, 1.0F, 1.75F);
//        this.animate(renderState.idleAnimationState, TitaniumGolemAnimation.IDLE, renderState.ageInTicks);
//        this.animate(renderState.standbyAnimationState, TitaniumGolemAnimation.STANDBY, renderState.ageInTicks);

    this.head.yRot = renderState.yRot * (float) (Math.PI / 180.0);
    this.head.xRot = renderState.xRot * (float) (Math.PI / 180.0);
  }
}
