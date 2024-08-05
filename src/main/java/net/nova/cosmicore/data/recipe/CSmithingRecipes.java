package net.nova.cosmicore.data.recipe;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.Items;
import net.nova.cosmicore.init.CItems;

import java.util.concurrent.CompletableFuture;

public class CSmithingRecipes extends CRecipeProvider {
    public final RecipeOutput recipeOutput;

    public CSmithingRecipes(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, RecipeOutput recipeOutput) {
        super(output, lookupProvider);
        this.recipeOutput = recipeOutput;
    }

    public void build() {
        // Titanium Stuff
        titaniumSmithing(recipeOutput, Items.IRON_HELMET, RecipeCategory.COMBAT, CItems.TITANIUM_HELMET.get());
        titaniumSmithing(recipeOutput, Items.IRON_CHESTPLATE, RecipeCategory.COMBAT, CItems.TITANIUM_CHESTPLATE.get());
        titaniumSmithing(recipeOutput, Items.IRON_LEGGINGS, RecipeCategory.COMBAT, CItems.TITANIUM_LEGGINGS.get());
        titaniumSmithing(recipeOutput, Items.IRON_BOOTS, RecipeCategory.COMBAT, CItems.TITANIUM_BOOTS.get());
        titaniumSmithing(recipeOutput, Items.IRON_SWORD, RecipeCategory.COMBAT, CItems.TITANIUM_SWORD.get());
        titaniumSmithing(recipeOutput, Items.IRON_AXE, RecipeCategory.TOOLS, CItems.TITANIUM_AXE.get());
        titaniumSmithing(recipeOutput, Items.IRON_PICKAXE, RecipeCategory.TOOLS, CItems.TITANIUM_PICKAXE.get());
        titaniumSmithing(recipeOutput, Items.IRON_HOE, RecipeCategory.TOOLS, CItems.TITANIUM_HOE.get());
        titaniumSmithing(recipeOutput, Items.IRON_SHOVEL, RecipeCategory.TOOLS, CItems.TITANIUM_SHOVEL.get());
    }
}
