package net.nova.cosmicore.item;

import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlag;

import java.util.List;

import static net.nova.cosmicore.Cosmicore.rl;

public class TitaniumSmithingTemplate extends BaseSmithingTemplate {
    public static final Component TITANIUM_UPGRADE = Component.translatable(
            Util.makeDescriptionId("upgrade", rl("titanium_upgrade"))
    ).withStyle(TITLE_FORMAT);
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

    public TitaniumSmithingTemplate(Component pAppliesTo, Component pIngredients, Component pUpgradeDescription, Component pBaseSlotDescription, Component pAdditionsSlotDescription, FeatureFlag... pRequiredFeatures) {
        super(pAppliesTo, pIngredients, pUpgradeDescription, pBaseSlotDescription, pAdditionsSlotDescription, pRequiredFeatures);
    }

    public static TitaniumSmithingTemplate createTitaniumUpgradeTemplate() {
        return new TitaniumSmithingTemplate(
                TITANIUM_UPGRADE_APPLIES_TO,
                TITANIUM_UPGRADE_INGREDIENTS,
                TITANIUM_UPGRADE,
                TITANIUM_UPGRADE_BASE_SLOT_DESCRIPTION,
                TITANIUM_UPGRADE_ADDITIONS_SLOT_DESCRIPTION
        );
    }
}
