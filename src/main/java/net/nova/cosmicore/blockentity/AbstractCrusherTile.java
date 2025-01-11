package net.nova.cosmicore.blockentity;

import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Container;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.nova.cosmicore.gui.CrusherItemStackHandler;
import net.nova.cosmicore.init.CBlocks;
import net.nova.cosmicore.init.CItems;
import net.nova.cosmicore.recipe.crusher.BaseCrushingRecipe;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public abstract class AbstractCrusherTile extends BlockEntity implements Container, MenuProvider {
    public CrusherItemStackHandler inventory;

    public int FUEL_SLOT;
    public int RESULT_SLOT_START;
    public int RESULT_SLOT_END;


    protected int ignisCharge;
    protected int ignisPower = 44;
    protected int crushingProgress;
    protected int maxCrushingProgress = 400;
    public boolean hasRecipe;

    public static final Map<Item, Integer> FUEL_MAP = Map.of(
            CItems.INFERNIUM_CRYSTAL.asItem(), 11,
            CBlocks.INFERNIUM_BLOCK.asItem(), 44
    );

    public final RecipeType<? extends BaseCrushingRecipe> recipeType;
    public final RecipeManager.CachedCheck<SingleRecipeInput, ? extends BaseCrushingRecipe> quickCheck;

    public abstract void hasIgnis();
    public abstract boolean hasRecipe();
    public abstract void craftItem();

    protected AbstractCrusherTile(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState, RecipeType<? extends BaseCrushingRecipe> recipeType) {
        super(pType, pPos, pBlockState);
        this.quickCheck = RecipeManager.createCheck(recipeType);
        this.recipeType = recipeType;
    }

    // Block Entity Stuff
    @Override
    public void clearContent() {
        inventory.clear();
    }

    @Override
    public int getContainerSize() {
        return inventory.getSlots();
    }

    // Crafting Stuff

    // Logic for GUI
    public void serverTick(ServerLevel level, BlockPos pos, BlockState state) {
        boolean changed = false;

        hasIgnis();
        if (isCharged() && hasRecipe()) {
            crushingProgress++;
            changed = true;

            if (hasProgressFinished()) {
                craftItem();
                resetProgress();
                ignisCharge--;
                changed = true;
            }
        } else {
            resetProgress();
        }

        if (changed) {
            setChanged(level, pos, state);
            level.sendBlockUpdated(pos, state, state, Block.UPDATE_ALL);
        }
    }

    public boolean isCharged() {
        return this.ignisCharge > 0;
    }

    public boolean isFuel(ItemStack item) {
        return FUEL_MAP.containsKey(item.getItem());
    }

    public void insertOrMergeResult(ItemStack result) {
        for (int i = RESULT_SLOT_START; i <= RESULT_SLOT_END; i++) {
            ItemStack slotStack = inventory.getStackInSlot(i);
            if (slotStack.isEmpty()) {
                inventory.setStackInSlot(i, result.copy());
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

    // Methods for checking Insertion
    public boolean canInsertItemInOutputSlot(Item item) {
        for (int i = RESULT_SLOT_START; i <= RESULT_SLOT_END; i++) {
            ItemStack slotStack = inventory.getStackInSlot(i);
            if (slotStack.isEmpty() || (slotStack.is(item) && slotStack.getCount() < slotStack.getMaxStackSize())) {
                return true;
            }
        }
        return false;
    }

    public boolean canInsertAmountIntoOutputSlot(int count) {
        int availableSpace = 0;
        for (int i = RESULT_SLOT_START; i <= RESULT_SLOT_END; i++) {
            ItemStack slotStack = inventory.getStackInSlot(i);
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

    // Block nbt data
    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        inventory.deserializeNBT(registries, tag.getCompound("Inventory"));
        ignisCharge = tag.getInt("IgnisCharge");
        ignisPower = tag.getInt("IgnisPower");
        crushingProgress = tag.getInt("CrushingProgress");
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.put("Inventory", inventory.serializeNBT(registries));
        tag.putInt("IgnisCharge", ignisCharge);
        tag.putInt("IgnisPower", ignisPower);
        tag.putInt("CrushingProgress", crushingProgress);
    }

    // Updates for Rendering
    @Override
    public void onDataPacket(Connection connection, ClientboundBlockEntityDataPacket packet, HolderLookup.Provider registries) {
        super.onDataPacket(connection, packet, registries);
        if (level != null && level.isClientSide) {
            CompoundTag tag = packet.getTag();
            handleUpdateTag(tag, level.registryAccess());
        }
    }

    @Override
    public void handleUpdateTag(CompoundTag tag, HolderLookup.Provider registries) {
        super.handleUpdateTag(tag, registries);
        inventory.deserializeNBT(registries, tag.getCompound("Inventory"));
        crushingProgress = tag.getInt("CrushingProgress");
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        CompoundTag tag = super.getUpdateTag(registries);
        tag.putInt("CrushingProgress", crushingProgress);
        return tag;
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    protected void applyImplicitComponents(DataComponentInput componentInput) {
        super.applyImplicitComponents(componentInput);
        componentInput.getOrDefault(DataComponents.CONTAINER, ItemContainerContents.EMPTY).copyInto(inventory.getItems());
    }

    @Override
    protected void collectImplicitComponents(DataComponentMap.Builder components) {
        super.collectImplicitComponents(components);
        components.set(DataComponents.CONTAINER, ItemContainerContents.fromItems(inventory.getItems()));
    }
}
