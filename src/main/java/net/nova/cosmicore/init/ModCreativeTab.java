package net.nova.cosmicore.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static net.nova.cosmicore.Cosmicore.MODID;

public class ModCreativeTab {
    public static DeferredRegister<CreativeModeTab> CREATIVE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static String COSMICORE_TAB_TITLE = "cosmicore.creativetab";

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> COSMICORE_TAB = CREATIVE_TAB.register("cosmicore_tab", () -> {
        CreativeModeTab.Builder builder = CreativeModeTab.builder();

        builder.displayItems((itemDisplay, output) -> {
            // Titanium Stuff
            output.accept(ModItems.TITANIUM_HELMET);
            output.accept(ModItems.TITANIUM_CHESTPLATE);
            output.accept(ModItems.TITANIUM_LEGGINGS);
            output.accept(ModItems.TITANIUM_BOOTS);
            output.accept(ModItems.TITANIUM_SWORD);
            output.accept(ModItems.TITANIUM_PICKAXE);
            output.accept(ModItems.TITANIUM_AXE);
            output.accept(ModItems.TITANIUM_SHOVEL);
            output.accept(ModItems.TITANIUM_HOE);

            output.accept(ModItems.TITANIUM_CROSSBOW);

            output.accept(ModItems.RAW_TITANIUM);
            output.accept(ModItems.TITANIUM_NUGGET);
            output.accept(ModItems.TITANIUM_INGOT);
            output.accept(ModBlocks.RAW_TITANIUM_BLOCK);
            output.accept(ModBlocks.TITANIUM_BLOCK);
            
            output.accept(ModItems.TITANIUM_UPGRADE_SMITHING_TEMPLATE);

            // Meteorites
            output.accept(ModBlocks.ACHONDRITE);
            output.accept(ModBlocks.METEORITE);
            output.accept(ModBlocks.PALLASITE);

            // Crusher
            output.accept(ModBlocks.CRUSHER);

            // Infernium
            output.accept(ModItems.INFERNIUM_CRYSTAL);

            // Gears
            output.accept(ModItems.IRON_GEAR);
            output.accept(ModItems.TITANIUM_GEAR);
        });

        builder.icon(() -> new ItemStack(ModBlocks.RAW_TITANIUM_BLOCK));
        builder.title(Component.translatable(COSMICORE_TAB_TITLE));

        return builder.build();
    });
}