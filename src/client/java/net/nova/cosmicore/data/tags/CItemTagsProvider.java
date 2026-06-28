package net.nova.cosmicore.data.tags;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.ItemTags;
import net.nova.cosmicore.init.CItems;

import java.util.concurrent.CompletableFuture;

public class CItemTagsProvider extends FabricTagsProvider.ItemTagsProvider {
  public CItemTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
    super(output, registryLookupFuture);
  }

  @Override
  protected void addTags(HolderLookup.Provider registries) {
    tag(ItemTags.SWORDS).add(
        CItems.TITANIUM_SWORD.getSecond(), CItems.LONSDALEITE_SWORD.getSecond()
    );

    tag(ItemTags.PICKAXES).add(
        CItems.TITANIUM_PICKAXE.getSecond(), CItems.LONSDALEITE_PICKAXE.getSecond()
    );

    tag(ItemTags.AXES).add(
        CItems.TITANIUM_AXE.getSecond(), CItems.LONSDALEITE_AXE.getSecond()
    );

    tag(ItemTags.SHOVELS).add(
        CItems.TITANIUM_SHOVEL.getSecond(), CItems.LONSDALEITE_SHOVEL.getSecond()
    );

    tag(ItemTags.HOES).add(
        CItems.TITANIUM_HOE.getSecond(), CItems.LONSDALEITE_HOE.getSecond()
    );

    tag(ItemTags.HEAD_ARMOR).add(
        CItems.TITANIUM_HELMET.getSecond(), CItems.LONSDALEITE_HELMET.getSecond()
    );

    tag(ItemTags.CHEST_ARMOR).add(
        CItems.TITANIUM_CHESTPLATE.getSecond(), CItems.LONSDALEITE_CHESTPLATE.getSecond()
    );

    tag(ItemTags.LEG_ARMOR).add(
        CItems.TITANIUM_LEGGINGS.getSecond(), CItems.LONSDALEITE_LEGGINGS.getSecond()
    );

    tag(ItemTags.FOOT_ARMOR).add(
        CItems.TITANIUM_BOOTS.getSecond(), CItems.LONSDALEITE_BOOTS.getSecond()
    );

    tag(ItemTags.TRIM_MATERIALS).add(
        CItems.TITANIUM_INGOT.getSecond(), CItems.LONSDALEITE.getSecond()
    );

    tag(ItemTags.CROSSBOW_ENCHANTABLE).add(CItems.TITANIUM_CROSSBOW.getSecond());
  }
}
