package net.nova.cosmicore.data.models;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;

public class CModelProvider extends FabricModelProvider {
  public CModelProvider(FabricPackOutput output) {
    super(output);
  }

  @Override
  public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {
    new CBlockModelGenerator(blockModelGenerators.blockStateOutput, blockModelGenerators.itemModelOutput, blockModelGenerators.modelOutput).run();
  }

  @Override
  public void generateItemModels(ItemModelGenerators itemModelGenerators) {
    new CItemModelGenerator(itemModelGenerators.itemModelOutput, itemModelGenerators.modelOutput).run();
  }
}
