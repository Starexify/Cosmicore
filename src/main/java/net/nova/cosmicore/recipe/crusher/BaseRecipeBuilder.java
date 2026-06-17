package net.nova.cosmicore.recipe.crusher;

import net.minecraft.advancements.triggers.Criterion;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.nova.cosmicore.recipe.WeightedResult;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class BaseRecipeBuilder implements RecipeBuilder {
  public Ingredient ingredient;
  public List<WeightedResult> results;

  public BaseRecipeBuilder addResult(Item result, int count, float chance) {
    return addResult(new ItemStackTemplate(result, count), chance);
  }

  public BaseRecipeBuilder addResult(ItemStackTemplate result, float chance) {
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
}
