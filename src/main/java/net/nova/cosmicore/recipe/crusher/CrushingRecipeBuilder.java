package net.nova.cosmicore.recipe.crusher;

import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;

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
  public void save(RecipeOutput output, ResourceKey<Recipe<?>> location) {
    CrushingRecipe recipe = new CrushingRecipe(ingredient, results);
    output.accept(location, recipe, null);
  }
}
