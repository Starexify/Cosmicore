package net.nova.cosmicore.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.nova.cosmicore.Cosmicore;

@OnlyIn(Dist.CLIENT)
public class TranslucentArmorModel<T extends LivingEntity> extends HumanoidArmorModel<T> {
    public final String name;
    private final EquipmentSlot slot;

    public TranslucentArmorModel(ModelPart root, String name, EquipmentSlot slot) {
        super(root);
        this.name = name;
        this.slot = slot;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, int color) {
        RenderType renderType = RenderType.entityTranslucent(getArmorLocation());
        MultiBufferSource.BufferSource bufferSource = Minecraft.getInstance().renderBuffers().bufferSource();

        VertexConsumer translucentBuffer = ItemRenderer.getArmorFoilBuffer(bufferSource, renderType, false);

        super.renderToBuffer(poseStack, translucentBuffer, packedLight, packedOverlay, color);
    }

    private ResourceLocation getArmorLocation() {
        String layer = slot == EquipmentSlot.LEGS ? "layer_2" : "layer_1";
        return Cosmicore.rl("textures/models/armor/" + name + "_" + layer + ".png");
    }
}