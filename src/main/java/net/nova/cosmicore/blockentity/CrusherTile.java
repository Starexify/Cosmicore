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
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.nova.cosmicore.gui.crusher.CrusherMenu;
import net.nova.cosmicore.init.CBlockEntities;
import net.nova.cosmicore.init.CItems;
import net.nova.cosmicore.init.CRecipeTypes;
import net.nova.cosmicore.recipe.crusher.CrushingRecipe;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class CrusherTile extends BaseContainerBlockEntity {
    protected NonNullList<ItemStack> inventory = NonNullList.withSize(8, ItemStack.EMPTY);

    private static final int INGREDIENT_SLOT = 0;
    private static final int FUEL_SLOT = 1;
    private static final int RESULT_SLOT_START = 2;
    private static final int RESULT_SLOT_END = 7;

    private int ignisCharge;
    private int ignisPower;
    private int crushingProgress;
    private int maxCrushingProgress = 5; // TODO: Change ticks when done

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
        super(CBlockEntities.CRUSHER_TILE.get(), pPos, pBlockState);
    }

    // Crafting stuff
    private boolean isCharged() {
        return this.ignisCharge > 0;
    }

    // Logic for GUI
    public void serverTick(Level pLevel, BlockPos pPos, BlockState pState) {
        hasIgnis();
        if (isCharged()) {
            if (hasRecipe()) {
                this.crushingProgress++;
                setChanged(pLevel, pPos, pState);

                if (hasProgressFinished()) {
                    craftItem();
                    resetProgress();
                    this.ignisCharge--;
                }
            } else {
                resetProgress();
            }
        } else {
            resetProgress();
        }
    }

    public boolean isFuel(ItemStack item) {
        return item.is(CItems.INFERNIUM_CRYSTAL);
    }

    private void hasIgnis() { // TODO: Add tags?
        boolean hasFuel = isFuel(this.inventory.get(FUEL_SLOT).getItem().getDefaultInstance());
        if (hasFuel && !isCharged()) {
            int chargeTime = 11;

            this.ignisCharge = chargeTime;
            this.ignisPower = chargeTime;
            this.inventory.get(FUEL_SLOT).setCount(this.inventory.get(FUEL_SLOT).getCount() - 1);
        }
    }

    private boolean hasRecipe() {
        Optional<RecipeHolder<CrushingRecipe>> recipe = getCurrentRecipe(this.inventory.getFirst());
        if (recipe.isEmpty()) return false;

        ItemStack result = recipe.get().value().getResultItem(null);

        return canInsertAmountIntoOutputSlot(result.getCount()) && canInsertItemInOutputSlot(result.getItem());
    }

    private void craftItem() {
        Optional<RecipeHolder<CrushingRecipe>> recipe = getCurrentRecipe(this.inventory.getFirst());
        ItemStack result = recipe.get().value().getResultItem(null);
        this.inventory.getFirst().setCount(this.inventory.getFirst().getCount() - 1);

        // Find the first available result slot
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

    private Optional<RecipeHolder<CrushingRecipe>> getCurrentRecipe(ItemStack itemStack) {
        return this.level.getRecipeManager().getRecipeFor(CRecipeTypes.CRUSHING_RECIPE_TYPE.get(), new SingleRecipeInput(itemStack), level);
    }

    // Checking for recipes
    private boolean canInsertItemInOutputSlot(Item item) {
        for (int i = RESULT_SLOT_START; i <= RESULT_SLOT_END; i++) {
            ItemStack slotStack = this.inventory.get(i);
            if (slotStack.isEmpty() || (slotStack.is(item) && slotStack.getCount() < slotStack.getMaxStackSize())) {
                return true;
            }
        }
        return false;
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
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

    // Recipe progress Stuff
    private boolean hasProgressFinished() {
        return crushingProgress >= maxCrushingProgress;
    }

    private void resetProgress() {
        crushingProgress = 0;
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
