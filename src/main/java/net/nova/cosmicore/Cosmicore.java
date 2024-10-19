package net.nova.cosmicore;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.nova.cosmicore.data.DataGenerators;
import net.nova.cosmicore.init.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.nova.cosmicore.Cosmicore.MODID;

@Mod(MODID)
public class Cosmicore {
    public static final String MODID = "cosmicore";
    public static final Logger logger = LoggerFactory.getLogger(Cosmicore.class);

    public Cosmicore(IEventBus bus) {
        CArmorMaterials.ARMOR_MATERIALS.register(bus);
        CreativeTab.CREATIVE_TAB.register(bus);
        CItems.ITEMS.register(bus);
        CBlocks.BLOCKS.register(bus);
        CRecipeTypes.RECIPE_TYPES.register(bus);
        CRecipeSerializers.RECIPE_SERIALIZERS.register(bus);
        CBlockEntities.BLOCK_ENTITIES.register(bus);
        CEntities.ENTITY_TYPES.register(bus);
        CMenuTypes.MENUS.register(bus);

        bus.addListener(DataGenerators::gatherData);
    }

    public static ResourceLocation rl(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }
}
