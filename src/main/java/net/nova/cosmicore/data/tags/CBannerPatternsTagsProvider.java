package net.nova.cosmicore.data.tags;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BannerPatternTagsProvider;
import net.nova.cosmicore.data.CBannerPatterns;
import net.nova.cosmicore.init.CTags;

import java.util.concurrent.CompletableFuture;

import static net.nova.cosmicore.Cosmicore.MODID;

public class CBannerPatternsTagsProvider extends BannerPatternTagsProvider {
    public CBannerPatternsTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider, MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(CTags.BannerPatternTags.PATTERN_ITEM_METEORITE).addOptional(CBannerPatterns.METEORITE.location());
    }
}
