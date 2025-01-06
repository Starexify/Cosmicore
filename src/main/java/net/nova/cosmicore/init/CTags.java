package net.nova.cosmicore.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraft.world.level.storage.loot.LootTable;
import net.nova.cosmicore.Cosmicore;

import java.util.HashSet;
import java.util.Set;

public class CTags {
    private static final Set<ResourceKey<LootTable>> LOCATIONS = new HashSet<>();

    public static class CItemTags {
        public static final TagKey<Item> REPAIRS_TITANIUM_ARMOR = itemTag("repairs_titanium_armor");
        public static final TagKey<Item> REPAIRS_LONSDALEITE_ARMOR = itemTag("repairs_lonsdaleite_armor");
        public static final TagKey<Item> TITANIUM_TOOL_MATERIALS = itemTag("titanium_tool_materials");
        public static final TagKey<Item> LONSDALEITE_TOOL_MATERIALS = itemTag("lonsdaleite_tool_materials");
    }

    public static class BlockTags {
        public static final TagKey<Block> METEOR_BREAKABLES = createBlockTag("meteor_breakables");
    }

    public static class BiomeTags {
        public static final TagKey<Biome> HAS_METEOR_SITE = create("has_structure/meteor_site");
        public static final TagKey<Biome> HAS_DESERT_METEOR_SITE = create("has_structure/desert_meteor_site");
        public static final TagKey<Biome> HAS_BADLANDS_METEOR_SITE = create("has_structure/badlands_meteor_site");
    }

    public static class ChestLootTags {
        public static final ResourceKey<LootTable> METEOR_SITE_1 = register("chests/meteor_site_1");
    }

    public static class BannerPatternTags {
        public static final TagKey<BannerPattern> PATTERN_ITEM_METEORITE = createBanner("pattern_item/meteorite");
    }

    // Register Tags
    public static TagKey<Item> itemTag(String name) {
        return ItemTags.create(Cosmicore.rl(name));
    }

    public static TagKey<Block> createBlockTag(String name) {
        return TagKey.create(Registries.BLOCK, Cosmicore.rl(name));
    }

    public static TagKey<Biome> create(String name) {
        return TagKey.create(Registries.BIOME, Cosmicore.rl(name));
    }

    public static ResourceKey<LootTable> register(String name) {
        return register(ResourceKey.create(Registries.LOOT_TABLE, Cosmicore.rl(name)));
    }

    public static ResourceKey<LootTable> register(ResourceKey<LootTable> pName) {
        if (LOCATIONS.add(pName)) {
            return pName;
        } else {
            throw new IllegalArgumentException(pName.location() + " is already a registered built-in loot table");
        }
    }

    public static TagKey<BannerPattern> createBanner(String name) {
        return TagKey.create(Registries.BANNER_PATTERN, Cosmicore.rl(name));
    }
}
