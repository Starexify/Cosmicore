package net.nova.cosmicore.gui;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;
import net.nova.cosmicore.blockentity.CosmicShieldTile;

public class CosmicShieldItemStackHandler extends ItemStacksResourceHandler {
  public final CosmicShieldTile cosmicShieldTile;

  public CosmicShieldItemStackHandler(CosmicShieldTile cosmicShieldTile) {
    super(1);
    this.cosmicShieldTile = cosmicShieldTile;
  }

  @Override
  protected void onContentsChanged(int index, ItemStack previousContents) {
    super.onContentsChanged(index, previousContents);
    if (cosmicShieldTile != null && (cosmicShieldTile.getLevel() == null || !cosmicShieldTile.getLevel().isClientSide())) {
      cosmicShieldTile.setChanged();
      cosmicShieldTile.getLevel().sendBlockUpdated(cosmicShieldTile.getBlockPos(), cosmicShieldTile.getBlockState(), cosmicShieldTile.getBlockState(), Block.UPDATE_ALL);
    }
  }

  public NonNullList<ItemStack> getItems() {
    return stacks;
  }
}
