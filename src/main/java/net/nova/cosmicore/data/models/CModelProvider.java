package net.nova.cosmicore.data.models;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.data.PackOutput;

import static net.nova.cosmicore.Cosmicore.MODID;

public class CModelProvider extends ModelProvider {
    public CModelProvider(PackOutput output) {
        super(output, MODID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        new CBlockModelGenerator(blockModels.blockStateOutput, itemModels.itemModelOutput, blockModels.modelOutput).run();
        new CItemModelGenerator(itemModels.itemModelOutput, itemModels.modelOutput).run();
    }
}
