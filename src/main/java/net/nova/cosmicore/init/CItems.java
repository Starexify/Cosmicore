package net.nova.cosmicore.init;

import net.minecraft.core.component.DataComponents;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ChargedProjectiles;
import net.minecraft.world.item.equipment.ArmorType;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.nova.cosmicore.item.LonsdaleiteSmithingTemplate;
import net.nova.cosmicore.item.TitaniumCrossbow;
import net.nova.cosmicore.item.TitaniumSmithingTemplate;

import static net.nova.cosmicore.Cosmicore.MODID;

public class CItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);

    // Titanium Items
    public static final DeferredItem<Item> TITANIUM_HELMET = ITEMS.registerItem("titanium_helmet", properties -> new ArmorItem(CArmorMaterials.TITANIUM, ArmorType.HELMET, properties));
    public static final DeferredItem<Item> TITANIUM_CHESTPLATE = ITEMS.registerItem("titanium_chestplate", properties -> new ArmorItem(CArmorMaterials.TITANIUM, ArmorType.CHESTPLATE, properties));
    public static final DeferredItem<Item> TITANIUM_LEGGINGS = ITEMS.registerItem("titanium_leggings", properties -> new ArmorItem(CArmorMaterials.TITANIUM, ArmorType.LEGGINGS, properties));
    public static final DeferredItem<Item> TITANIUM_BOOTS = ITEMS.registerItem("titanium_boots", properties -> new ArmorItem(CArmorMaterials.TITANIUM, ArmorType.BOOTS, properties));
    public static final DeferredItem<Item> TITANIUM_SWORD = ITEMS.registerItem("titanium_sword", properties -> new SwordItem(CToolMaterial.TITANIUM, 3, -2.4F, properties));
    public static final DeferredItem<Item> TITANIUM_PICKAXE = ITEMS.registerItem("titanium_pickaxe", properties -> new PickaxeItem(CToolMaterial.TITANIUM, 1.0F, -2.8F, properties));
    public static final DeferredItem<Item> TITANIUM_AXE = ITEMS.registerItem("titanium_axe", properties -> new AxeItem(CToolMaterial.TITANIUM, 5.0F, -3.0F, properties));
    public static final DeferredItem<Item> TITANIUM_SHOVEL = ITEMS.registerItem("titanium_shovel", properties -> new ShovelItem(CToolMaterial.TITANIUM, 1.5F, -3.0F, properties));
    public static final DeferredItem<Item> TITANIUM_HOE = ITEMS.registerItem("titanium_hoe", properties -> new HoeItem(CToolMaterial.TITANIUM, -3.5F, 0.0F, properties));

    public static final DeferredItem<Item> TITANIUM_CROSSBOW = ITEMS.registerItem("titanium_crossbow", properties -> new TitaniumCrossbow(properties.stacksTo(1).durability(465).component(DataComponents.CHARGED_PROJECTILES, ChargedProjectiles.EMPTY).enchantable(CToolMaterial.TITANIUM.enchantmentValue())));
    public static final DeferredItem<Item> TITANIUM_HORSE_ARMOR = ITEMS.registerItem("titanium_horse_armor", properties -> new AnimalArmorItem(CArmorMaterials.TITANIUM, AnimalArmorItem.BodyType.EQUESTRIAN, SoundEvents.HORSE_ARMOR, false, properties.stacksTo(1)));

    public static final DeferredItem<Item> RAW_TITANIUM = ITEMS.registerItem("raw_titanium", Item::new);
    public static final DeferredItem<Item> TITANIUM_NUGGET = ITEMS.registerItem("titanium_nugget", Item::new);
    public static final DeferredItem<Item> TITANIUM_INGOT = ITEMS.registerItem("titanium_ingot", Item::new);

    public static final DeferredItem<Item> TITANIUM_UPGRADE_SMITHING_TEMPLATE = ITEMS.registerItem("titanium_upgrade_smithing_template", TitaniumSmithingTemplate::createTitaniumUpgradeTemplate);

    // Lonsdaleite Items
    public static final DeferredItem<Item> LONSDALEITE_HELMET = ITEMS.registerItem("lonsdaleite_helmet", properties -> new ArmorItem(CArmorMaterials.LONSDALEITE, ArmorType.HELMET, properties));
    public static final DeferredItem<Item> LONSDALEITE_CHESTPLATE = ITEMS.registerItem("lonsdaleite_chestplate", properties -> new ArmorItem(CArmorMaterials.LONSDALEITE, ArmorType.CHESTPLATE, properties));
    public static final DeferredItem<Item> LONSDALEITE_LEGGINGS = ITEMS.registerItem("lonsdaleite_leggings", properties -> new ArmorItem(CArmorMaterials.LONSDALEITE, ArmorType.LEGGINGS, properties));
    public static final DeferredItem<Item> LONSDALEITE_BOOTS = ITEMS.registerItem("lonsdaleite_boots", properties -> new ArmorItem(CArmorMaterials.LONSDALEITE, ArmorType.BOOTS, properties));
    public static final DeferredItem<Item> LONSDALEITE_SWORD = ITEMS.registerItem("lonsdaleite_sword", properties -> new SwordItem(CToolMaterial.LONSDALEITE, 3, -2.4F, properties));
    public static final DeferredItem<Item> LONSDALEITE_PICKAXE = ITEMS.registerItem("lonsdaleite_pickaxe", properties -> new PickaxeItem(CToolMaterial.LONSDALEITE, 1.0F, -2.8F, properties));
    public static final DeferredItem<Item> LONSDALEITE_AXE = ITEMS.registerItem("lonsdaleite_axe", properties -> new AxeItem(CToolMaterial.LONSDALEITE, 5.0F, -2.9F, properties));
    public static final DeferredItem<Item> LONSDALEITE_SHOVEL = ITEMS.registerItem("lonsdaleite_shovel", properties -> new ShovelItem(CToolMaterial.LONSDALEITE, 1.5F, -3.0F, properties));
    public static final DeferredItem<Item> LONSDALEITE_HOE = ITEMS.registerItem("lonsdaleite_hoe", properties -> new HoeItem(CToolMaterial.LONSDALEITE, -4.7F, 0.0F, properties));

    public static final DeferredItem<Item> LONSDALEITE_HORSE_ARMOR = ITEMS.registerItem("lonsdaleite_horse_armor", properties -> new AnimalArmorItem(CArmorMaterials.LONSDALEITE, AnimalArmorItem.BodyType.EQUESTRIAN, SoundEvents.HORSE_ARMOR, false, properties.stacksTo(1)));

    public static final DeferredItem<Item> LONSDALEITE = ITEMS.registerItem("lonsdaleite", Item::new);

    public static final DeferredItem<Item> LONSDALEITE_UPGRADE_SMITHING_TEMPLATE = ITEMS.registerItem("lonsdaleite_upgrade_smithing_template", LonsdaleiteSmithingTemplate::createLonsdaleiteUpgradeTemplate);

    // Infernium
    public static final DeferredItem<Item> INFERNIUM_CRYSTAL = ITEMS.registerItem("infernium_crystal", Item::new);

    // Gears
    public static final DeferredItem<Item> IRON_GEAR = ITEMS.registerItem("iron_gear", Item::new);
    public static final DeferredItem<Item> TITANIUM_GEAR = ITEMS.registerItem("titanium_gear", Item::new);

    // Banner Pattern
    public static final DeferredItem<BannerPatternItem> METEORITE_BANNER_PATTERN = ITEMS.registerItem("meteorite_banner_pattern", properties -> new BannerPatternItem(CTags.BannerPatternTags.PATTERN_ITEM_METEORITE, properties.stacksTo(1).rarity(Rarity.RARE)));
}