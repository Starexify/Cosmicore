package net.nova.cosmicore.data;

import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.nova.cosmicore.Cosmicore;
import net.nova.cosmicore.init.CArmorMaterials;
import net.nova.cosmicore.init.CItems;

import java.util.Map;

public class CTrimMaterials {
    public static final ResourceKey<TrimMaterial> TITANIUM = createKey("titanium");
    public static final ResourceKey<TrimMaterial> LONSDALEITE = createKey("lonsdaleite");

    public static void bootstrap(BootstrapContext<TrimMaterial> pContext) {
        register(pContext, TITANIUM, CItems.TITANIUM_INGOT.get(), Style.EMPTY.withColor(TextColor.parseColor("#f5ffff").getOrThrow()), 0.2F, Map.of(CArmorMaterials.TITANIUM, "titanium_darker"));
        register(pContext, LONSDALEITE, CItems.LONSDALEITE.get(), Style.EMPTY.withColor(TextColor.parseColor("#ccffff").getOrThrow()), 0.8F, Map.of(CArmorMaterials.LONSDALEITE, "lonsdaleite_darker"));
    }

    // Registers
    private static ResourceKey<TrimMaterial> createKey(String name) {
        return ResourceKey.create(Registries.TRIM_MATERIAL, Cosmicore.rl(name));
    }

    public static void register(BootstrapContext<TrimMaterial> context, ResourceKey<TrimMaterial> materialKey, Item ingredient, Style style, float itemModelIndex) {
        register(context, materialKey, ingredient, style, itemModelIndex, Map.of());
    }

    public static void register(BootstrapContext<TrimMaterial> pContext, ResourceKey<TrimMaterial> pMaterialKey, Item pIngredient, Style pStyle, float pItemModelIndex, Map<Holder<ArmorMaterial>, String> pOverrideArmorMaterials) {
        TrimMaterial trimmaterial = TrimMaterial.create(pMaterialKey.location().getPath(), pIngredient, pItemModelIndex, Component.translatable(Util.makeDescriptionId("trim_material", pMaterialKey.location())).withStyle(pStyle), pOverrideArmorMaterials);
        pContext.register(pMaterialKey, trimmaterial);
    }
}
