package net.nova.cosmicore.item;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Util;
import net.minecraft.world.item.Item;
import net.nova.cosmicore.Cosmicore;

import java.util.List;

import static net.nova.cosmicore.Cosmicore.rl;

public class TitaniumSmithingTemplate extends BaseSmithingTemplate {
  public static final Component TITANIUM_UPGRADE_APPLIES_TO = Component.translatable(
      Util.makeDescriptionId("item", rl("smithing_template.titanium_upgrade.applies_to"))
  ).withStyle(DESCRIPTION_FORMAT);
  public static final Component TITANIUM_UPGRADE_INGREDIENTS = Component.translatable(
      Util.makeDescriptionId("item", rl("smithing_template.titanium_upgrade.ingredients"))
  ).withStyle(DESCRIPTION_FORMAT);
  public static final Component TITANIUM_UPGRADE_BASE_SLOT_DESCRIPTION = Component.translatable(
      Util.makeDescriptionId("item", rl("smithing_template.titanium_upgrade.base_slot_description"))
  );
  public static final Component TITANIUM_UPGRADE_ADDITIONS_SLOT_DESCRIPTION = Component.translatable(
      Util.makeDescriptionId("item", rl("smithing_template.titanium_upgrade.additions_slot_description"))
  );

  public static final Identifier EMPTY_SLOT_INGOT = Identifier.withDefaultNamespace("container/slot/ingot");
  public static final Identifier EMPTY_SLOT_CROSSBOW = Cosmicore.rl("container/slot/crossbow");

  public TitaniumSmithingTemplate(Component appliesTo, Component ingredients, Component baseSlotDescription, Component additionsSlotDescription, Properties properties) {
    super(appliesTo, ingredients, baseSlotDescription, additionsSlotDescription, createBasicUpgradeIconList(List.of(EMPTY_SLOT_CROSSBOW)), createBasicUpgradeMaterialList(List.of(EMPTY_SLOT_INGOT)), properties);
  }

  public static TitaniumSmithingTemplate createTitaniumUpgradeTemplate(Item.Properties properties) {
    return new TitaniumSmithingTemplate(
        TITANIUM_UPGRADE_APPLIES_TO,
        TITANIUM_UPGRADE_INGREDIENTS,
        TITANIUM_UPGRADE_BASE_SLOT_DESCRIPTION,
        TITANIUM_UPGRADE_ADDITIONS_SLOT_DESCRIPTION,
        properties
    );
  }
}
