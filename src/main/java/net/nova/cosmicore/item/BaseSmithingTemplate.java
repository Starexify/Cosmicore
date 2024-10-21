package net.nova.cosmicore.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.item.SmithingTemplateItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BaseSmithingTemplate extends SmithingTemplateItem {
    public static final ChatFormatting TITLE_FORMAT = ChatFormatting.GRAY;
    public static final ChatFormatting DESCRIPTION_FORMAT = ChatFormatting.BLUE;

    protected static final List<ResourceLocation> EMPTY_SLOTS = new ArrayList<>(Arrays.asList(
            ResourceLocation.withDefaultNamespace("item/empty_armor_slot_helmet"),
            ResourceLocation.withDefaultNamespace("item/empty_armor_slot_chestplate"),
            ResourceLocation.withDefaultNamespace("item/empty_armor_slot_leggings"),
            ResourceLocation.withDefaultNamespace("item/empty_armor_slot_boots"),
            ResourceLocation.withDefaultNamespace("item/empty_slot_hoe"),
            ResourceLocation.withDefaultNamespace("item/empty_slot_axe"),
            ResourceLocation.withDefaultNamespace("item/empty_slot_sword"),
            ResourceLocation.withDefaultNamespace("item/empty_slot_shovel"),
            ResourceLocation.withDefaultNamespace("item/empty_slot_pickaxe")
    ));

    protected static final List<ResourceLocation> MATERIAL_SLOTS = new ArrayList<>(Arrays.asList(
            ResourceLocation.withDefaultNamespace("item/empty_slot_ingot")
    ));

    public BaseSmithingTemplate(Component appliesTo, Component ingredients, Component upgradeDescription, Component baseSlotDescription, Component additionsSlotDescription, List<ResourceLocation> baseSlotEmptyIcons, List<ResourceLocation> additionalSlotEmptyIcons, FeatureFlag... requiredFeatures) {
        super(appliesTo, ingredients, upgradeDescription, baseSlotDescription, additionsSlotDescription, baseSlotEmptyIcons, additionalSlotEmptyIcons, requiredFeatures);
    }

    public BaseSmithingTemplate(Component appliesTo, Component ingredients, Component upgradeDescription, Component baseSlotDescription, Component additionsSlotDescription, FeatureFlag... requiredFeatures) {
        super(appliesTo, ingredients, upgradeDescription, baseSlotDescription, additionsSlotDescription, createBasicUpgradeIconList(), createBasicUpgradeMaterialList(), requiredFeatures);
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
