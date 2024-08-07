package net.nova.cosmicore.recipe.crusher;

import net.minecraft.world.item.crafting.*;
import net.nova.cosmicore.init.CRecipeSerializers;
import net.nova.cosmicore.init.CRecipeTypes;
import net.nova.cosmicore.recipe.WeightedResult;

import java.util.List;

public class AdvancedCrushingRecipe extends BaseCrushingRecipe {
    public AdvancedCrushingRecipe(Ingredient ingredient, List<WeightedResult> results) {
        this.ingredient = ingredient;
        this.results = results;
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
