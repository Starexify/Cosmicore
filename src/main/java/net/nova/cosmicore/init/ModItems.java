package net.nova.cosmicore.init;

import net.minecraft.world.item.*;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import static net.nova.cosmicore.Cosmicore.MODID;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);


    public static DeferredItem<Item> RAW_TITANIUM = ITEMS.register("raw_titanium",
            () -> new Item(new Item.Properties()));
    public static DeferredItem<Item> TITANIUM_INGOT = ITEMS.register("titanium_ingot",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> TITANIUM_SHOVEL = ITEMS.register("titanium_shovel",
            () -> new ShovelItem(ModToolTiers.TITANIUM, new Item.Properties().attributes(ShovelItem.createAttributes(ModToolTiers.TITANIUM, 1.5F, -3.0F))));
    public static final DeferredItem<Item> TITANIUM_PICKAXE = ITEMS.register("titanium_pickaxe",
            () -> new PickaxeItem(ModToolTiers.TITANIUM, new Item.Properties().attributes(PickaxeItem.createAttributes(ModToolTiers.TITANIUM, 1.0F, -2.8F))));
    public static final DeferredItem<Item> TITANIUM_AXE = ITEMS.register("titanium_axe",
            () -> new AxeItem(ModToolTiers.TITANIUM, new Item.Properties().attributes(AxeItem.createAttributes(ModToolTiers.TITANIUM, 5.0F, -3.0F))));
    public static final DeferredItem<Item> TITANIUM_HOE = ITEMS.register("titanium_hoe",
            () -> new HoeItem(ModToolTiers.TITANIUM, new Item.Properties().attributes(HoeItem.createAttributes(ModToolTiers.TITANIUM, -3.5F, 0.0F))));
    public static final DeferredItem<Item> TITANIUM_SWORD = ITEMS.register("titanium_sword",
            () -> new SwordItem(ModToolTiers.TITANIUM, new Item.Properties().attributes(SwordItem.createAttributes(ModToolTiers.TITANIUM, 3, -2.4F))));
}