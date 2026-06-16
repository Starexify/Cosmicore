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

      List<ItemEntity> nearbyItems = level.getEntitiesOfClass(ItemEntity.class, player.getBoundingBox().inflate(magnetRange), itemEntity -> isEligibleItem(player, itemEntity));

      if (!nearbyItems.isEmpty()) for (ItemEntity itemEntity : nearbyItems) attractItems(itemEntity, player);
    }
  }

  public boolean isEligibleItem(Player player, ItemEntity itemEntity) {
    return itemEntity.isAlive() && !itemEntity.isRemoved() && (itemEntity.getOwner() == null || !itemEntity.getOwner().equals(player) || itemEntity.getAge() > 100);
  }

  public static void attractItems(ItemEntity itemEntity, Player player) {
    boolean hasSpace = player.getInventory().getFreeSlot() != -1;
    Vec3 itemPos = itemEntity.position();
    Vec3 targetPos;
    double speed;

    if (!hasSpace) {
      double orbitRadius = 0.5;
      int itemOffset = Math.abs(itemEntity.getUUID().hashCode()) % 360;
      double angle = Math.toRadians(itemOffset + (player.level().getGameTime() * 2));

      targetPos = new Vec3(
          player.getX() + Math.cos(angle) * orbitRadius,
          player.getY() + 0.7,
          player.getZ() + Math.sin(angle) * orbitRadius
      );

      double distance = itemPos.distanceTo(targetPos);
      speed = Math.min(0.5, distance * 0.4);
    }
    else {
      targetPos = player.position().add(0, 0.5, 0);
      double distance = itemPos.distanceTo(targetPos);
      speed = Math.min(1.0, distance * 0.4);
    }

    Vec3 currentVelocity = itemEntity.getDeltaMovement();
    Vec3 targetMotion = targetPos.subtract(itemPos).normalize().scale(speed);
    Vec3 newMotion = currentVelocity.add(targetMotion).scale(0.8);

    itemEntity.setDeltaMovement(newMotion);
//    itemEntity.hasImpulse = true;
  }

  @Override
  public MapCodec<MagnetismEffect> codec() {
    return CODEC;
  }
}
