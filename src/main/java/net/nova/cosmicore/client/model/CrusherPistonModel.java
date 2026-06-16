package net.nova.cosmicore.client.model;

import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.Identifier;
import net.nova.cosmicore.Cosmicore;
import net.nova.cosmicore.client.renderer.blockentity.state.CrusherRenderState;

public class CrusherPistonModel extends Model<CrusherRenderState> {
  public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Cosmicore.rl("crusher_piston"), "main");
  public static final Identifier TEXTURE = Cosmicore.rl("textures/entity/crusher_piston/crusher_piston.png");
  public static final Identifier ADVANCED_TEXTURE = Cosmicore.rl("textures/entity/crusher_piston/advanced_crusher_piston.png");

  public final ModelPart piston;
  public final ModelPart neck;
  public final ModelPart head;

  private final float baseNeckY;
  private final float baseHeadY;

  public CrusherPistonModel(ModelPart root) {
    super(root, RenderTypes::entityCutout);
    this.piston = root.getChild("piston");
    this.neck = piston.getChild("neck");
    this.head = piston.getChild("head");

    this.baseNeckY = this.neck.y;
    this.baseHeadY = this.head.y;
  }

  @Override
  public void setupAnim(CrusherRenderState state) {
    this.neck.y = this.baseNeckY + (state.neckOffset * 16.0F);
    this.head.y = this.baseHeadY + (state.headOffset * 16.0F);
  }

  public static LayerDefinition createLayer() {
    MeshDefinition meshdefinition = new MeshDefinition();
    PartDefinition partdefinition = meshdefinition.getRoot();

    PartDefinition piston = partdefinition.addOrReplaceChild("piston", CubeListBuilder.create(), PartPose.offset(0.0F, 8.0F, 0.0F));
    PartDefinition neck = piston.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(0, 15).addBox(-5.0F, -15.9F, -5.0F, 10.0F, 6.9F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 16.0F, 0.0F));
    PartDefinition head = piston.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -11.9F, -6.0F, 12.0F, 3.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 16.0F, 0.0F));

    return LayerDefinition.create(meshdefinition, 48, 48);
  }
}
