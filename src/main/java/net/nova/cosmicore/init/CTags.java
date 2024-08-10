package net.nova.cosmicore.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.storage.loot.LootTable;
import net.nova.cosmicore.Cosmicore;

import java.util.HashSet;
import java.util.Set;

public class CTags {
    private static final Set<ResourceKey<LootTable>> LOCATIONS = new HashSet<>();

    public static class BiomeTags {
        public static final TagKey<Biome> HAS_METEOR_SITE = create("has_structure/meteor_site");
    }

    public static class ChestLootTags {
        public static final ResourceKey<LootTable> METEOR_SITE_1 = register("chests/meteor_site_1");
    }

    private static TagKey<Biome> create(String name) {
        return TagKey.create(Registries.BIOME, Cosmicore.rl(name));
    }

    private static ResourceKey<LootTable> register(String name) {
        return register(ResourceKey.create(Registries.LOOT_TABLE, Cosmicore.rl(name)));
    }

    private static ResourceKey<LootTable> register(ResourceKey<LootTable> pName) {
        if (LOCATIONS.add(pName)) {
            return pName;
        } else {
            throw new IllegalArgumentException(pName.location() + " is already a registered built-in loot table");
        }
    }
}
