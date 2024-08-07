package net.nova.cosmicore.data;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.nova.cosmicore.init.CBlocks;

import static net.nova.cosmicore.Cosmicore.MODID;

public class BlockStateAndModelProvider extends BlockStateProvider {
    public BlockStateAndModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MODID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        // Titanium Models
        normalBlock(CBlocks.RAW_TITANIUM_BLOCK.get());
        normalBlock(CBlocks.TITANIUM_BLOCK.get());

        // Meteors Models
        normalBlock(CBlocks.ACHONDRITE.get());
        normalBlock(CBlocks.METEORITE.get());
        normalBlock(CBlocks.PALLASITE.get());

        // Crusher Model
        crusherModel(CBlocks.CRUSHER.get());
        crusherModel(CBlocks.ADVANCED_CRUSHER.get());

        // Infernium Model
        customDirectionalBlock(CBlocks.INFERNIUM_CLUSTER.get());
    }

    // Models
    private void crusherModel(Block block) {
        horizontalBlock(block, models().getBuilder(name(block))
                .parent(models().getExistingFile(modLoc("template_crusher")))
                .texture("layer0", "block/" + name(block))
                .texture("particle", "block/crusher_base"));
        simpleBlockItem(block, new ModelFile.UncheckedModelFile(modLoc("block/" + name(block))));
    }

    private void customDirectionalBlock(Block block) {
        directionalBlock(block, models().cross(name(block), modLoc("block/" + name(block))).renderType(RenderType.CUTOUT.name));
        itemModels().getBuilder(name(block))
                .parent(itemModels().getExistingFile(mcLoc("item/generated")))
                .texture("layer0", "block/" + name(block));
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
