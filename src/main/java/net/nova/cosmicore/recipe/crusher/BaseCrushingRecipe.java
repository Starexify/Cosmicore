package net.nova.cosmicore.recipe.crusher;

import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.nova.cosmicore.recipe.WeightedResult;

import java.util.List;
import java.util.Optional;
import java.util.Random;

public class BaseCrushingRecipe implements Recipe<SingleRecipeInput> {
    public Optional<Ingredient> ingredient;
    public List<WeightedResult> results;
    public static final Random RANDOM = new Random();

    private PlacementInfo placementInfo;

    @Override
    public ItemStack assemble(SingleRecipeInput pInput, HolderLookup.Provider pRegistries) {
        return getRandomResult();
    }

    @Override
    public boolean matches(SingleRecipeInput input, Level pLevel) {
        return Ingredient.testOptionalIngredient(this.ingredient(), input.item());
    }

    @Override
    public PlacementInfo placementInfo() {
        if (this.placementInfo == null) {
            this.placementInfo = PlacementInfo.createFromOptionals(List.of(this.ingredient));
        }

        return this.placementInfo;
    }

    public ItemStack getRandomResult() {
        float totalChance = results.stream().map(r -> r.chance).reduce(0f, Float::sum);
        float roll = RANDOM.nextFloat() * totalChance;
        float currentSum = 0f;

        for (WeightedResult result : results) {
            currentSum += result.chance;
            if (roll < currentSum) {
                return result.item.copy();
            }
        }

        return ItemStack.EMPTY;
    }

    public Optional<Ingredient> ingredient() {
        return this.ingredient;
    }

    @Override
    public RecipeSerializer<? extends Recipe<SingleRecipeInput>> getSerializer() {
        return null;
    }

    @Override
    public RecipeType<? extends Recipe<SingleRecipeInput>> getType() {
        return null;
    }

    @Override
    public RecipeBookCategory recipeBookCategory() {
        return null;
    }
}
