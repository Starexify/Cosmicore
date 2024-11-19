package net.nova.cosmicore.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.nova.cosmicore.entity.Achondrite;
import net.nova.cosmicore.entity.Meteorite;

import java.util.function.Supplier;

import static net.nova.cosmicore.Cosmicore.MODID;

public class CEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(Registries.ENTITY_TYPE, MODID);

    public static final Supplier<EntityType<Achondrite>> ACHONDRITE = ENTITY_TYPES.register("achondrite",
            () -> EntityType.Builder.of(Achondrite::new, MobCategory.MISC)
                    .sized(1.4F, 1.0F)
                    .clientTrackingRange(10)
                    .updateInterval(20)
                    .build("achondrite")
    );

    public static final Supplier<EntityType<Meteorite>> METEORITE = ENTITY_TYPES.register("meteorite",
            () -> EntityType.Builder.of(Meteorite::new, MobCategory.MISC)
                    .sized(1.4F, 1.0F)
                    .clientTrackingRange(10)
                    .updateInterval(20)
                    .build("meteorite")
    );
}
