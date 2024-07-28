package net.nova.cosmicore.data.recipe;

import com.google.common.collect.ImmutableList;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.nova.cosmicore.init.ModBlocks;
import net.nova.cosmicore.init.ModItems;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static net.nova.cosmicore.Cosmicore.MODID;

public class ModRecipeProvider extends RecipeProvider {
    public static String path = MODID + ":";
    public static final ImmutableList<ItemLike> TITANIUM_SMELTABLES = ImmutableList.of(ModItems.RAW_TITANIUM);

    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        // Titanium Stuff
        nineBlockStorageRecipes(recipeOutput, RecipeCategory.MISC, ModItems.RAW_TITANIUM, RecipeCategory.BUILDING_BLOCKS, ModBlocks.RAW_TITANIUM_BLOCK);
        nineBlockStorageRecipesWithCustomPacking(recipeOutput, RecipeCategory.MISC, ModItems.TITANIUM_NUGGET, RecipeCategory.MISC, ModItems.TITANIUM_INGOT, "titanium_ingot_from_nuggets", "titanium_ingot");
        nineBlockStorageRecipesRecipesWithCustomUnpacking(recipeOutput, RecipeCategory.MISC, ModItems.TITANIUM_INGOT, RecipeCategory.BUILDING_BLOCKS, ModBlocks.TITANIUM_BLOCK, "titanium_ingot_from_titanium_block", "titanium_ingot");

