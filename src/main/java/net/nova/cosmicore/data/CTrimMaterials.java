package net.nova.cosmicore.data;

import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.trim.MaterialAssetGroup;
import net.minecraft.world.item.equipment.trim.TrimMaterial;
import net.nova.cosmicore.Cosmicore;
import net.nova.cosmicore.equipment.CMaterialAssetGroup;

public class CTrimMaterials {
    public static final ResourceKey<TrimMaterial> TITANIUM = createKey("titanium");
    public static final ResourceKey<TrimMaterial> LONSDALEITE = createKey("lonsdaleite");

    public static void bootstrap(BootstrapContext<TrimMaterial> context) {
        register(context, TITANIUM, Style.EMPTY.withColor(TextColor.parseColor("#f5ffff").getOrThrow()), CMaterialAssetGroup.TITANIUM);
        register(context, LONSDALEITE, Style.EMPTY.withColor(TextColor.parseColor("#ccffff").getOrThrow()), CMaterialAssetGroup.LONSDALEITE);
    }

    // Registers
    public static void register(BootstrapContext<TrimMaterial> context, ResourceKey<TrimMaterial> materialKey, Style style, MaterialAssetGroup overrideArmorMaterials) {
        Component component = Component.translatable(Util.makeDescriptionId("trim_material", materialKey.location())).withStyle(style);
        context.register(materialKey, new TrimMaterial(overrideArmorMaterials, component));
    }

    public static ResourceKey<TrimMaterial> createKey(String name) {
        return ResourceKey.create(Registries.TRIM_MATERIAL, Cosmicore.rl(name));
    }
}
