package net.nova.cosmicore.data.tags;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.nova.cosmicore.init.ModBlocks;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

import static net.nova.cosmicore.Cosmicore.MODID;

public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        // Tags
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                ModBlocks.RAW_TITANIUM_BLOCK.getKey(), ModBlocks.TITANIUM_BLOCK.getKey(),
                ModBlocks.ACHONDRITE.getKey(), ModBlocks.METEORITE.getKey(), ModBlocks.PALLASITE.getKey(),
                ModBlocks.INFERNIUM_CLUSTER.getKey()
        );

        tag(BlockTags.NEEDS_IRON_TOOL).add(
                ModBlocks.RAW_TITANIUM_BLOCK.getKey(), ModBlocks.TITANIUM_BLOCK.getKey(),
                ModBlocks.ACHONDRITE.getKey()
        );

        tag(BlockTags.NEEDS_DIAMOND_TOOL).add(
                ModBlocks.METEORITE.getKey(), ModBlocks.PALLASITE.getKey(),
                ModBlocks.INFERNIUM_CLUSTER.getKey()
        );

    }
}
