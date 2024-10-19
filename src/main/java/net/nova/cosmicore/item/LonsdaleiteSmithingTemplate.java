package net.nova.cosmicore.item;

import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlag;

import java.util.List;

import static net.nova.cosmicore.Cosmicore.rl;

public class LonsdaleiteSmithingTemplate extends BaseSmithingTemplate{
    public static final Component LONSDALEITE_UPGRADE = Component.translatable(
            Util.makeDescriptionId("upgrade", rl("lonsdaleite_upgrade"))
    ).withStyle(TITLE_FORMAT);
    public static final Component LONSDALEITE_UPGRADE_APPLIES_TO = Component.translatable(
            Util.makeDescriptionId("item", rl("smithing_template.lonsdaleite_upgrade.applies_to"))
    ).withStyle(DESCRIPTION_FORMAT);
    public static final Component LONSDALEITE_UPGRADE_INGREDIENTS = Component.translatable(
            Util.makeDescriptionId("item", rl("smithing_template.lonsdaleite_upgrade.ingredients"))
    ).withStyle(DESCRIPTION_FORMAT);
    public static final Component LONSDALEITE_UPGRADE_BASE_SLOT_DESCRIPTION = Component.translatable(
            Util.makeDescriptionId("item", rl("smithing_template.lonsdaleite_upgrade.base_slot_description"))
    );
    public static final Component LONSDALEITE_UPGRADE_ADDITIONS_SLOT_DESCRIPTION = Component.translatable(
            Util.makeDescriptionId("item", rl("smithing_template.lonsdaleite_upgrade.additions_slot_description"))
    );

    public LonsdaleiteSmithingTemplate(Component pAppliesTo, Component pIngredients, Component pUpgradeDescription, Component pBaseSlotDescription, Component pAdditionsSlotDescription, FeatureFlag... pRequiredFeatures) {
        super(pAppliesTo, pIngredients, pUpgradeDescription, pBaseSlotDescription, pAdditionsSlotDescription, pRequiredFeatures);
    }

    public static LonsdaleiteSmithingTemplate createLonsdaleiteUpgradeTemplate() {
        return new LonsdaleiteSmithingTemplate(
                LONSDALEITE_UPGRADE_APPLIES_TO,
                LONSDALEITE_UPGRADE_INGREDIENTS,
                LONSDALEITE_UPGRADE,
                LONSDALEITE_UPGRADE_BASE_SLOT_DESCRIPTION,
                LONSDALEITE_UPGRADE_ADDITIONS_SLOT_DESCRIPTION
        );
    }
}
