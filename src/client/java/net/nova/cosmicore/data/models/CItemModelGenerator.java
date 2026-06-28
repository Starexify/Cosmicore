package net.nova.cosmicore.data.models;

import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.model.ModelInstance;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.resources.Identifier;
import net.nova.cosmicore.equipment.CEquipmentAssets;
import net.nova.cosmicore.init.CItems;

import java.util.function.BiConsumer;

public class CItemModelGenerator extends ItemModelGenerators {
  public CItemModelGenerator(ItemModelOutput itemModelOutput, BiConsumer<Identifier, ModelInstance> modelOutput) {
    super(itemModelOutput, modelOutput);
  }

  @Override
  public void run() {
    // Titanium Models
    generateTrimmableItem(CItems.TITANIUM_HELMET.getFirst().value(), CEquipmentAssets.TITANIUM, TRIM_PREFIX_HELMET, false);
    generateTrimmableItem(CItems.TITANIUM_CHESTPLATE.getFirst().value(), CEquipmentAssets.TITANIUM, TRIM_PREFIX_CHESTPLATE, false);
    generateTrimmableItem(CItems.TITANIUM_LEGGINGS.getFirst().value(), CEquipmentAssets.TITANIUM, TRIM_PREFIX_LEGGINGS, false);
    generateTrimmableItem(CItems.TITANIUM_BOOTS.getFirst().value(), CEquipmentAssets.TITANIUM, TRIM_PREFIX_BOOTS, false);

    generateFlatItem(CItems.TITANIUM_SWORD.getFirst().value(), ModelTemplates.FLAT_HANDHELD_ITEM);
    generateFlatItem(CItems.TITANIUM_PICKAXE.getFirst().value(), ModelTemplates.FLAT_HANDHELD_ITEM);
    generateFlatItem(CItems.TITANIUM_AXE.getFirst().value(), ModelTemplates.FLAT_HANDHELD_ITEM);
    generateFlatItem(CItems.TITANIUM_SHOVEL.getFirst().value(), ModelTemplates.FLAT_HANDHELD_ITEM);
    generateFlatItem(CItems.TITANIUM_HOE.getFirst().value(), ModelTemplates.FLAT_HANDHELD_ITEM);

    generateCrossbow(CItems.TITANIUM_CROSSBOW.getFirst().value());
    generateFlatItem(CItems.TITANIUM_HORSE_ARMOR.getFirst().value(), ModelTemplates.FLAT_ITEM);

    generateFlatItem(CItems.RAW_TITANIUM.getFirst().value(), ModelTemplates.FLAT_ITEM);
    generateFlatItem(CItems.TITANIUM_NUGGET.getFirst().value(), ModelTemplates.FLAT_ITEM);
    generateFlatItem(CItems.TITANIUM_INGOT.getFirst().value(), ModelTemplates.FLAT_ITEM);

//    generateFlatItem(CItems.TITANIUM_UPGRADE_SMITHING_TEMPLATE.getFirst().value(), ModelTemplates.FLAT_ITEM);

    // Lonsdaleite Models
    generateTrimmableItem(CItems.LONSDALEITE_HELMET.getFirst().value(), CEquipmentAssets.TITANIUM, TRIM_PREFIX_HELMET, false);
    generateTrimmableItem(CItems.LONSDALEITE_CHESTPLATE.getFirst().value(), CEquipmentAssets.TITANIUM, TRIM_PREFIX_CHESTPLATE, false);
    generateTrimmableItem(CItems.LONSDALEITE_LEGGINGS.getFirst().value(), CEquipmentAssets.TITANIUM, TRIM_PREFIX_LEGGINGS, false);
    generateTrimmableItem(CItems.LONSDALEITE_BOOTS.getFirst().value(), CEquipmentAssets.TITANIUM, TRIM_PREFIX_BOOTS, false);

    generateFlatItem(CItems.LONSDALEITE_SWORD.getFirst().value(), ModelTemplates.FLAT_HANDHELD_ITEM);
    generateFlatItem(CItems.LONSDALEITE_PICKAXE.getFirst().value(), ModelTemplates.FLAT_HANDHELD_ITEM);
    generateFlatItem(CItems.LONSDALEITE_AXE.getFirst().value(), ModelTemplates.FLAT_HANDHELD_ITEM);
    generateFlatItem(CItems.LONSDALEITE_SHOVEL.getFirst().value(), ModelTemplates.FLAT_HANDHELD_ITEM);
    generateFlatItem(CItems.LONSDALEITE_HOE.getFirst().value(), ModelTemplates.FLAT_HANDHELD_ITEM);

    generateFlatItem(CItems.LONSDALEITE_HORSE_ARMOR.getFirst().value(), ModelTemplates.FLAT_ITEM);

    generateFlatItem(CItems.LONSDALEITE.getFirst().value(), ModelTemplates.FLAT_ITEM);

//    generateFlatItem(CItems.LONSDALEITE_UPGRADE_SMITHING_TEMPLATE.getFirst().value(), ModelTemplates.FLAT_ITEM);

    // Magnetite
    generateFlatItem(CItems.MAGNETITE.getFirst().value(), ModelTemplates.FLAT_ITEM);
//    generateFlatItem(CItems.FALLEN_METEOR_LOCATOR.getFirst().value(), ModelTemplates.FLAT_HANDHELD_ITEM);

    // Fuels
    generateFlatItem(CItems.INFERNIUM_CRYSTAL.getFirst().value(), ModelTemplates.FLAT_ITEM);
    generateFlatItem(CItems.OLIVINE.getFirst().value(), ModelTemplates.FLAT_ITEM);

    // Gear Model
    generateFlatItem(CItems.IRON_GEAR.getFirst().value(), CModelTemplates.GEAR_ITEM);
    generateFlatItem(CItems.TITANIUM_GEAR.getFirst().value(), CModelTemplates.GEAR_ITEM);

    // Banner Patterns
    generateFlatItem(CItems.METEORITE_BANNER_PATTERN.getFirst().value(), ModelTemplates.FLAT_ITEM);

    // Spawn Eggs
//        generateFlatItem(CItems.TITANIUM_GOLEM_SPAWN_EGG.getFirst().value(), ModelTemplates.FLAT_ITEM);
  }
}
