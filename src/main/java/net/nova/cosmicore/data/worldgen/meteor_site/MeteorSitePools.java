package net.nova.cosmicore.data.worldgen.meteor_site;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.nova.cosmicore.data.worldgen.StructurePools;

public class MeteorSitePools {
    public static final ResourceKey<StructureTemplatePool> METEOR_SITE = StructurePools.createKey("meteor_site/base_plates");
    public static final ResourceKey<StructureTemplatePool> DESERT_METEOR_SITE = StructurePools.createKey("desert_meteor_site/base_plates");
    public static final ResourceKey<StructureTemplatePool> BADLANDS_METEOR_SITE = StructurePools.createKey("badlands_meteor_site/base_plates");
    public static final ResourceKey<StructureTemplatePool> PALLASITE_METEOR_SITE = StructurePools.createKey("pallasite_meteor_site/base_plates");
    public static final ResourceKey<StructureTemplatePool> DESERT_PALLASITE_METEOR_SITE = StructurePools.createKey("desert_pallasite_meteor_site/base_plates");
    public static final ResourceKey<StructureTemplatePool> BADLANDS_PALLASITE_METEOR_SITE = StructurePools.createKey("badlands_pallasite_meteor_site/base_plates");

    public static void bootstrap(BootstrapContext<StructureTemplatePool> context) {
        HolderGetter<StructureTemplatePool> holdergetter = context.lookup(Registries.TEMPLATE_POOL);
        Holder<StructureTemplatePool> holder = holdergetter.getOrThrow(Pools.EMPTY);

        // Normal Meteor Site
        context.register(METEOR_SITE, new StructureTemplatePool(holder, ImmutableList.of(
                        Pair.of(StructurePoolElement.legacy("cosmicore:meteor_site/base_plate"), 1)
                ), StructureTemplatePool.Projection.RIGID)
        );
        StructurePools.register(context, "meteor_site/towers", new StructureTemplatePool(holder, ImmutableList.of(
                        Pair.of(StructurePoolElement.legacy("cosmicore:meteor_site/site_watchtower"), 6),
                        Pair.of(StructurePoolElement.legacy("cosmicore:meteor_site/site_watchtower_abandoned"), 1)
                ), StructureTemplatePool.Projection.RIGID)
        );
        StructurePools.register(context, "meteor_site/meteor", new StructureTemplatePool(holder, ImmutableList.of(
                        Pair.of(StructurePoolElement.legacy("cosmicore:meteor_site/meteor"), 1)
                ), StructureTemplatePool.Projection.RIGID)
        );

        // Desert Meteor Site
        context.register(DESERT_METEOR_SITE, new StructureTemplatePool(holder, ImmutableList.of(
                        Pair.of(StructurePoolElement.legacy("cosmicore:desert_meteor_site/base_plate"), 1)
                ), StructureTemplatePool.Projection.RIGID)
        );
        StructurePools.register(context, "desert_meteor_site/desert_meteor", new StructureTemplatePool(holder, ImmutableList.of(
                        Pair.of(StructurePoolElement.legacy("cosmicore:desert_meteor_site/desert_meteor"), 1)
                ), StructureTemplatePool.Projection.RIGID)
        );

        // Badlands Meteor Site
        context.register(BADLANDS_METEOR_SITE, new StructureTemplatePool(holder, ImmutableList.of(
                        Pair.of(StructurePoolElement.legacy("cosmicore:badlands_meteor_site/base_plate"), 1)
                ), StructureTemplatePool.Projection.RIGID)
        );
        StructurePools.register(context, "badlands_meteor_site/badlands_meteor", new StructureTemplatePool(holder, ImmutableList.of(
                        Pair.of(StructurePoolElement.legacy("cosmicore:badlands_meteor_site/badlands_meteor"), 1)
                ), StructureTemplatePool.Projection.RIGID)
        );

        // Normal Pallasite Meteor Site
        context.register(PALLASITE_METEOR_SITE, new StructureTemplatePool(holder, ImmutableList.of(
                        Pair.of(StructurePoolElement.legacy("cosmicore:pallasite_meteor_site/base_plate"), 1)
                ), StructureTemplatePool.Projection.RIGID)
        );
        StructurePools.register(context, "pallasite_meteor_site/towers", new StructureTemplatePool(holder, ImmutableList.of(
                        Pair.of(StructurePoolElement.legacy("cosmicore:pallasite_meteor_site/site_watchtower"), 1)
                ), StructureTemplatePool.Projection.RIGID)
        );
        StructurePools.register(context, "pallasite_meteor_site/pallasite_meteor", new StructureTemplatePool(holder, ImmutableList.of(
                        Pair.of(StructurePoolElement.legacy("cosmicore:pallasite_meteor_site/pallasite_meteor"), 1)
                ), StructureTemplatePool.Projection.RIGID)
        );

        // Desert Pallasite Meteor Site
        context.register(DESERT_PALLASITE_METEOR_SITE, new StructureTemplatePool(holder, ImmutableList.of(
                        Pair.of(StructurePoolElement.legacy("cosmicore:desert_pallasite_meteor_site/base_plate"), 1)
                ), StructureTemplatePool.Projection.RIGID)
        );
        StructurePools.register(context, "desert_pallasite_meteor_site/desert_pallasite_meteor", new StructureTemplatePool(holder, ImmutableList.of(
                        Pair.of(StructurePoolElement.legacy("cosmicore:desert_pallasite_meteor_site/desert_pallasite_meteor"), 1)
                ), StructureTemplatePool.Projection.RIGID)
        );

        // Badlands Pallasite Meteor Site
        context.register(BADLANDS_PALLASITE_METEOR_SITE, new StructureTemplatePool(holder, ImmutableList.of(
                        Pair.of(StructurePoolElement.legacy("cosmicore:badlands_pallasite_meteor_site/base_plate"), 1)
                ), StructureTemplatePool.Projection.RIGID)
        );
        StructurePools.register(context, "badlands_pallasite_meteor_site/badlands_pallasite_meteor", new StructureTemplatePool(holder, ImmutableList.of(
                        Pair.of(StructurePoolElement.legacy("cosmicore:badlands_pallasite_meteor_site/badlands_pallasite_meteor"), 1)
                ), StructureTemplatePool.Projection.RIGID)
        );
    }
}
