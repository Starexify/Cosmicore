package net.nova.cosmicore.recipe.crusher;

import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.ArrayList;

public class CrushingRecipeBuilder extends BaseRecipeBuilder {
    public CrushingRecipeBuilder(Ingredient ingredient) {
        this.ingredient = ingredient;
        this.results = new ArrayList<>();
    }

    public static CrushingRecipeBuilder crushing(Ingredient ingredient) {
        return new CrushingRecipeBuilder(ingredient);
    }

    @Override
    public void save(RecipeOutput recipeOutput, ResourceLocation id) {
        CrushingRecipe recipe = new CrushingRecipe(this.ingredient, this.results);
        recipeOutput.accept(id, recipe, null);
    }
}
