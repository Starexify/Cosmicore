package net.nova.cosmicore.data.recipe;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.nova.cosmicore.init.CBlocks;
import net.nova.cosmicore.init.CItems;
import net.nova.cosmicore.recipe.crusher.CrushingRecipeBuilder;

import java.util.concurrent.CompletableFuture;

public class CrushingRecipes extends CRecipeProvider {
    public final RecipeOutput recipeOutput;

    public CrushingRecipes(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, RecipeOutput recipeOutput) {
        super(output, lookupProvider);
        this.recipeOutput = recipeOutput;
    }

    public void build() {
        new CrushingRecipeBuilder(
                Ingredient.of(CBlocks.ACHONDRITE),
                Blocks.COBBLESTONE,
                2
        ).save(recipeOutput, path + getCrushingRecipeName(Blocks.COBBLESTONE));

        new CrushingRecipeBuilder(
                Ingredient.of(CBlocks.ACHONDRITE),
                Items.IRON_INGOT,
                2
        ).save(recipeOutput, path + getCrushingRecipeName(Items.IRON_INGOT));
    }

}