        oreSmelting(recipeOutput, TITANIUM_SMELTABLES, RecipeCategory.MISC, ModItems.TITANIUM_INGOT, 0.7F, 200, "titanium_ingot");
        oreBlasting(recipeOutput, TITANIUM_SMELTABLES, RecipeCategory.MISC, ModItems.TITANIUM_INGOT, 0.7F, 100, "titanium_ingot");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(
                                ModItems.TITANIUM_PICKAXE,
                                ModItems.TITANIUM_SHOVEL,
                                ModItems.TITANIUM_AXE,
                                ModItems.TITANIUM_HOE,
                                ModItems.TITANIUM_SWORD,
                                ModItems.TITANIUM_HELMET,
                                ModItems.TITANIUM_CHESTPLATE,
                                ModItems.TITANIUM_LEGGINGS,
                                ModItems.TITANIUM_BOOTS
                        ),
                        RecipeCategory.MISC,
                        ModItems.TITANIUM_NUGGET,
                        0.1F,
                        200
                )
                .unlockedBy("has_titanium_pickaxe", has(ModItems.TITANIUM_PICKAXE))
                .unlockedBy("has_titanium_shovel", has(ModItems.TITANIUM_SHOVEL))
                .unlockedBy("has_titanium_axe", has(ModItems.TITANIUM_AXE))
                .unlockedBy("has_titanium_hoe", has(ModItems.TITANIUM_HOE))
                .unlockedBy("has_titanium_sword", has(ModItems.TITANIUM_SWORD))
                .unlockedBy("has_titanium_helmet", has(ModItems.TITANIUM_HELMET))
                .unlockedBy("has_titanium_chestplate", has(ModItems.TITANIUM_CHESTPLATE))
                .unlockedBy("has_titanium_leggings", has(ModItems.TITANIUM_LEGGINGS))
                .unlockedBy("has_titanium_boots", has(ModItems.TITANIUM_BOOTS))
                .save(recipeOutput, path + getSmeltingRecipeName(ModItems.TITANIUM_NUGGET));

        SimpleCookingRecipeBuilder.blasting(Ingredient.of(
                                ModItems.TITANIUM_PICKAXE,
                                ModItems.TITANIUM_SHOVEL,
                                ModItems.TITANIUM_AXE,
                                ModItems.TITANIUM_HOE,
                                ModItems.TITANIUM_SWORD,
                                ModItems.TITANIUM_HELMET,
                                ModItems.TITANIUM_CHESTPLATE,
                                ModItems.TITANIUM_LEGGINGS,
                                ModItems.TITANIUM_BOOTS
                        ),
                        RecipeCategory.MISC,
                        ModItems.TITANIUM_NUGGET,
                        0.1F,
                        100
                )
                .unlockedBy("has_titanium_pickaxe", has(ModItems.TITANIUM_PICKAXE))
                .unlockedBy("has_titanium_shovel", has(ModItems.TITANIUM_SHOVEL))
                .unlockedBy("has_titanium_axe", has(ModItems.TITANIUM_AXE))
                .unlockedBy("has_titanium_hoe", has(ModItems.TITANIUM_HOE))
                .unlockedBy("has_titanium_sword", has(ModItems.TITANIUM_SWORD))
                .unlockedBy("has_titanium_helmet", has(ModItems.TITANIUM_HELMET))
                .unlockedBy("has_titanium_chestplate", has(ModItems.TITANIUM_CHESTPLATE))
                .unlockedBy("has_titanium_leggings", has(ModItems.TITANIUM_LEGGINGS))
                .unlockedBy("has_titanium_boots", has(ModItems.TITANIUM_BOOTS))
                .save(recipeOutput, path + getBlastingRecipeName(ModItems.TITANIUM_NUGGET));

        titaniumSmithing(recipeOutput, Items.IRON_HELMET, RecipeCategory.COMBAT, ModItems.TITANIUM_HELMET.get());
        titaniumSmithing(recipeOutput, Items.IRON_CHESTPLATE, RecipeCategory.COMBAT, ModItems.TITANIUM_CHESTPLATE.get());
        titaniumSmithing(recipeOutput, Items.IRON_LEGGINGS, RecipeCategory.COMBAT, ModItems.TITANIUM_LEGGINGS.get());
        titaniumSmithing(recipeOutput, Items.IRON_BOOTS, RecipeCategory.COMBAT, ModItems.TITANIUM_BOOTS.get());
        titaniumSmithing(recipeOutput, Items.IRON_SWORD, RecipeCategory.COMBAT, ModItems.TITANIUM_SWORD.get());
        titaniumSmithing(recipeOutput, Items.IRON_AXE, RecipeCategory.TOOLS, ModItems.TITANIUM_AXE.get());
        titaniumSmithing(recipeOutput, Items.IRON_PICKAXE, RecipeCategory.TOOLS, ModItems.TITANIUM_PICKAXE.get());
        titaniumSmithing(recipeOutput, Items.IRON_HOE, RecipeCategory.TOOLS, ModItems.TITANIUM_HOE.get());
        titaniumSmithing(recipeOutput, Items.IRON_SHOVEL, RecipeCategory.TOOLS, ModItems.TITANIUM_SHOVEL.get());
    }

    protected static void nineBlockStorageRecipes(RecipeOutput pRecipeOutput, RecipeCategory pUnpackedCategory, ItemLike pUnpacked, RecipeCategory pPackedCategory, ItemLike pPacked) {
        nineBlockStorageRecipes(pRecipeOutput, pUnpackedCategory, pUnpacked, pPackedCategory, pPacked, getSimpleRecipeName(pPacked), null, getSimpleRecipeName(pUnpacked), null);
    }

    protected static void nineBlockStorageRecipesWithCustomPacking(RecipeOutput pRecipeOutput, RecipeCategory pUnpackedCategory, ItemLike pUnpacked, RecipeCategory pPackedCategory, ItemLike pPacked, String pPackedName, String pPackedGroup) {
        nineBlockStorageRecipes(pRecipeOutput, pUnpackedCategory, pUnpacked, pPackedCategory, pPacked, path + pPackedName, pPackedGroup, getSimpleRecipeName(pUnpacked), null);
    }

    protected static void nineBlockStorageRecipesRecipesWithCustomUnpacking(RecipeOutput pRecipeOutput, RecipeCategory pUnpackedCategory, ItemLike pUnpacked, RecipeCategory pPackedCategory, ItemLike pPacked, String pUnpackedName, String pUnpackedGroup) {
        nineBlockStorageRecipes(pRecipeOutput, pUnpackedCategory, pUnpacked, pPackedCategory, pPacked, getSimpleRecipeName(pPacked), null, path + pUnpackedName, pUnpackedGroup);
    }

    protected static String getSimpleRecipeName(ItemLike pItemLike) {
        return path + getItemName(pItemLike);
    }

    protected static void oreSmelting(RecipeOutput pRecipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pRecipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(RecipeOutput pRecipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pRecipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput pRecipeOutput, RecipeSerializer<T> pSerializer, AbstractCookingRecipe.Factory<T> pRecipeFactory, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pSuffix) {
        for (ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pSerializer, pRecipeFactory)
                    .group(pGroup)
                    .unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pRecipeOutput, path + getItemName(pResult) + pSuffix + "_" + getItemName(itemlike));
        }
    }

    protected static void titaniumSmithing(RecipeOutput pRecipeOutput, Item pIngredientItem, RecipeCategory pCategory, Item pResultItem) {
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.TITANIUM_UPGRADE_SMITHING_TEMPLATE), Ingredient.of(pIngredientItem), Ingredient.of(ModItems.TITANIUM_INGOT), pCategory, pResultItem
                )
                .unlocks("has_titanium_ingot", has(ModItems.TITANIUM_INGOT))
                .save(pRecipeOutput, path + getItemName(pResultItem) + "_smithing");
    }
}