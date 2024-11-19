package net.nova.cosmicore.event;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;
import net.nova.cosmicore.MeteorSpawner;

import static net.nova.cosmicore.Cosmicore.MODID;

@EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.GAME)
public class CEventBusGame {
    private static MeteorSpawner meteorSpawner;

    @SubscribeEvent
    public static void onServerStarting(ServerStartingEvent event) {
        meteorSpawner = new MeteorSpawner(event.getServer().overworld(), 3000, 5000); // 150s - 250s
    }

    @SubscribeEvent
    public static void onLevelTick(LevelTickEvent.Post event) {
        if (!event.getLevel().isClientSide() && event.getLevel() instanceof ServerLevel serverLevel && serverLevel.dimension() == Level.OVERWORLD) {
            if (meteorSpawner != null) {
                meteorSpawner.onTick();
            }
        }
    }
}
