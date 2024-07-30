package net.nova.cosmicore.data;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.nova.cosmicore.init.ModBlocks;
import net.nova.cosmicore.init.ModCreativeTab;
import net.nova.cosmicore.init.ModItems;
import net.nova.cosmicore.item.ModSmithingTemplateItem;

import static net.nova.cosmicore.Cosmicore.MODID;

public class ModLangProvider extends LanguageProvider {
    public ModLangProvider(PackOutput output) {
        super(output, MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        // Items
        addItem(ModItems.TITANIUM_HELMET, "Titanium Helmet");
        addItem(ModItems.TITANIUM_CHESTPLATE, "Titanium Chestplate");
        addItem(ModItems.TITANIUM_LEGGINGS, "Titanium Leggings");
        addItem(ModItems.TITANIUM_BOOTS, "Titanium Boots");
        addItem(ModItems.TITANIUM_SWORD, "Titanium Sword");
        addItem(ModItems.TITANIUM_PICKAXE, "Titanium Pickaxe");
        addItem(ModItems.TITANIUM_AXE, "Titanium Axe");
        addItem(ModItems.TITANIUM_SHOVEL, "Titanium Shovel");
        addItem(ModItems.TITANIUM_HOE, "Titanium Hoe");
        addItem(ModItems.TITANIUM_CROSSBOW, "Titanium Crossbow");
        addItem(ModItems.RAW_TITANIUM, "Raw Titanium");
        addItem(ModItems.TITANIUM_NUGGET, "Titanium Nugget");
        addItem(ModItems.TITANIUM_INGOT, "Titanium Ingot");
        addItem(ModItems.TITANIUM_UPGRADE_SMITHING_TEMPLATE, "Smithing Template");

        // Blocks
        addBlock(ModBlocks.RAW_TITANIUM_BLOCK, "Raw Titanium Block");
        addBlock(ModBlocks.TITANIUM_BLOCK, "Titanium Block");
        addBlock(ModBlocks.ACHONDRITE, "Achondrite");
        addBlock(ModBlocks.METEORITE, "Meteorite");
        addBlock(ModBlocks.PALLASITE, "Pallasite");
        addBlock(ModBlocks.CRUSHER, "Crusher");

        // Creative Tab
        add(ModCreativeTab.COSMICORE_TAB_TITLE, "Cosmicore");

        // Trim Material
        add("trim_material.cosmicore.titanium", "Titanium Material");

        // Smithing Template
        add(ModSmithingTemplateItem.TITANIUM_UPGRADE_ADDITIONS_SLOT_DESCRIPTION.getString(), "Add Titanium Ingot");
        add(ModSmithingTemplateItem.TITANIUM_UPGRADE_APPLIES_TO.getString(), "Iron Equipment");
        add(ModSmithingTemplateItem.TITANIUM_UPGRADE_BASE_SLOT_DESCRIPTION.getString(), "Add iron armor, weapon, or tool");
        add(ModSmithingTemplateItem.TITANIUM_UPGRADE_INGREDIENTS.getString(), "Titanium Ingot");
        add(ModSmithingTemplateItem.TITANIUM_UPGRADE.getString(), "Titanium Upgrade");
    }
}
