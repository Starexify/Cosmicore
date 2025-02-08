package net.nova.cosmicore.data;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.nova.cosmicore.Cosmicore;
import net.nova.cosmicore.entity.Achondrite;
import net.nova.cosmicore.gui.crusher.CrusherScreen;
import net.nova.cosmicore.init.CBlocks;
import net.nova.cosmicore.init.CEntities;
import net.nova.cosmicore.init.CItems;
import net.nova.cosmicore.init.CreativeTab;
import net.nova.cosmicore.item.LonsdaleiteSmithingTemplate;
import net.nova.cosmicore.item.TitaniumSmithingTemplate;

import java.util.function.Supplier;

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
        addItem(CItems.TITANIUM_HORSE_ARMOR, "Titanium Horse Armor");
        addItem(CItems.RAW_TITANIUM, "Raw Titanium");
        addItem(CItems.TITANIUM_NUGGET, "Titanium Nugget");
        addItem(CItems.TITANIUM_INGOT, "Titanium Ingot");
        addItem(CItems.TITANIUM_UPGRADE_SMITHING_TEMPLATE, "Titanium Upgrade");
        addItem(CItems.LONSDALEITE_HELMET, "Lonsdaleite Helmet");
        addItem(CItems.LONSDALEITE_CHESTPLATE, "Lonsdaleite Chestplate");
        addItem(CItems.LONSDALEITE_LEGGINGS, "Lonsdaleite Leggings");
        addItem(CItems.LONSDALEITE_BOOTS, "Lonsdaleite Boots");
        addItem(CItems.LONSDALEITE_SWORD, "Lonsdaleite Sword");
        addItem(CItems.LONSDALEITE_PICKAXE, "Lonsdaleite Pickaxe");
        addItem(CItems.LONSDALEITE_AXE, "Lonsdaleite Axe");
        addItem(CItems.LONSDALEITE_SHOVEL, "Lonsdaleite Shovel");
        addItem(CItems.LONSDALEITE_HOE, "Lonsdaleite Hoe");
        addItem(CItems.LONSDALEITE_HORSE_ARMOR, "Lonsdaleite Horse Armor");
        addItem(CItems.LONSDALEITE,"Lonsdaleite");
        addItem(CItems.LONSDALEITE_UPGRADE_SMITHING_TEMPLATE,"Lonsdaleite Upgrade");

        addItem(CItems.MAGNETITE, "Magnetite");
        addItem(CItems.FALLEN_METEOR_LOCATOR, "Fallen Meteor Locator");
        addItem(CItems.INFERNIUM_CRYSTAL, "Infernium Crystal");
        addItem(CItems.IRON_GEAR, "Iron Gear");
        addItem(CItems.TITANIUM_GEAR, "Titanium Gear");

        // Banner Patterns
        addItem(CItems.METEORITE_BANNER_PATTERN, "Meteorite Banner Pattern");

        // Blocks
        addBlock(CBlocks.RAW_TITANIUM_BLOCK, "Raw Titanium Block");
        addBlock(CBlocks.TITANIUM_BLOCK, "Titanium Block");
        addBlock(CBlocks.LONSDALEITE_BLOCK, "Lonsdaleite Block");
        addBlock(CBlocks.ACHONDRITE, "Achondrite");
        addBlock(CBlocks.METEORITE, "Meteorite");
        addBlock(CBlocks.PALLASITE, "Pallasite");
        addBlock(CBlocks.INFERNIUM_CLUSTER, "Infernium Cluster");
        addBlock(CBlocks.INFERNIUM_BLOCK, "Infernium Block");
        addBlock(CBlocks.CRUSHER, "Crusher");
        addBlock(CBlocks.ADVANCED_CRUSHER, "Advanced Crusher");
        addBlock(CBlocks.COSMIC_SHIELD, "Cosmic Shield");

        // Creative Tab
        add(CreativeTab.COSMICORE_TAB_TITLE, "Cosmicore");

        // Trim Material
        addTrim(CItems.TITANIUM_INGOT, "Titanium Material");
        addTrim(CItems.LONSDALEITE, "Lonsdaleite Material");

        // Smithing Template
        add(TitaniumSmithingTemplate.TITANIUM_UPGRADE_ADDITIONS_SLOT_DESCRIPTION.getString(), "Add Titanium Ingot");
        add(TitaniumSmithingTemplate.TITANIUM_UPGRADE_APPLIES_TO.getString(), "Iron Equipment");
        add(TitaniumSmithingTemplate.TITANIUM_UPGRADE_BASE_SLOT_DESCRIPTION.getString(), "Add iron armor, weapon, or tool");
        add(TitaniumSmithingTemplate.TITANIUM_UPGRADE_INGREDIENTS.getString(), "Titanium Ingot");
        add(LonsdaleiteSmithingTemplate.LONSDALEITE_UPGRADE_ADDITIONS_SLOT_DESCRIPTION.getString(), "Add Lonsdaleite");
        add(LonsdaleiteSmithingTemplate.LONSDALEITE_UPGRADE_APPLIES_TO.getString(), "Diamond Equipment");
        add(LonsdaleiteSmithingTemplate.LONSDALEITE_UPGRADE_BASE_SLOT_DESCRIPTION.getString(), "Add diamond armor, weapon, or tool");
        add(LonsdaleiteSmithingTemplate.LONSDALEITE_UPGRADE_INGREDIENTS.getString(), "Lonsdaleite");

        // Entities
        add(CEntities.ACHONDRITE.get(), "Achondrite");
        add(CEntities.METEORITE.get(), "Meteorite");

        // Game Rules
        add(Cosmicore.ALLOW_METEORS_SPAWNING.getDescriptionId(), "Allow Meteors Spawning");

        // Messages
        add(Achondrite.METEOR_FALL_MESSAGE.getString(), "A meteor has entered the atmosphere!");
        add(Achondrite.METEOR_SHIELDED_MESSAGE.getString(), "The meteor has been blocked by a meteor shield.");

        // Crusher Tooltips
        add(CrusherScreen.IGNIS_TOOLTIP, "%s/%s Ignis");
    }

    public void addTrim(Supplier<? extends Item> item, String trim) {
        add("trim_material." + MODID + "." + getItemName(item.get()), trim);
    }

    public String getItemName(Item item) {
        return BuiltInRegistries.ITEM.getKey(item).toString().replace(MODID + ":", "");
    }
}
