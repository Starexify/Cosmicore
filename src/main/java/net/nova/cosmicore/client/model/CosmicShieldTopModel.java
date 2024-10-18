package net.nova.cosmicore.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.nova.cosmicore.Cosmicore;

import java.util.function.Function;

public class CosmicShieldTopModel extends Model {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Cosmicore.rl("cosmic_shield"), "main");
    public static final ResourceLocation TEXTURE = Cosmicore.rl("textures/entity/cosmic_shield/cosmic_shield_tier_1.png");
    public final ModelPart modelRoot;
    public final ModelPart antenna;

    public CosmicShieldTopModel(Function<ResourceLocation, RenderType> pRenderType, ModelPart root) {
        super(pRenderType);
        this.modelRoot = root.getChild("root");
        this.antenna = modelRoot.getChild("antenna");
    }

    public static LayerDefinition createLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(5.0F, 24.0F, -4.0F));

        PartDefinition antenna = root.addOrReplaceChild("antenna", CubeListBuilder.create(), PartPose.offset(0.05F, 0.0F, 0.2F));
        PartDefinition main_and_sensor = antenna.addOrReplaceChild("main_and_sensor", CubeListBuilder.create(), PartPose.offset(-0.05F, -16.0F, -1.2F));
        PartDefinition antenna_main = main_and_sensor.addOrReplaceChild("antenna_main", CubeListBuilder.create().texOffs(0, 0).addBox(-2.45F, 0.0868F, -1.6333F, 5.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(0, 11).addBox(-2.95F, -0.9132F, -1.1333F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(8, 7).addBox(2.05F, -0.9132F, -1.1333F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(6, 12).addBox(-1.95F, -0.9132F, -2.1333F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(14, 6).addBox(-1.95F, -0.9132F, 2.8667F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.05F, 0.1F, 0.3F));
        PartDefinition antenna_sensor = main_and_sensor.addOrReplaceChild("antenna_sensor", CubeListBuilder.create().texOffs(0, 0).addBox(-0.4377F, -1.0541F, 1.3667F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(8, 14).addBox(-0.9377F, -2.0541F, 0.8667F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.0623F, -0.7591F, -0.7F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int pPackedLight, int pPackedOverlay, int pColor) {
        modelRoot.render(poseStack, vertexConsumer, pPackedLight, pPackedOverlay, pColor);
    }
}
