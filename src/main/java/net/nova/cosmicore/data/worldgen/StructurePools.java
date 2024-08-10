package net.nova.cosmicore.data.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.*;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.nova.cosmicore.Cosmicore;
import net.nova.cosmicore.data.worldgen.meteor_site.MeteorSitePools;

public class StructurePools {

    public static ResourceKey<StructureTemplatePool> createKey(String name) {
        return ResourceKey.create(Registries.TEMPLATE_POOL, Cosmicore.rl(name));
    }

    public static ResourceKey<StructureTemplatePool> parseKey(String pKey) {
        return ResourceKey.create(Registries.TEMPLATE_POOL, ResourceLocation.parse(pKey));
    }

    public static void register(BootstrapContext<StructureTemplatePool> pContext, String pName, StructureTemplatePool pPool) {
        pContext.register(createKey(pName), pPool);
    }

    public static void bootstrap(BootstrapContext<StructureTemplatePool> pContext) {
        MeteorSitePools.bootstrap(pContext);
    }
}
