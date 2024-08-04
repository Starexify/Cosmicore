package net.nova.cosmicore.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.nova.cosmicore.blockentity.CrusherTile;

import static net.nova.cosmicore.Cosmicore.MODID;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, MODID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CrusherTile>> CRUSHER_TILE = BLOCK_ENTITIES.register(
            "crusher_tile", () -> BlockEntityType.Builder.of(CrusherTile::new, ModBlocks.CRUSHER.get()).build(null)
    );

}
