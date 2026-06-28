package net.nova.cosmicore.init;

import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.nova.cosmicore.Cosmicore;

public class CreativeTab {
  public static String COSMICORE_TAB_TITLE = "cosmicore.creativetab";

  public static final CreativeModeTab BIG_SWORDS_TAB = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, Cosmicore.rl("cosmicore_tab"),
      FabricCreativeModeTab.builder().displayItems((parameters, output) -> {
            output.accept(CItems.TITANIUM_HELMET.getFirst().value());
            output.accept(CItems.TITANIUM_CHESTPLATE.getFirst().value());
            output.accept(CItems.TITANIUM_LEGGINGS.getFirst().value());
            output.accept(CItems.TITANIUM_BOOTS.getFirst().value());
            output.accept(CItems.TITANIUM_SWORD.getFirst().value());
            output.accept(CItems.TITANIUM_PICKAXE.getFirst().value());
            output.accept(CItems.TITANIUM_AXE.getFirst().value());
            output.accept(CItems.TITANIUM_SHOVEL.getFirst().value());
            output.accept(CItems.TITANIUM_HOE.getFirst().value());

            output.accept(CItems.LONSDALEITE_HELMET.getFirst().value());
            output.accept(CItems.LONSDALEITE_CHESTPLATE.getFirst().value());
            output.accept(CItems.LONSDALEITE_LEGGINGS.getFirst().value());
            output.accept(CItems.LONSDALEITE_BOOTS.getFirst().value());
            output.accept(CItems.LONSDALEITE_SWORD.getFirst().value());
            output.accept(CItems.LONSDALEITE_PICKAXE.getFirst().value());
            output.accept(CItems.LONSDALEITE_AXE.getFirst().value());
            output.accept(CItems.LONSDALEITE_SHOVEL.getFirst().value());
            output.accept(CItems.LONSDALEITE_HOE.getFirst().value());

            output.accept(CItems.TITANIUM_CROSSBOW.getFirst().value());
            output.accept(CItems.TITANIUM_HORSE_ARMOR.getFirst().value());
            output.accept(CItems.LONSDALEITE_HORSE_ARMOR.getFirst().value());

            output.accept(CItems.MAGNETITE.getFirst().value());
            output.accept(CItems.RAW_TITANIUM.getFirst().value());
            output.accept(CItems.TITANIUM_NUGGET.getFirst().value());
            output.accept(CItems.TITANIUM_INGOT.getFirst().value());
            output.accept(CItems.LONSDALEITE.getFirst().value());
//            output.accept(CBlocks.RAW_TITANIUM_BLOCK);
//            output.accept(CBlocks.TITANIUM_BLOCK);
//            output.accept(CBlocks.LONSDALEITE_BLOCK);

//            output.accept(CItems.TITANIUM_UPGRADE_SMITHING_TEMPLATE.getFirst().value());
//            output.accept(CItems.LONSDALEITE_UPGRADE_SMITHING_TEMPLATE.getFirst().value());

            output.accept(CItems.METEORITE_BANNER_PATTERN.getFirst().value());

            // Meteorites
//            output.accept(CBlocks.ACHONDRITE);
//            output.accept(CBlocks.METEORITE);
//            output.accept(CBlocks.PALLASITE);

            // Fuels
//            output.accept(CBlocks.INFERNIUM_CLUSTER);
            output.accept(CItems.INFERNIUM_CRYSTAL.getFirst().value());
//            output.accept(CBlocks.INFERNIUM_BLOCK);
            output.accept(CItems.OLIVINE.getFirst().value());

            // Gears
            output.accept(CItems.IRON_GEAR.getFirst().value());
            output.accept(CItems.TITANIUM_GEAR.getFirst().value());

            // Crusher
//            output.accept(CBlocks.CRUSHER);
//            output.accept(CBlocks.ADVANCED_CRUSHER);

            // Shield
//            output.accept(CBlocks.COSMIC_SHIELD);

            // Extra
//            output.accept(CItems.FALLEN_METEOR_LOCATOR);

            // Spawn Eggs
//            output.accept(CItems.TITANIUM_GOLEM_SPAWN_EGG);
          })
          .icon(() -> new ItemStack(CItems.TITANIUM_SHOVEL.getFirst()))
          .title(Component.translatable(COSMICORE_TAB_TITLE))
          .build()
  );

  public static void init() {
    Cosmicore.LOGGER.info("Registering Creative Tab");
  }
}
