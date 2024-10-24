package net.nova.cosmicore.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.nova.cosmicore.blockentity.AdvancedCrusherTile;
import net.nova.cosmicore.blockentity.CosmicShieldTile;
import net.nova.cosmicore.blockentity.CrusherTile;

import java.util.function.Supplier;

import static net.nova.cosmicore.Cosmicore.MODID;

public class CBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, MODID);

    public static final Supplier<BlockEntityType<CrusherTile>> CRUSHER_TILE = BLOCK_ENTITIES.register("crusher_tile",
            () -> BlockEntityType.Builder.of(CrusherTile::new, CBlocks.CRUSHER.get()).build(null)
    );

    public static final Supplier<BlockEntityType<AdvancedCrusherTile>> ADVANCED_CRUSHER_TILE = BLOCK_ENTITIES.register("advanced_crusher_tile",
            () -> BlockEntityType.Builder.of(AdvancedCrusherTile::new, CBlocks.ADVANCED_CRUSHER.get()).build(null)
    );

    public static final Supplier<BlockEntityType<CosmicShieldTile>> COSMIC_SHIELD = BLOCK_ENTITIES.register("cosmic_shield",
            () -> BlockEntityType.Builder.of(CosmicShieldTile::new, CBlocks.COSMIC_SHIELD.get()).build(null)
    );
}
