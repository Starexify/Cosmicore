package net.nova.cosmicore.gui.crusher;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.nova.cosmicore.blockentity.AbstractCrusherTile;

public abstract class BaseCrusherMenu extends AbstractContainerMenu {
  public final ContainerData data;

  protected BaseCrusherMenu(MenuType<?> pMenuType, int pContainerId, ContainerData data) {
    super(pMenuType, pContainerId);
    this.data = data;
  }

  // Function for moving items through slots with Ctrl+Click changed to not take the recipe slot if it has the last slot index
  // and also takes the fuel slot in consideration
  public int FUEL_SLOT;
  public int RECIPE_SLOT;
  public int SLOTS;

  @Override
  public ItemStack quickMoveStack(Player playerIn, int index) {
    ItemStack itemstack = ItemStack.EMPTY;
    Slot slot = this.slots.get(index);
    if (slot != null && slot.hasItem()) {
      ItemStack currentStack = slot.getItem();
      itemstack = currentStack.copy();

      if (index < SLOTS) {
        if (!this.moveItemStackTo(currentStack, SLOTS, this.slots.size(), false)) {
          return ItemStack.EMPTY;
        }
        // Remove if no Fuel Slot exists
      }
      else if (this.isCrystal(itemstack)) {
        if (!this.moveItemStackTo(currentStack, FUEL_SLOT, FUEL_SLOT + 1, false)) {
          return ItemStack.EMPTY;
        }
      }
      else if (!this.moveItemStackTo(currentStack, 0, RECIPE_SLOT, false)) {
        return ItemStack.EMPTY;
      }

      if (currentStack.isEmpty()) {
        slot.set(ItemStack.EMPTY);
      }
      else {
        slot.setChanged();
      }
    }

    return itemstack;
  }

  public boolean isCrystal(ItemStack stack) {
    return AbstractCrusherTile.FUEL_MAP.containsKey(stack.getItem());
  }

  public boolean isCharged() {
    return this.data.get(0) > 0;
  }

  // Crash maybe?
  public int getChargedProgress() {
    int ignisChargeSize = AbstractCrusherScreen.IGNIS_TEXTURE_HEIGHT;
    if (data.get(1) == 0) return 0;

    return data.get(0) * ignisChargeSize / data.get(1);
  }

  public int getCrushingProgress() {
    int progressArrowSize = 18;

    return data.get(3) != 0 && data.get(2) != 0 ? data.get(2) * progressArrowSize / data.get(3) : 0;
  }
}
