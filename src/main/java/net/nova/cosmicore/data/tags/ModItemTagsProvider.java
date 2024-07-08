package net.nova.cosmicore.data.tags;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.nova.cosmicore.init.ModItems;

import java.util.concurrent.CompletableFuture;

import static net.nova.cosmicore.Cosmicore.MODID;

public class ModItemTagsProvider extends ItemTagsProvider {
    public ModItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ModBlockTagsProvider provider, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, provider.contentsGetter(), MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(ItemTags.SHOVELS).add(ModItems.TITANIUM_SHOVEL.get());
        tag(ItemTags.PICKAXES).add(ModItems.TITANIUM_PICKAXE.get());
        tag(ItemTags.AXES).add(ModItems.TITANIUM_AXE.get());
        tag(ItemTags.HOES).add(ModItems.TITANIUM_HOE.get());
        tag(ItemTags.SWORDS).add(ModItems.TITANIUM_SWORD.get());
        tag(ItemTags.HEAD_ARMOR).add(ModItems.TITANIUM_HELMET.get());
        tag(ItemTags.CHEST_ARMOR).add(ModItems.TITANIUM_CHESTPLATE.get());
        tag(ItemTags.LEG_ARMOR).add(ModItems.TITANIUM_LEGGINGS.get());
        tag(ItemTags.FOOT_ARMOR).add(ModItems.TITANIUM_BOOTS.get());
    }
}
