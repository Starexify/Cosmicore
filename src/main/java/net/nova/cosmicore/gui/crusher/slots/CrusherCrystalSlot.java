package net.nova.cosmicore.gui.crusher.slots;

import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.SlotItemHandler;
import net.nova.cosmicore.gui.crusher.CrusherMenu;

public class CrusherCrystalSlot extends SlotItemHandler {
    private final CrusherMenu menu;

    public CrusherCrystalSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition, CrusherMenu menu) {
        super(itemHandler, index, xPosition, yPosition);
        this.menu = menu;
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return this.menu.isCrystal(stack);
    }
}
