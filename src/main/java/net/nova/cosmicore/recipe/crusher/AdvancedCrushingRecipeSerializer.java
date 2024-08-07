package net.nova.cosmicore.recipe.crusher;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.nova.cosmicore.recipe.WeightedResult;

import java.util.ArrayList;

public class AdvancedCrushingRecipeSerializer implements RecipeSerializer<AdvancedCrushingRecipe> {
    private static final Codec<WeightedResult> WEIGHTED_RESULT_CODEC = RecordCodecBuilder.create(inst -> inst.group(
            ItemStack.CODEC.fieldOf("item").forGetter(wr -> wr.item),
            Codec.FLOAT.fieldOf("chance").forGetter(wr -> wr.chance)
    ).apply(inst, WeightedResult::new));

    public static final MapCodec<AdvancedCrushingRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
            Ingredient.CODEC_NONEMPTY.fieldOf("ingredient").forGetter(recipe -> recipe.ingredient),
            Codec.list(WEIGHTED_RESULT_CODEC).fieldOf("results").forGetter(recipe -> recipe.results)
    ).apply(inst, AdvancedCrushingRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, AdvancedCrushingRecipe> STREAM_CODEC = StreamCodec.composite(
            Ingredient.CONTENTS_STREAM_CODEC, recipe -> recipe.ingredient,
            ByteBufCodecs.collection(
                    ArrayList::new,
                    StreamCodec.composite(
                            ItemStack.STREAM_CODEC, wr -> wr.item,
                            ByteBufCodecs.FLOAT, wr -> wr.chance,
                            WeightedResult::new
                    )
            ), recipe -> recipe.results,
            AdvancedCrushingRecipe::new
    );

    @Override
    public MapCodec<AdvancedCrushingRecipe> codec() {
        return CODEC;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, AdvancedCrushingRecipe> streamCodec() {
        return STREAM_CODEC;
    }
}
