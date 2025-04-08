package net.nova.cosmicore.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.nova.cosmicore.data.CEnchantments;
import net.nova.cosmicore.event.CEventBusGame;
import net.nova.cosmicore.init.CDataComponents;
import org.jetbrains.annotations.Nullable;

public class FallenMeteorLocator extends Item {
    public static Component OUT_OF_RANGE = Component.translatable("item.cosmicore.meteor_locator.out_of_range").withStyle(ChatFormatting.RED);
    public static String METEOR_LOCATION_STR = "item.cosmicore.meteor_locator.distance";

    public FallenMeteorLocator(Properties properties) {
        super(properties);
    }

    @Override
    public void inventoryTick(ItemStack stack, ServerLevel serverLevel, Entity entity, @Nullable EquipmentSlot equipmentSlot) {
        super.inventoryTick(stack, serverLevel, entity, equipmentSlot);

        if (!serverLevel.isClientSide && entity instanceof Player player) {
            boolean isHoldingItem = player.getOffhandItem() == stack;
            if (!isHoldingItem) return;

            BlockPos currentPos = stack.get(CDataComponents.LAST_LOCATION);
            BlockPos newPos = getBlockPos(currentPos);

            if (newPos != null && !newPos.equals(currentPos)) stack.set(CDataComponents.LAST_LOCATION, newPos);

            BlockPos displayPos = stack.get(CDataComponents.LAST_LOCATION);
            if (displayPos != null) {
                BlockPos playerPos = player.blockPosition();
                int xDistance = Math.abs(displayPos.getX() - playerPos.getX());
                int zDistance = Math.abs(displayPos.getZ() - playerPos.getZ());
                double horizontalDistance = Math.sqrt(xDistance * xDistance + zDistance * zDistance);

                Holder<Enchantment> magnetism = player.level().registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(CEnchantments.MAGNETISM);
                int magnetismLevel = stack.getEnchantmentLevel(magnetism);
                int detectionRange = 750 + (magnetismLevel * 350);

                if (horizontalDistance > detectionRange) {
                    player.displayClientMessage(OUT_OF_RANGE, true);
                } else {
                    Component METEOR_LOCATION = Component.translatable(METEOR_LOCATION_STR,
                                    (int) horizontalDistance, displayPos.getX(), displayPos.getZ())
                            .withStyle(ChatFormatting.GOLD);
                    player.displayClientMessage(METEOR_LOCATION, true);
                }
            }
        }
    }

    public static @Nullable BlockPos getBlockPos(@Nullable BlockPos currentPos) {
        if (CEventBusGame.meteorSpawner != null) {
            BlockPos lastSpawnPos = CEventBusGame.meteorSpawner.getLastMeteorSpawnPos();
            if (lastSpawnPos != null) return lastSpawnPos;
        }
        return currentPos;
    }
}