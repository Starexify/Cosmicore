package net.nova.cosmicore.recipe.crusher;

import net.minecraft.advancements.Criterion;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.Nullable;

public class CrushingRecipeBuilder implements RecipeBuilder {
    private final Ingredient ingredient;
    private final ItemStack stackResult;
    private final Item result;

    public CrushingRecipeBuilder(Ingredient ingredient, ItemStack stackResult, @Nullable Item result) {
        this.ingredient = ingredient;
        this.stackResult = stackResult;
        this.result = result;
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
        return this.result;
    }

    @Override
    public void save(RecipeOutput recipeOutput, ResourceLocation id) {
        CrushingRecipe recipe = new CrushingRecipe(this.ingredient, this.stackResult);
        recipeOutput.accept(id, recipe, null);
    }
}
