package net.nova.cosmicore.event;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.nova.cosmicore.client.renderer.item.CItemProperties;
import net.nova.cosmicore.gui.crusher.CrusherScreen;
import net.nova.cosmicore.init.CMenuTypes;

import static net.nova.cosmicore.Cosmicore.MODID;

@EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class CEventBusClientEvents {
    @SubscribeEvent
    public static void setupClient(FMLClientSetupEvent event) {
        event.enqueueWork(CItemProperties::addCustomItemProperties);
    }

    // Connect Screen to Menu
    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(CMenuTypes.CRUSHER_MENU.get(), CrusherScreen::new);
    }
}