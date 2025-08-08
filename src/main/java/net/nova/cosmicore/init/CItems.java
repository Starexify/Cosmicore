package net.nova.cosmicore.init;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ChargedProjectiles;
import net.minecraft.world.item.equipment.ArmorType;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.nova.cosmicore.item.FallenMeteorLocator;
import net.nova.cosmicore.item.LonsdaleiteSmithingTemplate;
import net.nova.cosmicore.item.TitaniumSmithingTemplate;

import static net.nova.cosmicore.Cosmicore.MODID;

public class CItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);

    // Titanium Items
    public static final DeferredItem<Item> TITANIUM_HELMET = ITEMS.registerItem("titanium_helmet", properties -> new Item(properties.humanoidArmor(CArmorMaterials.TITANIUM, ArmorType.HELMET)));
    public static final DeferredItem<Item> TITANIUM_CHESTPLATE = ITEMS.registerItem("titanium_chestplate", properties -> new Item(properties.humanoidArmor(CArmorMaterials.TITANIUM, ArmorType.CHESTPLATE)));
    public static final DeferredItem<Item> TITANIUM_LEGGINGS = ITEMS.registerItem("titanium_leggings", properties -> new Item(properties.humanoidArmor(CArmorMaterials.TITANIUM, ArmorType.LEGGINGS)));
    public static final DeferredItem<Item> TITANIUM_BOOTS = ITEMS.registerItem("titanium_boots", properties -> new Item(properties.humanoidArmor(CArmorMaterials.TITANIUM, ArmorType.BOOTS)));
    public static final DeferredItem<Item> TITANIUM_SWORD = ITEMS.registerItem("titanium_sword", properties -> new Item(properties.sword(CToolMaterial.TITANIUM, 3, -2.4F)));
    public static final DeferredItem<Item> TITANIUM_PICKAXE = ITEMS.registerItem("titanium_pickaxe", properties -> new Item(properties.pickaxe(CToolMaterial.TITANIUM, 1.0F, -2.8F)));
    public static final DeferredItem<Item> TITANIUM_AXE = ITEMS.registerItem("titanium_axe", properties -> new AxeItem(CToolMaterial.TITANIUM, 5.0F, -3.0F, properties));
    public static final DeferredItem<Item> TITANIUM_SHOVEL = ITEMS.registerItem("titanium_shovel", properties -> new ShovelItem(CToolMaterial.TITANIUM, 1.5F, -3.0F, properties));
    public static final DeferredItem<Item> TITANIUM_HOE = ITEMS.registerItem("titanium_hoe", properties -> new HoeItem(CToolMaterial.TITANIUM, -3.5F, 0.0F, properties));

    public static final DeferredItem<Item> TITANIUM_CROSSBOW = ITEMS.registerItem("titanium_crossbow", properties -> new CrossbowItem(properties.stacksTo(1).durability(700).component(DataComponents.CHARGED_PROJECTILES, ChargedProjectiles.EMPTY).enchantable(CToolMaterial.TITANIUM.enchantmentValue())) {
        @Override
        public int getDefaultProjectileRange() {
            return 10;
        }
    });
    public static final DeferredItem<Item> TITANIUM_HORSE_ARMOR = ITEMS.registerItem("titanium_horse_armor", properties -> new Item(properties.horseArmor(CArmorMaterials.TITANIUM)));

    public static final DeferredItem<Item> RAW_TITANIUM = ITEMS.registerSimpleItem("raw_titanium");
    public static final DeferredItem<Item> TITANIUM_NUGGET = ITEMS.registerSimpleItem("titanium_nugget");
    public static final DeferredItem<Item> TITANIUM_INGOT = ITEMS.registerSimpleItem("titanium_ingot");

    public static final DeferredItem<Item> TITANIUM_UPGRADE_SMITHING_TEMPLATE = ITEMS.registerItem("titanium_upgrade_smithing_template", properties -> TitaniumSmithingTemplate.createTitaniumUpgradeTemplate(properties.rarity(Rarity.UNCOMMON)));

    // Lonsdaleite Items
    public static final DeferredItem<Item> LONSDALEITE_HELMET = ITEMS.registerItem("lonsdaleite_helmet", properties -> new Item(properties.humanoidArmor(CArmorMaterials.LONSDALEITE, ArmorType.HELMET)));
    public static final DeferredItem<Item> LONSDALEITE_CHESTPLATE = ITEMS.registerItem("lonsdaleite_chestplate", properties -> new Item(properties.humanoidArmor(CArmorMaterials.LONSDALEITE, ArmorType.CHESTPLATE)));
    public static final DeferredItem<Item> LONSDALEITE_LEGGINGS = ITEMS.registerItem("lonsdaleite_leggings", properties -> new Item(properties.humanoidArmor(CArmorMaterials.LONSDALEITE, ArmorType.LEGGINGS)));
    public static final DeferredItem<Item> LONSDALEITE_BOOTS = ITEMS.registerItem("lonsdaleite_boots", properties -> new Item(properties.humanoidArmor(CArmorMaterials.LONSDALEITE, ArmorType.BOOTS)));
    public static final DeferredItem<Item> LONSDALEITE_SWORD = ITEMS.registerItem("lonsdaleite_sword", properties -> new Item(properties.sword(CToolMaterial.LONSDALEITE, 3, -2.4F)));
    public static final DeferredItem<Item> LONSDALEITE_PICKAXE = ITEMS.registerItem("lonsdaleite_pickaxe", properties -> new Item(properties.pickaxe(CToolMaterial.LONSDALEITE, 1.0F, -2.8F)));
    public static final DeferredItem<Item> LONSDALEITE_AXE = ITEMS.registerItem("lonsdaleite_axe", properties -> new AxeItem(CToolMaterial.LONSDALEITE, 5.0F, -2.9F, properties));
    public static final DeferredItem<Item> LONSDALEITE_SHOVEL = ITEMS.registerItem("lonsdaleite_shovel", properties -> new ShovelItem(CToolMaterial.LONSDALEITE, 1.5F, -3.0F, properties));
    public static final DeferredItem<Item> LONSDALEITE_HOE = ITEMS.registerItem("lonsdaleite_hoe", properties -> new HoeItem(CToolMaterial.LONSDALEITE, -4.7F, 0.0F, properties));

    public static final DeferredItem<Item> LONSDALEITE_HORSE_ARMOR = ITEMS.registerItem("lonsdaleite_horse_armor", properties -> new Item(properties.horseArmor(CArmorMaterials.LONSDALEITE)));

    public static final DeferredItem<Item> LONSDALEITE = ITEMS.registerSimpleItem("lonsdaleite");

    public static final DeferredItem<Item> LONSDALEITE_UPGRADE_SMITHING_TEMPLATE = ITEMS.registerItem("lonsdaleite_upgrade_smithing_template", properties -> LonsdaleiteSmithingTemplate.createLonsdaleiteUpgradeTemplate(properties.rarity(Rarity.RARE)));

    // Magnetite
    public static final DeferredItem<Item> MAGNETITE = ITEMS.registerSimpleItem("magnetite");
    public static final DeferredItem<Item> FALLEN_METEOR_LOCATOR = ITEMS.registerItem("fallen_meteor_locator", properties -> new FallenMeteorLocator(properties.stacksTo(1)));

    // Fuels
    public static final DeferredItem<Item> INFERNIUM_CRYSTAL = ITEMS.registerSimpleItem("infernium_crystal");
    public static final DeferredItem<Item> OLIVINE = ITEMS.registerSimpleItem("olivine");

    // Gears
    public static final DeferredItem<Item> IRON_GEAR = ITEMS.registerSimpleItem("iron_gear");
    public static final DeferredItem<Item> TITANIUM_GEAR = ITEMS.registerSimpleItem("titanium_gear");

    // Banner Pattern
    public static final DeferredItem<Item> METEORITE_BANNER_PATTERN = ITEMS.registerItem("meteorite_banner_pattern", properties -> new Item(properties.stacksTo(1).rarity(Rarity.RARE).component(DataComponents.PROVIDES_BANNER_PATTERNS, CTags.BannerPatternTags.PATTERN_ITEM_METEORITE)));

    // Spawn Eggs
    public static final DeferredItem<SpawnEggItem> TITANIUM_GOLEM_SPAWN_EGG = ITEMS.registerItem("titanium_golem_spawn_egg", properties -> new SpawnEggItem(CEntities.TITANIUM_GOLEM.get(), properties));
}