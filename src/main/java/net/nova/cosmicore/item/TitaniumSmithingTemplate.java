package net.nova.cosmicore.item;

import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

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

    public static final ResourceLocation EMPTY_SLOT_INGOT = ResourceLocation.withDefaultNamespace("container/slot/ingot");

    static {
        addMaterialSlot(EMPTY_SLOT_INGOT);
    }

    public TitaniumSmithingTemplate(Component appliesTo, Component ingredients, Component baseSlotDescription, Component additionsSlotDescription, Properties properties) {
        super(appliesTo, ingredients, baseSlotDescription, additionsSlotDescription, properties);
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
