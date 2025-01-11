package net.nova.cosmicore.gui.crusher;

import net.minecraft.world.entity.player.Inventory;
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

    public boolean isCrystal(ItemStack pStack) {
        return AbstractCrusherTile.FUEL_MAP.containsKey(pStack.getItem());
    }

    public void addPlayerSlots(Inventory playerInventory) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 102 + i * 18));
            }
        }

        for (int k = 0; k < 9; k++) {
            this.addSlot(new Slot(playerInventory, k, 8 + k * 18, 160));
        }
    }

    public boolean isCharged() {
        return this.data.get(0) > 0;
    }

    // Crash maybe?
    public int getChargedProgress() {
        int ignisChargeSize = AbstractCrusherScreen.IGNIS_TEXTURE_HEIGHT;
        if (data.get(1) == 0) {
            return 0;
        }
        return data.get(0) * ignisChargeSize / data.get(1);
    }

    public int getCrushingProgress() {
        int progressArrowSize = 18;

        return data.get(3) != 0 && data.get(2) != 0 ? data.get(2) * progressArrowSize / data.get(3) : 0;
    }
}
