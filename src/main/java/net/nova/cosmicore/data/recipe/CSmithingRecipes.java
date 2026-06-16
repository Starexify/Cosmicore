package net.nova.cosmicore.data.recipe;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.Items;
import net.nova.cosmicore.init.CItems;

import java.util.concurrent.CompletableFuture;

public class CSmithingRecipes extends CRecipeProvider {
  public CSmithingRecipes(HolderLookup.Provider registries, RecipeOutput output) {
    super(registries, output);
  }

  public void build() {
    // Titanium Stuff
    titaniumSmithing(Items.IRON_HELMET, RecipeCategory.COMBAT, CItems.TITANIUM_HELMET.get());
    titaniumSmithing(Items.IRON_CHESTPLATE, RecipeCategory.COMBAT, CItems.TITANIUM_CHESTPLATE.get());
    titaniumSmithing(Items.IRON_LEGGINGS, RecipeCategory.COMBAT, CItems.TITANIUM_LEGGINGS.get());
    titaniumSmithing(Items.IRON_BOOTS, RecipeCategory.COMBAT, CItems.TITANIUM_BOOTS.get());
    titaniumSmithing(Items.IRON_SWORD, RecipeCategory.COMBAT, CItems.TITANIUM_SWORD.get());
    titaniumSmithing(Items.IRON_AXE, RecipeCategory.TOOLS, CItems.TITANIUM_AXE.get());
    titaniumSmithing(Items.IRON_PICKAXE, RecipeCategory.TOOLS, CItems.TITANIUM_PICKAXE.get());
    titaniumSmithing(Items.IRON_HOE, RecipeCategory.TOOLS, CItems.TITANIUM_HOE.get());
    titaniumSmithing(Items.IRON_SHOVEL, RecipeCategory.TOOLS, CItems.TITANIUM_SHOVEL.get());
    titaniumSmithing(Items.CROSSBOW, RecipeCategory.TOOLS, CItems.TITANIUM_CROSSBOW.get());
    titaniumSmithing(Items.IRON_HORSE_ARMOR, RecipeCategory.TOOLS, CItems.TITANIUM_HORSE_ARMOR.get());

    // Lonsdaleite Stuff
    lonsdaleiteSmithing(Items.DIAMOND_HELMET, RecipeCategory.COMBAT, CItems.LONSDALEITE_HELMET.get());
    lonsdaleiteSmithing(Items.DIAMOND_CHESTPLATE, RecipeCategory.COMBAT, CItems.LONSDALEITE_CHESTPLATE.get());
    lonsdaleiteSmithing(Items.DIAMOND_LEGGINGS, RecipeCategory.COMBAT, CItems.LONSDALEITE_LEGGINGS.get());
    lonsdaleiteSmithing(Items.DIAMOND_BOOTS, RecipeCategory.COMBAT, CItems.LONSDALEITE_BOOTS.get());
    lonsdaleiteSmithing(Items.DIAMOND_SWORD, RecipeCategory.COMBAT, CItems.LONSDALEITE_SWORD.get());
    lonsdaleiteSmithing(Items.DIAMOND_AXE, RecipeCategory.TOOLS, CItems.LONSDALEITE_AXE.get());
    lonsdaleiteSmithing(Items.DIAMOND_PICKAXE, RecipeCategory.TOOLS, CItems.LONSDALEITE_PICKAXE.get());
    lonsdaleiteSmithing(Items.DIAMOND_HOE, RecipeCategory.TOOLS, CItems.LONSDALEITE_HOE.get());
    lonsdaleiteSmithing(Items.DIAMOND_SHOVEL, RecipeCategory.TOOLS, CItems.LONSDALEITE_SHOVEL.get());
    lonsdaleiteSmithing(Items.DIAMOND_HORSE_ARMOR, RecipeCategory.TOOLS, CItems.LONSDALEITE_HORSE_ARMOR.get());
  }
}
