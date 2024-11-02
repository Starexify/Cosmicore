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

public class MeteorPools {
    public static final ResourceKey<StructureTemplatePool> ACHONDRITE_METEOR = StructurePools.createKey("achondrite_meteor");

    public static void bootstrap(BootstrapContext<StructureTemplatePool> pContext) {
        HolderGetter<StructureTemplatePool> holdergetter = pContext.lookup(Registries.TEMPLATE_POOL);
        Holder<StructureTemplatePool> holder = holdergetter.getOrThrow(Pools.EMPTY);

        // Meteor Crater
        pContext.register(ACHONDRITE_METEOR, new StructureTemplatePool(holder, ImmutableList.of(
                        Pair.of(StructurePoolElement.legacy("cosmicore:achondrite_meteor/crater_1"), 5),
                        Pair.of(StructurePoolElement.legacy("cosmicore:achondrite_meteor/crater_2"), 1)
                ), StructureTemplatePool.Projection.RIGID)
        );
    }
}
