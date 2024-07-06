package net.nova.cosmicore.data;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.nova.cosmicore.init.ModBlocks;
import net.nova.cosmicore.init.ModCreativeTab;
import net.nova.cosmicore.init.ModItems;

import static net.nova.cosmicore.Cosmicore.MODID;

public class ModLangProvider extends LanguageProvider {
    public ModLangProvider(PackOutput output) {
        super(output, MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        // Items
        addItem(ModItems.RAW_TITANIUM, "Raw Titanium");
        addItem(ModItems.TITANIUM_INGOT, "Titanium Ingot");
        addItem(ModItems.TITANIUM_SHOVEL, "Titanium Shovel");
        addItem(ModItems.TITANIUM_PICKAXE, "Titanium Pickaxe");
        addItem(ModItems.TITANIUM_AXE, "Titanium Axe");
        addItem(ModItems.TITANIUM_HOE, "Titanium Hoe");
        addItem(ModItems.TITANIUM_SWORD, "Titanium Sword");

        // Blocks
        addBlock(ModBlocks.RAW_TITANIUM_BLOCK, "Raw Titanium Block");
        addBlock(ModBlocks.TITANIUM_BLOCK, "Titanium Block");

        // Creative Tab
        add(ModCreativeTab.COSMICORE_TAB_TITLE, "Cosmicore");
    }
}
