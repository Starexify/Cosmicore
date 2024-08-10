package net.nova.cosmicore.data.worldgen;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.heightproviders.ConstantHeight;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.structures.JigsawStructure;
import net.nova.cosmicore.Cosmicore;
import net.nova.cosmicore.data.worldgen.meteor_site.MeteorSitePools;
import net.nova.cosmicore.init.CTags;

public class CStructures {
    public static ResourceKey<Structure> METEOR_SITE_1 = createKey("meteor_site_1");

    public static void bootstrap(BootstrapContext<Structure> pContext) {
        HolderGetter<Biome> holdergetter = pContext.lookup(Registries.BIOME);
        HolderGetter<StructureTemplatePool> holdergetter1 = pContext.lookup(Registries.TEMPLATE_POOL);

        pContext.register(METEOR_SITE_1, new JigsawStructure(
                new Structure.StructureSettings.Builder(holdergetter.getOrThrow(CTags.BiomeTags.HAS_METEOR_SITE))
                        .terrainAdapation(TerrainAdjustment.BEARD_THIN)
                        .build(),
                holdergetter1.getOrThrow(MeteorSitePools.START),
                7,
                ConstantHeight.of(VerticalAnchor.absolute(0)),
                true,
                Heightmap.Types.WORLD_SURFACE_WG
        ));

    }

    private static ResourceKey<Structure> createKey(String name) {
        return ResourceKey.create(Registries.STRUCTURE, Cosmicore.rl(name));
    }
}
