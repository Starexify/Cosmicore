package net.nova.cosmicore.data.recipe;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.nova.cosmicore.init.CBlocks;
import net.nova.cosmicore.init.CItems;
import net.nova.cosmicore.recipe.crusher.SimpleCrushingRecipeBuilder;

public class CrushingRecipes extends CRecipeProvider {
  public CrushingRecipes(HolderLookup.Provider registries, RecipeOutput output) {
    super(registries, output);
  }

  public void build() {
    // Crusher Recipes
    SimpleCrushingRecipeBuilder.crushing(Ingredient.of(CBlocks.ACHONDRITE), 400)
        .addResult(CItems.MAGNETITE.get(), 1, 0.02f)
        .addResult(Items.RAW_IRON, 2, 0.05f)
        .addResult(Items.RAW_IRON, 1, 0.1f)
        .addResult(Items.RAW_COPPER, 5, 0.1f)
        .addResult(Items.RAW_COPPER, 3, 0.2f)
        .addResult(Items.FLINT, 2, 0.2f)
        .addResult(Items.FLINT, 1, 0.35f)
        .addResult(Items.COBBLESTONE, 3, 0.25f)
        .addResult(Items.COBBLESTONE, 2, 0.35f)
        .addResult(Items.COBBLESTONE, 1, 0.5f)
        .save(output, getCrushingRecipeName(CBlocks.ACHONDRITE));

    SimpleCrushingRecipeBuilder.crushing(Ingredient.of(CBlocks.METEORITE), 400)
        .addResult(CItems.RAW_TITANIUM.get(), 2, 0.05f)
        .addResult(CItems.RAW_TITANIUM.get(), 1, 0.12f)
        .addResult(CItems.MAGNETITE.get(), 1, 0.12f)
        .addResult(Items.RAW_IRON, 3, 0.14f)
        .addResult(Items.RAW_IRON, 2, 0.25f)
        .addResult(Items.RAW_COPPER, 5, 0.2f)
        .addResult(Items.RAW_COPPER, 3, 0.33f)
        .addResult(Items.COAL, 3, 0.12f)
        .addResult(Items.COAL, 2, 0.27f)
        .addResult(Items.COBBLESTONE, 1, 0.16f)
        .save(output, getCrushingRecipeName(CBlocks.METEORITE));

    // Advanced Crusher Recipes
    SimpleCrushingRecipeBuilder.advanceCrushing(Ingredient.of(CBlocks.ACHONDRITE), 400)
        .addResult(CItems.MAGNETITE.get(), 2, 0.05f)
        .addResult(CItems.MAGNETITE.get(), 1, 0.09f)
        .addResult(Items.RAW_IRON, 2, 0.15f)
        .addResult(Items.RAW_IRON, 1, 0.2f)
        .addResult(Items.RAW_COPPER, 5, 0.2f)
        .addResult(Items.RAW_COPPER, 3, 0.3f)
        .addResult(Items.FLINT, 2, 0.25f)
        .addResult(Items.FLINT, 1, 0.4f)
        .addResult(Items.COBBLESTONE, 3, 0.2f)
        .addResult(Items.COBBLESTONE, 2, 0.3f)
        .addResult(Items.COBBLESTONE, 1, 0.45f)
        .save(output, getAdvancedCrushingRecipeName(CBlocks.ACHONDRITE));

    SimpleCrushingRecipeBuilder.advanceCrushing(Ingredient.of(CBlocks.METEORITE), 400)
        .addResult(CItems.LONSDALEITE.get(), 1, 0.001f)
        .addResult(Items.DIAMOND, 1, 0.01f)
        .addResult(CItems.RAW_TITANIUM.get(), 2, 0.1f)
        .addResult(CItems.RAW_TITANIUM.get(), 1, 0.25f)
        .addResult(CItems.MAGNETITE.get(), 3, 0.1f)
        .addResult(CItems.MAGNETITE.get(), 1, 0.19f)
        .addResult(Items.RAW_IRON, 3, 0.21f)
        .addResult(Items.RAW_IRON, 2, 0.3f)
        .addResult(Items.RAW_COPPER, 5, 0.27f)
        .addResult(Items.RAW_COPPER, 3, 0.4f)
        .addResult(Items.COAL, 2, 0.12f)
        .addResult(Items.COBBLESTONE, 1, 0.06f)
        .save(output, getAdvancedCrushingRecipeName(CBlocks.METEORITE));

    SimpleCrushingRecipeBuilder.advanceCrushing(Ingredient.of(CBlocks.PALLASITE), 400)
        .addResult(CItems.LONSDALEITE.get(), 2, 0.005f)
        .addResult(CItems.LONSDALEITE.get(), 1, 0.02f)
        .addResult(Items.DIAMOND, 2, 0.02f)
        .addResult(Items.DIAMOND, 1, 0.06f)
        .addResult(CItems.OLIVINE.get(), 3, 0.045f)
        .addResult(CItems.OLIVINE.get(), 1, 0.1f)
        .addResult(CItems.RAW_TITANIUM.get(), 3, 0.15f)
        .addResult(CItems.RAW_TITANIUM.get(), 2, 0.23f)
        .addResult(CItems.RAW_TITANIUM.get(), 1, 0.4f)
        .addResult(Items.RAW_GOLD, 4, 0.25f)
        .addResult(Items.RAW_GOLD, 2, 0.37f)
        .addResult(Items.RAW_IRON, 6, 0.18f)
        .addResult(Items.RAW_IRON, 4, 0.3f)
        .save(output, getAdvancedCrushingRecipeName(CBlocks.PALLASITE));
  }
}
