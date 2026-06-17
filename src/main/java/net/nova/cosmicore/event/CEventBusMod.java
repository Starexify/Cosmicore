package net.nova.cosmicore.event;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.nova.cosmicore.entity.TitaniumGolem;
import net.nova.cosmicore.init.CBlockEntities;
import net.nova.cosmicore.init.CEntities;

import static net.nova.cosmicore.Cosmicore.MODID;

@EventBusSubscriber(modid = MODID)
public class CEventBusMod {
  @SubscribeEvent
  public static void registerCapabilities(RegisterCapabilitiesEvent event) {
    event.registerBlockEntity(Capabilities.Item.BLOCK, CBlockEntities.CRUSHER_TILE.get(), (be, side) -> {
      if (side == null) return be.stackHandler;
      return switch (side) {
        case UP -> be.top;
        case DOWN -> be.down;
        default -> be.sides;
      };
    });
    event.registerBlockEntity(Capabilities.Item.BLOCK, CBlockEntities.ADVANCED_CRUSHER_TILE.get(), (be, side) -> {
      if (side == null) return be.stackHandler;
      return switch (side) {
        case UP -> be.top;
        case DOWN -> be.down;
        default -> be.sides;
      };
    });
  }

  @SubscribeEvent
  public static void registerEntityAttributes(EntityAttributeCreationEvent event) {
    event.put(CEntities.TITANIUM_GOLEM.get(), TitaniumGolem.createAttributes().build());
  }
}