package net.nova.cosmicore.recipe.crusher;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.nova.cosmicore.Cosmicore;

import java.util.function.Supplier;

import static net.nova.cosmicore.Cosmicore.MODID;

public class CRecipeTypes {
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(Registries.RECIPE_TYPE, MODID);

    public static final Supplier<RecipeType<CrushingRecipe>> CRUSHING_RECIPE_TYPE = RECIPE_TYPES.register("crushing", () -> RecipeType.<CrushingRecipe>simple(Cosmicore.rl("crushing")));
}
