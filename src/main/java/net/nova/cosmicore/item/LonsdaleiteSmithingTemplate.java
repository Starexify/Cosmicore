package net.nova.cosmicore.item;

import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.nova.cosmicore.Cosmicore;

import static net.nova.cosmicore.Cosmicore.rl;

public class LonsdaleiteSmithingTemplate extends BaseSmithingTemplate{
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

    public static final ResourceLocation EMPTY_SLOT_LONSDALEITE = Cosmicore.rl("item/empty_slot_lonsdaleite");

    static {
        addMaterialSlot(EMPTY_SLOT_LONSDALEITE);
    }

    public LonsdaleiteSmithingTemplate(Component appliesTo, Component ingredients, Component baseSlotDescription, Component additionsSlotDescription, Properties properties) {
        super(appliesTo, ingredients, baseSlotDescription, additionsSlotDescription, properties);
    }

    public static LonsdaleiteSmithingTemplate createLonsdaleiteUpgradeTemplate(Item.Properties properties) {
        return new LonsdaleiteSmithingTemplate(
                LONSDALEITE_UPGRADE_APPLIES_TO,
                LONSDALEITE_UPGRADE_INGREDIENTS,
                LONSDALEITE_UPGRADE_BASE_SLOT_DESCRIPTION,
                LONSDALEITE_UPGRADE_ADDITIONS_SLOT_DESCRIPTION,
                properties
        );
    }
}
