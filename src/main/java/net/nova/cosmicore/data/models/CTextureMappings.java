package net.nova.cosmicore.data.models;

import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.world.level.block.Block;
import net.nova.cosmicore.Cosmicore;

public class CTextureMappings {
    public static TextureMapping templateBlocks(Block block) {
        return (new TextureMapping()).put(TextureSlot.LAYER0, TextureMapping.getBlockTexture(block)).put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(block, "_base"));
    }

    public static TextureMapping templateCrusher(Block block) {
        return (new TextureMapping()).put(TextureSlot.LAYER0, TextureMapping.getBlockTexture(block)).put(TextureSlot.PARTICLE, Cosmicore.rl("crusher_base").withPrefix("block/"));
    }
}
