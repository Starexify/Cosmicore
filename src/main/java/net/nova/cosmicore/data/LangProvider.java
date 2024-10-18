package net.nova.cosmicore.data;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.nova.cosmicore.init.CBlocks;
import net.nova.cosmicore.init.CCreativeTab;
import net.nova.cosmicore.init.CItems;
import net.nova.cosmicore.item.TitaniumSmithingTemplate;

import static net.nova.cosmicore.Cosmicore.MODID;

public class LangProvider extends LanguageProvider {
    public LangProvider(PackOutput output) {
        super(output, MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        // Items
        addItem(CItems.TITANIUM_HELMET, "Titanium Helmet");
        addItem(CItems.TITANIUM_CHESTPLATE, "Titanium Chestplate");
        addItem(CItems.TITANIUM_LEGGINGS, "Titanium Leggings");
        addItem(CItems.TITANIUM_BOOTS, "Titanium Boots");
        addItem(CItems.TITANIUM_SWORD, "Titanium Sword");
        addItem(CItems.TITANIUM_PICKAXE, "Titanium Pickaxe");
        addItem(CItems.TITANIUM_AXE, "Titanium Axe");
        addItem(CItems.TITANIUM_SHOVEL, "Titanium Shovel");
        addItem(CItems.TITANIUM_HOE, "Titanium Hoe");
        addItem(CItems.TITANIUM_CROSSBOW, "Titanium Crossbow");
        addItem(CItems.RAW_TITANIUM, "Raw Titanium");
        addItem(CItems.TITANIUM_NUGGET, "Titanium Nugget");
        addItem(CItems.TITANIUM_INGOT, "Titanium Ingot");
        addItem(CItems.TITANIUM_UPGRADE_SMITHING_TEMPLATE, "Smithing Template");
        addItem(CItems.INFERNIUM_CRYSTAL, "Infernium Crystal");
        addItem(CItems.IRON_GEAR, "Iron Gear");
        addItem(CItems.TITANIUM_GEAR, "Titanium Gear");

        // Blocks
        addBlock(CBlocks.RAW_TITANIUM_BLOCK, "Raw Titanium Block");
        addBlock(CBlocks.TITANIUM_BLOCK, "Titanium Block");
        addBlock(CBlocks.ACHONDRITE, "Achondrite");
        addBlock(CBlocks.METEORITE, "Meteorite");
        addBlock(CBlocks.PALLASITE, "Pallasite");
        addBlock(CBlocks.INFERNIUM_CLUSTER, "Infernium Cluster");
        addBlock(CBlocks.CRUSHER, "Crusher");
        addBlock(CBlocks.ADVANCED_CRUSHER, "Advanced Crusher");
        addBlock(CBlocks.COSMIC_SHIELD, "Cosmic Shield");

        // Creative Tab
        add(CCreativeTab.COSMICORE_TAB_TITLE, "Cosmicore");

        // Trim Material
        add("trim_material.cosmicore.titanium", "Titanium Material");

        // Smithing Template
        add(TitaniumSmithingTemplate.TITANIUM_UPGRADE_ADDITIONS_SLOT_DESCRIPTION.getString(), "Add Titanium Ingot");
        add(TitaniumSmithingTemplate.TITANIUM_UPGRADE_APPLIES_TO.getString(), "Iron Equipment");
        add(TitaniumSmithingTemplate.TITANIUM_UPGRADE_BASE_SLOT_DESCRIPTION.getString(), "Add iron armor, weapon, or tool");
        add(TitaniumSmithingTemplate.TITANIUM_UPGRADE_INGREDIENTS.getString(), "Titanium Ingot");
        add(TitaniumSmithingTemplate.TITANIUM_UPGRADE.getString(), "Titanium Upgrade");
    }
}
