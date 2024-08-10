package net.nova.cosmicore.data.worldgen;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.BuiltinStructureSets;
import net.minecraft.world.level.levelgen.structure.BuiltinStructures;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacement;
import net.nova.cosmicore.Cosmicore;

import java.util.List;
import java.util.Optional;

public class CStructureSets {
    public static final ResourceKey<StructureSet> METEOR_SITE_1 = register("meteor_site_1");

    public static void bootstrap(BootstrapContext<StructureSet> pContext) {
        HolderGetter<Structure> holdergetter = pContext.lookup(Registries.STRUCTURE);
        Holder.Reference<StructureSet> reference = pContext.register(
                BuiltinStructureSets.VILLAGES,
                new StructureSet(
                        List.of(
                                StructureSet.entry(holdergetter.getOrThrow(BuiltinStructures.VILLAGE_PLAINS)),
                                StructureSet.entry(holdergetter.getOrThrow(BuiltinStructures.VILLAGE_DESERT)),
                                StructureSet.entry(holdergetter.getOrThrow(BuiltinStructures.VILLAGE_SAVANNA)),
                                StructureSet.entry(holdergetter.getOrThrow(BuiltinStructures.VILLAGE_SNOWY)),
                                StructureSet.entry(holdergetter.getOrThrow(BuiltinStructures.VILLAGE_TAIGA))
                        ),
                        new RandomSpreadStructurePlacement(34, 8, RandomSpreadType.LINEAR, 10387312)
                )
        );

        pContext.register(METEOR_SITE_1, new StructureSet(holdergetter.getOrThrow(CStructures.METEOR_SITE_1),
                new RandomSpreadStructurePlacement(
                        Vec3i.ZERO,
                        StructurePlacement.FrequencyReductionMethod.LEGACY_TYPE_1,
                        0.2F,
                        165745297,
                        Optional.of(new StructurePlacement.ExclusionZone(reference, 10)),
                        32,
                        8,
                        RandomSpreadType.LINEAR
                )
        ));

    }

    private static ResourceKey<StructureSet> register(String name) {
        return ResourceKey.create(Registries.STRUCTURE_SET, Cosmicore.rl(name));
    }
}
