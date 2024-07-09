package net.nova.cosmicore.init;

import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.nova.cosmicore.Cosmicore;

import java.util.Map;

import static net.nova.cosmicore.Cosmicore.MODID;

public class ModTrimMaterials {
    private static ResourceKey<TrimMaterial> registryKey(String pKey) {
        return ResourceKey.create(Registries.TRIM_MATERIAL, Cosmicore.rl(pKey));
    }

    public static final ResourceKey<TrimMaterial> TITANIUM = registryKey("titanium");

    public static void bootstrap(BootstrapContext<TrimMaterial> pContext) {
        register(pContext, TITANIUM, ModItems.TITANIUM_INGOT.get(), Style.EMPTY.withColor(15527148), 1.1F);
    }

    private static void register(
            BootstrapContext<TrimMaterial> pContext, ResourceKey<TrimMaterial> pMaterialKey, Item pIngredient, Style pStyle, float pItemModelIndex
    ) {
        register(pContext, pMaterialKey, pIngredient, pStyle, pItemModelIndex, Map.of());
    }

    private static void register(
            BootstrapContext<TrimMaterial> pContext,
            ResourceKey<TrimMaterial> pMaterialKey,
            Item pIngredient,
            Style pStyle,
            float pItemModelIndex,
            Map<Holder<ArmorMaterial>, String> pOverrideArmorMaterials
    ) {
        TrimMaterial trimmaterial = TrimMaterial.create(
                pMaterialKey.location().getPath(),
                pIngredient,
                pItemModelIndex,
                Component.translatable(Util.makeDescriptionId("trim_material", pMaterialKey.location())).withStyle(pStyle),
                pOverrideArmorMaterials
        );
        pContext.register(pMaterialKey, trimmaterial);
    }
}
