package net.nova.cosmicore.recipe.crusher;

import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.nova.cosmicore.init.CRecipeSerializers;
import net.nova.cosmicore.init.CRecipeTypes;

public class CrushingRecipe implements Recipe<SingleRecipeInput> {
    protected final Ingredient ingredient;
    protected final ItemStack result;

    public CrushingRecipe(Ingredient ingredient, ItemStack result) {
        this.ingredient = ingredient;
        this.result = result;
    }

    @Override
    public boolean matches(SingleRecipeInput pInput, Level pLevel) {
        return this.ingredient.test(pInput.item());
    }

    @Override
    public ItemStack assemble(SingleRecipeInput pInput, HolderLookup.Provider pRegistries) {
        return this.result.copy();
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider pRegistries) {
        return this.result.copy();
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
