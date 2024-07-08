package net.nova.cosmicore;

import net.minecraft.resources.ResourceLocation;
import net.nova.cosmicore.data.DataGenerators;
import net.nova.cosmicore.init.ModArmorMaterial;
import net.nova.cosmicore.init.ModBlocks;
import net.nova.cosmicore.init.ModCreativeTab;
import net.nova.cosmicore.init.ModItems;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import org.slf4j.LoggerFactory;

@Mod(Cosmicore.MODID)
public class Cosmicore {
    public static final String MODID = "cosmicore";
    public static final Logger logger = LoggerFactory.getLogger(Cosmicore.class);

    public Cosmicore(IEventBus bus) {
        ModArmorMaterial.ARMOR_MATERIALS.register(bus);
        ModCreativeTab.CREATIVE_TAB.register(bus);
        ModItems.ITEMS.register(bus);
        ModBlocks.BLOCKS.register(bus);

        bus.addListener(DataGenerators::gatherData);

    }

    public static ResourceLocation rl(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }
}
