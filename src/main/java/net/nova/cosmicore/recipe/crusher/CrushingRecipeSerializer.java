package net.nova.cosmicore.recipe.crusher;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class CrushingRecipeSerializer implements RecipeSerializer<CrushingRecipe> {

    public static final MapCodec<CrushingRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
            Ingredient.CODEC_NONEMPTY.fieldOf("ingredient").forGetter(CrushingRecipe::getIngredient),
            ItemStack.CODEC.fieldOf("result").forGetter(CrushingRecipe::getResult)
    ).apply(inst, CrushingRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, CrushingRecipe> STREAM_CODEC = StreamCodec.composite(
            Ingredient.CONTENTS_STREAM_CODEC, CrushingRecipe::getIngredient,
            ItemStack.STREAM_CODEC, CrushingRecipe::getResult,
            CrushingRecipe::new
    );

    @Override
    public MapCodec<CrushingRecipe> codec() {
        return CODEC;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, CrushingRecipe> streamCodec() {
        return STREAM_CODEC;
    }
}
