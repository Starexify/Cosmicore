package net.nova.cosmicore.init;

import net.minecraft.Util;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.nova.cosmicore.equipment.CEquipmentAssets;

import java.util.EnumMap;

public class CArmorMaterials {
    public static ArmorMaterial TITANIUM = new ArmorMaterial(29, Util.make(new EnumMap<>(ArmorType.class), map -> {
        map.put(ArmorType.BOOTS, 3);
        map.put(ArmorType.LEGGINGS, 6);
        map.put(ArmorType.CHESTPLATE, 8);
        map.put(ArmorType.HELMET, 3);
        map.put(ArmorType.BODY, 7);
    }), 17, SoundEvents.ARMOR_EQUIP_IRON, 2.5F, 0.09F, CTags.CItemTags.REPAIRS_TITANIUM_ARMOR, CEquipmentAssets.TITANIUM);

    public static ArmorMaterial LONSDALEITE = new ArmorMaterial(29, Util.make(new EnumMap<>(ArmorType.class), map -> {
        map.put(ArmorType.BOOTS, 3);
        map.put(ArmorType.LEGGINGS, 6);
        map.put(ArmorType.CHESTPLATE, 8);
        map.put(ArmorType.HELMET, 3);
        map.put(ArmorType.BODY, 13);
    }), 18, SoundEvents.ARMOR_EQUIP_DIAMOND, 3.5F, 0.15F, CTags.CItemTags.REPAIRS_LONSDALEITE_ARMOR, CEquipmentAssets.LONSDALEITE);
}

