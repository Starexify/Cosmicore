package net.nova.cosmicore.enchantment;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.Comparator;
import java.util.List;

public record MagnetismEffect(LevelBasedValue range) implements EnchantmentEntityEffect {
    public static final MapCodec<MagnetismEffect> CODEC = RecordCodecBuilder.mapCodec(builder -> builder.group(
            LevelBasedValue.CODEC.fieldOf("range").forGetter(MagnetismEffect::range)
    ).apply(builder, MagnetismEffect::new));

    @Override
    public void apply(ServerLevel level, int enchantmentLevel, EnchantedItemInUse item, Entity entity, Vec3 origin) {
        if (entity instanceof Player player) {
            double magnetRange = range.calculate(enchantmentLevel);
            List<ItemEntity> nearbyItems = level.getEntitiesOfClass(
                    ItemEntity.class,
                    player.getBoundingBox().inflate(magnetRange),
                    itemEntity -> itemEntity.isAlive() && !itemEntity.isRemoved()
            );

            player.displayClientMessage(Component.literal("Nearby Items: " + nearbyItems.size()), false);

            for (ItemEntity itemEntity : nearbyItems) {
                Vec3 playerPos = player.position().add(0, 1, 0);
                Vec3 itemPos = itemEntity.position();
                Vec3 direction = playerPos.subtract(itemPos);

                double distance = direction.length();
                if (distance > 0.5) {
                    double speed = Math.min(0.5, distance * 0.2);
                    Vec3 movement = direction.normalize().scale(speed);
                    itemEntity.setDeltaMovement(movement);
                    itemEntity.setPickUpDelay(2);
                }
            }
        }
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
