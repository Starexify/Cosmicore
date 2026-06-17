package net.nova.cosmicore.gui.slots;

import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.transfer.IndexModifier;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.item.ResourceHandlerSlot;
import net.nova.cosmicore.gui.crusher.CrusherMenu;

public class CrusherCrystalSlot extends ResourceHandlerSlot {
  private final CrusherMenu menu;

  public CrusherCrystalSlot(ResourceHandler<ItemResource> handler, IndexModifier<ItemResource> slotModifier, int handlerSlot, int xPosition, int yPosition, CrusherMenu menu) {
    super(handler, slotModifier, handlerSlot, xPosition, yPosition);
    this.menu = menu;
  }

  @Override
  public boolean mayPlace(ItemStack stack) {
    return this.menu.isCrystal(stack);
  }
}
