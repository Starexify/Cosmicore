package net.nova.cosmicore.data.recipe;

import com.google.common.collect.ImmutableList;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.nova.cosmicore.init.CItems;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static net.nova.cosmicore.Cosmicore.MODID;

public class CRecipeProvider extends RecipeProvider {
    public static String path = MODID + ":";
    protected static final ImmutableList<ItemLike> TITANIUM_SMELTABLES = ImmutableList.of(CItems.RAW_TITANIUM);

    public CRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
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
    public void copySmithingTemplate(ItemLike pTemplate, ItemLike pBaseItem, ItemLike pCopyItem) {
        shaped(RecipeCategory.MISC, pTemplate, 2)
                .define('#', pCopyItem)
                .define('C', pBaseItem)
                .define('S', pTemplate)
                .pattern("#S#")
                .pattern("#C#")
                .pattern("###")
                .unlockedBy(getHasName(pTemplate), has(pTemplate))
                .save(output);
    }

    public String getAdvancedCrushingRecipeName(ItemLike pItemLike) {
        return path + "advanced_crushing_" + getItemName(pItemLike);
    }

    public String getCrushingRecipeName(ItemLike pItemLike) {
        return path + "crushing_" + getItemName(pItemLike);
    }

    public void titaniumSmithing(Item pIngredientItem, RecipeCategory pCategory, Item pResultItem) {
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(CItems.TITANIUM_UPGRADE_SMITHING_TEMPLATE), Ingredient.of(pIngredientItem),
                        Ingredient.of(CItems.TITANIUM_INGOT), pCategory, pResultItem)
                .unlocks("has_" + getItemName(CItems.TITANIUM_INGOT), has(CItems.TITANIUM_INGOT))
                .save(output, path + getItemName(pResultItem) + "_smithing");
    }

    public void lonsdaleiteSmithing(Item pIngredientItem, RecipeCategory pCategory, Item pResultItem) {
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(CItems.LONSDALEITE_UPGRADE_SMITHING_TEMPLATE), Ingredient.of(pIngredientItem),
                        Ingredient.of(CItems.LONSDALEITE), pCategory, pResultItem)
                .unlocks("has_" + getItemName(CItems.LONSDALEITE), has(CItems.LONSDALEITE))
                .save(output, path + getItemName(pResultItem) + "_smithing");
    }

    @Override
    protected void nineBlockStorageRecipes(RecipeCategory unpackedCategory, ItemLike unpacked, RecipeCategory packedCategory, ItemLike packed, String packedName, @Nullable String packedGroup, String unpackedName, @Nullable String unpackedGroup) {
        super.nineBlockStorageRecipes(unpackedCategory, unpacked, packedCategory, packed, path + packedName, packedGroup, path + unpackedName, unpackedGroup);
    }

    @Override
    protected <T extends AbstractCookingRecipe> void oreCooking(RecipeSerializer<T> serializer, AbstractCookingRecipe.Factory<T> recipeFactory, List<ItemLike> ingredients, RecipeCategory category, ItemLike result, float experience, int cookingTime, String group, String suffix) {
        for (ItemLike itemlike : ingredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), category, result, experience, cookingTime, serializer, recipeFactory).group(group).unlockedBy(getHasName(itemlike), this.has(itemlike)).save(this.output, path + getItemName(result) + suffix + "_" + getItemName(itemlike));
        }
    }

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
            return "Cosmicore Recipes";
        }
    }
}