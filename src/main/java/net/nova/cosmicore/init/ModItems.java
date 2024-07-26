package net.nova.cosmicore.init;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ChargedProjectiles;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.nova.cosmicore.item.ModSmithingTemplateItem;
import net.nova.cosmicore.item.TitaniumCrossbow;

import static net.nova.cosmicore.Cosmicore.MODID;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);

    public static DeferredItem<Item> TITANIUM_HELMET = ITEMS.register("titanium_helmet",
            () -> new ArmorItem(ModArmorMaterial.TITANIUM, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(29))));
    public static DeferredItem<Item> TITANIUM_CHESTPLATE = ITEMS.register("titanium_chestplate",
            () -> new ArmorItem(ModArmorMaterial.TITANIUM, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(29))));
    public static DeferredItem<Item> TITANIUM_LEGGINGS = ITEMS.register("titanium_leggings",
            () -> new ArmorItem(ModArmorMaterial.TITANIUM, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(29))));
    public static DeferredItem<Item> TITANIUM_BOOTS = ITEMS.register("titanium_boots",
            () -> new ArmorItem(ModArmorMaterial.TITANIUM, ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(29))));

    public static DeferredItem<Item> TITANIUM_SWORD = ITEMS.register("titanium_sword",
            () -> new SwordItem(ModToolTiers.TITANIUM, new Item.Properties().attributes(SwordItem.createAttributes(ModToolTiers.TITANIUM, 3, -2.4F))));
    public static DeferredItem<Item> TITANIUM_PICKAXE = ITEMS.register("titanium_pickaxe",
            () -> new PickaxeItem(ModToolTiers.TITANIUM, new Item.Properties().attributes(PickaxeItem.createAttributes(ModToolTiers.TITANIUM, 1.0F, -2.8F))));
    public static DeferredItem<Item> TITANIUM_AXE = ITEMS.register("titanium_axe",
            () -> new AxeItem(ModToolTiers.TITANIUM, new Item.Properties().attributes(AxeItem.createAttributes(ModToolTiers.TITANIUM, 5.0F, -3.0F))));
    public static DeferredItem<Item> TITANIUM_SHOVEL = ITEMS.register("titanium_shovel",
            () -> new ShovelItem(ModToolTiers.TITANIUM, new Item.Properties().attributes(ShovelItem.createAttributes(ModToolTiers.TITANIUM, 1.5F, -3.0F))));
    public static DeferredItem<Item> TITANIUM_HOE = ITEMS.register("titanium_hoe",
            () -> new HoeItem(ModToolTiers.TITANIUM, new Item.Properties().attributes(HoeItem.createAttributes(ModToolTiers.TITANIUM, -3.5F, 0.0F))));

    public static DeferredItem<Item> TITANIUM_CROSSBOW = ITEMS.register("titanium_crossbow",
            () -> new TitaniumCrossbow(new Item.Properties().stacksTo(1).durability(930).component(DataComponents.CHARGED_PROJECTILES, ChargedProjectiles.EMPTY)));



    public static DeferredItem<Item> RAW_TITANIUM = ITEMS.register("raw_titanium",
            () -> new Item(new Item.Properties()));
    public static DeferredItem<Item> TITANIUM_NUGGET = ITEMS.register("titanium_nugget",
            () -> new Item(new Item.Properties()));
    public static DeferredItem<Item> TITANIUM_INGOT = ITEMS.register("titanium_ingot",
            () -> new Item(new Item.Properties()));

    public static DeferredItem<Item> TITANIUM_UPGRADE_SMITHING_TEMPLATE = ITEMS.register("titanium_upgrade_smithing_template",
            () -> ModSmithingTemplateItem.createTitaniumUpgradeTemplate());
}
