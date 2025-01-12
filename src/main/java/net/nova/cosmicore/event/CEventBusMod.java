package net.nova.cosmicore.event;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.nova.cosmicore.init.CBlockEntities;

import static net.nova.cosmicore.Cosmicore.MODID;

@EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD)
public class CEventBusMod {
    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, CBlockEntities.CRUSHER_TILE.get(), (be, side) -> {
            if (side == null) return be.inventory;
            return switch (side) {
                case UP -> be.top;
                case DOWN -> be.down;
                default -> be.sides;
            };
        });
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, CBlockEntities.ADVANCED_CRUSHER_TILE.get(), (be, side) -> {
            if (side == null) return be.inventory;
            return switch (side) {
                case UP -> be.top;
                case DOWN -> be.down;
                default -> be.sides;
            };
        });
    }
}