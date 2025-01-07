package net.nova.cosmicore.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.nova.cosmicore.Cosmicore;
import net.nova.cosmicore.entity.Achondrite;
import net.nova.cosmicore.entity.Meteorite;

import java.util.function.Supplier;

import static net.nova.cosmicore.Cosmicore.MODID;

public class CEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(Registries.ENTITY_TYPE, MODID);

    public static final Supplier<EntityType<Achondrite>> ACHONDRITE = register("achondrite",
            EntityType.Builder.of(Achondrite::new, MobCategory.MISC)
                    .sized(1.4F, 1.0F)
                    .clientTrackingRange(10)
                    .updateInterval(20)
    );

    public static final Supplier<EntityType<Meteorite>> METEORITE = register("meteorite",
            EntityType.Builder.of(Meteorite::new, MobCategory.MISC)
                    .sized(1.4F, 1.0F)
                    .clientTrackingRange(10)
                    .updateInterval(20)
    );

    // Register
    public static <T extends Entity> Supplier<EntityType<T>> register(String name, EntityType.Builder<T> builder) {
        return ENTITY_TYPES.register(ResourceKey.create(Registries.ENTITY_TYPE, Cosmicore.rl(name)).location().getPath(),
                () -> builder.build(ResourceKey.create(Registries.ENTITY_TYPE, Cosmicore.rl(name)))
        );
    }
}
