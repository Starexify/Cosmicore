package net.nova.cosmicore.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static net.nova.cosmicore.Cosmicore.MODID;

public class CreativeTabs {
    public static DeferredRegister<CreativeModeTab> CREATIVE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static String COSMICORE_TAB_TITLE = "cosmicore.creativetab";

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> COSMICORE_TAB = CREATIVE_TAB.register("cosmicore_tab", () -> {
        CreativeModeTab.Builder builder = CreativeModeTab.builder();

        builder.displayItems((itemDisplay, output) -> {
            // Armors & Tools Stuff
            output.accept(CItems.TITANIUM_HELMET);
            output.accept(CItems.TITANIUM_CHESTPLATE);
            output.accept(CItems.TITANIUM_LEGGINGS);
            output.accept(CItems.TITANIUM_BOOTS);
            output.accept(CItems.TITANIUM_SWORD);
            output.accept(CItems.TITANIUM_PICKAXE);
            output.accept(CItems.TITANIUM_AXE);
            output.accept(CItems.TITANIUM_SHOVEL);
            output.accept(CItems.TITANIUM_HOE);

            output.accept(CItems.LONSDALEITE_HELMET);
            output.accept(CItems.LONSDALEITE_CHESTPLATE);
            output.accept(CItems.LONSDALEITE_LEGGINGS);
            output.accept(CItems.LONSDALEITE_BOOTS);
            output.accept(CItems.LONSDALEITE_SWORD);
            output.accept(CItems.LONSDALEITE_PICKAXE);
            output.accept(CItems.LONSDALEITE_AXE);
            output.accept(CItems.LONSDALEITE_SHOVEL);
            output.accept(CItems.LONSDALEITE_HOE);

            output.accept(CItems.TITANIUM_CROSSBOW);
            output.accept(CItems.TITANIUM_HORSE_ARMOR);
            output.accept(CItems.LONSDALEITE_HORSE_ARMOR);

            output.accept(CItems.RAW_TITANIUM);
            output.accept(CItems.TITANIUM_NUGGET);
            output.accept(CItems.TITANIUM_INGOT);
            output.accept(CItems.LONSDALEITE);
            output.accept(CBlocks.RAW_TITANIUM_BLOCK);
            output.accept(CBlocks.TITANIUM_BLOCK);
            output.accept(CBlocks.LONSDALEITE_BLOCK);

            output.accept(CItems.TITANIUM_UPGRADE_SMITHING_TEMPLATE);
            output.accept(CItems.LONSDALEITE_UPGRADE_SMITHING_TEMPLATE);

            // Meteorites
            output.accept(CBlocks.ACHONDRITE);
            output.accept(CBlocks.METEORITE);
            output.accept(CBlocks.PALLASITE);

            // Infernium
            output.accept(CBlocks.INFERNIUM_CLUSTER);
            output.accept(CItems.INFERNIUM_CRYSTAL);
            output.accept(CBlocks.INFERNIUM_BLOCK);

            // Gears
            output.accept(CItems.IRON_GEAR);
            output.accept(CItems.TITANIUM_GEAR);

            // Crusher
            output.accept(CBlocks.CRUSHER);
            output.accept(CBlocks.ADVANCED_CRUSHER);

            // Sgield
            output.accept(CBlocks.COSMIC_SHIELD);
        });

        builder.icon(() -> new ItemStack(CBlocks.RAW_TITANIUM_BLOCK));
        builder.title(Component.translatable(COSMICORE_TAB_TITLE));

        return builder.build();
    });
}