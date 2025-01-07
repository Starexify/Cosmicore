package net.nova.cosmicore.data.models;

import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.item.SelectItemModel;
import net.minecraft.client.renderer.item.properties.select.TrimMaterialProperty;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;
import net.minecraft.world.item.equipment.Equippable;
import net.minecraft.world.item.equipment.trim.TrimMaterial;
import net.minecraft.world.item.equipment.trim.TrimMaterials;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.nova.cosmicore.data.CTrimMaterials;
import net.nova.cosmicore.equipment.CEquipmentAssets;
import net.nova.cosmicore.init.CItems;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

@OnlyIn(Dist.CLIENT)
public class CItemModelGenerator extends ItemModelGenerators {
    public static final List<TrimMaterialData> TRIM_MATERIAL_MODELS = List.of(
            new TrimMaterialData("quartz", TrimMaterials.QUARTZ, Map.of()),
            new TrimMaterialData("iron", TrimMaterials.IRON, Map.of(EquipmentAssets.IRON, "iron_darker")),
            new TrimMaterialData("netherite", TrimMaterials.NETHERITE, Map.of(EquipmentAssets.NETHERITE, "netherite_darker")),
            new TrimMaterialData("redstone", TrimMaterials.REDSTONE, Map.of()),
            new TrimMaterialData("copper", TrimMaterials.COPPER, Map.of()),
            new TrimMaterialData("gold", TrimMaterials.GOLD, Map.of(EquipmentAssets.GOLD, "gold_darker")),
            new TrimMaterialData("emerald", TrimMaterials.EMERALD, Map.of()),
            new TrimMaterialData("diamond", TrimMaterials.DIAMOND, Map.of(EquipmentAssets.DIAMOND, "diamond_darker")),
            new TrimMaterialData("lapis", TrimMaterials.LAPIS, Map.of()),
            new TrimMaterialData("amethyst", TrimMaterials.AMETHYST, Map.of()),
            new TrimMaterialData("resin", TrimMaterials.RESIN, Map.of()),
            new TrimMaterialData("livingmetal", CTrimMaterials.TITANIUM, Map.of(CEquipmentAssets.TITANIUM, "titanium_darker")),
            new TrimMaterialData("lonsdaleite", CTrimMaterials.LONSDALEITE, Map.of(CEquipmentAssets.LONSDALEITE, "lonsdaleite_darker"))
    );


    public CItemModelGenerator(ItemModelOutput itemModelOutput, BiConsumer<ResourceLocation, ModelInstance> modelOutput) {
        super(itemModelOutput, modelOutput);
    }

