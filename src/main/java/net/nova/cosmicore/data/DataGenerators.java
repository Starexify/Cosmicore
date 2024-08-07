package net.nova.cosmicore.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.nova.cosmicore.Cosmicore;
import net.nova.cosmicore.data.loot_table.CLootTableProvider;
import net.nova.cosmicore.data.recipe.CRecipeProvider;
import net.nova.cosmicore.data.tags.CBlockTagsProvider;
import net.nova.cosmicore.data.tags.CItemTagsProvider;

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

            generator.addProvider(true, new LangProvider(output));

            generator.addProvider(true, new CItemModelProvider(output, existingFileHelper));
            generator.addProvider(true, new BlockStateAndModelProvider(output, existingFileHelper));

            CBlockTagsProvider modBlockTagsProvider = new CBlockTagsProvider(output, lookupProvider, existingFileHelper);
            generator.addProvider(true, modBlockTagsProvider);
            generator.addProvider(true, new CItemTagsProvider(output, lookupProvider, modBlockTagsProvider, existingFileHelper));

            generator.addProvider(true, new AtlasesProvider(output, lookupProvider, existingFileHelper));

            generator.addProvider(true, new CLootTableProvider(output, lookupProvider));

            generator.addProvider(true, new CRecipeProvider(output, lookupProvider));

            generator.addProvider(true, new DatapackProvider(output, lookupProvider));

        } catch (RuntimeException e) {
            Cosmicore.logger.error("Cosmicore failed to gather data", e);
        }
    }
}
