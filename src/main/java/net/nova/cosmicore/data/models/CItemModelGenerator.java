package net.nova.cosmicore.data.models;

import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.model.*;
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
    generateTrimmableItem(CItems.TITANIUM_HELMET.get(), CEquipmentAssets.TITANIUM, TRIM_PREFIX_HELMET, false);
    generateTrimmableItem(CItems.TITANIUM_CHESTPLATE.get(), CEquipmentAssets.TITANIUM, TRIM_PREFIX_CHESTPLATE, false);
    generateTrimmableItem(CItems.TITANIUM_LEGGINGS.get(), CEquipmentAssets.TITANIUM, TRIM_PREFIX_LEGGINGS, false);
    generateTrimmableItem(CItems.TITANIUM_BOOTS.get(), CEquipmentAssets.TITANIUM, TRIM_PREFIX_BOOTS, false);

    generateFlatItem(CItems.TITANIUM_SWORD.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
    generateFlatItem(CItems.TITANIUM_PICKAXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
    generateFlatItem(CItems.TITANIUM_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
    generateFlatItem(CItems.TITANIUM_SHOVEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
    generateFlatItem(CItems.TITANIUM_HOE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);

    generateCrossbow(CItems.TITANIUM_CROSSBOW.get());
    generateFlatItem(CItems.TITANIUM_HORSE_ARMOR.get(), ModelTemplates.FLAT_ITEM);

    generateFlatItem(CItems.RAW_TITANIUM.get(), ModelTemplates.FLAT_ITEM);
    generateFlatItem(CItems.TITANIUM_NUGGET.get(), ModelTemplates.FLAT_ITEM);
    generateFlatItem(CItems.TITANIUM_INGOT.get(), ModelTemplates.FLAT_ITEM);

    generateFlatItem(CItems.TITANIUM_UPGRADE_SMITHING_TEMPLATE.get(), ModelTemplates.FLAT_ITEM);

    // Lonsdaleite Models
    generateTrimmableItem(CItems.LONSDALEITE_HELMET.get(), CEquipmentAssets.LONSDALEITE, TRIM_PREFIX_HELMET, false);
    generateTrimmableItem(CItems.LONSDALEITE_CHESTPLATE.get(), CEquipmentAssets.LONSDALEITE, TRIM_PREFIX_CHESTPLATE, false);
    generateTrimmableItem(CItems.LONSDALEITE_LEGGINGS.get(), CEquipmentAssets.LONSDALEITE, TRIM_PREFIX_LEGGINGS, false);
    generateTrimmableItem(CItems.LONSDALEITE_BOOTS.get(), CEquipmentAssets.LONSDALEITE, TRIM_PREFIX_BOOTS, false);

    generateFlatItem(CItems.LONSDALEITE_SWORD.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
    generateFlatItem(CItems.LONSDALEITE_PICKAXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
    generateFlatItem(CItems.LONSDALEITE_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
    generateFlatItem(CItems.LONSDALEITE_SHOVEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
    generateFlatItem(CItems.LONSDALEITE_HOE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);

    generateFlatItem(CItems.LONSDALEITE_HORSE_ARMOR.get(), ModelTemplates.FLAT_ITEM);

    generateFlatItem(CItems.LONSDALEITE.get(), ModelTemplates.FLAT_ITEM);

    generateFlatItem(CItems.LONSDALEITE_UPGRADE_SMITHING_TEMPLATE.get(), ModelTemplates.FLAT_ITEM);

    // Magnetite
    generateFlatItem(CItems.MAGNETITE.get(), ModelTemplates.FLAT_ITEM);
    generateFlatItem(CItems.FALLEN_METEOR_LOCATOR.get(), ModelTemplates.FLAT_HANDHELD_ITEM);

    // Fuels
    generateFlatItem(CItems.INFERNIUM_CRYSTAL.get(), ModelTemplates.FLAT_ITEM);
    generateFlatItem(CItems.OLIVINE.get(), ModelTemplates.FLAT_ITEM);

    // Gear Model
    generateFlatItem(CItems.IRON_GEAR.get(), CModelTemplates.GEAR_ITEM);
    generateFlatItem(CItems.TITANIUM_GEAR.get(), CModelTemplates.GEAR_ITEM);

    // Banner Patterns
    generateFlatItem(CItems.METEORITE_BANNER_PATTERN.get(), ModelTemplates.FLAT_ITEM);

    // Spawn Eggs
//        generateFlatItem(CItems.TITANIUM_GOLEM_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM);
  }
}
