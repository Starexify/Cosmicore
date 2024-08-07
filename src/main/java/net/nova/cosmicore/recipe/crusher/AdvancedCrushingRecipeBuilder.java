package net.nova.cosmicore.recipe.crusher;

import net.minecraft.advancements.Criterion;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.nova.cosmicore.recipe.WeightedResult;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class AdvancedCrushingRecipeBuilder implements RecipeBuilder {
    private final Ingredient ingredient;
    private final List<WeightedResult> results;

    public AdvancedCrushingRecipeBuilder(Ingredient ingredient) {
        this.ingredient = ingredient;
        this.results = new ArrayList<>();
    }

    public static AdvancedCrushingRecipeBuilder crushing(Ingredient ingredient) {
        return new AdvancedCrushingRecipeBuilder(ingredient);
    }

    public AdvancedCrushingRecipeBuilder addResult(ItemLike result, int count, float chance) {
        return addResult(new ItemStack(result, count), chance);
    }

    public AdvancedCrushingRecipeBuilder addResult(ItemStack result, float chance) {
        this.results.add(new WeightedResult(result, chance));
        return this;
    }

    @Override
    public RecipeBuilder unlockedBy(String pName, Criterion<?> pCriterion) {
        return null;
    }

    @Override
    public RecipeBuilder group(@Nullable String pGroupName) {
        return null;
    }

    @Override
    public Item getResult() {
        return this.results.isEmpty() ? Items.AIR : this.results.get(0).item.getItem();
    }

    @Override
    public void save(RecipeOutput recipeOutput, ResourceLocation id) {
        AdvancedCrushingRecipe recipe = new AdvancedCrushingRecipe(this.ingredient, this.results);
        recipeOutput.accept(id, recipe, null);
    }
}
