package net.nova.cosmicore.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Containers;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.nova.cosmicore.blockentity.CrusherTile;
import net.nova.cosmicore.init.CBlockEntities;
import org.jetbrains.annotations.Nullable;

public class Crusher extends AbstractCrusher {
    public Crusher(Properties properties) {
        super(properties);
    }

    @Override
    protected Class<? extends BlockEntity> getTileEntityClass() {
        return CrusherTile.class;
    }

    @Override
    protected BlockEntityType<?> getBlockEntityType() {
        return CBlockEntities.CRUSHER_TILE.get();
    }

    @Override
    protected void dropContents(Level level, BlockPos pos, BlockEntity blockEntity) {
        if (blockEntity instanceof CrusherTile crusherTile) {
            Containers.dropContents(level, pos, crusherTile.inventory.getItems());
        }
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new CrusherTile(pPos, pState);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return simpleCodec(Crusher::new);
    }
}
