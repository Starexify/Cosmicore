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
        titaniumSmithing(recipeOutput, Items.CROSSBOW, RecipeCategory.TOOLS, CItems.TITANIUM_CROSSBOW.get());
        titaniumSmithing(recipeOutput, Items.IRON_HORSE_ARMOR, RecipeCategory.TOOLS, CItems.TITANIUM_HORSE_ARMOR.get());

        // Lonsdaleite Stuff
        lonsdaleiteSmithing(recipeOutput, Items.DIAMOND_HELMET, RecipeCategory.COMBAT, CItems.LONSDALEITE_HELMET.get());
        lonsdaleiteSmithing(recipeOutput, Items.DIAMOND_CHESTPLATE, RecipeCategory.COMBAT, CItems.LONSDALEITE_CHESTPLATE.get());
        lonsdaleiteSmithing(recipeOutput, Items.DIAMOND_LEGGINGS, RecipeCategory.COMBAT, CItems.LONSDALEITE_LEGGINGS.get());
        lonsdaleiteSmithing(recipeOutput, Items.DIAMOND_BOOTS, RecipeCategory.COMBAT, CItems.LONSDALEITE_BOOTS.get());
        lonsdaleiteSmithing(recipeOutput, Items.DIAMOND_SWORD, RecipeCategory.COMBAT, CItems.LONSDALEITE_SWORD.get());
        lonsdaleiteSmithing(recipeOutput, Items.DIAMOND_AXE, RecipeCategory.TOOLS, CItems.LONSDALEITE_AXE.get());
        lonsdaleiteSmithing(recipeOutput, Items.DIAMOND_PICKAXE, RecipeCategory.TOOLS, CItems.LONSDALEITE_PICKAXE.get());
        lonsdaleiteSmithing(recipeOutput, Items.DIAMOND_HOE, RecipeCategory.TOOLS, CItems.LONSDALEITE_HOE.get());
        lonsdaleiteSmithing(recipeOutput, Items.DIAMOND_SHOVEL, RecipeCategory.TOOLS, CItems.LONSDALEITE_SHOVEL.get());
        lonsdaleiteSmithing(recipeOutput, Items.DIAMOND_HORSE_ARMOR, RecipeCategory.TOOLS, CItems.LONSDALEITE_HORSE_ARMOR.get());
    }
}
