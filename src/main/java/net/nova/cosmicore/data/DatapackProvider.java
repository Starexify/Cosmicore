package net.nova.cosmicore.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.worldgen.StructureSets;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.nova.cosmicore.data.worldgen.CStructureSets;
import net.nova.cosmicore.data.worldgen.StructurePools;
import net.nova.cosmicore.data.worldgen.CStructures;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

import static net.nova.cosmicore.Cosmicore.MODID;

public class DatapackProvider extends DatapackBuiltinEntriesProvider {
    public DatapackProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, new RegistrySetBuilder()
                        .add(Registries.STRUCTURE, CStructures::bootstrap)
                        .add(Registries.TEMPLATE_POOL, StructurePools::bootstrap)
                        .add(Registries.STRUCTURE_SET, CStructureSets::bootstrap)
                        .add(Registries.TRIM_MATERIAL, CTrimMaterials::bootstrap),
                Set.of(MODID));
    }
}
