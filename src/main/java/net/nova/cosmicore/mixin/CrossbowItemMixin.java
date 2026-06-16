package net.nova.cosmicore.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.nova.cosmicore.init.CItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(CrossbowItem.class)
public class CrossbowItemMixin {
  @WrapOperation(method = "getChargeDuration", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/enchantment/EnchantmentHelper;modifyCrossbowChargingTime(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/LivingEntity;F)F"))
  private static float crossChangeChargingTime(ItemStack stack, LivingEntity entity, float crossbowChargingTime, Operation<Float> original) {
    if (stack.is(CItems.TITANIUM_CROSSBOW)) return 1.1F;
    return crossbowChargingTime;
  }
}
