package net.nova.cosmicore.recipe.crusher;

import net.minecraft.world.item.crafting.*;
import net.nova.cosmicore.init.CRecipeSerializers;
import net.nova.cosmicore.init.CRecipeTypes;
import net.nova.cosmicore.recipe.WeightedResult;

import java.util.List;

public class CrushingRecipe extends BaseCrushingRecipe {
    public CrushingRecipe(Ingredient ingredient, List<WeightedResult> results) {
        this.ingredient = ingredient;
        this.results = results;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return CRecipeSerializers.CRUSHING_RECIPE_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return CRecipeTypes.CRUSHING_RECIPE_TYPE.get();
    }
}
