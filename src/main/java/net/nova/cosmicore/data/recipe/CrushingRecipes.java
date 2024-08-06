package net.nova.cosmicore.data.recipe;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.nova.cosmicore.recipe.crusher.CrushingRecipeBuilder;

import java.util.concurrent.CompletableFuture;

public class CrushingRecipes extends CRecipeProvider {
    public final RecipeOutput recipeOutput;

    public CrushingRecipes(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, RecipeOutput recipeOutput) {
        super(output, lookupProvider);
        this.recipeOutput = recipeOutput;
    }

    public void build() {
        new CrushingRecipeBuilder(
                Ingredient.of(Items.APPLE),
                new ItemStack(Items.DIAMOND),
                null
        );
    }
}
