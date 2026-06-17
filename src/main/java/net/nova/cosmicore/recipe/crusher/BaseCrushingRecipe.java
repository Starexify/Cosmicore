package net.nova.cosmicore.recipe.crusher;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.nova.cosmicore.recipe.WeightedResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class BaseCrushingRecipe implements Recipe<SingleRecipeInput> {
  public Ingredient ingredient;
  public Ingredient getIngredient() {
    return ingredient;
  }

  public List<WeightedResult> results;
  public static final Random RANDOM = new Random();

  private PlacementInfo placementInfo;

  public BaseCrushingRecipe(Ingredient ingredient, List<WeightedResult> results) {
    this.ingredient = ingredient;
    this.results = results;
  }

  @Override
  public ItemStack assemble(SingleRecipeInput input) {
    return getRandomResult().create();
  }

  @Override
  public boolean matches(SingleRecipeInput input, Level pLevel) {
    return this.getIngredient().test(input.item());
  }

  @Override
  public PlacementInfo placementInfo() {
    if (this.placementInfo == null) {
      this.placementInfo = PlacementInfo.create(List.of(this.ingredient));
    }
    return this.placementInfo;
  }

  public ItemStackTemplate getRandomResult() {
    float totalChance = results.stream().map(r -> r.chance()).reduce(0f, Float::sum);
    float roll = RANDOM.nextFloat() * totalChance;
    float currentSum = 0f;

    for (WeightedResult result : results) {
      currentSum += result.chance();
      if (roll < currentSum) return result.item();
    }

    return ItemStackTemplate.fromStack(ItemStack.EMPTY);
  }

  @Override
  public RecipeBookCategory recipeBookCategory() {
    return null;
  }

  private static final Codec<WeightedResult> WEIGHTED_RESULT_CODEC = RecordCodecBuilder.create(inst -> inst.group(
      ItemStackTemplate.CODEC.fieldOf("item").forGetter(wr -> wr.item()),
      Codec.FLOAT.fieldOf("chance").forGetter(wr -> wr.chance())
  ).apply(inst, WeightedResult::new));

  private static final StreamCodec<RegistryFriendlyByteBuf, WeightedResult> WEIGHTED_RESULT_STREAM_CODEC = StreamCodec.composite(
      ItemStack.STREAM_CODEC, wr -> wr.item().create(),
      ByteBufCodecs.FLOAT, wr -> wr.chance(),
      (stack, chance) -> new WeightedResult(new ItemStackTemplate(stack.getItem()), chance)
  );

  public static <T extends BaseCrushingRecipe> MapCodec<T> crushingMapCodec(BaseCrushingRecipe.Factory<T> factory) {
    return RecordCodecBuilder.mapCodec(inst -> inst.group(
            Ingredient.CODEC.fieldOf("ingredient").forGetter(recipe -> recipe.ingredient),
            Codec.list(WEIGHTED_RESULT_CODEC).fieldOf("results").forGetter(recipe -> recipe.results)
        ).apply(inst, factory::create)
    );
  }

  public static <T extends BaseCrushingRecipe> StreamCodec<RegistryFriendlyByteBuf, T> crushingStreamCodec(BaseCrushingRecipe.Factory<T> factory) {
    return StreamCodec.composite(
        Ingredient.CONTENTS_STREAM_CODEC, recipe -> recipe.ingredient,
        ByteBufCodecs.collection(ArrayList::new, WEIGHTED_RESULT_STREAM_CODEC), recipe -> recipe.results,
        factory::create
    );
  }

  @FunctionalInterface
  public interface Factory<T extends BaseCrushingRecipe> {
    T create(
        Ingredient ingredient,
        List<WeightedResult> results
    );
  }
}
