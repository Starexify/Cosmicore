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

public record MagnetismEffect(LevelBasedValue range) implements EnchantmentEntityEffect {
    public static final MapCodec<MagnetismEffect> CODEC = RecordCodecBuilder.mapCodec(builder -> builder.group(
            LevelBasedValue.CODEC.fieldOf("range").forGetter(MagnetismEffect::range)
    ).apply(builder, MagnetismEffect::new));

    @Override
    public void apply(ServerLevel level, int enchantmentLevel, EnchantedItemInUse item, Entity entity, Vec3 origin) {
        if (entity instanceof ItemEntity itemEntity) {
            Player player = level.getNearestPlayer(itemEntity, range.calculate(enchantmentLevel));

            if (player != null) {
                Vec3 playerPos = player.position();
                Vec3 itemPos = itemEntity.position();
                Vec3 direction = playerPos.subtract(itemPos);

                if (player.getInventory().getFreeSlot() == -1) {
                    // Rotate around player if inventory is full
                    rotateAroundPlayer(itemEntity, player, direction);
                } else {
                    // Drag item towards player
                    double speed = Math.min(0.5, direction.length() * 0.2);
                    Vec3 movement = direction.normalize().scale(speed);
                    itemEntity.setDeltaMovement(movement);
                }
            }
        }
    }

    private void rotateAroundPlayer(ItemEntity itemEntity, Player player, Vec3 direction) {
        // Calculate a perpendicular vector for rotation
        Vec3 up = new Vec3(0, 1, 0);
        Vec3 rotationAxis = direction.cross(up).normalize();

        // Rotate around the player in a circular motion
        double radius = 2.0;
        double angle = player.tickCount * 0.2; // Rotation speed
        Vec3 rotation = new Vec3(
                Math.cos(angle) * radius,
                0.5, // Slight vertical movement
                Math.sin(angle) * radius
        );

        itemEntity.setDeltaMovement(rotation);
    }

    @Override
    public LevelBasedValue range() {
        return this.range;
    }

    @Override
    public MapCodec<MagnetismEffect> codec() {
        return CODEC;
    }
}
