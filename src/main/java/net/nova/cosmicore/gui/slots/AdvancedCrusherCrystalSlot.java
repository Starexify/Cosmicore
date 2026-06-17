package net.nova.cosmicore.gui.slots;

import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.transfer.IndexModifier;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.item.ResourceHandlerSlot;
import net.nova.cosmicore.gui.crusher.AdvancedCrusherMenu;

public class AdvancedCrusherCrystalSlot extends ResourceHandlerSlot {
    private final AdvancedCrusherMenu menu;

  public AdvancedCrusherCrystalSlot(ResourceHandler<ItemResource> handler, IndexModifier<ItemResource> slotModifier, int handlerSlot, int xPosition, int yPosition, AdvancedCrusherMenu menu) {
    super(handler, slotModifier, handlerSlot, xPosition, yPosition);
    this.menu = menu;
  }

  @Override
    public boolean mayPlace(ItemStack stack) {
        return this.menu.isCrystal(stack);
    }
}
