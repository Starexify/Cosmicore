package net.nova.cosmicore.data.models;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.blockstates.BlockStateGenerator;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.Variant;
import net.minecraft.client.data.models.blockstates.VariantProperties;
import net.minecraft.client.data.models.model.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.nova.cosmicore.Cosmicore;
import net.nova.cosmicore.init.CBlocks;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

@OnlyIn(Dist.CLIENT)
public class CBlockModelGenerator extends BlockModelGenerators {
    public CBlockModelGenerator(Consumer<BlockStateGenerator> blockStateOutput, ItemModelOutput itemModelOutput, BiConsumer<ResourceLocation, ModelInstance> modelOutput) {
        super(blockStateOutput, itemModelOutput, modelOutput);
    }

    @Override
    public void run() {
        // Titanium Models
        createTrivialCube(CBlocks.RAW_TITANIUM_BLOCK.get());
        createTrivialCube(CBlocks.TITANIUM_BLOCK.get());

        // Lonsdaleite Models
        createTranslucentCube(CBlocks.LONSDALEITE_BLOCK.get());

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
    public void createTranslucentCube(Block block) {
        this.createTrivialBlock(block, TexturedModel.CUBE);
    }

    public void crusherModel(Block block) {
        ResourceLocation resourcelocation = TexturedModel.ORIENTABLE_ONLY_TOP.create(block, this.modelOutput);
        this.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block, Variant.variant().with(VariantProperties.MODEL, resourcelocation))
                .with(createHorizontalFacingDispatch()));

        //this.registerSimpleItemModel(block.asItem(), ModelTemplates.FLAT_ITEM.create(ModelLocationUtils.decorateItemModelLocation("template_crusher"), TextureMapping.layer0(block.asItem()), this.modelOutput));
    }

    public void cosmicShieldModel(Block block) {
        ResourceLocation resourcelocation = TexturedModel.ORIENTABLE_ONLY_TOP.create(block, this.modelOutput);
        this.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block, Variant.variant().with(VariantProperties.MODEL, resourcelocation))
                .with(createHorizontalFacingDispatch()));

    }

    public void createInferniumCluster(Block block) {
        this.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block, Variant.variant().with(VariantProperties.MODEL, ModelTemplates.CROSS.create(block, TextureMapping.cross(block), this.modelOutput))).with(this.createColumnWithFacing()));
    }


        /*    public void crusherModel(Block block) {
            horizontalBlock(block, models().withExistingParent(name(block), modLoc("template_crusher"))
                    .texture("layer0", "block/" + name(block))
                    .texture("particle", "block/crusher_base"));
        }
            public void cosmicShieldModel(Block block) {
        horizontalBlock(block, models().withExistingParent(name(block), modLoc("template_cosmic_shield"))
                .texture("layer0", "block/" + name(block))
                .texture("particle", "block/crusher_base"));
    }

        */

}