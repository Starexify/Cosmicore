package net.nova.cosmicore.data;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.data.models.blockstates.Variant;
import net.minecraft.data.models.blockstates.VariantProperties;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.nova.cosmicore.init.ModBlocks;

import static net.nova.cosmicore.Cosmicore.MODID;

public class ModBlockStateAndModelProvider extends BlockStateProvider {
    public ModBlockStateAndModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MODID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        // Titanium Stuff
        normalBlock(ModBlocks.RAW_TITANIUM_BLOCK.get());
        normalBlock(ModBlocks.TITANIUM_BLOCK.get());

        // Meteors Stuff
        normalBlock(ModBlocks.ACHONDRITE.get());
        normalBlock(ModBlocks.METEORITE.get());
        normalBlock(ModBlocks.PALLASITE.get());

        // Crusher
        customHorizontalBlockWOModel(ModBlocks.CRUSHER.get());

        // Infernium
        customDirectionalBlock(ModBlocks.INFERNIUM_CLUSTER.get());
    }

    // Models
    private void customDirectionalBlock(Block block) {
        directionalBlock(block, models().cross(name(block), modLoc("block/" + name(block))).renderType(RenderType.CUTOUT.name));
        itemModels().getBuilder(name(block))
                .parent(itemModels().getExistingFile(mcLoc("item/handheld")))
                .texture("layer0", "block/" + name(block));
    }

    private void customHorizontalBlockWOModel(Block block) {
        horizontalBlock(block, new ModelFile.UncheckedModelFile(modLoc("block/" + name(block))));
        simpleBlockItem(block, new ModelFile.UncheckedModelFile(modLoc("block/" + name(block))));
    }

    private void normalBlock(Block block) {
        simpleBlockWithItem(block, models().cubeAll(name(block), modLoc("block/" + name(block))));
    }

    // Other stuff
    private ResourceLocation key(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block);
    }

    private String name(Block block) {
        return key(block).getPath();
    }
}
