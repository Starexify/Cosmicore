package net.nova.cosmicore;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.GameRules;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.nova.cosmicore.init.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.nova.cosmicore.Cosmicore.MODID;

@Mod(MODID)
public class Cosmicore {
    public static final String MODID = "cosmicore";
    public static final Logger logger = LoggerFactory.getLogger(Cosmicore.class);

    public static final GameRules.Key<GameRules.BooleanValue> ALLOW_METEORS_SPAWNING = GameRules.register("doMeteorSpawning", GameRules.Category.SPAWNING, GameRules.BooleanValue.create(true));

    public Cosmicore(IEventBus bus) {
        CDataComponents.COMPONENTS.register(bus);
        CMenuTypes.MENUS.register(bus);
        CEnchantmentEffects.ENTITY_EFFECT.register(bus);
        CRecipeSerializers.RECIPE_SERIALIZERS.register(bus);
        CRecipeTypes.RECIPE_TYPES.register(bus);
        CEntities.ENTITY_TYPES.register(bus);
        CBlocks.BLOCKS.register(bus);
        CBlockEntities.BLOCK_ENTITIES.register(bus);
        CItems.ITEMS.register(bus);
        CreativeTab.CREATIVE_TAB.register(bus);
    }

    public static ResourceLocation rl(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }
}
