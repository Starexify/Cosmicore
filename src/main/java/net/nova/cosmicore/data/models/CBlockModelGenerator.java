package net.nova.cosmicore.data.models;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.blockstates.BlockModelDefinitionGenerator;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.model.*;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.nova.cosmicore.init.CBlocks;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class CBlockModelGenerator extends BlockModelGenerators {
  public CBlockModelGenerator(Consumer<BlockModelDefinitionGenerator> blockStateOutput, ItemModelOutput itemModelOutput, BiConsumer<Identifier, ModelInstance> modelOutput) {
    super(blockStateOutput, itemModelOutput, modelOutput);
  }

  @Override
  public void run() {
    // Titanium Models
    createTrivialCube(CBlocks.RAW_TITANIUM_BLOCK.get());
    createTrivialCube(CBlocks.TITANIUM_BLOCK.get());

    // Lonsdaleite Models
    createTrivialCube(CBlocks.LONSDALEITE_BLOCK.get());

    // Meteors Models
    createTrivialCube(CBlocks.ACHONDRITE.get());
    createTrivialCube(CBlocks.METEORITE.get());
    createTrivialCube(CBlocks.PALLASITE.get());

    // Crusher Model
    crusherModel(CBlocks.CRUSHER.get());
    crusherModel(CBlocks.ADVANCED_CRUSHER.get());

    // Cosmic Shield Model
    cosmicShieldModel(CBlocks.COSMIC_SHIELD.get());

    // Infernium Model
    createInferniumCluster(CBlocks.INFERNIUM_CLUSTER.get());
    createTrivialCube(CBlocks.INFERNIUM_BLOCK.get());
  }

  // Models
  public void crusherModel(Block block) {
    Identifier resourcelocation = TexturedModel.createDefault(CTextureMappings::templateCrusher, CModelTemplates.TEMPLATE_CRUSHER).create(block, modelOutput);
    blockStateOutput.accept(MultiVariantGenerator.dispatch(block, plainVariant(resourcelocation))
        .with(ROTATION_HORIZONTAL_FACING));
    Identifier itemLocation = CModelTemplates.TEMPLATE_CRUSHER_ITEM.create(ModelLocationUtils.getModelLocation(block.asItem()), TextureMapping.layer0(block), modelOutput);
    registerSimpleItemModel(block.asItem(), itemLocation);
  }

  public void cosmicShieldModel(Block block) {
    Identifier resourcelocation = TexturedModel.createDefault(CTextureMappings::templateBlocks, CModelTemplates.TEMPLATE_COSMIC_SHIELD).create(block, modelOutput);
    blockStateOutput.accept(MultiVariantGenerator.dispatch(block, plainVariant(resourcelocation))
        .with(ROTATION_HORIZONTAL_FACING));
    Identifier itemLocation = CModelTemplates.TEMPLATE_COSMIC_SHIELD_ITEM.create(ModelLocationUtils.getModelLocation(block.asItem()), TextureMapping.layer0(block), modelOutput);
    registerSimpleItemModel(block.asItem(), itemLocation);
  }

  public void createInferniumCluster(Block block) {
    blockStateOutput.accept(MultiVariantGenerator.dispatch(block, plainVariant(ModelTemplates.CROSS.create(block, TextureMapping.cross(block), modelOutput))).with(ROTATIONS_COLUMN_WITH_FACING));
    registerSimpleFlatItemModel(block);
  }
}