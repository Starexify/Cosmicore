package net.nova.cosmicore.event;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.nova.cosmicore.client.model.BaseMeteorModel;
import net.nova.cosmicore.client.model.CosmicShieldTierIModel;
import net.nova.cosmicore.client.model.CrusherPistonModel;
import net.nova.cosmicore.client.model.TitaniumGolemModel;
import net.nova.cosmicore.client.renderer.blockentity.AdvancedCrusherTileRenderer;
import net.nova.cosmicore.client.renderer.blockentity.CosmicShieldTileRenderer;
import net.nova.cosmicore.client.renderer.blockentity.CrusherTileRenderer;
import net.nova.cosmicore.client.renderer.entity.AchondriteRenderer;
import net.nova.cosmicore.client.renderer.entity.MeteoriteRenderer;
import net.nova.cosmicore.client.renderer.entity.TitaniumGolemRenderer;
import net.nova.cosmicore.gui.crusher.AdvancedCrusherScreen;
import net.nova.cosmicore.gui.crusher.CrusherScreen;
import net.nova.cosmicore.init.*;

import static net.nova.cosmicore.Cosmicore.MODID;

@EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class CEventBusClientEvents {

    @SubscribeEvent
    public static void setupClient(FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(CBlocks.INFERNIUM_CLUSTER.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(CBlocks.LONSDALEITE_BLOCK.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(CBlocks.CRUSHER.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(CBlocks.ADVANCED_CRUSHER.get(), RenderType.cutout());
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
        event.registerLayerDefinition(CModelLayers.ACHONDRITE, BaseMeteorModel::createBodyLayer);
        event.registerLayerDefinition(CModelLayers.METEORITE, BaseMeteorModel::createBodyLayer);
        event.registerLayerDefinition(CModelLayers.TITANIUM_GOLEM, TitaniumGolemModel::createBodyLayer);

        event.registerLayerDefinition(CrusherPistonModel.LAYER_LOCATION, CrusherPistonModel::createLayer);
        event.registerLayerDefinition(CosmicShieldTierIModel.LAYER_LOCATION, CosmicShieldTierIModel::createLayer);
    }

    // Entity Renderers
    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(CEntities.ACHONDRITE.get(), AchondriteRenderer::new);
        event.registerEntityRenderer(CEntities.METEORITE.get(), MeteoriteRenderer::new);
        event.registerEntityRenderer(CEntities.TITANIUM_GOLEM.get(), TitaniumGolemRenderer::new);

        event.registerBlockEntityRenderer(CBlockEntities.CRUSHER_TILE.get(), CrusherTileRenderer::new);
        event.registerBlockEntityRenderer(CBlockEntities.ADVANCED_CRUSHER_TILE.get(), AdvancedCrusherTileRenderer::new);
        event.registerBlockEntityRenderer(CBlockEntities.COSMIC_SHIELD.get(), CosmicShieldTileRenderer::new);
    }
}