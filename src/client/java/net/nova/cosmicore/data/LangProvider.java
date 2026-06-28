package net.nova.cosmicore.data;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import net.nova.cosmicore.init.CItems;
import net.nova.cosmicore.init.CreativeTab;

import java.util.concurrent.CompletableFuture;

public class LangProvider extends FabricLanguageProvider {
  public LangProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
    super(packOutput, registryLookup);
  }

  @Override
  public void generateTranslations(HolderLookup.Provider registryLookup, TranslationBuilder translationBuilder) {
    // Items
    translationBuilder.add(CItems.TITANIUM_HELMET.getFirst().value(), "Titanium Helmet");
    translationBuilder.add(CItems.TITANIUM_CHESTPLATE.getFirst().value(), "Titanium Chestplate");
    translationBuilder.add(CItems.TITANIUM_LEGGINGS.getFirst().value(), "Titanium Leggings");
    translationBuilder.add(CItems.TITANIUM_BOOTS.getFirst().value(), "Titanium Boots");
    translationBuilder.add(CItems.TITANIUM_SWORD.getFirst().value(), "Titanium Sword");
    translationBuilder.add(CItems.TITANIUM_PICKAXE.getFirst().value(), "Titanium Pickaxe");
    translationBuilder.add(CItems.TITANIUM_AXE.getFirst().value(), "Titanium Axe");
    translationBuilder.add(CItems.TITANIUM_SHOVEL.getFirst().value(), "Titanium Shovel");
    translationBuilder.add(CItems.TITANIUM_HOE.getFirst().value(), "Titanium Hoe");
    translationBuilder.add(CItems.TITANIUM_CROSSBOW.getFirst().value(), "Titanium Crossbow");
    translationBuilder.add(CItems.TITANIUM_HORSE_ARMOR.getFirst().value(), "Titanium Horse Armor");
    translationBuilder.add(CItems.RAW_TITANIUM.getFirst().value(), "Raw Titanium");
    translationBuilder.add(CItems.TITANIUM_NUGGET.getFirst().value(), "Titanium Nugget");
    translationBuilder.add(CItems.TITANIUM_INGOT.getFirst().value(), "Titanium Ingot");
//    translationBuilder.add(CItems.TITANIUM_UPGRADE_SMITHING_TEMPLATE.getFirst().value(), "Titanium Upgrade");
    translationBuilder.add(CItems.LONSDALEITE_HELMET.getFirst().value(), "Lonsdaleite Helmet");
    translationBuilder.add(CItems.LONSDALEITE_CHESTPLATE.getFirst().value(), "Lonsdaleite Chestplate");
    translationBuilder.add(CItems.LONSDALEITE_LEGGINGS.getFirst().value(), "Lonsdaleite Leggings");
    translationBuilder.add(CItems.LONSDALEITE_BOOTS.getFirst().value(), "Lonsdaleite Boots");
    translationBuilder.add(CItems.LONSDALEITE_SWORD.getFirst().value(), "Lonsdaleite Sword");
    translationBuilder.add(CItems.LONSDALEITE_PICKAXE.getFirst().value(), "Lonsdaleite Pickaxe");
    translationBuilder.add(CItems.LONSDALEITE_AXE.getFirst().value(), "Lonsdaleite Axe");
    translationBuilder.add(CItems.LONSDALEITE_SHOVEL.getFirst().value(), "Lonsdaleite Shovel");
    translationBuilder.add(CItems.LONSDALEITE_HOE.getFirst().value(), "Lonsdaleite Hoe");
    translationBuilder.add(CItems.LONSDALEITE_HORSE_ARMOR.getFirst().value(), "Lonsdaleite Horse Armor");
    translationBuilder.add(CItems.LONSDALEITE.getFirst().value(), "Lonsdaleite");
//    translationBuilder.add(CItems.LONSDALEITE_UPGRADE_SMITHING_TEMPLATE.getFirst().value(), "Lonsdaleite Upgrade");
    translationBuilder.add(CItems.MAGNETITE.getFirst().value(), "Magnetite");
//    translationBuilder.add(CItems.FALLEN_METEOR_LOCATOR.getFirst().value(), "Fallen Meteor Locator");
    translationBuilder.add(CItems.INFERNIUM_CRYSTAL.getFirst().value(), "Infernium Crystal");
    translationBuilder.add(CItems.OLIVINE.getFirst().value(), "Olivine");
    translationBuilder.add(CItems.IRON_GEAR.getFirst().value(), "Iron Gear");
    translationBuilder.add(CItems.TITANIUM_GEAR.getFirst().value(), "Titanium Gear");
    //        addItem(CItems.TITANIUM_GOLEM_SPAWN_EGG, "Titanium Golem Spawn Egg");

    // Banner Patterns
    translationBuilder.add(CItems.METEORITE_BANNER_PATTERN.getFirst().value(), "Meteorite Banner Pattern");

    // Blocks
//    addBlock(CBlocks.RAW_TITANIUM_BLOCK, "Raw Titanium Block");
//    addBlock(CBlocks.TITANIUM_BLOCK, "Titanium Block");
//    addBlock(CBlocks.LONSDALEITE_BLOCK, "Lonsdaleite Block");
//    addBlock(CBlocks.ACHONDRITE, "Achondrite");
//    addBlock(CBlocks.METEORITE, "Meteorite");
//    addBlock(CBlocks.PALLASITE, "Pallasite");
//    addBlock(CBlocks.INFERNIUM_CLUSTER, "Infernium Cluster");
//    addBlock(CBlocks.INFERNIUM_BLOCK, "Infernium Block");
//    addBlock(CBlocks.CRUSHER, "Crusher");
//    addBlock(CBlocks.ADVANCED_CRUSHER, "Advanced Crusher");
//    addBlock(CBlocks.COSMIC_SHIELD, "Cosmic Shield");

    // Creative Tab
    translationBuilder.add(CreativeTab.COSMICORE_TAB_TITLE, "Cosmicore");

    // Trim Material
    translationBuilder.add("trim_material.cosmicore.titanium_ingot", "Titanium Material");
    translationBuilder.add("trim_material.cosmicore.lonsdaleite", "Lonsdaleite Material");

    // Smithing Template
//    add(TitaniumSmithingTemplate.TITANIUM_UPGRADE_ADDITIONS_SLOT_DESCRIPTION.getString(), "Add Titanium Ingot");
//    add(TitaniumSmithingTemplate.TITANIUM_UPGRADE_APPLIES_TO.getString(), "Iron Equipment");
//    add(TitaniumSmithingTemplate.TITANIUM_UPGRADE_BASE_SLOT_DESCRIPTION.getString(), "Add iron armor, weapon, or tool");
//    add(TitaniumSmithingTemplate.TITANIUM_UPGRADE_INGREDIENTS.getString(), "Titanium Ingot");
//    add(LonsdaleiteSmithingTemplate.LONSDALEITE_UPGRADE_ADDITIONS_SLOT_DESCRIPTION.getString(), "Add Lonsdaleite");
//    add(LonsdaleiteSmithingTemplate.LONSDALEITE_UPGRADE_APPLIES_TO.getString(), "Diamond Equipment");
//    add(LonsdaleiteSmithingTemplate.LONSDALEITE_UPGRADE_BASE_SLOT_DESCRIPTION.getString(), "Add diamond armor, weapon, or tool");
//    add(LonsdaleiteSmithingTemplate.LONSDALEITE_UPGRADE_INGREDIENTS.getString(), "Lonsdaleite");

    // Entities
//    add(CEntities.ACHONDRITE.get(), "Achondrite");
//    add(CEntities.METEORITE.get(), "Meteorite");

    // Game Rules
//        add(Cosmicore.ALLOW_METEORS_SPAWNING.getDescriptionId(), "Allow Meteors Spawning");

    // Messages
//    add(Achondrite.METEOR_FALL_MESSAGE.getString(), "A meteor has entered the atmosphere!");
//    add(Achondrite.METEOR_SHIELDED_MESSAGE.getString(), "The meteor has been blocked by a meteor shield.");

//    add(FallenMeteorLocator.OUT_OF_RANGE.getString(), "No meteors found");
//    add(FallenMeteorLocator.METEOR_LOCATION_STR, "Meteor found %d blocks away");

    // Advancements
    //addAdvancement("root", "The core of Cosmicore", "");

    // Crusher Tooltips
//    add(CrusherScreen.IGNIS_TOOLTIP, "%s/%s Ignis");
  }
}
