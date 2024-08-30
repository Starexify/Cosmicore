package net.nova.cosmicore.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Function;

public class BaseModel extends Model {
    public final ModelPart piston;
    public final ModelPart neck;
    public final ModelPart head;

    public BaseModel(Function<ResourceLocation, RenderType> pRenderType, ModelPart root) {
        super(pRenderType);
        this.piston = root.getChild("piston");
        this.neck = piston.getChild("neck");
        this.head = piston.getChild("head");
    }

    public static LayerDefinition createLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition piston = partdefinition.addOrReplaceChild("piston", CubeListBuilder.create(), PartPose.offset(0.0F, 8.0F, 0.0F));
        PartDefinition neck = piston.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(0, 15).addBox(-5.0F, -15.9F, -5.0F, 10.0F, 6.9F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 16.0F, 0.0F));
        PartDefinition head = piston.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -11.9F, -6.0F, 12.0F, 3.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 16.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 48, 48);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int pPackedLight, int pPackedOverlay, int pColor) {
        piston.render(poseStack, vertexConsumer, pPackedLight, pPackedOverlay, pColor);
    }
}
