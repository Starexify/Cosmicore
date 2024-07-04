package net.nova.cosmicore.init;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import static net.nova.cosmicore.Cosmicore.MODID;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);


    public static DeferredItem<Item> RAW_TITANIUM = ITEMS.register("raw_titanium",
            () -> new Item(new Item.Properties()));
    public static DeferredItem<Item> TITANIUM_INGOT = ITEMS.register("titanium_ingot",
            () -> new Item(new Item.Properties()));

    public static DeferredItem<Item> ASTRALITE_CLUSTER = ITEMS.register("astralite_cluster",
            () -> new Item(new Item.Properties()));
    public static DeferredItem<Item> ASTRALITE_CRYSTAL = ITEMS.register("astralite_crystal",
            () -> new Item(new Item.Properties()));

    public static DeferredItem<Item> UNSTABLE_HYPERIONITE = ITEMS.register("unstable_hyperionite",
            () -> new Item(new Item.Properties()));
    public static DeferredItem<Item> STABLE_HYPERIONITE = ITEMS.register("stable_hyperionite",
            () -> new Item(new Item.Properties()));

    public static DeferredItem<Item> LONSDALEITE = ITEMS.register("lonsdaleite",
            () -> new Item(new Item.Properties()));
}
