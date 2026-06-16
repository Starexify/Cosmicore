package net.nova.cosmicore.gui;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.nova.cosmicore.blockentity.AbstractCrusherTile;

public class CrusherItemStackHandler extends ItemStackHandler {
    public final AbstractCrusherTile crusherTile;

    public CrusherItemStackHandler(AbstractCrusherTile crusherTile, int size) {
        super(size);
        this.crusherTile = crusherTile;
    }

    @Override
    protected void onContentsChanged(int slot) {
        super.onContentsChanged(slot);
        if (crusherTile != null && (crusherTile.getLevel() == null || !crusherTile.getLevel().isClientSide())) {
                crusherTile.setChanged();
            crusherTile.getLevel().sendBlockUpdated(crusherTile.getBlockPos(), crusherTile.getBlockState(), crusherTile.getBlockState(), Block.UPDATE_ALL);
        }
    }

    public void removeStackFromSlot(int slot, int amount) {
        validateSlotIndex(slot);
        getStackInSlot(slot).shrink(amount);
    }

    public void removeStackFromSlot(int slot) {
        removeStackFromSlot(slot, 1);
    }

    public NonNullList<ItemStack> getItems() {
        return stacks;
    }

    public ItemStack getFirst() {
        validateSlotIndex(0);
        return getStackInSlot(0);
    }
}
