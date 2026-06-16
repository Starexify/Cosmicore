package net.nova.cosmicore.data.loot;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.nova.cosmicore.init.CBlocks;
import net.nova.cosmicore.init.CItems;

import java.util.Set;

public class BlockLootTables extends BlockLootSubProvider {
  public BlockLootTables(HolderLookup.Provider pProvider) {
    super(Set.of(), FeatureFlags.REGISTRY.allFlags(), pProvider);
  }

  @Override
  protected void generate() {
    // Titanium Drops
    dropSelf(CBlocks.RAW_TITANIUM_BLOCK.get());
    dropSelf(CBlocks.TITANIUM_BLOCK.get());

    // Lonsdaleite Drops
    dropSelf(CBlocks.LONSDALEITE_BLOCK.get());

    // Meteor Drops
    dropSelf(CBlocks.ACHONDRITE.get());
    add(CBlocks.METEORITE.get(), block -> this.createSingleItemTableWithSilkTouch(block, CBlocks.ACHONDRITE));
    add(CBlocks.PALLASITE.get(), block -> this.createSingleItemTableWithSilkTouch(block, CBlocks.ACHONDRITE));

    // Crusher
    dropSelf(CBlocks.CRUSHER.get());
    dropSelf(CBlocks.ADVANCED_CRUSHER.get());

    // Cosmic Shield
    dropSelf(CBlocks.COSMIC_SHIELD.get());

    // Infernium
    add(CBlocks.INFERNIUM_CLUSTER.get(), this.createSingleItemTable(CItems.INFERNIUM_CRYSTAL, UniformGenerator.between(2, 4)));
    dropSelf(CBlocks.INFERNIUM_BLOCK.get());
  }

  @Override
  protected Iterable<Block> getKnownBlocks() {
    return CBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
  }
}
