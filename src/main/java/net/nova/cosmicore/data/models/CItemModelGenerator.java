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
import net.minecraft.world.item.equipment.trim.MaterialAssetGroup;
import net.minecraft.world.item.equipment.trim.TrimMaterial;
import net.minecraft.world.item.equipment.trim.TrimMaterials;
import net.nova.cosmicore.data.CTrimMaterials;
import net.nova.cosmicore.equipment.CEquipmentAssets;
import net.nova.cosmicore.equipment.CMaterialAssetGroup;
import net.nova.cosmicore.init.CItems;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class CItemModelGenerator extends ItemModelGenerators {
    public static final List<ItemModelGenerators.TrimMaterialData> TRIM_MATERIAL_MODELS = List.of(
            new ItemModelGenerators.TrimMaterialData(MaterialAssetGroup.QUARTZ, TrimMaterials.QUARTZ),
            new ItemModelGenerators.TrimMaterialData(MaterialAssetGroup.IRON, TrimMaterials.IRON),
            new ItemModelGenerators.TrimMaterialData(MaterialAssetGroup.NETHERITE, TrimMaterials.NETHERITE),
            new ItemModelGenerators.TrimMaterialData(MaterialAssetGroup.REDSTONE, TrimMaterials.REDSTONE),
            new ItemModelGenerators.TrimMaterialData(MaterialAssetGroup.COPPER, TrimMaterials.COPPER),
            new ItemModelGenerators.TrimMaterialData(MaterialAssetGroup.GOLD, TrimMaterials.GOLD),
            new ItemModelGenerators.TrimMaterialData(MaterialAssetGroup.EMERALD, TrimMaterials.EMERALD),
            new ItemModelGenerators.TrimMaterialData(MaterialAssetGroup.DIAMOND, TrimMaterials.DIAMOND),
            new ItemModelGenerators.TrimMaterialData(MaterialAssetGroup.LAPIS, TrimMaterials.LAPIS),
            new ItemModelGenerators.TrimMaterialData(MaterialAssetGroup.AMETHYST, TrimMaterials.AMETHYST),
            new ItemModelGenerators.TrimMaterialData(MaterialAssetGroup.RESIN, TrimMaterials.RESIN),
            new ItemModelGenerators.TrimMaterialData(CMaterialAssetGroup.TITANIUM, CTrimMaterials.TITANIUM),
            new ItemModelGenerators.TrimMaterialData(CMaterialAssetGroup.LONSDALEITE, CTrimMaterials.LONSDALEITE)
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

        generateCrossbow(CItems.TITANIUM_CROSSBOW.get());
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

        // Magnetite
        generateFlatItem(CItems.MAGNETITE.get(), ModelTemplates.FLAT_ITEM);
        generateFlatItem(CItems.FALLEN_METEOR_LOCATOR.get(), ModelTemplates.FLAT_HANDHELD_ITEM);

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
        ResourceLocation modelLocation = ModelLocationUtils.getModelLocation(item);
        ResourceLocation textureLocation = TextureMapping.getItemTexture(item);
        List<SelectItemModel.SwitchCase<ResourceKey<TrimMaterial>>> list = new ArrayList<>(TRIM_MATERIAL_MODELS.size());
        EquipmentSlot slot = item.getDefaultInstance().get(DataComponents.EQUIPPABLE).slot();
        String armorType = switch (slot) {
            case HEAD -> "helmet";
            case CHEST -> "chestplate";
            case LEGS -> "leggings";
            case FEET -> "boots";
            default -> "";
        };

        for (TrimMaterialData trimMaterialData : TRIM_MATERIAL_MODELS) {
            ResourceLocation trimModelName = modelLocation.withSuffix("_" + trimMaterialData.assets().base().suffix() + "_trim");
            ResourceLocation layer1Location = ResourceLocation.withDefaultNamespace("trims/items/" + armorType + "_trim_" + trimMaterialData.assets().assetId(equipmentAsset).suffix());

            generateLayeredItem(trimModelName, textureLocation, layer1Location);
            list.add(ItemModelUtils.when(trimMaterialData.materialKey(), ItemModelUtils.plainModel(trimModelName)));
        }

        ItemModel.Unbaked basicItem = ItemModelUtils.plainModel(modelLocation);
        ModelTemplates.FLAT_ITEM.create(modelLocation, TextureMapping.layer0(textureLocation), modelOutput);
        itemModelOutput.accept(item, ItemModelUtils.select(new TrimMaterialProperty(), basicItem, list));
    }
}
