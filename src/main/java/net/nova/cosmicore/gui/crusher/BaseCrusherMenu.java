package net.nova.cosmicore.gui.crusher;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.nova.cosmicore.init.CItems;
import org.jetbrains.annotations.Nullable;

public class BaseCrusherMenu extends AbstractContainerMenu {
    public final ContainerData data;

    protected BaseCrusherMenu(@Nullable MenuType<?> pMenuType, int pContainerId, ContainerData data) {
        super(pMenuType, pContainerId);
        this.data = data;
    }

    public boolean isCrystal(ItemStack pStack) {
        return pStack.is(CItems.INFERNIUM_CRYSTAL);
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
        int ignisCharge = this.data.get(0);
        int ignisPower = this.data.get(1);
        int ignisChargeSize = 56;

        if (ignisPower == 0) {
            return 0;
        }

        return ignisCharge * ignisChargeSize / ignisPower;
    }

    public int getCrushingProgress() {
        int progress = this.data.get(2);
        int maxProgress = this.data.get(3);  // Max Progress
        int progressArrowSize = 18;

        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }


    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        return null;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return false;
    }
}
