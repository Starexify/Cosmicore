package net.nova.cosmicore.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.transfer.RangedResourceHandler;
import net.nova.cosmicore.gui.CrusherItemStackHandler;
import net.nova.cosmicore.gui.crusher.AdvancedCrusherMenu;
import net.nova.cosmicore.init.CBlockEntities;
import net.nova.cosmicore.init.CRecipeTypes;
import net.nova.cosmicore.recipe.crusher.AdvancedCrushingRecipe;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class AdvancedCrusherTile extends AbstractCrusherTile {
  public static final int ADDITIONAL_SLOT = 1;
  protected final ContainerData dataAccess = new ContainerData() {
    @Override
    public int get(int pIndex) {
      return switch (pIndex) {
        case 0 -> AdvancedCrusherTile.this.ignisCharge;
        case 1 -> AdvancedCrusherTile.this.ignisPower;
        case 2 -> AdvancedCrusherTile.this.crushingProgress;
        case 3 -> AdvancedCrusherTile.this.maxCrushingProgress;
        default -> 0;
      };
    }

    @Override
    public void set(int pIndex, int pValue) {
      switch (pIndex) {
        case 0 -> AdvancedCrusherTile.this.ignisCharge = pValue;
        case 1 -> AdvancedCrusherTile.this.ignisPower = pValue;
        case 2 -> AdvancedCrusherTile.this.crushingProgress = pValue;
        case 3 -> AdvancedCrusherTile.this.maxCrushingProgress = pValue;
      }
    }

    @Override
    public int getCount() {
      return 4;
    }
  };

  public AdvancedCrusherTile(BlockPos pPos, BlockState pBlockState) {
    super(CBlockEntities.ADVANCED_CRUSHER_TILE.get(), pPos, pBlockState, CRecipeTypes.ADVANCED_CRUSHING_RECIPE_TYPE.get());
    this.FUEL_SLOT = 2;
    this.RESULT_SLOT_START = 3;
    this.RESULT_SLOT_END = 10;

    this.stackHandler = new CrusherItemStackHandler(AdvancedCrusherTile.this, 11);
    this.top = RangedResourceHandler.of(stackHandler, 0, FUEL_SLOT);
    this.sides = RangedResourceHandler.of(stackHandler, FUEL_SLOT, RESULT_SLOT_START);
    this.down = RangedResourceHandler.of(stackHandler, RESULT_SLOT_START, RESULT_SLOT_END + 1);
  }

  // Render Item
  public ItemStack getRenderedAddition() {
    return stackHandler.getResource(ADDITIONAL_SLOT).toStack();
  }

  public int getCrushingProgress() {
    return crushingProgress;
  }

  // Crafting stuff
  @Override
  public boolean hasRecipe() {
    Optional<RecipeHolder<AdvancedCrushingRecipe>> recipe = getCurrentRecipe(this.stackHandler.getResource(0).toStack());
    Optional<RecipeHolder<AdvancedCrushingRecipe>> additionalRecipe = getCurrentRecipe(this.stackHandler.getResource(ADDITIONAL_SLOT).toStack());
    if (recipe.isEmpty() && additionalRecipe.isEmpty()) return false;

    ItemStack result = recipe.map(r -> r.value().assemble(createRecipeInput()))
        .orElseGet(() -> additionalRecipe.get().value().assemble(createAdditionalRecipeInput()));

    return canInsertAmountIntoOutputSlot(result.getCount()) && canInsertItemInOutputSlot(result.getItem());
  }

  public SingleRecipeInput createAdditionalRecipeInput() {
    return new SingleRecipeInput(this.stackHandler.getResource(ADDITIONAL_SLOT).toStack());
  }

  @Override
  public void craftItem() {
    Optional<RecipeHolder<AdvancedCrushingRecipe>> recipe = getCurrentRecipe(stackHandler.getResource(0).toStack());
    Optional<RecipeHolder<AdvancedCrushingRecipe>> additionalRecipe = getCurrentRecipe(stackHandler.getResource(ADDITIONAL_SLOT).toStack());

    if (recipe.isPresent()) {
      ItemStack result = recipe.get().value().assemble(createRecipeInput());
      stackHandler.getResource(0).toStack().shrink(1);
      insertOrMergeResult(result);
    }

    if (additionalRecipe.isPresent()) {
      ItemStack additionalResult = additionalRecipe.get().value().assemble(createAdditionalRecipeInput());
      stackHandler.getResource(ADDITIONAL_SLOT).toStack().shrink(1);
      insertOrMergeResult(additionalResult);
    }
  }

  public Optional<RecipeHolder<AdvancedCrushingRecipe>> getCurrentRecipe(ItemStack itemStack) {
    if (this.level instanceof ServerLevel serverlevel) {
      return serverlevel.recipeAccess().getRecipeFor(CRecipeTypes.ADVANCED_CRUSHING_RECIPE_TYPE.get(), new SingleRecipeInput(itemStack), serverlevel);
    }
    else {
      return Optional.empty();
    }
  }

  // GUI title
  @Override
  public Component getDisplayName() {
    return Component.translatable("block.cosmicore.advanced_crusher");
  }

  // Menu
  @Override
  public @Nullable AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
    return new AdvancedCrusherMenu(containerId, playerInventory, this, dataAccess, stackHandler);
  }
}