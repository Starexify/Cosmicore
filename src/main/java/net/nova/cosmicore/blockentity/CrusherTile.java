package net.nova.cosmicore.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.nova.cosmicore.gui.crusher.CrusherMenu;
import net.nova.cosmicore.init.ModBlockEntities;
import net.nova.cosmicore.init.ModBlocks;
import net.nova.cosmicore.init.ModItems;
import org.jetbrains.annotations.Nullable;

public class CrusherTile extends BaseContainerBlockEntity {
    protected NonNullList<ItemStack> inventory = NonNullList.withSize(8, ItemStack.EMPTY);

    private static final int INGREDIENT_SLOT = 0;
    private static final int FUEL_SLOT = 2;
    private static final int RESULT_SLOT = 3; // TODO: 3-7 not only 3

    private int ignisCharge;
    private int ignisPower;
    private int crushingProgress;
    private int maxCrushingProgress = 60; // TODO: Change ticks when done

    protected final ContainerData dataAccess = new ContainerData() {
        @Override
        public int get(int pIndex) {
            return switch (pIndex) {
                case 0 -> CrusherTile.this.ignisCharge;
                case 1 -> CrusherTile.this.ignisPower;
                case 2 -> CrusherTile.this.crushingProgress;
                case 3 -> CrusherTile.this.maxCrushingProgress;
                default -> 0;
            };
        }

        @Override
        public void set(int pIndex, int pValue) {
            switch (pIndex) {
                case 0 -> CrusherTile.this.ignisCharge = pValue;
                case 1 -> CrusherTile.this.ignisPower = pValue;
                case 2 -> CrusherTile.this.crushingProgress = pValue;
                case 3 -> CrusherTile.this.maxCrushingProgress = pValue;
            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    };

    public CrusherTile(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.CRUSHER_TILE.get(), pPos, pBlockState);
    }

    // Crafting stuff
    private boolean isCharged() {
        return this.ignisCharge > 0;
    }

    // Logic for GUI
    public void serverTick(Level pLevel, BlockPos pPos, BlockState pState) {
        hasIgnis();
        if (isCharged()) {
            ignisPower--;
            if (hasRecipe()) {
                crushingProgress++;
                setChanged(pLevel, pPos, pState);

                if (hasProgressFinished()) {
                    craftItem();
                    resetProgress();
                }
            } else {
                resetProgress();
            }
        } else {
            resetProgress();
        }
    }

    private boolean hasIgnis() { // TODO: Change fuel or add tags?
        boolean hasFuel = this.inventory.get(FUEL_SLOT).getItem() == ModItems.INFERNIUM_CRYSTAL.get();
        if (hasRecipe() && hasFuel && !isCharged()) {
            int chargeTime = 400;

            this.ignisCharge = chargeTime;
            this.ignisPower = chargeTime;
            this.inventory.get(FUEL_SLOT).setCount(this.inventory.get(FUEL_SLOT).getCount() - 1);
        } else {
            hasFuel = false;
        }
        return hasFuel;
    }

    private void craftItem() {
        ItemStack result = new ItemStack(Blocks.COBBLESTONE, 3);
        this.inventory.get(INGREDIENT_SLOT).setCount(this.inventory.get(INGREDIENT_SLOT).getCount() - 1);

        this.inventory.set(RESULT_SLOT, new ItemStack(result.getItem(),
                this.inventory.get(RESULT_SLOT).getCount() + result.getCount()));
    }

    private boolean hasRecipe() {
        boolean hasCraftingItem = this.inventory.get(INGREDIENT_SLOT).getItem() == ModBlocks.ACHONDRITE.asItem();
        ItemStack result = new ItemStack(Blocks.COBBLESTONE);

        return hasCraftingItem && canInsertAmountIntoOutputSlot(result.getCount()) && canInsertItemInOutputSlot(result.getItem());
    }

    // Checking for recipes
    private boolean canInsertItemInOutputSlot(Item item) {
        return this.inventory.get(RESULT_SLOT).isEmpty() || this.inventory.get(RESULT_SLOT).is(item);
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        return this.inventory.get(RESULT_SLOT).getCount() + count <= this.inventory.get(RESULT_SLOT).getMaxStackSize();
    }

    // Recipe progress Stuff
    private boolean hasProgressFinished() {
        return crushingProgress >= maxCrushingProgress;
    }

    private void resetProgress() {
        crushingProgress = 0;
    }

    // Place or take item out from slots (Hoppers or other mods ig)
    @Override
    public boolean canPlaceItem(int pSlot, ItemStack pStack) {
        if (pSlot == RESULT_SLOT) {
            return false;
        } else if (pSlot != FUEL_SLOT) {
            return true;
        } else {
            ItemStack itemstack = this.inventory.get(FUEL_SLOT);
            return pStack.is(ModItems.INFERNIUM_CRYSTAL) && !itemstack.is(Items.BUCKET);
        }
    }

    @Override
    public boolean canTakeItem(Container pTarget, int pSlot, ItemStack pStack) {
        if (pSlot == RESULT_SLOT) {
            return true;
        } else if (pSlot == FUEL_SLOT) {
            ItemStack itemstack = this.inventory.get(FUEL_SLOT);
            return pStack.is(Items.BUCKET) && !itemstack.is(Items.LAVA_BUCKET);
        }
        return false;
    }

    // GUI title
    @Override
    protected Component getDefaultName() {
        return Component.translatable("block.cosmicore.crusher");
    }

    // Block nbt data
    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.loadAdditional(pTag, pRegistries);
        ContainerHelper.loadAllItems(pTag, inventory, pRegistries);
        ignisCharge = pTag.getInt("IgnisCharge");
        ignisPower = pTag.getInt("IgnisPower");
        crushingProgress = pTag.getInt("CrushingProgress");
    }

    @Override
    protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.saveAdditional(pTag, pRegistries);
        ContainerHelper.saveAllItems(pTag, inventory, pRegistries);
        pTag.putInt("IgnisCharge", ignisCharge);
        pTag.putInt("IgnisPower", ignisPower);
        pTag.putInt("CrushingProgress", crushingProgress);
    }

    // Menu
    @Override
    protected AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory) {
        return new CrusherMenu(pContainerId, pInventory, this, this.dataAccess);
    }

    // Stuff idk
    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider pRegistries) {
        return saveWithoutMetadata(pRegistries);
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.inventory;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> pItems) {
        this.inventory = pItems;
    }


    @Override
    public int getContainerSize() {
        return this.inventory.size();
    }
}
