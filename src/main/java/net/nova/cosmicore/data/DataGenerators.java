package net.nova.cosmicore.data;

import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.nova.cosmicore.data.loot.BlockLootTables;
import net.nova.cosmicore.data.loot.ChestLoot;
import net.nova.cosmicore.data.models.CEquipmentModelProvider;
import net.nova.cosmicore.data.models.CModelProvider;
import net.nova.cosmicore.data.recipe.CRecipeProvider;
import net.nova.cosmicore.data.tags.CBannerPatternsTagsProvider;
import net.nova.cosmicore.data.tags.CBiomeTagsProvider;
import net.nova.cosmicore.data.tags.CBlockTagsProvider;
import net.nova.cosmicore.data.tags.CItemTagsProvider;
import net.nova.cosmicore.data.worldgen.CStructureSets;
import net.nova.cosmicore.data.worldgen.CStructures;
import net.nova.cosmicore.data.worldgen.StructurePools;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static net.nova.cosmicore.Cosmicore.MODID;

@EventBusSubscriber(modid = MODID)
public class DataGenerators {
  @SubscribeEvent
  public static void gatherData(GatherDataEvent.Client event) {
    event.createProvider(LangProvider::new);
    event.createProvider(CModelProvider::new);
    event.createProvider(CEquipmentModelProvider::new);
    event.createBlockAndItemTags(CBlockTagsProvider::new, CItemTagsProvider::new);
    event.createProvider(CBiomeTagsProvider::new);
    event.createProvider(CBannerPatternsTagsProvider::new);
    event.createProvider(AtlasesProvider::new);
    event.createProvider(CRecipeProvider.Runner::new);
/*        event.addProvider(new AdvancementProvider(output, lookupProvider, List.of(
                new CosmicoreAdvancements()
        )));*/

    event.createProvider((output, registries) ->
        new LootTableProvider(output, Collections.emptySet(), List.of(
            new LootTableProvider.SubProviderEntry(BlockLootTables::new, LootContextParamSets.BLOCK),
            new LootTableProvider.SubProviderEntry(ChestLoot::new, LootContextParamSets.CHEST)
        ), registries));

    event.createDatapackRegistryObjects(new RegistrySetBuilder()
            .add(Registries.STRUCTURE, CStructures::bootstrap)
            .add(Registries.TEMPLATE_POOL, StructurePools::bootstrap)
            .add(Registries.STRUCTURE_SET, CStructureSets::bootstrap)
            .add(Registries.TRIM_MATERIAL, CTrimMaterials::bootstrap)
            .add(Registries.BANNER_PATTERN, CBannerPatterns::bootstrap)
            .add(Registries.ENCHANTMENT, CEnchantments::bootstrap),
        Set.of(MODID));
  }
}
