package net.nova.cosmicore.gui;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;
import net.nova.cosmicore.blockentity.AbstractCrusherTile;

public class CrusherItemStackHandler extends ItemStacksResourceHandler {
  public final AbstractCrusherTile crusherTile;

  public CrusherItemStackHandler(AbstractCrusherTile crusherTile, int size) {
    super(size);
    this.crusherTile = crusherTile;
  }

  @Override
  protected void onContentsChanged(int index, ItemStack previousContents) {
    super.onContentsChanged(index, previousContents);

    if (crusherTile != null && (crusherTile.getLevel() == null || !crusherTile.getLevel().isClientSide())) {
      crusherTile.setChanged();
      crusherTile.getLevel().sendBlockUpdated(crusherTile.getBlockPos(), crusherTile.getBlockState(), crusherTile.getBlockState(), Block.UPDATE_ALL);
    }
  }

  public void removeStackFromSlot(int slot) {
    set(slot, getResource(slot), 1);
  }

  public NonNullList<ItemStack> getItems() {
    return stacks;
  }
}
