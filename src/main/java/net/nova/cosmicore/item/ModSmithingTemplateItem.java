package net.nova.cosmicore.item;

import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.item.SmithingTemplateItem;

import java.util.List;

import static net.nova.cosmicore.Cosmicore.rl;

public class ModSmithingTemplateItem extends SmithingTemplateItem {
    private static final ChatFormatting TITLE_FORMAT = ChatFormatting.GRAY;
    private static final ChatFormatting DESCRIPTION_FORMAT = ChatFormatting.BLUE;

    private static final Component TITANIUM_UPGRADE = Component.translatable(
                    Util.makeDescriptionId("upgrade", rl("titanium_upgrade"))
            )
            .withStyle(TITLE_FORMAT);
    private static final Component TITANIUM_UPGRADE_APPLIES_TO = Component.translatable(
                    Util.makeDescriptionId("item", rl("smithing_template.titanium_upgrade.applies_to"))
            )
            .withStyle(DESCRIPTION_FORMAT);
    private static final Component TITANIUM_UPGRADE_INGREDIENTS = Component.translatable(
                    Util.makeDescriptionId("item", rl("smithing_template.titanium_upgrade.ingredients"))
            )
            .withStyle(DESCRIPTION_FORMAT);
    private static final Component TITANIUM_UPGRADE_BASE_SLOT_DESCRIPTION = Component.translatable(
            Util.makeDescriptionId("item", rl("smithing_template.titanium_upgrade.base_slot_description"))
    );
    private static final Component TITANIUM_UPGRADE_ADDITIONS_SLOT_DESCRIPTION = Component.translatable(
            Util.makeDescriptionId("item", rl("smithing_template.titanium_upgrade.additions_slot_description"))
    );
    private static final ResourceLocation EMPTY_SLOT_HELMET = ResourceLocation.withDefaultNamespace("item/empty_armor_slot_helmet");
    private static final ResourceLocation EMPTY_SLOT_CHESTPLATE = ResourceLocation.withDefaultNamespace("item/empty_armor_slot_chestplate");
    private static final ResourceLocation EMPTY_SLOT_LEGGINGS = ResourceLocation.withDefaultNamespace("item/empty_armor_slot_leggings");
    private static final ResourceLocation EMPTY_SLOT_BOOTS = ResourceLocation.withDefaultNamespace("item/empty_armor_slot_boots");
    private static final ResourceLocation EMPTY_SLOT_HOE = ResourceLocation.withDefaultNamespace("item/empty_slot_hoe");
    private static final ResourceLocation EMPTY_SLOT_AXE = ResourceLocation.withDefaultNamespace("item/empty_slot_axe");
    private static final ResourceLocation EMPTY_SLOT_SWORD = ResourceLocation.withDefaultNamespace("item/empty_slot_sword");
    private static final ResourceLocation EMPTY_SLOT_SHOVEL = ResourceLocation.withDefaultNamespace("item/empty_slot_shovel");
    private static final ResourceLocation EMPTY_SLOT_PICKAXE = ResourceLocation.withDefaultNamespace("item/empty_slot_pickaxe");
    private static final ResourceLocation EMPTY_SLOT_INGOT = ResourceLocation.withDefaultNamespace("item/empty_slot_ingot");

    public ModSmithingTemplateItem(Component pAppliesTo, Component pIngredients, Component pUpgradeDescription, Component pBaseSlotDescription, Component pAdditionsSlotDescription, List<ResourceLocation> pBaseSlotEmptyIcons, List<ResourceLocation> pAdditionalSlotEmptyIcons, FeatureFlag... pRequiredFeatures) {
        super(pAppliesTo, pIngredients, pUpgradeDescription, pBaseSlotDescription, pAdditionsSlotDescription, pBaseSlotEmptyIcons, pAdditionalSlotEmptyIcons, pRequiredFeatures);
    }

    public static ModSmithingTemplateItem createTitaniumUpgradeTemplate() {
        return new ModSmithingTemplateItem(
                TITANIUM_UPGRADE_APPLIES_TO,
                TITANIUM_UPGRADE_INGREDIENTS,
                TITANIUM_UPGRADE,
                TITANIUM_UPGRADE_BASE_SLOT_DESCRIPTION,
                TITANIUM_UPGRADE_ADDITIONS_SLOT_DESCRIPTION,
                createTitaniumUpgradeIconList(),
                createTitaniumUpgradeMaterialList()
        );
    }

    private static List<ResourceLocation> createTitaniumUpgradeIconList() {
        return List.of(
                EMPTY_SLOT_HELMET,
                EMPTY_SLOT_SWORD,
                EMPTY_SLOT_CHESTPLATE,
                EMPTY_SLOT_PICKAXE,
                EMPTY_SLOT_LEGGINGS,
                EMPTY_SLOT_AXE,
                EMPTY_SLOT_BOOTS,
                EMPTY_SLOT_HOE,
                EMPTY_SLOT_SHOVEL
        );
    }

    private static List<ResourceLocation> createTitaniumUpgradeMaterialList() {
        return List.of(EMPTY_SLOT_INGOT);
    }

}
