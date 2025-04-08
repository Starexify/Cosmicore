package net.nova.cosmicore.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
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
