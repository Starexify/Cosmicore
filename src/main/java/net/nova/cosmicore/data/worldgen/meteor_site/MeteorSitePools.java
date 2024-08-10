package net.nova.cosmicore.data.worldgen.meteor_site;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.data.worldgen.ProcessorLists;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.nova.cosmicore.data.worldgen.StructurePools;

public class MeteorSitePools {
    public static final ResourceKey<StructureTemplatePool> START = StructurePools.createKey("meteor_site_1/base_plates");

    public static void bootstrap(BootstrapContext<StructureTemplatePool> pContext) {
        HolderGetter<StructureProcessorList> holdergetter = pContext.lookup(Registries.PROCESSOR_LIST);
        Holder<StructureProcessorList> holder = holdergetter.getOrThrow(ProcessorLists.OUTPOST_ROT);
        HolderGetter<StructureTemplatePool> holdergetter1 = pContext.lookup(Registries.TEMPLATE_POOL);
        Holder<StructureTemplatePool> holder1 = holdergetter1.getOrThrow(Pools.EMPTY);

        // Base Plate
        pContext.register(START, new StructureTemplatePool(holder1, ImmutableList.of(
                        Pair.of(StructurePoolElement.legacy("cosmicore:meteor_site_1/base_plate"), 1)
                ), StructureTemplatePool.Projection.RIGID)
        );

        // Watchtower
        StructurePools.register(pContext, "meteor_site_1/towers", new StructureTemplatePool(holder1, ImmutableList.of(
                        Pair.of(StructurePoolElement.legacy("cosmicore:meteor_site_1/outpost_site_0"), 1)
                ), StructureTemplatePool.Projection.RIGID)
        );

        // Features
        StructurePools.register(pContext, "meteor_site_1/feature_plates", new StructureTemplatePool(holder1, ImmutableList.of(
                        Pair.of(StructurePoolElement.legacy("pillager_outpost/feature_plate"), 1)
                ), StructureTemplatePool.Projection.TERRAIN_MATCHING)
        );

        StructurePools.register(pContext, "meteor_site_1/features", new StructureTemplatePool(holder1, ImmutableList.of(
                        Pair.of(StructurePoolElement.legacy("pillager_outpost/feature_cage1"), 1),
                        Pair.of(StructurePoolElement.legacy("pillager_outpost/feature_cage2"), 1),
                        Pair.of(StructurePoolElement.legacy("pillager_outpost/feature_cage_with_allays"), 1),
                        Pair.of(StructurePoolElement.legacy("pillager_outpost/feature_logs"), 1),
                        Pair.of(StructurePoolElement.legacy("pillager_outpost/feature_tent1"), 1),
                        Pair.of(StructurePoolElement.legacy("pillager_outpost/feature_tent2"), 1),
                        Pair.of(StructurePoolElement.legacy("pillager_outpost/feature_targets"), 1),
                        Pair.of(StructurePoolElement.empty(), 6)
                ), StructureTemplatePool.Projection.RIGID)
        );
    }
}
