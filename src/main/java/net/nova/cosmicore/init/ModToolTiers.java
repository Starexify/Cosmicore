package net.nova.cosmicore.init;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

public class ModToolTiers {
    public static final Tier TITANIUM = new SimpleTier(
            BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1031, 9.0F, 3.5F, 15,
            () -> Ingredient.of(ModItems.TITANIUM_INGOT)
    );
}

