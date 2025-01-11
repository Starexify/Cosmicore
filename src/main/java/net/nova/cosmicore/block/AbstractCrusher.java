package net.nova.cosmicore.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.nova.cosmicore.blockentity.AbstractCrusherTile;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractCrusher extends BaseModel {
    public AbstractCrusher(Properties properties) {
        super(properties);
    }

    // Abstract methods for block entity handling
    protected abstract Class<? extends BlockEntity> getTileEntityClass();

    protected abstract BlockEntityType<?> getBlockEntityType();

    @Override
    public abstract BlockEntity newBlockEntity(BlockPos pPos, BlockState pState);

    @Override
    protected abstract MapCodec<? extends BaseEntityBlock> codec();

    // Block Entity Operations

    @Override
    protected InteractionResult useWithoutItem(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, BlockHitResult pHitResult) {
        if (!pLevel.isClientSide) {
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if (getTileEntityClass().isInstance(blockEntity) && pPlayer instanceof ServerPlayer serverPlayer) {
                serverPlayer.openMenu((MenuProvider) blockEntity, pPos);
            } else {
                throw new IllegalStateException(getClass().getSimpleName() + " container provider is missing!");
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return level instanceof ServerLevel serverlevel
                ? createTickerHelper(pBlockEntityType, getBlockEntityType(), (pLevel1, pPos, pState1, pBlockEntity) -> ((AbstractCrusherTile) pBlockEntity).serverTick(serverlevel, pPos, pState1))
                : null;
    }

    // Block Shape
    @Override
    protected VoxelShape makeShape() {
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0, 0.875, 0, 1, 1, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0, 0, 1, 0.25, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0.25, 0, 0.125, 0.875, 0.125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.875, 0.25, 0, 1, 0.875, 0.125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.875, 0.25, 0.875, 1, 0.875, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0.25, 0.875, 0.125, 0.875, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.03125, 0.125, 0.046875, 0.96875, 0.875, 0.984375), BooleanOp.OR);

        return shape;
    }
}