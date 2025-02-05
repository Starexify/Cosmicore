package net.nova.cosmicore.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.SmithingTemplateItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BaseSmithingTemplate extends SmithingTemplateItem {
    public static final ChatFormatting DESCRIPTION_FORMAT = ChatFormatting.BLUE;

    protected static final List<ResourceLocation> EMPTY_SLOTS = new ArrayList<>(Arrays.asList(
            ResourceLocation.withDefaultNamespace("container/slot/helmet"),
            ResourceLocation.withDefaultNamespace("container/slot/chestplate"),
            ResourceLocation.withDefaultNamespace("container/slot/leggings"),
            ResourceLocation.withDefaultNamespace("container/slot/boots"),
            ResourceLocation.withDefaultNamespace("container/slot/hoe"),
            ResourceLocation.withDefaultNamespace("container/slot/axe"),
            ResourceLocation.withDefaultNamespace("container/slot/sword"),
            ResourceLocation.withDefaultNamespace("container/slot/shovel"),
            ResourceLocation.withDefaultNamespace("container/slot/pickaxe")
    ));

    protected static final List<ResourceLocation> MATERIAL_SLOTS = new ArrayList<>(Arrays.asList());

    public BaseSmithingTemplate(Component appliesTo, Component ingredients, Component baseSlotDescription, Component additionsSlotDescription, List<ResourceLocation> baseSlotEmptyIcons, List<ResourceLocation> additionalSlotEmptyIcons, Properties properties) {
        super(appliesTo, ingredients, baseSlotDescription, additionsSlotDescription, baseSlotEmptyIcons, additionalSlotEmptyIcons, properties);
    }

    public BaseSmithingTemplate(Component appliesTo, Component ingredients, Component baseSlotDescription, Component additionsSlotDescription, Properties properties) {
        super(appliesTo, ingredients, baseSlotDescription, additionsSlotDescription, createBasicUpgradeIconList(), createBasicUpgradeMaterialList(), properties);
    }

    protected static List<ResourceLocation> createBasicUpgradeIconList() {
        return new ArrayList<>(EMPTY_SLOTS);
    }

    protected static List<ResourceLocation> createBasicUpgradeMaterialList() {
        return new ArrayList<>(MATERIAL_SLOTS);
    }

    protected static void addEmptySlot(ResourceLocation slot) {
        EMPTY_SLOTS.add(slot);
    }

    protected static void addMaterialSlot(ResourceLocation slot) {
        MATERIAL_SLOTS.add(slot);
    }
}
