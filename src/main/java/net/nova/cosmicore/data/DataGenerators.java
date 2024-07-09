package net.nova.cosmicore.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.nova.cosmicore.Cosmicore;
import net.nova.cosmicore.data.loot_table.ModLootTableProvider;
import net.nova.cosmicore.data.recipe.ModRecipeProvider;
import net.nova.cosmicore.data.tags.ModBlockTagsProvider;
import net.nova.cosmicore.data.tags.ModItemTagsProvider;

import java.util.concurrent.CompletableFuture;

import static net.nova.cosmicore.Cosmicore.MODID;

@EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        try {
            DataGenerator generator = event.getGenerator();
            PackOutput output = generator.getPackOutput();
            ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
            CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

            generator.addProvider(true, new ModLangProvider(output));

            generator.addProvider(true, new ModItemModelProvider(output, existingFileHelper));
            generator.addProvider(true, new ModBlockStateProvider(output, existingFileHelper));

            ModBlockTagsProvider modBlockTagsProvider = new ModBlockTagsProvider(output, lookupProvider, existingFileHelper);
            generator.addProvider(true, modBlockTagsProvider);
            generator.addProvider(true, new ModItemTagsProvider(output, lookupProvider, modBlockTagsProvider, existingFileHelper));

            generator.addProvider(true, new ModAtlasesProvider(output, lookupProvider, existingFileHelper));

            generator.addProvider(true, new ModLootTableProvider(output, lookupProvider));
            generator.addProvider(true, new ModRecipeProvider(output, lookupProvider));

        } catch (RuntimeException e) {
            Cosmicore.logger.error("Failed to gather data", e);
        }
    }
}
