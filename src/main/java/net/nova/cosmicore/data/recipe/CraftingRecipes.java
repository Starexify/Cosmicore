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
    public CraftingRecipes(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    public void build() {
        // Titanium Stuff
        nineBlockStorageRecipes(RecipeCategory.MISC, CItems.RAW_TITANIUM, RecipeCategory.BUILDING_BLOCKS, CBlocks.RAW_TITANIUM_BLOCK);
        nineBlockStorageRecipesWithCustomPacking(RecipeCategory.MISC, CItems.TITANIUM_NUGGET, RecipeCategory.MISC, CItems.TITANIUM_INGOT, "titanium_ingot_from_nuggets", "titanium_ingot");
        nineBlockStorageRecipesRecipesWithCustomUnpacking(RecipeCategory.MISC, CItems.TITANIUM_INGOT, RecipeCategory.BUILDING_BLOCKS, CBlocks.TITANIUM_BLOCK, "titanium_ingot_from_titanium_block", "titanium_ingot");
        nineBlockStorageRecipesRecipesWithCustomUnpacking(RecipeCategory.MISC, CItems.LONSDALEITE, RecipeCategory.BUILDING_BLOCKS, CBlocks.LONSDALEITE_BLOCK, "lonsdaleite_from_lonsdaleite_block", "lonsdaleite");

        copySmithingTemplate(CItems.TITANIUM_UPGRADE_SMITHING_TEMPLATE, CBlocks.METEORITE, Items.IRON_INGOT);
        copySmithingTemplate(CItems.LONSDALEITE_UPGRADE_SMITHING_TEMPLATE, CBlocks.PALLASITE, Items.DIAMOND);

        // Gears Recipes
        shaped(RecipeCategory.MISC, CItems.IRON_GEAR)
                .define('I', Items.IRON_INGOT)
                .define('N', Items.IRON_NUGGET)
                .pattern("NIN")
                .pattern("I I")
                .pattern("NIN")
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .save(output);
        shaped(RecipeCategory.MISC, CItems.TITANIUM_GEAR)
                .define('G', CItems.IRON_GEAR)
                .define('I', CItems.TITANIUM_INGOT)
                .define('N', CItems.TITANIUM_NUGGET)
                .pattern("NIN")
                .pattern("IGI")
                .pattern("NIN")
                .unlockedBy("has_iron_gear", has(CItems.IRON_GEAR))
                .save(output);

        // Crusher
        shaped(RecipeCategory.DECORATIONS, CBlocks.CRUSHER)
                .define('G', CItems.IRON_GEAR)
                .define('P', Blocks.PISTON)
                .define('I', Items.IRON_INGOT)
                .define('F', Blocks.GLASS_PANE)
                .define('#', Blocks.IRON_BLOCK)
                .pattern("GPG")
                .pattern("IFI")
                .pattern("###")
                .unlockedBy("has_iron_gear", has(CItems.IRON_GEAR))
                .save(output);

        shaped(RecipeCategory.DECORATIONS, CBlocks.ADVANCED_CRUSHER)
                .define('G', CItems.TITANIUM_GEAR)
                .define('T', CItems.TITANIUM_INGOT)
                .define('C', CBlocks.CRUSHER)
                .define('#', CBlocks.TITANIUM_BLOCK)
                .pattern("GGG")
                .pattern("TCT")
                .pattern("###")
                .unlockedBy("has_crusher", has(CBlocks.CRUSHER))
                .save(output);

        twoByTwoPacker(RecipeCategory.BUILDING_BLOCKS, CBlocks.INFERNIUM_BLOCK, CItems.INFERNIUM_CRYSTAL);

        // Cosmic Shield
        shaped(RecipeCategory.DECORATIONS, CBlocks.COSMIC_SHIELD)
                .define('#', CBlocks.TITANIUM_BLOCK)
                .define('X', CItems.TITANIUM_GEAR)
                .define('I', CItems.TITANIUM_INGOT)
                .pattern("  I")
                .pattern("X#X")
                .pattern("# #")
                .unlockedBy("has_", has(CBlocks.CRUSHER))
                .save(output);
    }
}
