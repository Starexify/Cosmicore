package net.nova.cosmicore.event;

import net.minecraft.server.level.ServerLevel;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.LevelTickEvent;
import net.nova.cosmicore.MeteorSpawner;

import static net.nova.cosmicore.Cosmicore.MODID;

@EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.GAME)
public class CEventBusGame {
    @SubscribeEvent
    public static void onLevelTick(LevelTickEvent.Post event) {
        if (!event.getLevel().isClientSide() && event.getLevel() instanceof ServerLevel serverLevel) {
            MeteorSpawner meteorSpawner = new MeteorSpawner(serverLevel, 3000, 5000); // 3000 and 5000 basic
            meteorSpawner.onTick();
        }
    }
}
