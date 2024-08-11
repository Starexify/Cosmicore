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

    public static void bootstrap(BootstrapContext<StructureTemplatePool> pContext) {
        HolderGetter<StructureTemplatePool> holdergetter = pContext.lookup(Registries.TEMPLATE_POOL);
        Holder<StructureTemplatePool> holder = holdergetter.getOrThrow(Pools.EMPTY);

        // Normal Meteor Site
        pContext.register(METEOR_SITE, new StructureTemplatePool(holder, ImmutableList.of(
                        Pair.of(StructurePoolElement.legacy("cosmicore:meteor_site/base_plate"), 1)
                ), StructureTemplatePool.Projection.RIGID)
        );
        StructurePools.register(pContext, "meteor_site/towers", new StructureTemplatePool(holder, ImmutableList.of(
                        Pair.of(StructurePoolElement.legacy("cosmicore:meteor_site/site_watchtower"), 1)
                ), StructureTemplatePool.Projection.RIGID)
        );
        StructurePools.register(pContext, "meteor_site/meteor", new StructureTemplatePool(holder, ImmutableList.of(
                        Pair.of(StructurePoolElement.legacy("cosmicore:meteor_site/meteor"), 1)
                ), StructureTemplatePool.Projection.RIGID)
        );

        // Desert Meteor Site
        pContext.register(DESERT_METEOR_SITE, new StructureTemplatePool(holder, ImmutableList.of(
                        Pair.of(StructurePoolElement.legacy("cosmicore:desert_meteor_site/base_plate"), 1)
                ), StructureTemplatePool.Projection.RIGID)
        );
        StructurePools.register(pContext, "desert_meteor_site/meteor", new StructureTemplatePool(holder, ImmutableList.of(
                        Pair.of(StructurePoolElement.legacy("cosmicore:desert_meteor_site/desert_meteor"), 1)
                ), StructureTemplatePool.Projection.RIGID)
        );

        // Desert Meteor Site
        pContext.register(BADLANDS_METEOR_SITE, new StructureTemplatePool(holder, ImmutableList.of(
                        Pair.of(StructurePoolElement.legacy("cosmicore:badlands_meteor_site/base_plate"), 1)
                ), StructureTemplatePool.Projection.RIGID)
        );
        StructurePools.register(pContext, "badlands_meteor_site/meteor", new StructureTemplatePool(holder, ImmutableList.of(
                        Pair.of(StructurePoolElement.legacy("cosmicore:badlands_meteor_site/badlands_meteor"), 1)
                ), StructureTemplatePool.Projection.RIGID)
        );
    }
}
