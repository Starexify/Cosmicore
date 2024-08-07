package net.nova.cosmicore.recipe.crusher;

import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.nova.cosmicore.recipe.WeightedResult;

import java.util.List;
import java.util.Random;

public class BaseCrushingRecipe implements Recipe<SingleRecipeInput> {
    public Ingredient ingredient;
    public List<WeightedResult> results;
    public static final Random RANDOM = new Random();

    @Override
    public boolean matches(SingleRecipeInput pInput, Level pLevel) {
        return this.ingredient.test(pInput.item());
    }

    @Override
    public ItemStack assemble(SingleRecipeInput pInput, HolderLookup.Provider pRegistries) {
        return getRandomResult();
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider pRegistries) {
        return getRandomResult();
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

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return null;
    }

    @Override
    public RecipeType<?> getType() {
        return null;
    }
}
