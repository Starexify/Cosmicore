package net.nova.cosmicore.data.tags;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.world.level.biome.Biomes;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.nova.cosmicore.init.CTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

import static net.nova.cosmicore.Cosmicore.MODID;

public class CBiomeTagsProvider extends BiomeTagsProvider {
    public CBiomeTagsProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pProvider, MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(CTags.BiomeTags.HAS_METEOR_SITE).add(
                Biomes.PLAINS
        );

    }
}
