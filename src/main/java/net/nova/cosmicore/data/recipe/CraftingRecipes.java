package net.nova.cosmicore.data.recipe;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.nova.cosmicore.init.CBlocks;
import net.nova.cosmicore.init.CItems;

import java.util.concurrent.CompletableFuture;

public class CraftingRecipes extends CRecipeProvider {
    public final RecipeOutput recipeOutput;

    public CraftingRecipes(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, RecipeOutput recipeOutput) {
        super(output, lookupProvider);
        this.recipeOutput = recipeOutput;
    }

    public void build() {
        // Titanium Stuff
        nineBlockStorageRecipes(recipeOutput, RecipeCategory.MISC, CItems.RAW_TITANIUM, RecipeCategory.BUILDING_BLOCKS, CBlocks.RAW_TITANIUM_BLOCK);
        nineBlockStorageRecipesWithCustomPacking(recipeOutput, RecipeCategory.MISC, CItems.TITANIUM_NUGGET, RecipeCategory.MISC, CItems.TITANIUM_INGOT, "titanium_ingot_from_nuggets", "titanium_ingot");
        nineBlockStorageRecipesRecipesWithCustomUnpacking(recipeOutput, RecipeCategory.MISC, CItems.TITANIUM_INGOT, RecipeCategory.BUILDING_BLOCKS, CBlocks.TITANIUM_BLOCK, "titanium_ingot_from_titanium_block", "titanium_ingot");

        copySmithingTemplate(recipeOutput, CItems.TITANIUM_UPGRADE_SMITHING_TEMPLATE, CBlocks.METEORITE, Items.RAW_IRON);

        // Gears Recipes
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CItems.IRON_GEAR)
                .define('I', Items.IRON_INGOT)
                .define('N', Items.IRON_NUGGET)
                .pattern("NIN")
                .pattern("I I")
                .pattern("NIN")
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CItems.TITANIUM_GEAR)
                .define('G', CItems.IRON_GEAR)
                .define('I', CItems.TITANIUM_INGOT)
                .define('N', CItems.TITANIUM_NUGGET)
                .pattern("NIN")
                .pattern("IGI")
                .pattern("NIN")
                .unlockedBy("has_iron_gear", has(CItems.IRON_GEAR))
                .save(recipeOutput);

        // Crusher
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, CBlocks.CRUSHER)
                .define('G', CItems.IRON_GEAR)
                .define('P', Blocks.PISTON)
                .define('I', Items.IRON_INGOT)
                .define('F', Blocks.GLASS_PANE)
                .define('#', Blocks.IRON_BLOCK)
                .pattern("GPG")
                .pattern("IFI")
                .pattern("###")
                .unlockedBy("has_iron_gear", has(CItems.IRON_GEAR))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, CBlocks.ADVANCED_CRUSHER)
                .define('G', CItems.TITANIUM_GEAR)
                .define('T', CItems.TITANIUM_INGOT)
                .define('C', CBlocks.CRUSHER)
                .define('#', CBlocks.TITANIUM_BLOCK)
                .pattern("GGG")
                .pattern("TCT")
                .pattern("###")
                .unlockedBy("has_crusher", has(CBlocks.CRUSHER))
                .save(recipeOutput);
    }
}
