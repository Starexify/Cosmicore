package net.nova.cosmicore.data.tags;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.nova.cosmicore.init.CItems;

import java.util.concurrent.CompletableFuture;

import static net.nova.cosmicore.Cosmicore.MODID;

public class CItemTagsProvider extends ItemTagsProvider {
    public CItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CBlockTagsProvider provider, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, provider.contentsGetter(), MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        // Titanium Stuff
        tag(ItemTags.SHOVELS).add(CItems.TITANIUM_SHOVEL.get());
        tag(ItemTags.PICKAXES).add(CItems.TITANIUM_PICKAXE.get());
        tag(ItemTags.AXES).add(CItems.TITANIUM_AXE.get());
        tag(ItemTags.HOES).add(CItems.TITANIUM_HOE.get());
        tag(ItemTags.SWORDS).add(CItems.TITANIUM_SWORD.get());
        tag(ItemTags.HEAD_ARMOR).add(CItems.TITANIUM_HELMET.get());
        tag(ItemTags.CHEST_ARMOR).add(CItems.TITANIUM_CHESTPLATE.get());
        tag(ItemTags.LEG_ARMOR).add(CItems.TITANIUM_LEGGINGS.get());
        tag(ItemTags.FOOT_ARMOR).add(CItems.TITANIUM_BOOTS.get());
        tag(ItemTags.TRIM_MATERIALS).add(CItems.TITANIUM_INGOT.get());
    }
}