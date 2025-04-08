package net.nova.cosmicore.data.recipe;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.crafting.Ingredient;
import net.nova.cosmicore.init.CItems;

public class FurnaceRecipes extends CRecipeProvider {
    public FurnaceRecipes(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    public void build() {
        // Titanium Stuff
        oreSmelting(TITANIUM_SMELTABLES, RecipeCategory.MISC, CItems.TITANIUM_INGOT, 0.7F, 200, "titanium_ingot");
        oreBlasting(TITANIUM_SMELTABLES, RecipeCategory.MISC, CItems.TITANIUM_INGOT, 0.7F, 100, "titanium_ingot");

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
                .unlockedBy(getHasName(CItems.TITANIUM_PICKAXE), has(CItems.TITANIUM_PICKAXE))
                .unlockedBy(getHasName(CItems.TITANIUM_SHOVEL), has(CItems.TITANIUM_SHOVEL))
                .unlockedBy(getHasName(CItems.TITANIUM_AXE), has(CItems.TITANIUM_AXE))
                .unlockedBy(getHasName(CItems.TITANIUM_HOE), has(CItems.TITANIUM_HOE))
                .unlockedBy(getHasName(CItems.TITANIUM_SWORD), has(CItems.TITANIUM_SWORD))
                .unlockedBy(getHasName(CItems.TITANIUM_HELMET), has(CItems.TITANIUM_HELMET))
                .unlockedBy(getHasName(CItems.TITANIUM_CHESTPLATE), has(CItems.TITANIUM_CHESTPLATE))
                .unlockedBy(getHasName(CItems.TITANIUM_LEGGINGS), has(CItems.TITANIUM_LEGGINGS))
                .unlockedBy(getHasName(CItems.TITANIUM_BOOTS), has(CItems.TITANIUM_BOOTS))
                .save(output, path + getSmeltingRecipeName(CItems.TITANIUM_NUGGET));

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
                .unlockedBy(getHasName(CItems.TITANIUM_PICKAXE), has(CItems.TITANIUM_PICKAXE))
                .unlockedBy(getHasName(CItems.TITANIUM_SHOVEL), has(CItems.TITANIUM_SHOVEL))
                .unlockedBy(getHasName(CItems.TITANIUM_AXE), has(CItems.TITANIUM_AXE))
                .unlockedBy(getHasName(CItems.TITANIUM_HOE), has(CItems.TITANIUM_HOE))
                .unlockedBy(getHasName(CItems.TITANIUM_SWORD), has(CItems.TITANIUM_SWORD))
                .unlockedBy(getHasName(CItems.TITANIUM_HELMET), has(CItems.TITANIUM_HELMET))
                .unlockedBy(getHasName(CItems.TITANIUM_CHESTPLATE), has(CItems.TITANIUM_CHESTPLATE))
                .unlockedBy(getHasName(CItems.TITANIUM_LEGGINGS), has(CItems.TITANIUM_LEGGINGS))
                .unlockedBy(getHasName(CItems.TITANIUM_BOOTS), has(CItems.TITANIUM_BOOTS))
                .save(output, path + getBlastingRecipeName(CItems.TITANIUM_NUGGET));

    }
}
