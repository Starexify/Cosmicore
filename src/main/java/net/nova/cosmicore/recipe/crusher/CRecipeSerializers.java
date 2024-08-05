package net.nova.cosmicore.recipe.crusher;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static net.nova.cosmicore.Cosmicore.MODID;

public class CRecipeSerializers {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, MODID);

    public static final Supplier<RecipeSerializer<CrushingRecipe>> CRUSHING_RECIPE_SERIALIZER =
            RECIPE_SERIALIZERS.register("crushing", CrushingRecipeSerializer::new);
}
