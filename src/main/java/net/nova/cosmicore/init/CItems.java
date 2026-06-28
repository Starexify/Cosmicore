package net.nova.cosmicore.init;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ChargedProjectiles;
import net.minecraft.world.item.equipment.ArmorType;
import net.nova.cosmicore.Cosmicore;

import java.util.function.Function;

public class CItems {
  // Titanium Items
  public static final Pair<Holder<Item>, ResourceKey<Item>> TITANIUM_HELMET = registerItem("titanium_helmet", p -> new Item(p.humanoidArmor(CArmorMaterials.TITANIUM, ArmorType.HELMET)));
  public static final Pair<Holder<Item>, ResourceKey<Item>> TITANIUM_CHESTPLATE = registerItem("titanium_chestplate", p -> new Item(p.humanoidArmor(CArmorMaterials.TITANIUM, ArmorType.CHESTPLATE)));
  public static final Pair<Holder<Item>, ResourceKey<Item>> TITANIUM_LEGGINGS = registerItem("titanium_leggings", p -> new Item(p.humanoidArmor(CArmorMaterials.TITANIUM, ArmorType.LEGGINGS)));
  public static final Pair<Holder<Item>, ResourceKey<Item>> TITANIUM_BOOTS = registerItem("titanium_boots", p -> new Item(p.humanoidArmor(CArmorMaterials.TITANIUM, ArmorType.BOOTS)));
  public static final Pair<Holder<Item>, ResourceKey<Item>> TITANIUM_SWORD = registerItem("titanium_sword", p -> new Item(p.sword(CToolMaterial.TITANIUM, 3, -2.4F)));
  public static final Pair<Holder<Item>, ResourceKey<Item>> TITANIUM_PICKAXE = registerItem("titanium_pickaxe", p -> new Item(p.pickaxe(CToolMaterial.TITANIUM, 1.0F, -2.8F)));
  public static final Pair<Holder<Item>, ResourceKey<Item>> TITANIUM_AXE = registerItem("titanium_axe", p -> new AxeItem(CToolMaterial.TITANIUM, 5.0F, -3.0F, p));
  public static final Pair<Holder<Item>, ResourceKey<Item>> TITANIUM_SHOVEL = registerItem("titanium_shovel", p -> new ShovelItem(CToolMaterial.TITANIUM, 1.5F, -3.0F, p));
  public static final Pair<Holder<Item>, ResourceKey<Item>> TITANIUM_HOE = registerItem("titanium_hoe", p -> new HoeItem(CToolMaterial.TITANIUM, -3.5F, 0.0F, p));

  public static final Pair<Holder<Item>, ResourceKey<Item>> TITANIUM_CROSSBOW = registerItem("titanium_crossbow", p -> new CrossbowItem(p.stacksTo(1).durability(700)
      .component(DataComponents.CHARGED_PROJECTILES, ChargedProjectiles.EMPTY)
      .enchantable(CToolMaterial.TITANIUM.enchantmentValue())
  ) {
    @Override
    public int getDefaultProjectileRange() {
      return 10;
    }
  });
  public static final Pair<Holder<Item>, ResourceKey<Item>> TITANIUM_HORSE_ARMOR = registerItem("titanium_horse_armor", p -> new Item(p.horseArmor(CArmorMaterials.TITANIUM)));

  public static final Pair<Holder<Item>, ResourceKey<Item>> RAW_TITANIUM = registerItem("raw_titanium", Item::new);
  public static final Pair<Holder<Item>, ResourceKey<Item>> TITANIUM_NUGGET = registerItem("titanium_nugget", Item::new);
  public static final Pair<Holder<Item>, ResourceKey<Item>> TITANIUM_INGOT = registerItem("titanium_ingot", Item::new);

//  public static final Pair<Holder<Item>, ResourceKey<Item>> TITANIUM_UPGRADE_SMITHING_TEMPLATE = registerItem("titanium_upgrade_smithing_template", p -> TitaniumSmithingTemplate.createTitaniumUpgradeTemplate(p.rarity(Rarity.UNCOMMON)));

