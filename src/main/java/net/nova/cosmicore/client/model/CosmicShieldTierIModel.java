package net.nova.cosmicore.client.model;

import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.nova.cosmicore.Cosmicore;

public class CosmicShieldTierIModel extends Model {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Cosmicore.rl("cosmic_shield"), "main");
    public final ModelPart modelRoot;

    public CosmicShieldTierIModel(ModelPart root) {
        super(root, RenderType::entityCutout);
        this.modelRoot = root.getChild("root");
    }

    public static LayerDefinition createLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create().texOffs(9, 0).addBox(-0.5F, -14.0F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 8).addBox(-0.5F, -18.0F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 5).addBox(-1.0F, -14.5F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-1.5F, -16.5F, -1.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(6, 6).addBox(-1.0F, -17.4F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 16, 16);
    }
}