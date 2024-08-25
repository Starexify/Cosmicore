package net.nova.cosmicore.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.nova.cosmicore.init.CItems;
import org.jetbrains.annotations.Nullable;

public class BaseCrusherTile extends BaseContainerBlockEntity {
    protected NonNullList<ItemStack> inventory;

    public int ignisCharge;
    public int ignisPower;
    public int crushingProgress;
    public int maxCrushingProgress = 400;

    public int FUEL_SLOT;
    public int RESULT_SLOT_START;
    public int RESULT_SLOT_END;

    protected BaseCrusherTile(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState) {
        super(pType, pPos, pBlockState);
    }

    // Crafting Stuff

    // Logic for GUI
    public void serverTick(Level level, BlockPos pos, BlockState state) {
        hasIgnis();
        if (isCharged() && hasRecipe()) {
            this.crushingProgress++;
            setChanged(level, pos, state);

            if (hasProgressFinished()) {
                craftItem();
                resetProgress();
                this.ignisCharge--;
            }
        } else {
            resetProgress();
        }
    }

    public boolean isCharged() {
        return this.ignisCharge > 0;
    }

    public boolean isFuel(ItemStack item) {
        return item.is(CItems.INFERNIUM_CRYSTAL);
    }

    public void hasIgnis() {
    }

    public boolean hasRecipe() {
        return false;
    }

    public void craftItem() {
    }

    public void insertOrMergeResult(ItemStack result) {
        for (int i = RESULT_SLOT_START; i <= RESULT_SLOT_END; i++) {
            ItemStack slotStack = this.inventory.get(i);
            if (slotStack.isEmpty()) {
                this.inventory.set(i, result.copy());
                break;
            } else if (ItemStack.isSameItem(slotStack, result) && slotStack.getCount() + result.getCount() <= slotStack.getMaxStackSize()) {
                slotStack.grow(result.getCount());
                break;
            }
        }
    }

    // Recipe progress Stuff
    public boolean hasProgressFinished() {
        return crushingProgress >= maxCrushingProgress;
    }

    public void resetProgress() {
        crushingProgress = 0;
    }

    // Checking for recipes
    public boolean canInsertItemInOutputSlot(Item item) {
        for (int i = RESULT_SLOT_START; i <= RESULT_SLOT_END; i++) {
            ItemStack slotStack = this.inventory.get(i);
            if (slotStack.isEmpty() || (slotStack.is(item) && slotStack.getCount() < slotStack.getMaxStackSize())) {
                return true;
            }
        }
        return false;
    }

    public boolean canInsertAmountIntoOutputSlot(int count) {
        int availableSpace = 0;
        for (int i = RESULT_SLOT_START; i <= RESULT_SLOT_END; i++) {
            ItemStack slotStack = this.inventory.get(i);
            if (slotStack.isEmpty()) {
                availableSpace += slotStack.getMaxStackSize();
            } else {
                availableSpace += slotStack.getMaxStackSize() - slotStack.getCount();
            }
            if (availableSpace >= count) {
                return true;
            }
        }
        return false;
    }

    // Place or take item out from slots (Hoppers or other mods ig)
    @Override
    public boolean canPlaceItem(int slot, ItemStack item) {
        if (slot >= RESULT_SLOT_START && slot <= RESULT_SLOT_END) {
            return false;
        } else if (slot == FUEL_SLOT) {
            return isFuel(item);
        }
        return !isFuel(item);
    }

    @Override
    public boolean canTakeItem(Container pTarget, int pSlot, ItemStack pStack) {
        if (pSlot >= RESULT_SLOT_START && pSlot <= RESULT_SLOT_END) {
            return true;
        }
        return false;
    }

    @Override
    protected Component getDefaultName() {
        return null;
    }

    @Override
    protected AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory) {
        return null;
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

    // Stuff idk
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

    // Updates
    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider pRegistries) {
        return saveWithoutMetadata(pRegistries);
    }
}
