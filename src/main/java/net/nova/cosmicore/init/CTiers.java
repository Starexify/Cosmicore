package net.nova.cosmicore.init;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

public class CTiers {
    public static final Tier TITANIUM = new SimpleTier(
            BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1031, 9.0F, 3.5F, 15,
            () -> Ingredient.of(CItems.TITANIUM_INGOT)
    );

    public static final Tier LONSDALEITE = new SimpleTier(
            BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 2467, 10.0F, 4.7F, 18,
            () -> Ingredient.of(CItems.LONSDALEITE)
    );
}

