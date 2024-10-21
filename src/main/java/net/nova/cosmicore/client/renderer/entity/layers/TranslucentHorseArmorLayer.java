package net.nova.cosmicore.client.renderer.entity.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HorseModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.HorseArmorLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.item.AnimalArmorItem;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.nova.cosmicore.Cosmicore;
import net.nova.cosmicore.init.CItems;

@OnlyIn(Dist.CLIENT)
public class TranslucentHorseArmorLayer extends HorseArmorLayer {
    public static final ModelLayerLocation HORSE_ARMOR =  new ModelLayerLocation(Cosmicore.rl("horse_armor"), "main");
    private final HorseModel<Horse> model;

    public TranslucentHorseArmorLayer(RenderLayerParent<Horse, HorseModel<Horse>> renderer, EntityModelSet modelSet) {
        super(renderer, modelSet);
        this.model = new HorseModel<>(modelSet.bakeLayer(HORSE_ARMOR));
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, Horse livingEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        ItemStack itemstack = livingEntity.getBodyArmorItem();
        if (itemstack.getItem() instanceof AnimalArmorItem animalarmoritem && animalarmoritem.getBodyType() == AnimalArmorItem.BodyType.EQUESTRIAN &&
                animalarmoritem == CItems.LONSDALEITE_HORSE_ARMOR.get()) {
            this.getParentModel().copyPropertiesTo(this.model);
            this.model.prepareMobModel(livingEntity, limbSwing, limbSwingAmount, partialTicks);
            this.model.setupAnim(livingEntity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

            VertexConsumer vertexconsumer = buffer.getBuffer(RenderType.entityTranslucentCull(animalarmoritem.getTexture()));

            this.model.renderToBuffer(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY, -1);
        } else {
            super.render(poseStack, buffer, packedLight, livingEntity, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch);
        }
    }
}