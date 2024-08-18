package net.nova.cosmicore;

import mod.azure.azurelib.common.internal.common.AzureLib;
import net.minecraft.resources.ResourceLocation;
import net.nova.cosmicore.data.DataGenerators;
import net.nova.cosmicore.init.*;
import net.nova.cosmicore.init.CRecipeSerializers;
import net.nova.cosmicore.init.CRecipeTypes;
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
        CArmorMaterial.ARMOR_MATERIALS.register(bus);
        CCreativeTab.CREATIVE_TAB.register(bus);
        CItems.ITEMS.register(bus);
        CBlocks.BLOCKS.register(bus);
        CRecipeTypes.RECIPE_TYPES.register(bus);
        CRecipeSerializers.RECIPE_SERIALIZERS.register(bus);
        CBlockEntities.BLOCK_ENTITIES.register(bus);
        CMenuTypes.MENUS.register(bus);

        AzureLib.initialize();

        bus.addListener(DataGenerators::gatherData);
    }

    public static ResourceLocation rl(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }
}
