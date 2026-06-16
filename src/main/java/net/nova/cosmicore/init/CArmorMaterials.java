package net.nova.cosmicore.init;

import com.google.common.collect.Maps;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.nova.cosmicore.equipment.CEquipmentAssets;

import java.util.Map;

public interface CArmorMaterials {
  ArmorMaterial TITANIUM = new ArmorMaterial(
      29, makeDefense(3, 6, 8, 3, 7), 17, SoundEvents.ARMOR_EQUIP_IRON, 2.5F, 0.09F, CTags.CItemTags.REPAIRS_TITANIUM_ARMOR, CEquipmentAssets.TITANIUM
  );
  ArmorMaterial LONSDALEITE = new ArmorMaterial(
      29, makeDefense(3, 6, 8, 3, 13), 18, SoundEvents.ARMOR_EQUIP_DIAMOND, 3.5F, 0.15F, CTags.CItemTags.REPAIRS_LONSDALEITE_ARMOR, CEquipmentAssets.LONSDALEITE
  );

  private static Map<ArmorType, Integer> makeDefense(int boots, int legs, int chest, int helm, int body) {
    return Maps.newEnumMap(
        Map.of(ArmorType.BOOTS, boots, ArmorType.LEGGINGS, legs, ArmorType.CHESTPLATE, chest, ArmorType.HELMET, helm, ArmorType.BODY, body)
    );
  }
}

