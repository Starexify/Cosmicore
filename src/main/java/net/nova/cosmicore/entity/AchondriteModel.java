package net.nova.cosmicore.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.nova.cosmicore.Cosmicore;

@OnlyIn(Dist.CLIENT)
public class AchondriteModel<T extends Achondrite> extends HierarchicalModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Cosmicore.rl("achondrite"), "main");
    public static final ResourceLocation TEXTURE = Cosmicore.rl("textures/entity/meteors/achondrite.png");
    private final ModelPart root;

    public AchondriteModel(ModelPart root) {
        this.root = root.getChild("root");
    }

    public static LayerDefinition createLayer() {
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
    public void setupAnim(Achondrite entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.animate(entity.fallingAnimationState, MeteorFallingAnimation.FALLING_ROTATION_ANIMATION, ageInTicks);
        this.animate(entity.explodedAnimationState, MeteorFallingAnimation.DEATH_ANIMATION, ageInTicks);
    }

    @Override
    public void renderToBuffer(PoseStack pPoseStack, VertexConsumer pBuffer, int pPackedLight, int pPackedOverlay, int pColor) {
        root.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pColor);
    }

    @Override
    public ModelPart root() {
        return this.root;
    }
}
