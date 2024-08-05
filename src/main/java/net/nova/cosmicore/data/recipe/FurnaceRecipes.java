package net.nova.cosmicore.data.recipe;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.crafting.Ingredient;
import net.nova.cosmicore.init.CItems;

import java.util.concurrent.CompletableFuture;

public class FurnaceRecipes extends CRecipeProvider {
    public final RecipeOutput recipeOutput;

    public FurnaceRecipes(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, RecipeOutput recipeOutput) {
        super(output, lookupProvider);
        this.recipeOutput = recipeOutput;
    }

    public void build() {
        // Titanium Stuff
        oreSmelting(recipeOutput, TITANIUM_SMELTABLES, RecipeCategory.MISC, CItems.TITANIUM_INGOT, 0.7F, 200, "titanium_ingot");
        oreBlasting(recipeOutput, TITANIUM_SMELTABLES, RecipeCategory.MISC, CItems.TITANIUM_INGOT, 0.7F, 100, "titanium_ingot");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(
                                CItems.TITANIUM_PICKAXE,
                                CItems.TITANIUM_SHOVEL,
                                CItems.TITANIUM_AXE,
                                CItems.TITANIUM_HOE,
                                CItems.TITANIUM_SWORD,
                                CItems.TITANIUM_HELMET,
                                CItems.TITANIUM_CHESTPLATE,
                                CItems.TITANIUM_LEGGINGS,
                                CItems.TITANIUM_BOOTS
                        ),
                        RecipeCategory.MISC,
                        CItems.TITANIUM_NUGGET,
                        0.1F,
                        200
                )
                .unlockedBy("has_titanium_pickaxe", has(CItems.TITANIUM_PICKAXE))
                .unlockedBy("has_titanium_shovel", has(CItems.TITANIUM_SHOVEL))
                .unlockedBy("has_titanium_axe", has(CItems.TITANIUM_AXE))
                .unlockedBy("has_titanium_hoe", has(CItems.TITANIUM_HOE))
                .unlockedBy("has_titanium_sword", has(CItems.TITANIUM_SWORD))
                .unlockedBy("has_titanium_helmet", has(CItems.TITANIUM_HELMET))
                .unlockedBy("has_titanium_chestplate", has(CItems.TITANIUM_CHESTPLATE))
                .unlockedBy("has_titanium_leggings", has(CItems.TITANIUM_LEGGINGS))
                .unlockedBy("has_titanium_boots", has(CItems.TITANIUM_BOOTS))
                .save(recipeOutput, path + getSmeltingRecipeName(CItems.TITANIUM_NUGGET));

        SimpleCookingRecipeBuilder.blasting(Ingredient.of(
                                CItems.TITANIUM_PICKAXE,
                                CItems.TITANIUM_SHOVEL,
                                CItems.TITANIUM_AXE,
                                CItems.TITANIUM_HOE,
                                CItems.TITANIUM_SWORD,
                                CItems.TITANIUM_HELMET,
                                CItems.TITANIUM_CHESTPLATE,
                                CItems.TITANIUM_LEGGINGS,
                                CItems.TITANIUM_BOOTS
                        ),
                        RecipeCategory.MISC,
                        CItems.TITANIUM_NUGGET,
                        0.1F,
                        100
                )
                .unlockedBy("has_titanium_pickaxe", has(CItems.TITANIUM_PICKAXE))
                .unlockedBy("has_titanium_shovel", has(CItems.TITANIUM_SHOVEL))
                .unlockedBy("has_titanium_axe", has(CItems.TITANIUM_AXE))
                .unlockedBy("has_titanium_hoe", has(CItems.TITANIUM_HOE))
                .unlockedBy("has_titanium_sword", has(CItems.TITANIUM_SWORD))
                .unlockedBy("has_titanium_helmet", has(CItems.TITANIUM_HELMET))
                .unlockedBy("has_titanium_chestplate", has(CItems.TITANIUM_CHESTPLATE))
                .unlockedBy("has_titanium_leggings", has(CItems.TITANIUM_LEGGINGS))
                .unlockedBy("has_titanium_boots", has(CItems.TITANIUM_BOOTS))
                .save(recipeOutput, path + getBlastingRecipeName(CItems.TITANIUM_NUGGET));

    }
}
