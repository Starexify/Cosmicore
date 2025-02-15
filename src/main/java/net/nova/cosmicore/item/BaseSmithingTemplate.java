package net.nova.cosmicore.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.SmithingTemplateItem;
import net.nova.cosmicore.Cosmicore;

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
            ResourceLocation.withDefaultNamespace("container/slot/pickaxe"),
            Cosmicore.rl("container/slot/horse_armor")
    ));

    protected static final List<ResourceLocation> MATERIAL_SLOTS = new ArrayList<>(Arrays.asList());

    public BaseSmithingTemplate(Component appliesTo, Component ingredients, Component baseSlotDescription, Component additionsSlotDescription, List<ResourceLocation> baseSlotEmptyIcons, List<ResourceLocation> additionalSlotEmptyIcons, Properties properties) {
        super(appliesTo, ingredients, baseSlotDescription, additionsSlotDescription, baseSlotEmptyIcons, additionalSlotEmptyIcons, properties);
    }

    protected static List<ResourceLocation> createBasicUpgradeIconList(List<ResourceLocation> additionalSlots) {
        List<ResourceLocation> combined = new ArrayList<>(EMPTY_SLOTS);
        combined.addAll(additionalSlots);
        return combined;
    }

    protected static List<ResourceLocation> createBasicUpgradeMaterialList(List<ResourceLocation> additionalSlots) {
        List<ResourceLocation> combined = new ArrayList<>(MATERIAL_SLOTS);
        combined.addAll(additionalSlots);
        return combined;
    }
}