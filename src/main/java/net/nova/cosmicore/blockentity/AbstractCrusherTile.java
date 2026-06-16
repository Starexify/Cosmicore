package net.nova.cosmicore.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.IItemHandler;
import net.nova.cosmicore.gui.CrusherItemStackHandler;
import net.nova.cosmicore.init.CBlocks;
import net.nova.cosmicore.init.CItems;
import net.nova.cosmicore.recipe.crusher.BaseCrushingRecipe;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public abstract class AbstractCrusherTile extends BlockEntity implements MenuProvider {
    public CrusherItemStackHandler inventory;
    public IItemHandler top;
    public IItemHandler sides;
    public IItemHandler down;

    public int FUEL_SLOT;
    public int RESULT_SLOT_START;
    public int RESULT_SLOT_END;

    protected int ignisCharge;
    protected int ignisPower = 44;
    protected int crushingProgress;
    protected int maxCrushingProgress = 400;

    public boolean hasRecipe;

    public static final Map<Item, Integer> FUEL_MAP = Map.of(
            CItems.INFERNIUM_CRYSTAL.get(), 11,
            CBlocks.INFERNIUM_BLOCK.asItem(), 44
    );

    public final RecipeType<? extends BaseCrushingRecipe> recipeType;
    public final RecipeManager.CachedCheck<SingleRecipeInput, ? extends BaseCrushingRecipe> quickCheck;

    public abstract boolean hasRecipe();

    public abstract void craftItem();

    protected AbstractCrusherTile(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState, RecipeType<? extends BaseCrushingRecipe> recipeType) {
        super(pType, pPos, pBlockState);
        this.quickCheck = RecipeManager.createCheck(recipeType);
        this.recipeType = recipeType;
    }

    // Render Item
    public ItemStack getRenderedStack() {
        return inventory.getFirst();
    }

    // Crafting Stuff

    // Logic for GUI
    public void serverTick(ServerLevel serverLevel, BlockPos pos, BlockState state) {
        hasIgnis();
        if (isCharged() && hasRecipe()) {
            hasRecipe = true;
            crushingProgress++;
            setChanged(serverLevel, pos, state);
            serverLevel.sendBlockUpdated(pos, state, state, Block.UPDATE_ALL);

            if (hasProgressFinished()) {
                craftItem();
                resetProgress();
                ignisCharge--;
                setChanged(serverLevel, pos, state);
                serverLevel.sendBlockUpdated(pos, state, state, Block.UPDATE_ALL);
            }
        } else {
            hasRecipe = false;
            resetProgress();
        }
    }

    public boolean isCharged() {
        return this.ignisCharge > 0;
    }

    public void hasIgnis() {
        Item fuelItem = inventory.getStackInSlot(FUEL_SLOT).getItem();
        boolean hasFuel = isFuel(inventory.getStackInSlot(FUEL_SLOT));

        int fuel = FUEL_MAP.getOrDefault(fuelItem, 0);
        if (hasFuel && ignisCharge <= ignisPower - fuel) {
            ignisCharge += fuel;
            inventory.removeStackFromSlot(FUEL_SLOT);
        }
    }

    public boolean isFuel(ItemStack item) {
        return FUEL_MAP.containsKey(item.getItem());
    }

    // Recipe progress Stuff
    public boolean hasProgressFinished() {
        return crushingProgress >= maxCrushingProgress;
    }

    public void resetProgress() {
        crushingProgress = 0;
    }

    // Methods for checking Insertion
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

    public SingleRecipeInput createRecipeInput() {
        return new SingleRecipeInput(this.inventory.getFirst());
    }

    // Drop Inventory
    @Override
    public void preRemoveSideEffects(BlockPos pos, BlockState state) {
        if (level != null) Containers.dropContents(level, pos, inventory.getItems());
    }

    // Stores NBT Data
//    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
//        super.saveAdditional(tag, registries);
//        tag.put("Inventory", inventory.serializeNBT(registries));
//        tag.putInt("IgnisCharge", ignisCharge);
//        tag.putInt("IgnisPower", ignisPower);
//        tag.putInt("CrushingProgress", crushingProgress);
//    }
//
//    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
//        super.loadAdditional(tag, registries);
//        inventory.deserializeNBT(registries, tag.getCompoundOrEmpty("Inventory"));
//        ignisCharge = tag.getIntOr("IgnisCharge", 0);
//        ignisPower = tag.getIntOr("IgnisPower", 0);
//        crushingProgress = tag.getIntOr("CrushingProgress", 0);
//    }
//
//    // Updates the BE between Client-Server
//    public void onDataPacket(Connection connection, ClientboundBlockEntityDataPacket packet, HolderLookup.Provider registries) {
//        super.onDataPacket(connection, packet, registries);
//        if (level != null && level.isClientSide) {
//            CompoundTag tag = packet.getTag();
//            handleUpdateTag(tag, level.registryAccess());
//        }
//    }
//
//    public void handleUpdateTag(CompoundTag tag, HolderLookup.Provider registries) {
//        super.handleUpdateTag(tag, registries);
//        inventory.deserializeNBT(registries, tag.getCompoundOrEmpty("Inventory"));
//        crushingProgress = tag.getIntOr("CrushingProgress", 0);
//        hasRecipe = tag.getBooleanOr("HasRecipe", false);
//    }
//
//    @Override
//    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
//        CompoundTag tag = super.getUpdateTag(registries);
//        tag.put("Inventory", inventory.serializeNBT(registries));
//        tag.putInt("CrushingProgress", crushingProgress);
//        tag.putBoolean("HasRecipe", hasRecipe());
//        return tag;
//    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }
}