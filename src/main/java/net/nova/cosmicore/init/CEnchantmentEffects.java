package net.nova.cosmicore.init;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.nova.cosmicore.enchantment.MagnetismEffect;

import java.util.function.Supplier;

import static net.nova.cosmicore.Cosmicore.MODID;

public class CEnchantmentEffects {
    public static final DeferredRegister<MapCodec<? extends EnchantmentEntityEffect>> ENTITY_EFFECT = DeferredRegister.create(BuiltInRegistries.ENCHANTMENT_ENTITY_EFFECT_TYPE, MODID);

    public static final Supplier<MapCodec<? extends EnchantmentEntityEffect>> MAGNETISM = ENTITY_EFFECT.register("magnetism", () -> MagnetismEffect.CODEC);
}
