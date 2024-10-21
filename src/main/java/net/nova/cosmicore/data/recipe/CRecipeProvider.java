package net.nova.cosmicore.data.recipe;

import com.google.common.collect.ImmutableList;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.nova.cosmicore.init.CItems;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static net.nova.cosmicore.Cosmicore.MODID;

public class CRecipeProvider extends RecipeProvider {
    public final PackOutput output;
    public final CompletableFuture<HolderLookup.Provider> lookupProvider;
    public static String path = MODID + ":";
    public static final ImmutableList<ItemLike> TITANIUM_SMELTABLES = ImmutableList.of(CItems.RAW_TITANIUM);

    public CRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider);
        this.output = output;
        this.lookupProvider = lookupProvider;
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        new CraftingRecipes(output, lookupProvider, recipeOutput).build();
        new FurnaceRecipes(output, lookupProvider, recipeOutput).build();
        new CSmithingRecipes(output, lookupProvider, recipeOutput).build();
        new CrushingRecipes(output, lookupProvider, recipeOutput).build();
    }

    // Recipes
    public static void copySmithingTemplate(RecipeOutput pRecipeOutput, ItemLike pTemplate, ItemLike pBaseItem, ItemLike pCopyItem) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, pTemplate, 2)
                .define('#', pCopyItem)
                .define('C', pBaseItem)
                .define('S', pTemplate)
                .pattern("#S#")
                .pattern("#C#")
                .pattern("###")
                .unlockedBy(getHasName(pTemplate), has(pTemplate))
                .save(pRecipeOutput);
    }

    public static String getAdvancedCrushingRecipeName(ItemLike pItemLike) {
        return "advanced_crushing_" + getItemName(pItemLike);
    }

    public static String getCrushingRecipeName(ItemLike pItemLike) {
        return "crushing_" + getItemName(pItemLike);
    }

    public static void nineBlockStorageRecipes(RecipeOutput pRecipeOutput, RecipeCategory pUnpackedCategory, ItemLike pUnpacked, RecipeCategory pPackedCategory, ItemLike pPacked) {
        nineBlockStorageRecipes(pRecipeOutput, pUnpackedCategory, pUnpacked, pPackedCategory, pPacked, getSimpleRecipeName(pPacked), null, getSimpleRecipeName(pUnpacked), null);
    }

    public static void nineBlockStorageRecipesWithCustomPacking(RecipeOutput pRecipeOutput, RecipeCategory pUnpackedCategory, ItemLike pUnpacked, RecipeCategory pPackedCategory, ItemLike pPacked, String pPackedName, String pPackedGroup) {
        nineBlockStorageRecipes(pRecipeOutput, pUnpackedCategory, pUnpacked, pPackedCategory, pPacked, path + pPackedName, pPackedGroup, getSimpleRecipeName(pUnpacked), null);
    }

    public static void nineBlockStorageRecipesRecipesWithCustomUnpacking(RecipeOutput pRecipeOutput, RecipeCategory pUnpackedCategory, ItemLike pUnpacked, RecipeCategory pPackedCategory, ItemLike pPacked, String pUnpackedName, String pUnpackedGroup) {
        nineBlockStorageRecipes(pRecipeOutput, pUnpackedCategory, pUnpacked, pPackedCategory, pPacked, getSimpleRecipeName(pPacked), null, path + pUnpackedName, pUnpackedGroup);
    }

    public static void oreSmelting(RecipeOutput pRecipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pRecipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_smelting");
    }

    public static void oreBlasting(RecipeOutput pRecipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pRecipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    public static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput pRecipeOutput, RecipeSerializer<T> pSerializer, AbstractCookingRecipe.Factory<T> pRecipeFactory, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pSuffix) {
        for (ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pSerializer, pRecipeFactory)
                    .group(pGroup)
                    .unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pRecipeOutput, path + getItemName(pResult) + pSuffix + "_" + getItemName(itemlike));
        }
    }

    public static void titaniumSmithing(RecipeOutput pRecipeOutput, Item pIngredientItem, RecipeCategory pCategory, Item pResultItem) {
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(CItems.TITANIUM_UPGRADE_SMITHING_TEMPLATE), Ingredient.of(pIngredientItem),
                        Ingredient.of(CItems.TITANIUM_INGOT), pCategory, pResultItem)
                .unlocks("has_" + getItemName(CItems.TITANIUM_INGOT), has(CItems.TITANIUM_INGOT))
                .save(pRecipeOutput, path + getItemName(pResultItem) + "_smithing");
    }

    public static void lonsdaleiteSmithing(RecipeOutput pRecipeOutput, Item pIngredientItem, RecipeCategory pCategory, Item pResultItem) {
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(CItems.LONSDALEITE_UPGRADE_SMITHING_TEMPLATE), Ingredient.of(pIngredientItem),
                        Ingredient.of(CItems.LONSDALEITE), pCategory, pResultItem)
                .unlocks("has_" + getItemName(CItems.LONSDALEITE), has(CItems.LONSDALEITE))
                .save(pRecipeOutput, path + getItemName(pResultItem) + "_smithing");
    }

    public static String getSimpleRecipeName(ItemLike pItemLike) {
        return path + getItemName(pItemLike);
    }
}