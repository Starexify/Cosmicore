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
import net.nova.cosmicore.gui.slots.CrusherCrystalSlot;
import net.nova.cosmicore.gui.slots.CrusherResultSlot;
import net.nova.cosmicore.init.CBlocks;
import net.nova.cosmicore.init.CMenuTypes;

public class CrusherMenu extends BaseCrusherMenu {
    public final CrusherTile blockEntity;
    public final Level level;
    public IItemHandler internal;

    public CrusherMenu(int pContainerId, Inventory inventory, FriendlyByteBuf extraData) {
        this(pContainerId, inventory, inventory.player.level().getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(8));
    }

    public CrusherMenu(int pContainerId, Inventory inventory, BlockEntity entity, ContainerData data) {
        super(CMenuTypes.CRUSHER_MENU.get(), pContainerId, data);
        checkContainerSize(inventory, 8);
        blockEntity = ((CrusherTile) entity);
        this.level = inventory.player.level();

        this.internal = new InvWrapper(blockEntity);

        addSlot(new SlotItemHandler(internal, 0, 80, 16));
        addSlot(new CrusherCrystalSlot(internal, 1, 152, 67, this));
        addSlot(new CrusherResultSlot(internal, 2, 62, 54));
        addSlot(new CrusherResultSlot(internal, 3, 80, 54));
        addSlot(new CrusherResultSlot(internal, 4, 98, 54));
        addSlot(new CrusherResultSlot(internal, 5, 62, 72));
        addSlot(new CrusherResultSlot(internal, 6, 80, 72));
        addSlot(new CrusherResultSlot(internal, 7, 98, 72));

        addPlayerSlots(inventory);

        addDataSlots(data);
    }

    // Function for moving items through slots with Ctrl+Click changed to not take the recipe slot if it has the last slot index (in this case index 3)
    // and also takes the fuel slot in consideration
    public static int FUEL_SLOT = 1;
    public static int RECIPE_SLOT = 2;
    public static int SLOTS = 8;

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
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()), pPlayer, CBlocks.CRUSHER.get());
    }
}
