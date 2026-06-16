package net.nova.cosmicore.data.models;

import net.minecraft.client.data.models.EquipmentAssetProvider;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.nova.cosmicore.Cosmicore;
import net.nova.cosmicore.equipment.CEquipmentAssets;

import java.util.function.BiConsumer;

public class CEquipmentModelProvider extends EquipmentAssetProvider {
  public CEquipmentModelProvider(PackOutput output) {
    super(output);
  }

  @Override
  protected void registerModels(BiConsumer<ResourceKey<EquipmentAsset>, EquipmentClientInfo> output) {
    output.accept(CEquipmentAssets.TITANIUM, humanoidAndMountArmor("cosmicore:titanium"));
    output.accept(CEquipmentAssets.LONSDALEITE, humanoidAndMountArmor("cosmicore:lonsdaleite"));
  }
}
