package net.nova.cosmicore.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashSet;
import java.util.Set;

import static net.nova.cosmicore.Cosmicore.MODID;

public class ModCreativeTab {
    public static DeferredRegister<CreativeModeTab> CREATIVE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static String COSMICORE_TAB_TITLE = "cosmicore.creativetab";

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> COSMICORE_TAB = CREATIVE_TAB.register("cosmicore_tab", () -> {
        CreativeModeTab.Builder builder = CreativeModeTab.builder();

        builder.displayItems((itemDisplay, output) -> {
            Set<Item> addedItems = new HashSet<>();

            ModItems.ITEMS.getEntries()
                    .stream()
                    .map((item) -> item.get().asItem())
                    .filter(addedItems::add)
                    .forEach(output::accept);

            ModBlocks.BLOCKS.getEntries()
                    .stream()
                    .map((item) -> item.get().asItem())
                    .filter(addedItems::add)
                    .forEach(output::accept);
        });

        builder.icon(() -> new ItemStack(ModBlocks.RAW_TITANIUM_BLOCK));
        builder.title(Component.translatable(COSMICORE_TAB_TITLE));

        return builder.build();
    });
}