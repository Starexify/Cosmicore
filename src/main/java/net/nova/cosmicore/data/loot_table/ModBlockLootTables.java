package net.nova.cosmicore.data.loot_table;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.nova.cosmicore.init.ModBlocks;
import net.nova.cosmicore.init.ModItems;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static net.nova.cosmicore.Cosmicore.MODID;

public class ModBlockLootTables extends BlockLootSubProvider {
    protected ModBlockLootTables(HolderLookup.Provider pProvider) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), pProvider);
    }

    @Override
    protected void generate() {
        // Titanium Drops
        dropSelf(ModBlocks.RAW_TITANIUM_BLOCK.get());
        dropSelf(ModBlocks.TITANIUM_BLOCK.get());

        // Meteor Drops
        dropSelf(ModBlocks.ACHONDRITE.get());
        add(ModBlocks.METEORITE.get(), block -> this.createSingleItemTableWithSilkTouch(block, ModBlocks.ACHONDRITE));
        add(ModBlocks.PALLASITE.get(), block -> this.createSingleItemTableWithSilkTouch(block, ModBlocks.ACHONDRITE));

        // Crusher
        dropSelf(ModBlocks.CRUSHER.get());

        // Infernium
        add(ModBlocks.INFERNIUM_CLUSTER.get(), this.createSingleItemTable(ModItems.INFERNIUM_CRYSTAL, UniformGenerator.between(2, 4)));
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
