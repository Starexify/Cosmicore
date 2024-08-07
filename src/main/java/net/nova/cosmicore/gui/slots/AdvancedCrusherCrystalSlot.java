package net.nova.cosmicore.gui.slots;

import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.SlotItemHandler;
import net.nova.cosmicore.gui.crusher.AdvancedCrusherMenu;

public class AdvancedCrusherCrystalSlot extends SlotItemHandler {
    private final AdvancedCrusherMenu menu;

    public AdvancedCrusherCrystalSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition, AdvancedCrusherMenu menu) {
        super(itemHandler, index, xPosition, yPosition);
        this.menu = menu;
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return this.menu.isCrystal(stack);
    }
}
