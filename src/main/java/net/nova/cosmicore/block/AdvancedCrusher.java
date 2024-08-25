package net.nova.cosmicore.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.nova.cosmicore.blockentity.AdvancedCrusherTile;
import net.nova.cosmicore.init.CBlockEntities;
import org.jetbrains.annotations.Nullable;

public class AdvancedCrusher extends BaseCrusher {
    public AdvancedCrusher(Properties properties) {
        super(properties);
    }

    // Block Entity Stuff
    // Drops item content
    @Override
    protected void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pMovedByPiston) {
        if (!pState.is(pNewState.getBlock())) {
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if (blockEntity instanceof AdvancedCrusherTile) {
                if (pLevel instanceof ServerLevel) {
                    Containers.dropContents(pLevel, pPos, (AdvancedCrusherTile) blockEntity);
                }

                super.onRemove(pState, pLevel, pPos, pNewState, pMovedByPiston);
                pLevel.updateNeighbourForOutputSignal(pPos, this);
            } else {
                super.onRemove(pState, pLevel, pPos, pNewState, pMovedByPiston);
            }
        }
    }

    // Open block GUI (1.21 Network changed)
    @Override
    protected InteractionResult useWithoutItem(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, BlockHitResult pHitResult) {
        if (!pLevel.isClientSide) {
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if (blockEntity instanceof AdvancedCrusherTile crusherTile && pPlayer instanceof ServerPlayer serverPlayer) {
                serverPlayer.openMenu(crusherTile, pPos);
            } else {
                throw new IllegalStateException("Advanced Crusher container provider is missing!");
            }
        }
        return InteractionResult.sidedSuccess(pLevel.isClientSide);
    }

    // Block Entity Ticks
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        if (pLevel.isClientSide()) {
            return null;
        }

        return createTickerHelper(pBlockEntityType, CBlockEntities.ADVANCED_CRUSHER_TILE.get(),
                (pLevel1, pPos, pState1, pBlockEntity) -> pBlockEntity.serverTick(pLevel1, pPos, pState1));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new AdvancedCrusherTile(pPos, pState);
    }
}
