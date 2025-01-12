package net.nova.cosmicore.gui;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.nova.cosmicore.blockentity.AbstractCrusherTile;
import net.nova.cosmicore.blockentity.CosmicShieldTile;

public class CosmicShieldItemStackHandler extends ItemStackHandler {
    public final CosmicShieldTile cosmicShieldTile;

    public CosmicShieldItemStackHandler(CosmicShieldTile cosmicShieldTile) {
        super(1);
        this.cosmicShieldTile = cosmicShieldTile;
    }

    @Override
    protected void onContentsChanged(int slot) {
        super.onContentsChanged(slot);
        if (cosmicShieldTile != null && (cosmicShieldTile.getLevel() == null || !cosmicShieldTile.getLevel().isClientSide)) {
            cosmicShieldTile.setChanged();
            cosmicShieldTile.getLevel().sendBlockUpdated(cosmicShieldTile.getBlockPos(), cosmicShieldTile.getBlockState(), cosmicShieldTile.getBlockState(), Block.UPDATE_ALL);
        }
    }

    public NonNullList<ItemStack> getItems() {
        return stacks;
    }
}
