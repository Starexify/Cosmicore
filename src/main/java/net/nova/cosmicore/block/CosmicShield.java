package net.nova.cosmicore.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.nova.cosmicore.blockentity.CosmicShieldTile;
import net.nova.cosmicore.init.CItems;
import org.jetbrains.annotations.Nullable;

public class CosmicShield extends BaseModel {
  public CosmicShield(Properties pProperties) {
    super(pProperties);
  }

  // Block Entity Stuff
  // Interaction
  @Override
  protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
    BlockEntity blockEntity = level.getBlockEntity(pos);
    if (blockEntity instanceof CosmicShieldTile cosmicShieldTile) {
      if (cosmicShieldTile.inventory.getItems().get(0).isEmpty() && stack.getItem() == CItems.INFERNIUM_CRYSTAL.get()) {
        cosmicShieldTile.inventory.getItems().set(0, new ItemStack(stack.getItem(), 1));
        stack.consume(1, player);
        level.playSound(player, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1f, 2f);
      }
      else if (!cosmicShieldTile.inventory.getItems().get(0).isEmpty()) {
        ItemStack tileStack = cosmicShieldTile.inventory.getItems().get(0);
        if (player.getItemInHand(InteractionHand.MAIN_HAND).isEmpty()) {
          player.setItemInHand(InteractionHand.MAIN_HAND, tileStack);
        }
        else {
          player.getInventory().placeItemBackInInventory(tileStack, true);
        }
        cosmicShieldTile.inventory.getItems().set(0, ItemStack.EMPTY);
        level.playSound(player, pos, SoundEvents.ITEM_FRAME_REMOVE_ITEM, SoundSource.BLOCKS, 1f, 2f);
        cosmicShieldTile.setChanged();
        level.sendBlockUpdated(pos, state, state, 3);
      }
      return InteractionResult.SUCCESS;
    }
    return InteractionResult.FAIL;
  }

  @Nullable
  @Override
  public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
    return new CosmicShieldTile(pos, state);
  }

  @Override
  public VoxelShape makeShape() {
    VoxelShape shape = Shapes.empty();
    shape = Shapes.join(shape, Shapes.box(0.125, 0.5625, 0.125, 0.875, 0.625, 0.875), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.125, 0.5625, 0.125, 0.5, 0.625, 0.875), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.5, 0.5625, 0.125, 0.875, 0.625, 0.875), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.125, 0.5625, 0.125, 0.875, 0.625, 0.5), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.125, 0.5625, 0.5, 0.875, 0.625, 0.875), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.4375, 0.34062500000000007, 0.4375, 0.5625, 0.46562500000000007, 0.5625), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.4375, 0.6875000000000002, 0.4375, 0.5625, 0.7500000000000002, 0.5625), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.3125, 0.6250000000000002, 0.3125, 0.6875, 0.6875000000000002, 0.6875), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.28125, 0.5937500000000002, 0.28125, 0.71875, 0.6562500000000002, 0.71875), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.40625, 0.6562500000000002, 0.40625, 0.59375, 0.7187500000000002, 0.59375), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.190625, 0.0625, 0.190625, 0.25312500000000004, 0.5, 0.2531250000000001), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.190625, 0.0625, 0.7468750000000001, 0.25312500000000004, 0.5, 0.8093750000000001), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.7468750000000001, 0.0625, 0.190625, 0.8093750000000001, 0.5, 0.2531250000000001), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.7468750000000001, 0.0625, 0.7468750000000001, 0.8093750000000001, 0.5, 0.8093750000000001), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.125, 0.5625, 0.1246875, 0.875, 0.625, 0.1246875), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.125, 0.5625, 0.8753125, 0.875, 0.625, 0.8753125), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.1221874999999999, 0.5625, 0.12812500000000004, 0.1221874999999999, 0.625, 0.8781250000000002), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.8753125, 0.5625, 0.12812500000000004, 0.8753125, 0.625, 0.8781250000000002), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.15937499999999996, 0, 0.7156250000000002, 0.28437500000000016, 0.0625, 0.8406250000000002), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.15937499999999996, 0, 0.15937500000000013, 0.28437500000000016, 0.0625, 0.2843750000000004), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.7218749999999999, 0, 0.15937500000000013, 0.8468750000000002, 0.0625, 0.2843750000000004), BooleanOp.OR);
    shape = Shapes.join(shape, Shapes.box(0.7218749999999999, 0, 0.7218750000000002, 0.8468750000000002, 0.0625, 0.8468750000000004), BooleanOp.OR);

    return shape;
  }

  @Override
  protected MapCodec<? extends BaseEntityBlock> codec() {
    return simpleCodec(CosmicShield::new);
  }
}
