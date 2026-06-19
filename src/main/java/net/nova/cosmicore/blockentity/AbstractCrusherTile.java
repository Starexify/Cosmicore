package net.nova.cosmicore.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
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
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.nova.cosmicore.gui.CrusherItemStackHandler;
import net.nova.cosmicore.init.CBlocks;
import net.nova.cosmicore.init.CItems;
import net.nova.cosmicore.recipe.crusher.AbstractCrushingRecipe;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public abstract class AbstractCrusherTile extends BlockEntity implements MenuProvider {
  public CrusherItemStackHandler stackHandler;
  public ResourceHandler<ItemResource> top;
  public ResourceHandler<ItemResource> sides;
  public ResourceHandler<ItemResource> down;

  public int FUEL_SLOT;
  public int RESULT_SLOT_START;
  public int RESULT_SLOT_END;

  protected int ignisCharge;
  protected int ignisPower = 44;
  protected int crushingProgress;
  protected int maxCrushingProgress = 10;

  public boolean hasRecipe;

  public static final Map<Item, Integer> FUEL_MAP = Map.of(
      CItems.INFERNIUM_CRYSTAL.get(), 11,
      CBlocks.INFERNIUM_BLOCK.asItem(), 44
  );

  public final RecipeType<? extends AbstractCrushingRecipe> recipeType;
  public final RecipeManager.CachedCheck<SingleRecipeInput, ? extends AbstractCrushingRecipe> quickCheck;

  public abstract boolean hasRecipe();

  public abstract void craftItem();

  protected AbstractCrusherTile(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState, RecipeType<? extends AbstractCrushingRecipe> recipeType) {
    super(pType, pPos, pBlockState);
    this.quickCheck = RecipeManager.createCheck(recipeType);
    this.recipeType = recipeType;
  }

  // Render Item
  public ItemStack getRenderedStack() {
    return stackHandler.getResource(0).toStack();
  }

  /// Crafting Logic
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
    }
    else {
      hasRecipe = false;
      resetProgress();
    }
  }

  public boolean isCharged() {
    return this.ignisCharge > 0;
  }

  public void hasIgnis() {
    Item fuelItem = stackHandler.getResource(FUEL_SLOT).getItem();
    boolean hasFuel = isFuel(stackHandler.getResource(FUEL_SLOT).toStack());

    int fuel = FUEL_MAP.getOrDefault(fuelItem, 0);
    if (hasFuel && ignisCharge <= ignisPower - fuel) {
      ignisCharge += fuel;
      stackHandler.removeStackFromSlot(FUEL_SLOT);
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
      ItemStack slotStack = stackHandler.getResource(i).toStack();
      if (slotStack.isEmpty()) {
//        stackHandler.set(i, result.copy(), 1);
        break;
      }
      else if (ItemStack.isSameItem(slotStack, result) && slotStack.getCount() + result.getCount() <= slotStack.getMaxStackSize()) {
        slotStack.grow(result.getCount());
        break;
      }
    }
  }

  public boolean canInsertItemInOutputSlot(Item item) {
    for (int i = RESULT_SLOT_START; i <= RESULT_SLOT_END; i++) {
      ItemStack slotStack = stackHandler.getResource(i).toStack();
      if (slotStack.isEmpty() || (slotStack.is(item) && slotStack.getCount() < slotStack.getMaxStackSize())) {
        return true;
      }
    }
    return false;
  }

  public boolean canInsertAmountIntoOutputSlot(int count) {
    int availableSpace = 0;
    for (int i = RESULT_SLOT_START; i <= RESULT_SLOT_END; i++) {
      ItemStack slotStack = stackHandler.getResource(i).toStack();
      if (slotStack.isEmpty()) {
        availableSpace += slotStack.getMaxStackSize();
      }
      else {
        availableSpace += slotStack.getMaxStackSize() - slotStack.getCount();
      }

      if (availableSpace >= count) return true;
    }
    return false;
  }

  public SingleRecipeInput createRecipeInput() {
    return new SingleRecipeInput(this.stackHandler.getResource(0).toStack());
  }

  // Drop Inventory
  @Override
  public void preRemoveSideEffects(BlockPos pos, BlockState state) {
    if (level == null) return;
    Containers.dropContents(level, pos, stackHandler.getItems());
  }

  // Stores NBT Data
  @Override
  protected void saveAdditional(ValueOutput out) {
    super.saveAdditional(out);

    out.putChild("Inventory", stackHandler);
    out.putInt("IgnisCharge", ignisCharge);
    out.putInt("IgnisPower", ignisPower);
    out.putInt("CrushingProgress", crushingProgress);
  }

  @Override
  protected void loadAdditional(ValueInput in) {
    super.loadAdditional(in);

    in.child("Inventory").ifPresent(stackHandler::deserialize);
    ignisCharge = in.getIntOr("IgnisCharge", 0);
    ignisPower = in.getIntOr("IgnisPower", 0);
    crushingProgress = in.getIntOr("CrushingProgress", 0);
  }

  // Updates the BE between Client-Server
//  @Override
//  public void onDataPacket(Connection net, ValueInput valueInput) {
//    super.onDataPacket(net, valueInput);
//    if (level == null && !level.isClientSide()) return;
//    handleUpdateTag(valueInput);
//  }

  @Override
  public void handleUpdateTag(ValueInput in) {
    super.handleUpdateTag(in);

    stackHandler.deserialize(in.child("Inventory").get());
    crushingProgress = in.getIntOr("CrushingProgress", 0);
    hasRecipe = in.getBooleanOr("HasRecipe", false);
  }

  @Override
  public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
    CompoundTag tag = super.getUpdateTag(registries);

//    tag.putInt("CrushingProgress", crushingProgress);
//    tag.putBoolean("HasRecipe", hasRecipe());

    return tag;
  }

  @Override
  public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
    return ClientboundBlockEntityDataPacket.create(this);
  }
}