    @Override
    public void run() {
        // Titanium Models
        generateTrimmableItem(CItems.TITANIUM_HELMET.get(), CEquipmentAssets.TITANIUM);
        generateTrimmableItem(CItems.TITANIUM_CHESTPLATE.get(), CEquipmentAssets.TITANIUM);
        generateTrimmableItem(CItems.TITANIUM_LEGGINGS.get(), CEquipmentAssets.TITANIUM);
        generateTrimmableItem(CItems.TITANIUM_BOOTS.get(), CEquipmentAssets.TITANIUM);

        generateFlatItem(CItems.TITANIUM_SWORD.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        generateFlatItem(CItems.TITANIUM_PICKAXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        generateFlatItem(CItems.TITANIUM_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        generateFlatItem(CItems.TITANIUM_SHOVEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        generateFlatItem(CItems.TITANIUM_HOE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);

        generateFlatItem(CItems.TITANIUM_HORSE_ARMOR.get(), ModelTemplates.FLAT_ITEM);

        generateFlatItem(CItems.RAW_TITANIUM.get(), ModelTemplates.FLAT_ITEM);
        generateFlatItem(CItems.TITANIUM_NUGGET.get(), ModelTemplates.FLAT_ITEM);
        generateFlatItem(CItems.TITANIUM_INGOT.get(), ModelTemplates.FLAT_ITEM);

        generateFlatItem(CItems.TITANIUM_UPGRADE_SMITHING_TEMPLATE.get(), ModelTemplates.FLAT_ITEM);

        // Lonsdaleite Models
        generateTrimmableItem(CItems.LONSDALEITE_HELMET.get(), CEquipmentAssets.LONSDALEITE);
        generateTrimmableItem(CItems.LONSDALEITE_CHESTPLATE.get(), CEquipmentAssets.LONSDALEITE);
        generateTrimmableItem(CItems.LONSDALEITE_LEGGINGS.get(), CEquipmentAssets.LONSDALEITE);
        generateTrimmableItem(CItems.LONSDALEITE_BOOTS.get(), CEquipmentAssets.LONSDALEITE);

        generateFlatItem(CItems.LONSDALEITE_SWORD.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        generateFlatItem(CItems.LONSDALEITE_PICKAXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        generateFlatItem(CItems.LONSDALEITE_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        generateFlatItem(CItems.LONSDALEITE_SHOVEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        generateFlatItem(CItems.LONSDALEITE_HOE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);

        generateFlatItem(CItems.LONSDALEITE_HORSE_ARMOR.get(), ModelTemplates.FLAT_ITEM);

        generateFlatItem(CItems.LONSDALEITE.get(), ModelTemplates.FLAT_ITEM);

        generateFlatItem(CItems.LONSDALEITE_UPGRADE_SMITHING_TEMPLATE.get(), ModelTemplates.FLAT_ITEM);

        // Infernium Model
        generateFlatItem(CItems.INFERNIUM_CRYSTAL.get(), ModelTemplates.FLAT_ITEM);

        // Gear Model
        generateFlatItem(CItems.IRON_GEAR.get(), CModelTemplates.GEAR_ITEM);
        generateFlatItem(CItems.TITANIUM_GEAR.get(), CModelTemplates.GEAR_ITEM);

        // Banner Patterns
        generateFlatItem(CItems.METEORITE_BANNER_PATTERN.get(), ModelTemplates.FLAT_ITEM);
    }

    // Methods
    public void generateTrimmableItem(Item item, ResourceKey<EquipmentAsset> equipmentAsset) {
        ResourceLocation resourcelocation = ModelLocationUtils.getModelLocation(item);
        ResourceLocation resourcelocation1 = TextureMapping.getItemTexture(item);
        List<SelectItemModel.SwitchCase<ResourceKey<TrimMaterial>>> list = new ArrayList<>(TRIM_MATERIAL_MODELS.size());
        Equippable equippable = item.getDefaultInstance().get(DataComponents.EQUIPPABLE);
        EquipmentSlot slot = equippable.slot();
        String armorType = switch (slot) {
            case HEAD -> "helmet";
            case CHEST -> "chestplate";
            case LEGS -> "leggings";
            case FEET -> "boots";
            default -> "";
        };

        for (TrimMaterialData trimMaterial : TRIM_MATERIAL_MODELS) {
            ResourceLocation resourcelocation3 = resourcelocation.withSuffix("_" + trimMaterial.name() + "_trim");
            ResourceLocation resourcelocation4 = ResourceLocation.withDefaultNamespace(
                    "trims/items/" + armorType + "_trim_" + trimMaterial.textureName(equipmentAsset)
            );
            ItemModel.Unbaked itemmodel$unbaked;

            generateLayeredItem(resourcelocation3, resourcelocation1, resourcelocation4);
            itemmodel$unbaked = ItemModelUtils.plainModel(resourcelocation3);

            list.add(ItemModelUtils.when(trimMaterial.materialKey, itemmodel$unbaked));
        }

        ItemModel.Unbaked basicModel;
        ModelTemplates.FLAT_ITEM.create(resourcelocation, TextureMapping.layer0(resourcelocation1), modelOutput);
        basicModel = ItemModelUtils.plainModel(resourcelocation);

        itemModelOutput.accept(item, ItemModelUtils.select(new TrimMaterialProperty(), basicModel, list));
    }

    @OnlyIn(Dist.CLIENT)
    record TrimMaterialData(String name, ResourceKey<TrimMaterial> materialKey,
                            Map<ResourceKey<EquipmentAsset>, String> overrideArmorMaterials) {
        public String textureName(ResourceKey<EquipmentAsset> p_387088_) {
            return overrideArmorMaterials.getOrDefault(p_387088_, name);
        }
    }
}
