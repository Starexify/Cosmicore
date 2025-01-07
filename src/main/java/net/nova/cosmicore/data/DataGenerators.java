package net.nova.cosmicore.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.nova.cosmicore.data.loot_table.CLootTableProvider;
import net.nova.cosmicore.data.models.CModelProvider;
import net.nova.cosmicore.data.recipe.CRecipeProvider;
import net.nova.cosmicore.data.tags.CBannerPatternsTagsProvider;
import net.nova.cosmicore.data.tags.CBiomeTagsProvider;
import net.nova.cosmicore.data.tags.CBlockTagsProvider;
import net.nova.cosmicore.data.tags.CItemTagsProvider;

import java.util.concurrent.CompletableFuture;

import static net.nova.cosmicore.Cosmicore.MODID;

@EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent.Client event) {
        PackOutput output = event.getGenerator().getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        event.addProvider(new LangProvider(output));

        event.addProvider(new CModelProvider(output));

        CBlockTagsProvider modBlockTagsProvider = new CBlockTagsProvider(output, lookupProvider);
        event.addProvider(modBlockTagsProvider);
        event.addProvider(new CItemTagsProvider(output, lookupProvider, modBlockTagsProvider));
        event.addProvider(new CBiomeTagsProvider(output, lookupProvider));
        event.addProvider(new CBannerPatternsTagsProvider(output, lookupProvider));

        event.addProvider(new AtlasesProvider(output, lookupProvider));

        event.addProvider(new CLootTableProvider(output, lookupProvider));

        event.addProvider(new CRecipeProvider.Runner(output, lookupProvider));

        event.addProvider(new DatapackProvider(output, lookupProvider));
    }
}
