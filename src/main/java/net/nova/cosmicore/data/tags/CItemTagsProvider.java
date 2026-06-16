package net.nova.cosmicore.data.tags;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.BlockTagCopyingItemTagProvider;
import net.nova.cosmicore.init.CItems;
import net.nova.cosmicore.init.CTags;

import java.util.concurrent.CompletableFuture;

import static net.nova.cosmicore.Cosmicore.MODID;

public class CItemTagsProvider extends BlockTagCopyingItemTagProvider {
  public CItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags) {
    super(output, lookupProvider, blockTags, MODID);
  }

  @Override
  protected void addTags(HolderLookup.Provider pProvider) {
    tag(ItemTags.SWORDS).add(
        CItems.TITANIUM_SWORD.getKey(), CItems.LONSDALEITE_SWORD.getKey()
    );

    tag(ItemTags.PICKAXES).add(
        CItems.TITANIUM_PICKAXE.getKey(), CItems.LONSDALEITE_PICKAXE.getKey()
    );

    tag(ItemTags.AXES).add(
        CItems.TITANIUM_AXE.getKey(), CItems.LONSDALEITE_AXE.getKey()
    );

    tag(ItemTags.SHOVELS).add(
        CItems.TITANIUM_SHOVEL.getKey(), CItems.LONSDALEITE_SHOVEL.getKey()
    );

    tag(ItemTags.HOES).add(
        CItems.TITANIUM_HOE.getKey(), CItems.LONSDALEITE_HOE.getKey()
    );

    tag(ItemTags.HEAD_ARMOR).add(
        CItems.TITANIUM_HELMET.getKey(), CItems.LONSDALEITE_HELMET.getKey()
    );

    tag(ItemTags.CHEST_ARMOR).add(
        CItems.TITANIUM_CHESTPLATE.getKey(), CItems.LONSDALEITE_CHESTPLATE.getKey()
    );

    tag(ItemTags.LEG_ARMOR).add(
        CItems.TITANIUM_LEGGINGS.getKey(), CItems.LONSDALEITE_LEGGINGS.getKey()
    );

    tag(ItemTags.FOOT_ARMOR).add(
        CItems.TITANIUM_BOOTS.getKey(), CItems.LONSDALEITE_BOOTS.getKey()
    );

    tag(ItemTags.TRIM_MATERIALS).add(
        CItems.TITANIUM_INGOT.getKey(), CItems.LONSDALEITE.getKey()
    );

    tag(ItemTags.CROSSBOW_ENCHANTABLE).add(CItems.TITANIUM_CROSSBOW.getKey());

    tag(CTags.CItemTags.MAGNETIC_ENCHANTABLE).addTags(ItemTags.DURABILITY_ENCHANTABLE).add(CItems.FALLEN_METEOR_LOCATOR.getKey());
  }
}