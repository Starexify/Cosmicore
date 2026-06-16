package net.nova.cosmicore.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.renderer.entity.layers.EquipmentLayerRenderer;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.nova.cosmicore.init.CItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EquipmentLayerRenderer.class)
public class EquipmentLayerRendererMixin {
  @Redirect(method = "renderLayers(Lnet/minecraft/client/resources/model/EquipmentClientInfo$LayerType;Lnet/minecraft/resources/ResourceKey;Lnet/minecraft/client/model/Model;Ljava/lang/Object;Lnet/minecraft/world/item/ItemStack;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;ILnet/minecraft/resources/Identifier;II)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/rendertype/RenderTypes;armorCutoutNoCull(Lnet/minecraft/resources/Identifier;)Lnet/minecraft/client/renderer/rendertype/RenderType;"))
  private RenderType redirectArmorRenderTypes(Identifier location, @Local(argsOnly = true) ItemStack item) {
    if (item.is(CItems.LONSDALEITE_HORSE_ARMOR) || item.is(CItems.LONSDALEITE_HELMET) || item.is(CItems.LONSDALEITE_CHESTPLATE) ||
        item.is(CItems.LONSDALEITE_LEGGINGS) || item.is(CItems.LONSDALEITE_BOOTS))
      return RenderTypes.armorTranslucent(location);

    return RenderTypes.armorCutoutNoCull(location);
  }
}
