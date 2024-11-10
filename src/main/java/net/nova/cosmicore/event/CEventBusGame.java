package net.nova.cosmicore.event;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.LevelTickEvent;
import net.nova.cosmicore.MeteorSpawner;

import static net.nova.cosmicore.Cosmicore.MODID;

@EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.GAME)
public class CEventBusGame {
    @SubscribeEvent
    public static void onLevelTick(LevelTickEvent.Post event) {
        if (!event.getLevel().isClientSide()) {
            MeteorSpawner meteorSpawner = new MeteorSpawner(event.getLevel(), 10, 50);
            meteorSpawner.onTick();
        }
    }
}
