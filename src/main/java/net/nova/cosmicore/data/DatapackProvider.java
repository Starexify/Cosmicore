package net.nova.cosmicore.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.nova.cosmicore.data.worldgen.Structures;

import java.sql.Struct;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import static net.nova.cosmicore.Cosmicore.MODID;

public class DatapackProvider extends DatapackBuiltinEntriesProvider {
    public DatapackProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, new RegistrySetBuilder()
                        .add(Registries.TRIM_MATERIAL, CTrimMaterials::bootstrap)
                        .add(Registries.STRUCTURE, Structures::bootstrap),
                Set.of(MODID));
    }
}
