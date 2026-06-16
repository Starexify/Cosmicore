package net.nova.cosmicore.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.nova.cosmicore.gui.CosmicShieldItemStackHandler;
import net.nova.cosmicore.init.CBlockEntities;

import javax.annotation.Nullable;

public class CosmicShieldTile extends BlockEntity {
  public CosmicShieldItemStackHandler inventory = new CosmicShieldItemStackHandler(CosmicShieldTile.this);

  public CosmicShieldTile(BlockPos pPos, BlockState pBlockState) {
    super(CBlockEntities.COSMIC_SHIELD.get(), pPos, pBlockState);
  }

  // Drop Inventory
  @Override
  public void preRemoveSideEffects(BlockPos pos, BlockState state) {
    if (level != null) Containers.dropContents(level, pos, inventory.getItems());
  }

  // Store data
  @Override
  protected void saveAdditional(ValueOutput output) {
    super.saveAdditional(output);
    inventory.serialize(output);
  }

  @Override
  protected void loadAdditional(ValueInput input) {
    super.loadAdditional(input);
    inventory.deserialize(input);
  }

  // Updates the BE between Client-Server

  @Override
  public void onDataPacket(Connection net, ValueInput valueInput) {
//    super.onDataPacket(net, valueInput);
    if (level != null && level.isClientSide()) {
      handleUpdateTag(valueInput);
    }
  }

  @Override
  public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
    CompoundTag tag = super.getUpdateTag(registries);
//    inventory.serialize(new ValueInput());
    return super.getUpdateTag(registries);
  }

  @Nullable
  @Override
  public Packet<ClientGamePacketListener> getUpdatePacket() {
    return ClientboundBlockEntityDataPacket.create(this);
  }
}