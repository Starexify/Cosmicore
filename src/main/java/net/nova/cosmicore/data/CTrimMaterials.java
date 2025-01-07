package net.nova.cosmicore.data;

import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.trim.TrimMaterial;
import net.nova.cosmicore.Cosmicore;
import net.nova.cosmicore.equipment.CEquipmentAssets;
import net.nova.cosmicore.init.CItems;

import java.util.Map;

public class CTrimMaterials {
    public static final ResourceKey<TrimMaterial> TITANIUM = createKey("titanium");
    public static final ResourceKey<TrimMaterial> LONSDALEITE = createKey("lonsdaleite");

    public static void bootstrap(BootstrapContext<TrimMaterial> context) {
        register(context, TITANIUM, CItems.TITANIUM_INGOT.get(), Style.EMPTY.withColor(TextColor.parseColor("#f5ffff").getOrThrow()), Map.of(CEquipmentAssets.TITANIUM, "titanium_darker"));
        register(context, LONSDALEITE, CItems.LONSDALEITE.get(), Style.EMPTY.withColor(TextColor.parseColor("#ccffff").getOrThrow()), Map.of(CEquipmentAssets.LONSDALEITE, "lonsdaleite_darker"));
    }

    // Registers
    public static ResourceKey<TrimMaterial> createKey(String name) {
        return ResourceKey.create(Registries.TRIM_MATERIAL, Cosmicore.rl(name));
    }

    public static void register(BootstrapContext<TrimMaterial> context, ResourceKey<TrimMaterial> materialKey, Item ingredient, Style style) {
        register(context, materialKey, ingredient, style, Map.of());
    }

    public static void register(BootstrapContext<TrimMaterial> context, ResourceKey<TrimMaterial> materialKey, Item ingredient, Style style, Map<ResourceKey<EquipmentAsset>, String> overrideArmorMaterials) {
        TrimMaterial trimmaterial = TrimMaterial.create(materialKey.location().getPath(), ingredient, Component.translatable(Util.makeDescriptionId("trim_material", materialKey.location())).withStyle(style), overrideArmorMaterials);
        context.register(materialKey, trimmaterial);
    }
}
