package net.nova.cosmicore.data.recipe;

import com.google.common.collect.ImmutableList;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.level.ItemLike;
import net.nova.cosmicore.init.CItems;

import java.util.concurrent.CompletableFuture;

import static net.nova.cosmicore.Cosmicore.MODID;

public class CRecipeProvider extends RecipeProvider {
    public static String path = MODID + ":";
    public static final ImmutableList<ItemLike> TITANIUM_SMELTABLES = ImmutableList.of(CItems.RAW_TITANIUM);

    protected CRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    @Override
    protected void buildRecipes() {
        new CraftingRecipes(registries, output).build();
        new FurnaceRecipes(registries, output).build();
        new CSmithingRecipes(registries, output).build();
        new CrushingRecipes(registries, output).build();
    }

    // Recipes
    public void copySmithingTemplate(ItemLike template, ItemLike baseItem, ItemLike copyItem) {
        shaped(RecipeCategory.MISC, template, 2)
                .define('#', copyItem)
                .define('C', baseItem)
                .define('S', template)
                .pattern("#S#")
                .pattern("#C#")
                .pattern("###")
                .unlockedBy(getHasName(template), has(template))
                .save(output);
    }

    public String getAdvancedCrushingRecipeName(ItemLike pItemLike) {
        return path + "advanced_crushing_" + getItemName(pItemLike);
    }

    public String getCrushingRecipeName(ItemLike pItemLike) {
        return path + "crushing_" + getItemName(pItemLike);
    }


/*

public void nineBlockStorageRecipes(RecipeCategory pUnpackedCategory, ItemLike pUnpacked, RecipeCategory pPackedCategory, ItemLike pPacked) {
        nineBlockStorageRecipes(pUnpackedCategory, pUnpacked, pPackedCategory, pPacked, getSimpleRecipeName(pPacked), null, getSimpleRecipeName(pUnpacked), null);
    }

    public void nineBlockStorageRecipesWithCustomPacking(RecipeCategory pUnpackedCategory, ItemLike pUnpacked, RecipeCategory pPackedCategory, ItemLike pPacked, String pPackedName, String pPackedGroup) {
        nineBlockStorageRecipes(pUnpackedCategory, pUnpacked, pPackedCategory, pPacked, path + pPackedName, pPackedGroup, getSimpleRecipeName(pUnpacked), null);
    }

    public void nineBlockStorageRecipesRecipesWithCustomUnpacking(RecipeCategory pUnpackedCategory, ItemLike pUnpacked, RecipeCategory pPackedCategory, ItemLike pPacked, String pUnpackedName, String pUnpackedGroup) {
        nineBlockStorageRecipes(pUnpackedCategory, pUnpacked, pPackedCategory, pPacked, getSimpleRecipeName(pPacked), null, path + pUnpackedName, pUnpackedGroup);
    }

    public static void titaniumSmithing(Item pIngredientItem, RecipeCategory pCategory, Item pResultItem) {
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(CItems.TITANIUM_UPGRADE_SMITHING_TEMPLATE), Ingredient.of(pIngredientItem),
                        Ingredient.of(CItems.TITANIUM_INGOT), pCategory, pResultItem)
                .unlocks("has_" + getItemName(CItems.TITANIUM_INGOT), has(CItems.TITANIUM_INGOT))
                .save(output, path + getItemName(pResultItem) + "_smithing");
    }

    public static void lonsdaleiteSmithing(Item pIngredientItem, RecipeCategory pCategory, Item pResultItem) {
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(CItems.LONSDALEITE_UPGRADE_SMITHING_TEMPLATE), Ingredient.of(pIngredientItem),
                        Ingredient.of(CItems.LONSDALEITE), pCategory, pResultItem)
                .unlocks("has_" + getItemName(CItems.LONSDALEITE), has(CItems.LONSDALEITE))
                .save(output, path + getItemName(pResultItem) + "_smithing");
    }


    public static String getSimpleRecipeName(ItemLike pItemLike) {
        return getPath() + getItemName(pItemLike);
    }

    public static String getPath() {
        return path;
    }*/


    public static class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
            super(output, lookupProvider);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput output) {
            return new CRecipeProvider(provider, output);
        }

        @Override
        public String getName() {
            return "Big Swords R Recipes";
        }
    }
}