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
        addItem(ModItems.ASTRALITE_CLUSTER, "Astralite Cluster");
        addItem(ModItems.ASTRALITE_CRYSTAL, "Astralite Crystal");
        addItem(ModItems.UNSTABLE_HYPERIONITE, "Unstable Hyperionite");
        addItem(ModItems.STABLE_HYPERIONITE, "Stable Hyperionite");
        addItem(ModItems.LONSDALEITE, "Lonsdaleite");

        // Blocks
        addBlock(ModBlocks.RAW_TITANIUM_BLOCK, "Raw Titanium Block");
        addBlock(ModBlocks.TITANIUM_BLOCK, "Titanium Block");
        addBlock(ModBlocks.METEOROID_BLOCK, "Meteoroid Block");

        // Creative Tab
        add(ModCreativeTab.COSMICORE_TAB_TITLE, "Cosmicore");
    }
}
