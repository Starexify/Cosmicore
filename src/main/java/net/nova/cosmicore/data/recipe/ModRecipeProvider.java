package net.nova.cosmicore.data.recipe;

import com.google.common.collect.ImmutableList;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
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

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.TITANIUM_PICKAXE)
                .define('#', Items.STICK)
                .define('X', ModItems.TITANIUM_INGOT)
                .pattern("XXX")
                .pattern(" # ")
                .pattern(" # ")
                .unlockedBy("has_titanium_ingot", has(ModItems.TITANIUM_INGOT))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.TITANIUM_SHOVEL)
                .define('#', Items.STICK)
                .define('X', ModItems.TITANIUM_INGOT)
                .pattern("X")
                .pattern("#")
                .pattern("#")
                .unlockedBy("has_titanium_ingot", has(ModItems.TITANIUM_INGOT))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.TITANIUM_SWORD)
                .define('#', Items.STICK)
                .define('X', ModItems.TITANIUM_INGOT)
                .pattern("X")
                .pattern("X")
                .pattern("#")
                .unlockedBy("has_titanium_ingot", has(ModItems.TITANIUM_INGOT))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.TITANIUM_HOE)
                .define('#', Items.STICK)
                .define('X', ModItems.TITANIUM_INGOT)
                .pattern("XX")
                .pattern(" #")
                .pattern(" #")
                .unlockedBy("has_titanium_ingot", has(ModItems.TITANIUM_INGOT))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.TITANIUM_AXE)
                .define('#', Items.STICK)
                .define('X', ModItems.TITANIUM_INGOT)
                .pattern("XX")
                .pattern("X#")
                .pattern(" #")
                .unlockedBy("has_titanium_ingot", has(ModItems.TITANIUM_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.TITANIUM_HELMET)
                .define('X', ModItems.TITANIUM_INGOT)
                .pattern("XXX")
                .pattern("X X")
                .unlockedBy("has_titanium_ingot", has(ModItems.TITANIUM_INGOT))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.TITANIUM_CHESTPLATE)
                .define('X', ModItems.TITANIUM_INGOT)
                .pattern("X X")
                .pattern("XXX")
                .pattern("XXX")
                .unlockedBy("has_titanium_ingot", has(ModItems.TITANIUM_INGOT))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.TITANIUM_LEGGINGS)
                .define('X', ModItems.TITANIUM_INGOT)
                .pattern("XXX")
                .pattern("X X")
                .pattern("X X")
                .unlockedBy("has_titanium_ingot", has(ModItems.TITANIUM_INGOT))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.TITANIUM_BOOTS)
                .define('X', ModItems.TITANIUM_INGOT)
                .pattern("X X")
                .pattern("X X")
                .unlockedBy("has_titanium_ingot", has(ModItems.TITANIUM_INGOT))
                .save(recipeOutput);
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
}