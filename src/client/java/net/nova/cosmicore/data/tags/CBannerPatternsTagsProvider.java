package net.nova.cosmicore.data.tags;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BannerPatternTagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.nova.cosmicore.data.CBannerPatterns;
import net.nova.cosmicore.init.Tags;

import java.util.concurrent.CompletableFuture;

import static net.nova.cosmicore.Cosmicore.MODID;

public class CBannerPatternsTagsProvider extends FabricTagsProvider<BannerPattern> {
  public CBannerPatternsTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
    super(output, Registries.BANNER_PATTERN, registryLookupFuture);
  }

  @Override
  protected void addTags(HolderLookup.Provider registries) {
    tag(Tags.BannerPatternTags.PATTERN_ITEM_METEORITE).addOptional(CBannerPatterns.METEORITE);
  }
}
