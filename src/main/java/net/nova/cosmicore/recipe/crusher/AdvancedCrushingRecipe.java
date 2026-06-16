package net.nova.cosmicore.recipe.crusher;

import com.mojang.serialization.MapCodec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.crafting.*;
import net.nova.cosmicore.init.CRecipeTypes;
import net.nova.cosmicore.recipe.WeightedResult;

import java.util.List;

public class AdvancedCrushingRecipe extends BaseCrushingRecipe {
  public static final MapCodec<AdvancedCrushingRecipe> MAP_CODEC = crushingMapCodec(AdvancedCrushingRecipe::new);
  public static final StreamCodec<RegistryFriendlyByteBuf, AdvancedCrushingRecipe> STREAM_CODEC = crushingStreamCodec(AdvancedCrushingRecipe::new);
  public static final RecipeSerializer<AdvancedCrushingRecipe> SERIALIZER = new RecipeSerializer<>(MAP_CODEC, STREAM_CODEC);

  public AdvancedCrushingRecipe(Ingredient ingredient, List<WeightedResult> results) {
    super(ingredient, results);
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
    return CRecipeTypes.ADVANCED_CRUSHING_RECIPE_TYPE.get();
  }
}
