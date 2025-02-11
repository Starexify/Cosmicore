package net.nova.cosmicore.enchantment;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;
import net.nova.cosmicore.item.FallenMeteorLocator;

import java.util.List;

public record MagnetismEffect(LevelBasedValue range) implements EnchantmentEntityEffect {
    public static final MapCodec<MagnetismEffect> CODEC = RecordCodecBuilder.mapCodec(builder -> builder.group(
            LevelBasedValue.CODEC.fieldOf("range").forGetter(MagnetismEffect::range)
    ).apply(builder, MagnetismEffect::new));

    @Override
    public void apply(ServerLevel level, int enchantmentLevel, EnchantedItemInUse item, Entity entity, Vec3 origin) {
        if (entity instanceof Player player && !(item.itemStack().getItem() instanceof FallenMeteorLocator)) {
            double magnetRange = range.calculate(enchantmentLevel);

            List<ItemEntity> nearbyItems = level.getEntitiesOfClass(
                    ItemEntity.class,
                    player.getBoundingBox().inflate(magnetRange),
                    itemEntity -> isEligibleItem(player, itemEntity)
            );

            if (!nearbyItems.isEmpty()) for (ItemEntity itemEntity : nearbyItems) dragItems(itemEntity, player);
        }
    }

    public boolean isEligibleItem(Player player, ItemEntity itemEntity) {
        return itemEntity.isAlive() && !itemEntity.isRemoved() && (itemEntity.getOwner() == null || !itemEntity.getOwner().equals(player) || itemEntity.getAge() > 100);
    }

    public static void dragItems(ItemEntity itemEntity, Player player) {
        Vec3 playerPos = player.position().add(0, 0.5, 0);
        Vec3 itemPos = itemEntity.position();
        Vec3 direction = playerPos.subtract(itemPos);

        if (player.getInventory().getFreeSlot() == -1) {
            // Rotate around player
            double angle = player.tickCount * 0.2;
            Vec3 rotationCenter = playerPos.add(0, 0.5F, 0);
            Vec3 rotatedPos = rotationCenter.add(
                    Math.cos(angle) * 1.5,
                    Math.sin(angle * 0.5) * 0.3,
                    Math.sin(angle) * 1.5
            );

            Vec3 movement = rotatedPos.subtract(itemPos).normalize().scale(0.15);
            itemEntity.setDeltaMovement(movement);
        } else {
            // Move towards player with dynamic speed
            double speedFactor = 0.25;  // Increased base speed
            double minSpeed = 0.1;      // Increased minimum speed
            double maxSpeed = 0.3;      // Increased maximum speed

            double distance = direction.length();
            double speed = Math.min(maxSpeed, Math.max(minSpeed, distance * speedFactor));
            Vec3 movement = direction.normalize().scale(speed);

            itemEntity.setDeltaMovement(movement);
            itemEntity.setNoPickUpDelay(); // Removes pickup delay completely
        }
    }

    @Override
    public MapCodec<MagnetismEffect> codec() {
        return CODEC;
    }
}
