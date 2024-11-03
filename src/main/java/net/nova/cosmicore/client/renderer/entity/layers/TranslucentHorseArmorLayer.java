package net.nova.cosmicore.client.renderer.entity.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HorseModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EquipmentLayerRenderer;
import net.minecraft.client.renderer.entity.layers.HorseArmorLayer;
import net.minecraft.client.renderer.entity.state.HorseRenderState;
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
    public static final ModelLayerLocation HORSE_ARMOR = new ModelLayerLocation(Cosmicore.rl("horse_armor"), "main");
    private final HorseModel adultModel;
    private final HorseModel babyModel;
    private final EquipmentLayerRenderer equipmentRenderer;

    public TranslucentHorseArmorLayer(RenderLayerParent<HorseRenderState, HorseModel> renderer, EntityModelSet modelSet, EquipmentLayerRenderer equipmentRenderer) {
        super(renderer, modelSet, equipmentRenderer);
        this.equipmentRenderer = equipmentRenderer;
        this.adultModel = new HorseModel(modelSet.bakeLayer(ModelLayers.HORSE_ARMOR));
        this.babyModel = new HorseModel(modelSet.bakeLayer(ModelLayers.HORSE_BABY_ARMOR));
    }


    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, HorseRenderState livingEntity, float p_117036_, float p_117037_) {
/*        ItemStack itemstack = livingEntity.getBodyArmorItem();
        if (itemstack.getItem() instanceof AnimalArmorItem animalarmoritem && animalarmoritem.getBodyType() == AnimalArmorItem.BodyType.EQUESTRIAN &&
                animalarmoritem == CItems.LONSDALEITE_HORSE_ARMOR.get()) {
            this.getParentModel().copyPropertiesTo(this.model);
            this.model.prepareMobModel(livingEntity, limbSwing, limbSwingAmount, partialTicks);
            this.model.setupAnim(livingEntity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

            VertexConsumer vertexconsumer = buffer.getBuffer(RenderType.entityTranslucentCull(animalarmoritem.getTexture()));

            this.model.renderToBuffer(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY, -1);
        } else {*/
            super.render(poseStack, buffer, packedLight, livingEntity, p_117036_, p_117037_);

    }
}