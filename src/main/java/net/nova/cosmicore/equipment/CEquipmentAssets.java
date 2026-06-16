package net.nova.cosmicore.equipment;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;
import net.nova.cosmicore.Cosmicore;

public interface CEquipmentAssets {
  ResourceKey<EquipmentAsset> TITANIUM = createId("titanium");
  ResourceKey<EquipmentAsset> LONSDALEITE = createId("lonsdaleite");

  static ResourceKey<EquipmentAsset> createId(String name) {
    return ResourceKey.create(EquipmentAssets.ROOT_ID, Cosmicore.rl(name));
  }
}
