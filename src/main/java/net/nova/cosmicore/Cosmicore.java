package net.nova.cosmicore;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.nova.cosmicore.client.renderer.item.ModItemProperties;
import net.nova.cosmicore.data.DataGenerators;
import net.nova.cosmicore.init.*;
import org.slf4j.Logger;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import org.slf4j.LoggerFactory;

import static net.nova.cosmicore.Cosmicore.MODID;

@Mod(MODID)
public class Cosmicore {
    public static final String MODID = "cosmicore";
    public static final Logger logger = LoggerFactory.getLogger(Cosmicore.class);

    public Cosmicore(IEventBus bus) {
        bus.addListener(this::setup);
        ModArmorMaterial.ARMOR_MATERIALS.register(bus);
        ModCreativeTab.CREATIVE_TAB.register(bus);
        ModItems.ITEMS.register(bus);
        ModBlocks.BLOCKS.register(bus);

        bus.addListener(DataGenerators::gatherData);
    }

    private void setup(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ModItemProperties.addCustomItemProperties();
        });
    }

    public static ResourceLocation rl(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }
}
