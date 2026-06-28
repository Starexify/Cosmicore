package net.nova.cosmicore.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.nova.cosmicore.Cosmicore;

public class Tags {
  public interface CItemTags {
    TagKey<Item> REPAIRS_TITANIUM_ARMOR = itemTag("repairs_titanium_armor");
    TagKey<Item> REPAIRS_LONSDALEITE_ARMOR = itemTag("repairs_lonsdaleite_armor");
    TagKey<Item> TITANIUM_TOOL_MATERIALS = itemTag("titanium_tool_materials");
    TagKey<Item> LONSDALEITE_TOOL_MATERIALS = itemTag("lonsdaleite_tool_materials");
    TagKey<Item> MAGNETIC_ENCHANTABLE = itemTag("enchantable/magnetic");
  }

  public interface BannerPatternTags {
    TagKey<BannerPattern> PATTERN_ITEM_METEORITE = bannerTag("pattern_item/meteorite");
  }

  public static TagKey<Item> itemTag(String id) {
    return TagKey.create(Registries.ITEM, Cosmicore.rl(id));
  }

  public static TagKey<BannerPattern> bannerTag(String name) {
    return TagKey.create(Registries.BANNER_PATTERN, Cosmicore.rl(name));
  }
}
