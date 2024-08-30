package net.nova.cosmicore.event;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.nova.cosmicore.client.model.AdvancedCrusherPistonModel;
import net.nova.cosmicore.client.model.CrusherPistonModel;
import net.nova.cosmicore.client.renderer.block.AdvancedCrusherTileRenderer;
import net.nova.cosmicore.client.renderer.block.CrusherTileRenderer;
import net.nova.cosmicore.client.renderer.item.CItemProperties;
import net.nova.cosmicore.entity.AchondriteModel;
import net.nova.cosmicore.entity.AchondriteRenderer;
import net.nova.cosmicore.gui.crusher.AdvancedCrusherScreen;
import net.nova.cosmicore.gui.crusher.CrusherScreen;
import net.nova.cosmicore.init.CBlockEntities;
import net.nova.cosmicore.init.CEntities;
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
        event.register(CMenuTypes.ADVANCED_CRUSHER_MENU.get(), AdvancedCrusherScreen::new);
    }

    // Entity Layers
    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(AchondriteModel.LAYER_LOCATION, AchondriteModel::createLayer);

        event.registerLayerDefinition(CrusherPistonModel.LAYER_LOCATION, CrusherPistonModel::createLayer);
        event.registerLayerDefinition(AdvancedCrusherPistonModel.LAYER_LOCATION, AdvancedCrusherPistonModel::createLayer);
    }

    // Entity Renderers
    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(CEntities.ACHONDRITE.get(), AchondriteRenderer::new);

        event.registerBlockEntityRenderer(CBlockEntities.CRUSHER_TILE.get(), CrusherTileRenderer::new);
        event.registerBlockEntityRenderer(CBlockEntities.ADVANCED_CRUSHER_TILE.get(), AdvancedCrusherTileRenderer::new);
    }
}
