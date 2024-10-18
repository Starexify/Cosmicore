package net.nova.cosmicore.data.loot_table;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.nova.cosmicore.init.CBlocks;
import net.nova.cosmicore.init.CItems;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static net.nova.cosmicore.Cosmicore.MODID;

public class BlockLootTables extends BlockLootSubProvider {
    protected BlockLootTables(HolderLookup.Provider pProvider) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), pProvider);
    }

    @Override
    protected void generate() {
        // Titanium Drops
        dropSelf(CBlocks.RAW_TITANIUM_BLOCK.get());
        dropSelf(CBlocks.TITANIUM_BLOCK.get());

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
        return BuiltInRegistries.BLOCK.stream()
                .filter(block -> Optional.of(BuiltInRegistries.BLOCK.getKey(block))
                        .filter(key -> key.getNamespace().equals(MODID))
                        .isPresent())
                .collect(Collectors.toSet());
    }
}
