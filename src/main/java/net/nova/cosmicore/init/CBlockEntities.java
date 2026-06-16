package net.nova.cosmicore.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.nova.cosmicore.blockentity.AdvancedCrusherTile;
import net.nova.cosmicore.blockentity.CosmicShieldTile;
import net.nova.cosmicore.blockentity.CrusherTile;

import java.util.function.Supplier;

import static net.nova.cosmicore.Cosmicore.MODID;

public class CBlockEntities {
  public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, MODID);

  public static final Supplier<BlockEntityType<CrusherTile>> CRUSHER_TILE = BLOCK_ENTITIES.register("crusher_tile",
      () -> new BlockEntityType<>(CrusherTile::new, CBlocks.CRUSHER.get())
  );

  public static final Supplier<BlockEntityType<AdvancedCrusherTile>> ADVANCED_CRUSHER_TILE = BLOCK_ENTITIES.register("advanced_crusher_tile",
      () -> new BlockEntityType<>(AdvancedCrusherTile::new, CBlocks.ADVANCED_CRUSHER.get())
  );

  public static final Supplier<BlockEntityType<CosmicShieldTile>> COSMIC_SHIELD = BLOCK_ENTITIES.register("cosmic_shield",
      () -> new BlockEntityType<>(CosmicShieldTile::new, CBlocks.COSMIC_SHIELD.get())
  );
}
