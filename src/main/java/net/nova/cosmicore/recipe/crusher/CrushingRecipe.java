package net.nova.cosmicore.recipe.crusher;

import com.mojang.serialization.MapCodec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.crafting.*;
import net.nova.cosmicore.init.CRecipeTypes;
import net.nova.cosmicore.recipe.WeightedResult;

import java.util.List;

public class CrushingRecipe extends AbstractCrushingRecipe {
  public static final MapCodec<CrushingRecipe> MAP_CODEC = crushingMapCodec(CrushingRecipe::new, 400);
  public static final StreamCodec<RegistryFriendlyByteBuf, CrushingRecipe> STREAM_CODEC = crushingStreamCodec(CrushingRecipe::new);
  public static final RecipeSerializer<CrushingRecipe> SERIALIZER = new RecipeSerializer<>(MAP_CODEC, STREAM_CODEC);

  public CrushingRecipe(Ingredient ingredient, List<WeightedResult> results, int crushingProgress) {
    super(ingredient, results, crushingProgress);
  }

  @Override
    public boolean showNotification() {
        return false;
    }

    @Override
    public String group() {
        return "";
    }

    @Override
    public RecipeSerializer<? extends Recipe<SingleRecipeInput>> getSerializer() {
        return SERIALIZER;
    }

    @Override
    public RecipeType<? extends Recipe<SingleRecipeInput>> getType() {
        return CRecipeTypes.CRUSHING_RECIPE_TYPE.get();
    }
}
