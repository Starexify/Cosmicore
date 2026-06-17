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
import net.nova.cosmicore.blockentity.CrusherTile;
import net.nova.cosmicore.gui.slots.CrusherCrystalSlot;
import net.nova.cosmicore.gui.slots.CrusherResultSlot;
import net.nova.cosmicore.init.CBlocks;
import net.nova.cosmicore.init.CMenuTypes;

public class CrusherMenu extends BaseCrusherMenu {
  public final CrusherTile blockEntity;
  public final Level level;

  public CrusherMenu(int pContainerId, Inventory inventory, FriendlyByteBuf extraData) {
    this(pContainerId, inventory, inventory.player.level().getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(4), new ItemStacksResourceHandler(8));
  }

  public CrusherMenu(int pContainerId, Inventory inventory, BlockEntity entity, ContainerData data, ItemStacksResourceHandler dataInventory) {
    super(CMenuTypes.CRUSHER_MENU.get(), pContainerId, data);
    checkContainerSize(inventory, 8);
    blockEntity = (CrusherTile) entity;
    this.level = inventory.player.level();

    this.FUEL_SLOT = 1;
    this.RECIPE_SLOT = 2;
    this.SLOTS = 8;

    addSlot(new ResourceHandlerSlot(dataInventory, dataInventory::set, 0, 80, 16));
    addSlot(new CrusherCrystalSlot(dataInventory, dataInventory::set, 1, 152, 67, this));
    addSlot(new CrusherResultSlot(dataInventory, dataInventory::set, 2, 62, 54));
    addSlot(new CrusherResultSlot(dataInventory, dataInventory::set, 3, 80, 54));
    addSlot(new CrusherResultSlot(dataInventory, dataInventory::set, 4, 98, 54));
    addSlot(new CrusherResultSlot(dataInventory, dataInventory::set, 5, 62, 72));
    addSlot(new CrusherResultSlot(dataInventory, dataInventory::set, 6, 80, 72));
    addSlot(new CrusherResultSlot(dataInventory, dataInventory::set, 7, 98, 72));

    addStandardInventorySlots(inventory, 8, 102);

    addDataSlots(data);
  }

  // Other stuff
  @Override
  public boolean stillValid(Player pPlayer) {
    return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()), pPlayer, CBlocks.CRUSHER.get());
  }
}