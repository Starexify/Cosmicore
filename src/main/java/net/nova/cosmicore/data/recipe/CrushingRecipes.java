package net.nova.cosmicore.data.recipe;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.nova.cosmicore.init.CBlocks;
import net.nova.cosmicore.init.CItems;
import net.nova.cosmicore.recipe.crusher.AdvancedCrushingRecipeBuilder;
import net.nova.cosmicore.recipe.crusher.CrushingRecipeBuilder;

import java.util.concurrent.CompletableFuture;

public class CrushingRecipes extends CRecipeProvider {
    public final RecipeOutput recipeOutput;

    public CrushingRecipes(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, RecipeOutput recipeOutput) {
        super(output, lookupProvider);
        this.recipeOutput = recipeOutput;
    }

    public void build() {
        // Crusher Recipes
        CrushingRecipeBuilder.crushing(Ingredient.of(CBlocks.ACHONDRITE))
                .addResult(Items.RAW_IRON, 2, 0.05f)
                .addResult(Items.RAW_IRON, 1, 0.1f)
                .addResult(Items.RAW_COPPER, 5, 0.1f)
                .addResult(Items.RAW_COPPER, 3, 0.2f)
                .addResult(Items.FLINT, 2, 0.2f)
                .addResult(Items.FLINT, 1, 0.35f)
                .addResult(Items.COBBLESTONE, 3, 0.25f)
                .addResult(Items.COBBLESTONE, 2, 0.35f)
                .addResult(Items.COBBLESTONE, 1, 0.5f)
                .save(recipeOutput, path + getCrushingRecipeName(CBlocks.ACHONDRITE));

        CrushingRecipeBuilder.crushing(Ingredient.of(CBlocks.METEORITE))
                .addResult(CItems.RAW_TITANIUM, 2, 0.05f)
                .addResult(CItems.RAW_TITANIUM, 1, 0.12f)
                .addResult(Items.RAW_IRON, 3, 0.14f)
                .addResult(Items.RAW_IRON, 2, 0.25f)
                .addResult(Items.RAW_COPPER, 5, 0.2f)
                .addResult(Items.RAW_COPPER, 3, 0.33f)
                .addResult(Items.COAL, 3, 0.12f)
                .addResult(Items.COAL, 2, 0.27f)
                .addResult(Items.COBBLESTONE, 1, 0.2f)
                .save(recipeOutput, path + getCrushingRecipeName(CBlocks.METEORITE));

        // Advanced Crusher Recipes
        AdvancedCrushingRecipeBuilder.crushing(Ingredient.of(CBlocks.ACHONDRITE))
                .addResult(Items.RAW_IRON, 2, 0.15f)
                .addResult(Items.RAW_IRON, 1, 0.2f)
                .addResult(Items.RAW_COPPER, 5, 0.2f)
                .addResult(Items.RAW_COPPER, 3, 0.3f)
                .addResult(Items.FLINT, 2, 0.25f)
                .addResult(Items.FLINT, 1, 0.4f)
                .addResult(Items.COBBLESTONE, 3, 0.2f)
                .addResult(Items.COBBLESTONE, 2, 0.3f)
                .addResult(Items.COBBLESTONE, 1, 0.45f)
                .save(recipeOutput, path + getAdvancedCrushingRecipeName(CBlocks.ACHONDRITE));

        AdvancedCrushingRecipeBuilder.crushing(Ingredient.of(CBlocks.METEORITE))
                .addResult(Items.DIAMOND, 1, 0.01f)
                .addResult(CItems.RAW_TITANIUM, 2, 0.1f)
                .addResult(CItems.RAW_TITANIUM, 1, 0.25f)
                .addResult(Items.RAW_IRON, 3, 0.21f)
                .addResult(Items.RAW_IRON, 2, 0.3f)
                .addResult(Items.RAW_COPPER, 5, 0.27f)
                .addResult(Items.RAW_COPPER, 3, 0.4f)
                .addResult(Items.COAL, 2, 0.12f)
                .addResult(Items.COBBLESTONE, 1, 0.2f)
                .save(recipeOutput, path + getAdvancedCrushingRecipeName(CBlocks.METEORITE));
    }

}
