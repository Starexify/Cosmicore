package net.nova.cosmicore.init;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ChargedProjectiles;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.nova.cosmicore.item.LonsdaleiteSmithingTemplate;
import net.nova.cosmicore.item.TitaniumCrossbow;
import net.nova.cosmicore.item.TitaniumSmithingTemplate;

import static net.nova.cosmicore.Cosmicore.MODID;

public class CItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);

    // Titanium Items
    public static final DeferredItem<Item> TITANIUM_HELMET = ITEMS.register("titanium_helmet", () -> new ArmorItem(CArmorMaterials.TITANIUM, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(29))));
    public static final DeferredItem<Item> TITANIUM_CHESTPLATE = ITEMS.register("titanium_chestplate", () -> new ArmorItem(CArmorMaterials.TITANIUM, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(29))));
    public static final DeferredItem<Item> TITANIUM_LEGGINGS = ITEMS.register("titanium_leggings", () -> new ArmorItem(CArmorMaterials.TITANIUM, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(29))));
    public static final DeferredItem<Item> TITANIUM_BOOTS = ITEMS.register("titanium_boots", () -> new ArmorItem(CArmorMaterials.TITANIUM, ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(29))));
    public static final DeferredItem<Item> TITANIUM_SWORD = ITEMS.register("titanium_sword", () -> new SwordItem(CTiers.TITANIUM, new Item.Properties().attributes(SwordItem.createAttributes(CTiers.TITANIUM, 3, -2.4F))));
    public static final DeferredItem<Item> TITANIUM_PICKAXE = ITEMS.register("titanium_pickaxe", () -> new PickaxeItem(CTiers.TITANIUM, new Item.Properties().attributes(PickaxeItem.createAttributes(CTiers.TITANIUM, 1.0F, -2.8F))));
    public static final DeferredItem<Item> TITANIUM_AXE = ITEMS.register("titanium_axe", () -> new AxeItem(CTiers.TITANIUM, new Item.Properties().attributes(AxeItem.createAttributes(CTiers.TITANIUM, 5.0F, -3.0F))));
    public static final DeferredItem<Item> TITANIUM_SHOVEL = ITEMS.register("titanium_shovel", () -> new ShovelItem(CTiers.TITANIUM, new Item.Properties().attributes(ShovelItem.createAttributes(CTiers.TITANIUM, 1.5F, -3.0F))));
    public static final DeferredItem<Item> TITANIUM_HOE = ITEMS.register("titanium_hoe", () -> new HoeItem(CTiers.TITANIUM, new Item.Properties().attributes(HoeItem.createAttributes(CTiers.TITANIUM, -3.5F, 0.0F))));

    public static final DeferredItem<Item> TITANIUM_CROSSBOW = ITEMS.register("titanium_crossbow", () -> new TitaniumCrossbow(new Item.Properties().stacksTo(1).durability(930).component(DataComponents.CHARGED_PROJECTILES, ChargedProjectiles.EMPTY)));
    public static final DeferredItem<Item> TITANIUM_HORSE_ARMOR = ITEMS.register("titanium_horse_armor", () -> new AnimalArmorItem(CArmorMaterials.TITANIUM, AnimalArmorItem.BodyType.EQUESTRIAN, false, new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> RAW_TITANIUM = ITEMS.register("raw_titanium", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TITANIUM_NUGGET = ITEMS.register("titanium_nugget", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TITANIUM_INGOT = ITEMS.register("titanium_ingot", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> TITANIUM_UPGRADE_SMITHING_TEMPLATE = ITEMS.register("titanium_upgrade_smithing_template", TitaniumSmithingTemplate::createTitaniumUpgradeTemplate);

    // Lonsdaleite Items
    public static final DeferredItem<Item> LONSDALEITE_HELMET = ITEMS.register("lonsdaleite_helmet", () -> new ArmorItem(CArmorMaterials.LONSDALEITE, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(29))));
    public static final DeferredItem<Item> LONSDALEITE_CHESTPLATE = ITEMS.register("lonsdaleite_chestplate", () -> new ArmorItem(CArmorMaterials.LONSDALEITE, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(29))));
    public static final DeferredItem<Item> LONSDALEITE_LEGGINGS = ITEMS.register("lonsdaleite_leggings", () -> new ArmorItem(CArmorMaterials.LONSDALEITE, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(29))));
    public static final DeferredItem<Item> LONSDALEITE_BOOTS = ITEMS.register("lonsdaleite_boots", () -> new ArmorItem(CArmorMaterials.LONSDALEITE, ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(29))));
    public static final DeferredItem<Item> LONSDALEITE_SWORD = ITEMS.register("lonsdaleite_sword", () -> new SwordItem(CTiers.LONSDALEITE, new Item.Properties().attributes(SwordItem.createAttributes(CTiers.LONSDALEITE, 3, -2.4F))));
    public static final DeferredItem<Item> LONSDALEITE_PICKAXE = ITEMS.register("lonsdaleite_pickaxe", () -> new PickaxeItem(CTiers.LONSDALEITE, new Item.Properties().attributes(PickaxeItem.createAttributes(CTiers.LONSDALEITE, 1.0F, -2.8F))));
    public static final DeferredItem<Item> LONSDALEITE_AXE = ITEMS.register("lonsdaleite_axe", () -> new AxeItem(CTiers.LONSDALEITE, new Item.Properties().attributes(AxeItem.createAttributes(CTiers.LONSDALEITE, 5.0F, -2.9F))));
    public static final DeferredItem<Item> LONSDALEITE_SHOVEL = ITEMS.register("lonsdaleite_shovel", () -> new ShovelItem(CTiers.LONSDALEITE, new Item.Properties().attributes(ShovelItem.createAttributes(CTiers.LONSDALEITE, 1.5F, -3.0F))));
    public static final DeferredItem<Item> LONSDALEITE_HOE = ITEMS.register("lonsdaleite_hoe", () -> new HoeItem(CTiers.LONSDALEITE, new Item.Properties().attributes(HoeItem.createAttributes(CTiers.LONSDALEITE, -4.7F, 0.0F))));

    public static final DeferredItem<Item> LONSDALEITE_HORSE_ARMOR = ITEMS.register("lonsdaleite_horse_armor", () -> new AnimalArmorItem(CArmorMaterials.LONSDALEITE, AnimalArmorItem.BodyType.EQUESTRIAN, false, new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> LONSDALEITE = ITEMS.register("lonsdaleite", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> LONSDALEITE_UPGRADE_SMITHING_TEMPLATE = ITEMS.register("lonsdaleite_upgrade_smithing_template", LonsdaleiteSmithingTemplate::createLonsdaleiteUpgradeTemplate);

    // Infernium
    public static final DeferredItem<Item> INFERNIUM_CRYSTAL = ITEMS.register("infernium_crystal", () -> new Item(new Item.Properties()));

    // Gears
    public static final DeferredItem<Item> IRON_GEAR = ITEMS.register("iron_gear", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TITANIUM_GEAR = ITEMS.register("titanium_gear", () -> new Item(new Item.Properties()));
}
