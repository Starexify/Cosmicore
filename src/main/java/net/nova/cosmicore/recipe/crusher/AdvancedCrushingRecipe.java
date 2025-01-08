package net.nova.cosmicore.recipe.crusher;

import net.minecraft.world.item.crafting.*;
import net.nova.cosmicore.init.CRecipeSerializers;
import net.nova.cosmicore.init.CRecipeTypes;
import net.nova.cosmicore.recipe.WeightedResult;

import java.util.List;
import java.util.Optional;

public class AdvancedCrushingRecipe extends BaseCrushingRecipe {
    public AdvancedCrushingRecipe(Optional<Ingredient> ingredient, List<WeightedResult> results) {
        this.ingredient = ingredient;
        this.results = results;
    }

    @Override
    public RecipeSerializer<? extends Recipe<SingleRecipeInput>> getSerializer() {
        return CRecipeSerializers.ADVANCED_CRUSHING_RECIPE_SERIALIZER.get();
    }

    @Override
    public RecipeType<? extends Recipe<SingleRecipeInput>> getType() {
        return CRecipeTypes.ADVANCED_CRUSHING_RECIPE_TYPE.get();
    }
}