  // Lonsdaleite Items
  public static final Pair<Holder<Item>, ResourceKey<Item>> LONSDALEITE_HELMET = registerItem("lonsdaleite_helmet", p -> new Item(p.humanoidArmor(CArmorMaterials.LONSDALEITE, ArmorType.HELMET)));
  public static final Pair<Holder<Item>, ResourceKey<Item>> LONSDALEITE_CHESTPLATE = registerItem("lonsdaleite_chestplate", p -> new Item(p.humanoidArmor(CArmorMaterials.LONSDALEITE, ArmorType.CHESTPLATE)));
  public static final Pair<Holder<Item>, ResourceKey<Item>> LONSDALEITE_LEGGINGS = registerItem("lonsdaleite_leggings", p -> new Item(p.humanoidArmor(CArmorMaterials.LONSDALEITE, ArmorType.LEGGINGS)));
  public static final Pair<Holder<Item>, ResourceKey<Item>> LONSDALEITE_BOOTS = registerItem("lonsdaleite_boots", p -> new Item(p.humanoidArmor(CArmorMaterials.LONSDALEITE, ArmorType.BOOTS)));
  public static final Pair<Holder<Item>, ResourceKey<Item>> LONSDALEITE_SWORD = registerItem("lonsdaleite_sword", p -> new Item(p.sword(CToolMaterial.LONSDALEITE, 3, -2.4F)));
  public static final Pair<Holder<Item>, ResourceKey<Item>> LONSDALEITE_PICKAXE = registerItem("lonsdaleite_pickaxe", p -> new Item(p.pickaxe(CToolMaterial.LONSDALEITE, 1.0F, -2.8F)));
  public static final Pair<Holder<Item>, ResourceKey<Item>> LONSDALEITE_AXE = registerItem("lonsdaleite_axe", p -> new AxeItem(CToolMaterial.LONSDALEITE, 5.0F, -2.9F, p));
  public static final Pair<Holder<Item>, ResourceKey<Item>> LONSDALEITE_SHOVEL = registerItem("lonsdaleite_shovel", p -> new ShovelItem(CToolMaterial.LONSDALEITE, 1.5F, -3.0F, p));
  public static final Pair<Holder<Item>, ResourceKey<Item>> LONSDALEITE_HOE = registerItem("lonsdaleite_hoe", p -> new HoeItem(CToolMaterial.LONSDALEITE, -4.7F, 0.0F, p));

  public static final Pair<Holder<Item>, ResourceKey<Item>> LONSDALEITE_HORSE_ARMOR = registerItem("lonsdaleite_horse_armor", p -> new Item(p.horseArmor(CArmorMaterials.LONSDALEITE)));

  public static final Pair<Holder<Item>, ResourceKey<Item>> LONSDALEITE = registerItem("lonsdaleite", Item::new);

//  public static final Pair<Holder<Item>, ResourceKey<Item>> LONSDALEITE_UPGRADE_SMITHING_TEMPLATE = registerItem("lonsdaleite_upgrade_smithing_template", p -> LonsdaleiteSmithingTemplate.createLonsdaleiteUpgradeTemplate(p.rarity(Rarity.RARE)));

  // Magnetite
  public static final Pair<Holder<Item>, ResourceKey<Item>> MAGNETITE = registerItem("magnetite", Item::new);
//  public static final Pair<Holder<Item>, ResourceKey<Item>> FALLEN_METEOR_LOCATOR = registerItem("fallen_meteor_locator", p -> new FallenMeteorLocator(p.stacksTo(1)));

  // Fuels
  public static final Pair<Holder<Item>, ResourceKey<Item>> INFERNIUM_CRYSTAL = registerItem("infernium_crystal", Item::new);
  public static final Pair<Holder<Item>, ResourceKey<Item>> OLIVINE = registerItem("olivine", Item::new);

  // Gears
  public static final Pair<Holder<Item>, ResourceKey<Item>> IRON_GEAR = registerItem("iron_gear", Item::new);
  public static final Pair<Holder<Item>, ResourceKey<Item>> TITANIUM_GEAR = registerItem("titanium_gear", Item::new);

  // Banner Pattern
  public static final Pair<Holder<Item>, ResourceKey<Item>> METEORITE_BANNER_PATTERN = registerItem("meteorite_banner_pattern", p -> new Item(p
          .stacksTo(1)
          .rarity(Rarity.RARE)
          .delayedComponent(DataComponents.PROVIDES_BANNER_PATTERNS, context -> context.getOrThrow(Tags.BannerPatternTags.PATTERN_ITEM_METEORITE)))
  );

  // Spawn Eggs
//    public static final DeferredItem<SpawnEggItem> TITANIUM_GOLEM_SPAWN_EGG = registerItem("titanium_golem_spawn_egg", p -> new SpawnEggItem(p));

  // Methods
  public static <T extends Item> Pair<Holder<T>, ResourceKey<Item>> registerItem(String name, Function<Item.Properties, T> function) {
    return register(name, function, new Item.Properties());
  }

  public static <T extends Item> Pair<Holder<T>, ResourceKey<Item>> register(String name, Function<Item.Properties, T> function, Item.Properties p) {
    ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, Cosmicore.rl(name));
    return Pair.of(Registry.registerForHolder(BuiltInRegistries.ITEM, key, function.apply(p.setId(key))), key);
  }

  public static void init() {
    Cosmicore.LOGGER.info("Registering Items");
  }
}
