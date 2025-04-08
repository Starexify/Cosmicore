package net.nova.cosmicore.equipment;

import net.minecraft.world.item.equipment.trim.MaterialAssetGroup;

import java.util.Map;

public interface CMaterialAssetGroup {
    MaterialAssetGroup TITANIUM = MaterialAssetGroup.create("titanium", Map.of(CEquipmentAssets.TITANIUM, "titanium_darker"));
    MaterialAssetGroup LONSDALEITE = MaterialAssetGroup.create("lonsdaleite", Map.of(CEquipmentAssets.LONSDALEITE, "lonsdaleite_darker"));
}