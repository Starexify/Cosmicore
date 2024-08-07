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

import java.util.List;

public class BaseRecipeBuilder implements RecipeBuilder {
    public Ingredient ingredient;
    public List<WeightedResult> results;

    public BaseRecipeBuilder addResult(ItemLike result, int count, float chance) {
        return addResult(new ItemStack(result, count), chance);
    }

    public BaseRecipeBuilder addResult(ItemStack result, float chance) {
        this.results.add(new WeightedResult(result, chance));
        return this;
    }

    @Override
    public Item getResult() {
        return this.results.isEmpty() ? Items.AIR : this.results.get(0).item.getItem();
    }

    @Override
    public void save(RecipeOutput pRecipeOutput, ResourceLocation pId) {
    }

    public RecipeBuilder unlockedBy(String pName, Criterion<?> pCriterion) {
        return null;
    }

    @Override
    public RecipeBuilder group(@Nullable String pGroupName) {
        return null;
    }
}
