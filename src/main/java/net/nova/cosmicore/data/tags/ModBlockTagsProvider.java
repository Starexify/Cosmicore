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
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.RAW_TITANIUM_BLOCK.getKey())
                .add(ModBlocks.TITANIUM_BLOCK.getKey())
                .add(ModBlocks.ACHONDRITE.getKey())
                .add(ModBlocks.METEORITE.getKey())
                .add(ModBlocks.PALLASITE.getKey());

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.RAW_TITANIUM_BLOCK.getKey())
                .add(ModBlocks.TITANIUM_BLOCK.getKey())
                .add(ModBlocks.ACHONDRITE.getKey());

        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.METEORITE.getKey());

        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.PALLASITE.getKey());
    }
}
