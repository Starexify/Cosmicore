package net.nova.cosmicore.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Containers;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.nova.cosmicore.blockentity.AdvancedCrusherTile;
import net.nova.cosmicore.init.CBlockEntities;

public class AdvancedCrusher extends AbstractCrusher {
    public AdvancedCrusher(Properties properties) {
        super(properties);
    }

    @Override
    protected Class<? extends BlockEntity> getTileEntityClass() {
        return AdvancedCrusherTile.class;
    }

    @Override
    protected BlockEntityType<?> getBlockEntityType() {
        return CBlockEntities.ADVANCED_CRUSHER_TILE.get();
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new AdvancedCrusherTile(pPos, pState);
    }

    @Override
    protected void dropContents(Level level, BlockPos pos, BlockEntity blockEntity) {
        if (blockEntity instanceof AdvancedCrusherTile crusherTile) {
            Containers.dropContents(level, pos, crusherTile.inventory.getItems());
        }
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return simpleCodec(AdvancedCrusher::new);
    }
}
