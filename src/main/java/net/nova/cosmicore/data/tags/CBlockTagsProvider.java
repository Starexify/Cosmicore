package net.nova.cosmicore.data.tags;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.nova.cosmicore.init.CBlocks;
import net.nova.cosmicore.init.CTags;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

import static net.nova.cosmicore.Cosmicore.MODID;

public class CBlockTagsProvider extends BlockTagsProvider {
    public CBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        // Tags
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                CBlocks.RAW_TITANIUM_BLOCK.getKey(), CBlocks.TITANIUM_BLOCK.getKey(),
                CBlocks.ACHONDRITE.getKey(), CBlocks.METEORITE.getKey(), CBlocks.PALLASITE.getKey(),
                CBlocks.INFERNIUM_CLUSTER.getKey(), CBlocks.INFERNIUM_BLOCK.getKey(),
                CBlocks.CRUSHER.getKey(), CBlocks.ADVANCED_CRUSHER.getKey(),
                CBlocks.COSMIC_SHIELD.getKey()
        );

        tag(BlockTags.NEEDS_IRON_TOOL).add(
                CBlocks.RAW_TITANIUM_BLOCK.getKey(), CBlocks.TITANIUM_BLOCK.getKey(),
                CBlocks.ACHONDRITE.getKey(),
                CBlocks.CRUSHER.getKey(), CBlocks.ADVANCED_CRUSHER.getKey(),
                CBlocks.COSMIC_SHIELD.getKey()
        );

        tag(BlockTags.NEEDS_DIAMOND_TOOL).add(
                CBlocks.METEORITE.getKey(), CBlocks.PALLASITE.getKey(),
                CBlocks.INFERNIUM_CLUSTER.getKey(), CBlocks.INFERNIUM_BLOCK.getKey()
        );

        tag(CTags.BlockTags.METEOR_BREAKABLES).addTags(
                BlockTags.SWORD_EFFICIENT, BlockTags.MINEABLE_WITH_AXE, BlockTags.MINEABLE_WITH_HOE, BlockTags.PLANKS, BlockTags.BUTTONS, BlockTags.PRESSURE_PLATES, BlockTags.DOORS,
                BlockTags.STAIRS, BlockTags.SLABS, BlockTags.WALLS, BlockTags.RAILS, BlockTags.TRAPDOORS, BlockTags.FENCES, BlockTags.BEDS,
                Tags.Blocks.CHESTS
        );

    }
}
