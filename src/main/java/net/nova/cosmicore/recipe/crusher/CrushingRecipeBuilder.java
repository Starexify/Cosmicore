package net.nova.cosmicore.recipe.crusher;

import net.minecraft.advancements.Criterion;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.Nullable;

public class CrushingRecipeBuilder implements RecipeBuilder {
    private final Ingredient ingredient;
    private final ItemStack result;
    protected final int resultCount;

    public CrushingRecipeBuilder(Ingredient ingredient, ItemLike result, int resultCount) {
        this(ingredient, new ItemStack(result, resultCount));
    }

    public CrushingRecipeBuilder(Ingredient ingredient, ItemStack result) {
        this.ingredient = ingredient;
        this.result = result;
        this.resultCount = result.getCount();
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
        return this.result.getItem();
    }

    @Override
    public void save(RecipeOutput recipeOutput, ResourceLocation id) {
        CrushingRecipe recipe = new CrushingRecipe(this.ingredient, this.result);
        recipeOutput.accept(id, recipe, null);
    }
}

