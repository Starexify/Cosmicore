package net.nova.cosmicore.recipe.crusher;

import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.nova.cosmicore.init.CRecipeSerializers;
import net.nova.cosmicore.init.CRecipeTypes;
import net.nova.cosmicore.recipe.WeightedResult;

import java.util.List;
import java.util.Random;

public class AdvancedCrushingRecipe implements Recipe<SingleRecipeInput> {
    protected final Ingredient ingredient;
    protected final List<WeightedResult> results;
    private static final Random RANDOM = new Random();

    public AdvancedCrushingRecipe(Ingredient ingredient, List<WeightedResult> results) {
        this.ingredient = ingredient;
        this.results = results;
    }

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
        return CRecipeSerializers.ADVANCED_CRUSHING_RECIPE_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return CRecipeTypes.ADVANCED_CRUSHING_RECIPE_TYPE.get();
    }
}
