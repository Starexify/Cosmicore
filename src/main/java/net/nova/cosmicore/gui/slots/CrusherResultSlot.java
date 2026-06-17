package net.nova.cosmicore.gui.slots;

import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.transfer.IndexModifier;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.item.ResourceHandlerSlot;

public class CrusherResultSlot extends ResourceHandlerSlot {
  public CrusherResultSlot(ResourceHandler<ItemResource> handler, IndexModifier<ItemResource> slotModifier, int handlerSlot, int xPosition, int yPosition) {
    super(handler, slotModifier, handlerSlot, xPosition, yPosition);
  }

  @Override
    public boolean mayPlace(ItemStack stack) {
        return false;
    }
}
