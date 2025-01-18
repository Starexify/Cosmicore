package net.nova.cosmicore.init;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.nova.cosmicore.entity.Achondrite;
import net.nova.cosmicore.entity.Meteorite;

import static net.nova.cosmicore.Cosmicore.MODID;

public class CEntities {
    public static final DeferredRegister.Entities ENTITY_TYPES = DeferredRegister.createEntities(MODID);

    public static final DeferredHolder<EntityType<?>, EntityType<Achondrite>> ACHONDRITE = ENTITY_TYPES.registerEntityType("achondrite",
            Achondrite::new, MobCategory.MISC, meteoriteBuilder -> meteoriteBuilder
                    .sized(1.4F, 1.0F)
                    .clientTrackingRange(10)
                    .updateInterval(20));

    public static final DeferredHolder<EntityType<?>, EntityType<Meteorite>> METEORITE = ENTITY_TYPES.registerEntityType("meteorite",
            Meteorite::new, MobCategory.MISC, meteoriteBuilder -> meteoriteBuilder
                    .sized(1.4F, 1.0F)
                    .clientTrackingRange(10)
                    .updateInterval(20));
}
