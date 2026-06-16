package net.nova.cosmicore.recipe;

import net.minecraft.world.item.ItemStackTemplate;

public class WeightedResult {
  public final ItemStackTemplate item;
  public final float chance;

  public WeightedResult(ItemStackTemplate item, float chance) {
    this.item = item;
    this.chance = chance;
  }
}
