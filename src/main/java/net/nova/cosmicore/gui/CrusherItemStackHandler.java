package net.nova.cosmicore.gui;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.nova.cosmicore.blockentity.CrusherTile;

public class CrusherItemStackHandler extends ItemStackHandler {
    public final CrusherTile crusherTile;

    public CrusherItemStackHandler(CrusherTile crusherTile) {
        super(8);
        this.crusherTile = crusherTile;
    }

    @Override
    protected void onContentsChanged(int slot) {
        super.onContentsChanged(slot);
        if (crusherTile != null && (crusherTile.getLevel() == null || !crusherTile.getLevel().isClientSide)) {
            crusherTile.setChanged();
        }
        //this.crusherTile.markInputInventoryChanged();
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

    public void clear() {
        stacks.clear();
    }
}
