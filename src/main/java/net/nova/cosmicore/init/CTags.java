package net.nova.cosmicore.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.nova.cosmicore.Cosmicore;

public class CTags {
    public static class BiomeTags {
        public static final TagKey<Biome> HAS_METEOR_SITE = create("has_structure/meteor_site");
    }


    private static TagKey<Biome> create(String name) {
        return TagKey.create(Registries.BIOME, Cosmicore.rl(name));
    }
}
