package net.nova.cosmicore;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.nova.cosmicore.data.CBannerPatterns;
import net.nova.cosmicore.data.LangProvider;
import net.nova.cosmicore.data.models.CModelProvider;
import net.nova.cosmicore.data.tags.CBannerPatternsTagsProvider;
import net.nova.cosmicore.data.tags.CBlockTagsProvider;
import net.nova.cosmicore.data.tags.CItemTagsProvider;

import java.util.concurrent.CompletableFuture;

public class DataGenerators implements DataGeneratorEntrypoint {
  @Override
  public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
    FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

    pack.addProvider(CBannerPatternsTagsProvider::new);
    pack.addProvider(CBlockTagsProvider::new);
    pack.addProvider(CItemTagsProvider::new);

    pack.addProvider(CModelProvider::new);

    pack.addProvider(LangProvider::new);

    pack.addProvider(DynamicRegistry::new);
  }

  @Override
  public void buildRegistry(RegistrySetBuilder registryBuilder) {
    registryBuilder.add(Registries.BANNER_PATTERN, CBannerPatterns::bootstrap);
  }

  static class DynamicRegistry extends FabricDynamicRegistryProvider {
    public DynamicRegistry(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
      super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
      entries.addAll(registries.lookupOrThrow(Registries.BANNER_PATTERN));
    }

    @Override
    public String getName() {
      return "Cosmicore Dynamic Registry";
    }
  }
}
