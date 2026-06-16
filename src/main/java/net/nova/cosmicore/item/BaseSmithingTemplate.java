package net.nova.cosmicore.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.SmithingTemplateItem;
import net.nova.cosmicore.Cosmicore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BaseSmithingTemplate extends SmithingTemplateItem {
  public static final ChatFormatting DESCRIPTION_FORMAT = ChatFormatting.BLUE;

  protected static final List<Identifier> EMPTY_SLOTS = new ArrayList<>(Arrays.asList(
      Identifier.withDefaultNamespace("container/slot/helmet"),
      Identifier.withDefaultNamespace("container/slot/chestplate"),
      Identifier.withDefaultNamespace("container/slot/leggings"),
      Identifier.withDefaultNamespace("container/slot/boots"),
      Identifier.withDefaultNamespace("container/slot/hoe"),
      Identifier.withDefaultNamespace("container/slot/axe"),
      Identifier.withDefaultNamespace("container/slot/sword"),
      Identifier.withDefaultNamespace("container/slot/shovel"),
      Identifier.withDefaultNamespace("container/slot/pickaxe"),
      Cosmicore.rl("container/slot/horse_armor")
  ));

  protected static final List<Identifier> MATERIAL_SLOTS = new ArrayList<>(Arrays.asList());

  public BaseSmithingTemplate(Component appliesTo, Component ingredients, Component baseSlotDescription, Component additionsSlotDescription, List<Identifier> baseSlotEmptyIcons, List<Identifier> additionalSlotEmptyIcons, Properties properties) {
    super(appliesTo, ingredients, baseSlotDescription, additionsSlotDescription, baseSlotEmptyIcons, additionalSlotEmptyIcons, properties);
  }

  protected static List<Identifier> createBasicUpgradeIconList(List<Identifier> additionalSlots) {
    List<Identifier> combined = new ArrayList<>(EMPTY_SLOTS);
    combined.addAll(additionalSlots);
    return combined;
  }

  protected static List<Identifier> createBasicUpgradeMaterialList(List<Identifier> additionalSlots) {
    List<Identifier> combined = new ArrayList<>(MATERIAL_SLOTS);
    combined.addAll(additionalSlots);
    return combined;
  }
}