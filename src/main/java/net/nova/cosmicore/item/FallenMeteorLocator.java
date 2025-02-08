package net.nova.cosmicore.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundPlayerLookAtPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerRotationPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.PacketDistributor;
import net.nova.cosmicore.event.CEventBusGame;
import net.nova.cosmicore.init.CDataComponents;
import org.jetbrains.annotations.Nullable;

public class FallenMeteorLocator extends Item {
    public FallenMeteorLocator(Properties properties) {
        super(properties);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        super.inventoryTick(stack, level, entity, slotId, isSelected);

        if (!level.isClientSide && entity instanceof Player player) {

            BlockPos currentPos = stack.get(CDataComponents.LAST_LOCATION);
            BlockPos newPos = getBlockPos(currentPos);

            if (newPos != null && !newPos.equals(currentPos)) {
                stack.set(CDataComponents.LAST_LOCATION, newPos);
            }

            BlockPos displayPos = stack.get(CDataComponents.LAST_LOCATION);
            if (displayPos != null) {
                BlockPos playerPos = player.blockPosition();
                int xDistance = Math.abs(displayPos.getX() - playerPos.getX());
                int zDistance = Math.abs(displayPos.getZ() - playerPos.getZ());

                if (xDistance >= 750 || zDistance >= 750) {
                    // Calculate direction to meteor
                    double dx = displayPos.getX() - playerPos.getX();
                    double dz = displayPos.getZ() - playerPos.getZ();

                    // Calculate yaw angle (in degrees)
                    double angle = Math.toDegrees(Math.atan2(-dx, dz));

                    // Normalize angle to Minecraft's coordinate system
                    float targetYaw = (float) ((angle + 180) % 360);
                    if (targetYaw < 0) targetYaw += 360;

                }
            }
        }
    }

    public static @Nullable BlockPos getBlockPos(@Nullable BlockPos currentPos) {
        if (CEventBusGame.meteorSpawner != null) {
            BlockPos lastSpawnPos = CEventBusGame.meteorSpawner.getLastMeteorSpawnPos();
            if (lastSpawnPos != null) {
                return lastSpawnPos;
            }
        }
        return currentPos;
    }
}