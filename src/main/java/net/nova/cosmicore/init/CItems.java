package net.nova.cosmicore.init;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ChargedProjectiles;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.nova.cosmicore.item.TitaniumSmithingTemplate;
import net.nova.cosmicore.item.TitaniumCrossbow;

import static net.nova.cosmicore.Cosmicore.MODID;

public class CItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);

    // Titanium Items
    public static DeferredItem<Item> TITANIUM_HELMET = ITEMS.register("titanium_helmet",
            () -> new ArmorItem(CArmorMaterial.TITANIUM, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(29))));
    public static DeferredItem<Item> TITANIUM_CHESTPLATE = ITEMS.register("titanium_chestplate",
            () -> new ArmorItem(CArmorMaterial.TITANIUM, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(29))));
    public static DeferredItem<Item> TITANIUM_LEGGINGS = ITEMS.register("titanium_leggings",
            () -> new ArmorItem(CArmorMaterial.TITANIUM, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(29))));
    public static DeferredItem<Item> TITANIUM_BOOTS = ITEMS.register("titanium_boots",
            () -> new ArmorItem(CArmorMaterial.TITANIUM, ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(29))));
    public static DeferredItem<Item> TITANIUM_SWORD = ITEMS.register("titanium_sword",
            () -> new SwordItem(CToolTiers.TITANIUM, new Item.Properties().attributes(SwordItem.createAttributes(CToolTiers.TITANIUM, 3, -2.4F))));
    public static DeferredItem<Item> TITANIUM_PICKAXE = ITEMS.register("titanium_pickaxe",
            () -> new PickaxeItem(CToolTiers.TITANIUM, new Item.Properties().attributes(PickaxeItem.createAttributes(CToolTiers.TITANIUM, 1.0F, -2.8F))));
    public static DeferredItem<Item> TITANIUM_AXE = ITEMS.register("titanium_axe",
            () -> new AxeItem(CToolTiers.TITANIUM, new Item.Properties().attributes(AxeItem.createAttributes(CToolTiers.TITANIUM, 5.0F, -3.0F))));
    public static DeferredItem<Item> TITANIUM_SHOVEL = ITEMS.register("titanium_shovel",
            () -> new ShovelItem(CToolTiers.TITANIUM, new Item.Properties().attributes(ShovelItem.createAttributes(CToolTiers.TITANIUM, 1.5F, -3.0F))));
    public static DeferredItem<Item> TITANIUM_HOE = ITEMS.register("titanium_hoe",
            () -> new HoeItem(CToolTiers.TITANIUM, new Item.Properties().attributes(HoeItem.createAttributes(CToolTiers.TITANIUM, -3.5F, 0.0F))));

    public static DeferredItem<Item> TITANIUM_CROSSBOW = ITEMS.register("titanium_crossbow",
            () -> new TitaniumCrossbow(new Item.Properties().stacksTo(1).durability(930).component(DataComponents.CHARGED_PROJECTILES, ChargedProjectiles.EMPTY)));

    public static DeferredItem<Item> RAW_TITANIUM = ITEMS.register("raw_titanium",
            () -> new Item(new Item.Properties()));
    public static DeferredItem<Item> TITANIUM_NUGGET = ITEMS.register("titanium_nugget",
            () -> new Item(new Item.Properties()));
    public static DeferredItem<Item> TITANIUM_INGOT = ITEMS.register("titanium_ingot",
            () -> new Item(new Item.Properties()));

    public static DeferredItem<Item> TITANIUM_UPGRADE_SMITHING_TEMPLATE = ITEMS.register("titanium_upgrade_smithing_template",
            () -> TitaniumSmithingTemplate.createTitaniumUpgradeTemplate());

    // Infernium
    public static DeferredItem<Item> INFERNIUM_CRYSTAL = ITEMS.register("infernium_crystal",
            () -> new Item(new Item.Properties()));

    // Gears
    public static DeferredItem<Item> IRON_GEAR = ITEMS.register("iron_gear",
            () -> new Item(new Item.Properties()));
    public static DeferredItem<Item> TITANIUM_GEAR = ITEMS.register("titanium_gear",
            () -> new Item(new Item.Properties()));
}
