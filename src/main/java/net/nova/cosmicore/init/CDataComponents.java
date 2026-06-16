package net.nova.cosmicore.init;

import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static net.nova.cosmicore.Cosmicore.MODID;

public class CDataComponents {
  public static final DeferredRegister.DataComponents COMPONENTS = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, MODID);

  public static final DeferredHolder<DataComponentType<?>, DataComponentType<BlockPos>> LAST_LOCATION = COMPONENTS.registerComponentType(
      "last_location", builder -> builder.persistent(BlockPos.CODEC).networkSynchronized(BlockPos.STREAM_CODEC)
  );
}
