package net.nova.cosmicore.data;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.nova.cosmicore.init.ModBlocks;

import static net.nova.cosmicore.Cosmicore.MODID;

public class ModBlockStateAndModelProvider extends BlockStateProvider {
    public ModBlockStateAndModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MODID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        normalBlock(ModBlocks.RAW_TITANIUM_BLOCK.get());
        normalBlock(ModBlocks.TITANIUM_BLOCK.get());
        normalBlock(ModBlocks.ACHONDRITE.get());
        normalBlock(ModBlocks.METEORITE.get());
        normalBlock(ModBlocks.PALLASITE.get());
    }

    private void normalBlock(Block block) {
        ResourceLocation blockKey = BuiltInRegistries.BLOCK.getKey(block);
        String path = blockKey.getPath();
        simpleBlock(block, models().cubeAll(path, modLoc("block/" + path)));
        simpleBlockItem(block, models().getExistingFile(modLoc("block/" + path)));
    }
}
