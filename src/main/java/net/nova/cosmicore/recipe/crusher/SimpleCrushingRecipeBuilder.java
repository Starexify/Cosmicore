package net.nova.cosmicore.recipe.crusher;

import net.minecraft.advancements.triggers.Criterion;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.nova.cosmicore.recipe.WeightedResult;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SimpleCrushingRecipeBuilder implements RecipeBuilder {
  public Ingredient ingredient;
  public List<WeightedResult> results;
  public int crushingProgress;
  private AbstractCrushingRecipe.Factory<?> factory;

  private SimpleCrushingRecipeBuilder(
      Ingredient ingredient,
      int crushingProgress,
      AbstractCrushingRecipe.Factory<?> factory
  ) {
    this.ingredient = ingredient;
    this.results = new ArrayList<>();
    this.crushingProgress = crushingProgress;
    this.factory = factory;
  }

  public static SimpleCrushingRecipeBuilder crushing(Ingredient ingredient, int cookingTime) {
    return new SimpleCrushingRecipeBuilder(ingredient, cookingTime, CrushingRecipe::new);
  }

  public static SimpleCrushingRecipeBuilder advanceCrushing(Ingredient ingredient, int cookingTime) {
    return new SimpleCrushingRecipeBuilder(ingredient, cookingTime, AdvancedCrushingRecipe::new);
  }

  public SimpleCrushingRecipeBuilder addResult(Item result, int count, float chance) {
    return addResult(new ItemStackTemplate(result, count), chance);
  }

  public SimpleCrushingRecipeBuilder addResult(ItemStackTemplate result, float chance) {
    this.results.add(new WeightedResult(result, chance));
    return this;
  }

  public ItemStackTemplate getResult() {
    return this.results.isEmpty() ? Items.AIR.getCraftingRemainder() : this.results.get(0).item().getCraftingRemainder();
  }

  @Override
  public ResourceKey<Recipe<?>> defaultId() {
    return null;
  }

  @Override
  public RecipeBuilder unlockedBy(String name, Criterion<?> criterion) {
    return null;
  }

  @Override
  public RecipeBuilder group(@Nullable String pGroupName) {
    return null;
  }

  @Override
  public void save(RecipeOutput output, ResourceKey<Recipe<?>> id) {
    AbstractCrushingRecipe recipe = this.factory
        .create(
            this.ingredient,
            this.results,
            this.crushingProgress
        );
    output.accept(id, recipe, null);
  }
}
