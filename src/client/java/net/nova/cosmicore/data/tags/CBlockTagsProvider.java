package net.nova.cosmicore.data.tags;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class CBlockTagsProvider extends FabricTagsProvider.BlockTagsProvider {
  public CBlockTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
    super(output, registryLookupFuture);
  }

  @Override
  protected void addTags(HolderLookup.Provider registries) {

  }
}
