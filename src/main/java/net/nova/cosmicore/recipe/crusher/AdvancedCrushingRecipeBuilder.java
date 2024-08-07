package net.nova.cosmicore.recipe.crusher;

import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

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
    public void save(RecipeOutput recipeOutput, ResourceLocation id) {
        AdvancedCrushingRecipe recipe = new AdvancedCrushingRecipe(this.ingredient, this.results);
        recipeOutput.accept(id, recipe, null);
    }
}
