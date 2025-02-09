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
    public static final ResourceKey<StructureSet> METEOR_SITE = register("meteor_site");
    public static final ResourceKey<StructureSet> DESERT_METEOR_SITE = register("desert_meteor_site");
    public static final ResourceKey<StructureSet> BADLANDS_METEOR_SITE = register("badlands_meteor_site");
    public static final ResourceKey<StructureSet> PALLASITE_METEOR_SITE = register("pallasite_meteor_site");
    public static final ResourceKey<StructureSet> DESERT_PALLASITE_METEOR_SITE = register("desert_pallasite_meteor_site");
    public static final ResourceKey<StructureSet> BADLANDS_PALLASITE_METEOR_SITE = register("badlands_pallasite_meteor_site");

    public static void bootstrap(BootstrapContext<StructureSet> context) {
        HolderGetter<Structure> holdergetter = context.lookup(Registries.STRUCTURE);
        Holder.Reference<StructureSet> reference = context.register(
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

        context.register(METEOR_SITE, new StructureSet(holdergetter.getOrThrow(CStructures.METEOR_SITE),
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

        context.register(DESERT_METEOR_SITE, new StructureSet(holdergetter.getOrThrow(CStructures.DESERT_METEOR_SITE),
                new RandomSpreadStructurePlacement(
                        Vec3i.ZERO,
                        StructurePlacement.FrequencyReductionMethod.LEGACY_TYPE_1,
                        0.2F,
                        165745298,
                        Optional.of(new StructurePlacement.ExclusionZone(reference, 10)),
                        32,
                        8,
                        RandomSpreadType.LINEAR
                )
        ));

        context.register(BADLANDS_METEOR_SITE, new StructureSet(holdergetter.getOrThrow(CStructures.BADLANDS_METEOR_SITE),
                new RandomSpreadStructurePlacement(
                        Vec3i.ZERO,
                        StructurePlacement.FrequencyReductionMethod.LEGACY_TYPE_1,
                        0.2F,
                        165745299,
                        Optional.of(new StructurePlacement.ExclusionZone(reference, 10)),
                        32,
                        8,
                        RandomSpreadType.LINEAR
                )
        ));

        context.register(PALLASITE_METEOR_SITE, new StructureSet(holdergetter.getOrThrow(CStructures.PALLASITE_METEOR_SITE),
                new RandomSpreadStructurePlacement(
                        Vec3i.ZERO,
                        StructurePlacement.FrequencyReductionMethod.LEGACY_TYPE_1,
                        0.15F,
                        165745300,
                        Optional.of(new StructurePlacement.ExclusionZone(reference, 10)),
                        64,
                        8,
                        RandomSpreadType.LINEAR
                )
        ));

        context.register(DESERT_PALLASITE_METEOR_SITE, new StructureSet(holdergetter.getOrThrow(CStructures.DESERT_PALLASITE_METEOR_SITE),
                new RandomSpreadStructurePlacement(
                        Vec3i.ZERO,
                        StructurePlacement.FrequencyReductionMethod.LEGACY_TYPE_1,
                        0.15F,
                        165745301,
                        Optional.of(new StructurePlacement.ExclusionZone(reference, 10)),
                        64,
                        8,
                        RandomSpreadType.LINEAR
                )
        ));

        context.register(BADLANDS_PALLASITE_METEOR_SITE, new StructureSet(holdergetter.getOrThrow(CStructures.BADLANDS_PALLASITE_METEOR_SITE),
                new RandomSpreadStructurePlacement(
                        Vec3i.ZERO,
                        StructurePlacement.FrequencyReductionMethod.LEGACY_TYPE_1,
                        0.15F,
                        165745302,
                        Optional.of(new StructurePlacement.ExclusionZone(reference, 10)),
                        64,
                        8,
                        RandomSpreadType.LINEAR
                )
        ));
    }

    public static ResourceKey<StructureSet> register(String name) {
        return ResourceKey.create(Registries.STRUCTURE_SET, Cosmicore.rl(name));
    }
}
