package net.nova.cosmicore.gui.crusher;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.SlotItemHandler;
import net.neoforged.neoforge.items.wrapper.InvWrapper;
import net.nova.cosmicore.blockentity.CrusherTile;
import net.nova.cosmicore.gui.crusher.slots.CrusherCrystalSlot;
import net.nova.cosmicore.gui.crusher.slots.CrusherResultSlot;
import net.nova.cosmicore.init.ModBlocks;
import net.nova.cosmicore.init.ModItems;
import net.nova.cosmicore.init.ModMenuTypes;

public class CrusherMenu extends BasicCrusherMenu {
    public final CrusherTile blockEntity;
    private final ContainerData data;
    private final Level level;
    private IItemHandler internal;

    public CrusherMenu(int pContainerId, Inventory inventory, FriendlyByteBuf extraData) {
        this(pContainerId, inventory, inventory.player.level().getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(8));
    }

    public CrusherMenu(int pContainerId, Inventory inventory, BlockEntity entity, ContainerData data) {
        super(ModMenuTypes.CRUSHER_MENU.get(), pContainerId);
        checkContainerSize(inventory, 4);
        blockEntity = ((CrusherTile) entity);
        this.level = inventory.player.level();
        this.data = data;

        this.internal = new InvWrapper(blockEntity);

        addSlot(new SlotItemHandler(internal, 0, 80, 16));
        addSlot(new CrusherCrystalSlot(internal, 1, 152, 72, this));
        addSlot(new CrusherResultSlot(internal, 2, 62, 54));
        addSlot(new CrusherResultSlot(internal, 3, 80, 54));
        addSlot(new CrusherResultSlot(internal, 4, 98, 54));
        addSlot(new CrusherResultSlot(internal, 5, 62, 72));
        addSlot(new CrusherResultSlot(internal, 6, 80, 72));
        addSlot(new CrusherResultSlot(internal, 7, 98, 72));

        addPlayerSlots(inventory);

        addDataSlots(data);
    }

    public boolean isCharged() {
        return this.data.get(0) > 0;
    }

    public int getChargedProgress() {
        int ignisCharge = this.data.get(0);
        int ignisPower = this.data.get(1);
        int ignisChargeSize = 56;

        return ignisCharge != 0 ? ignisChargeSize * ignisCharge / ignisPower : 0;
    }

    public int getCrushingProgress() {
        int progress = this.data.get(2);
        int maxProgress = this.data.get(3);  // Max Progress
        int progressArrowSize = 18;

        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }

    // Function for moving items through slots with Ctrl+Click changed to not take the recipe slot if it has the last slot index (in this case index 3)
    // and also takes the fuel slot in consideration
    private static int FUEL_SLOT = 1;
    private static int RECIPE_SLOT = 2;
    private static int SLOTS = 8;

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
            } else if (this.isCrystal(itemstack)) {
                if (!this.moveItemStackTo(currentStack, FUEL_SLOT, FUEL_SLOT + 1, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(currentStack, 0, RECIPE_SLOT, false)) {
                return ItemStack.EMPTY;
            }

            if (currentStack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return itemstack;
    }

    // Other stuff
    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()), pPlayer, ModBlocks.CRUSHER.get());
    }
}
