package net.nova.cosmicore.recipe.crusher;

import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;

import java.util.ArrayList;

public class AdvancedCrushingRecipeBuilder extends BaseRecipeBuilder {
    public AdvancedCrushingRecipeBuilder(Ingredient ingredient) {
        this.ingredient = ingredient;
        this.results = new ArrayList<>();
    }

    public static AdvancedCrushingRecipeBuilder crushing(Ingredient ingredient) {
        return new AdvancedCrushingRecipeBuilder(ingredient);
    }

    @Override
    public void save(RecipeOutput recipeOutput, ResourceKey<Recipe<?>> resourceKey) {
        AdvancedCrushingRecipe recipe = new AdvancedCrushingRecipe(this.ingredient, this.results);
        recipeOutput.accept(resourceKey, recipe, null);
    }
}
