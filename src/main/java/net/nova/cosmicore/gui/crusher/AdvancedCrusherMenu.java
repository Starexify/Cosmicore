package net.nova.cosmicore.gui.crusher;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;
import net.neoforged.neoforge.transfer.item.ResourceHandlerSlot;
import net.nova.cosmicore.blockentity.AdvancedCrusherTile;
import net.nova.cosmicore.gui.slots.AdvancedCrusherCrystalSlot;
import net.nova.cosmicore.gui.slots.CrusherResultSlot;
import net.nova.cosmicore.init.CBlocks;
import net.nova.cosmicore.init.CMenuTypes;

public class AdvancedCrusherMenu extends BaseCrusherMenu {
  public final AdvancedCrusherTile blockEntity;
  public final Level level;

  public AdvancedCrusherMenu(int pContainerId, Inventory inventory, FriendlyByteBuf extraData) {
    this(pContainerId, inventory, inventory.player.level().getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(4), new ItemStacksResourceHandler(11));
  }

  public AdvancedCrusherMenu(int pContainerId, Inventory inventory, BlockEntity entity, ContainerData data, ItemStacksResourceHandler dataInventory) {
    super(CMenuTypes.ADVANCED_CRUSHER_MENU.get(), pContainerId, data);
    checkContainerSize(inventory, 11);
    blockEntity = (AdvancedCrusherTile) entity;
    this.level = inventory.player.level();

    this.FUEL_SLOT = 2;
    this.RECIPE_SLOT = 3;
    this.SLOTS = 11;

    addSlot(new ResourceHandlerSlot(dataInventory, dataInventory::set, 0, 71, 16));
    addSlot(new ResourceHandlerSlot(dataInventory, dataInventory::set, 1, 89, 16));
    addSlot(new AdvancedCrusherCrystalSlot(dataInventory, dataInventory::set, 2, 152, 67, this));
    addSlot(new CrusherResultSlot(dataInventory, dataInventory::set, 3, 53, 54));
    addSlot(new CrusherResultSlot(dataInventory, dataInventory::set, 4, 71, 54));
    addSlot(new CrusherResultSlot(dataInventory, dataInventory::set, 5, 89, 54));
    addSlot(new CrusherResultSlot(dataInventory, dataInventory::set, 6, 107, 54));
    addSlot(new CrusherResultSlot(dataInventory, dataInventory::set, 7, 53, 72));
    addSlot(new CrusherResultSlot(dataInventory, dataInventory::set, 8, 71, 72));
    addSlot(new CrusherResultSlot(dataInventory, dataInventory::set, 9, 89, 72));
    addSlot(new CrusherResultSlot(dataInventory, dataInventory::set, 10, 107, 72));

    addStandardInventorySlots(inventory, 8, 102);

    addDataSlots(data);
  }

  // Other stuff
  @Override
  public boolean stillValid(Player pPlayer) {
    return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()), pPlayer, CBlocks.ADVANCED_CRUSHER.get());
  }
}
