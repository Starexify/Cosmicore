package net.nova.cosmicore.recipe;

import net.minecraft.world.item.ItemStack;

public class WeightedResult {
    public final ItemStack item;
    public final float chance;

    public WeightedResult(ItemStack item, float chance) {
        this.item = item;
        this.chance = chance;
    }
}
