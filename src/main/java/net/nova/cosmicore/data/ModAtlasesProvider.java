package net.nova.cosmicore.data;

import net.minecraft.client.renderer.texture.atlas.sources.PalettedPermutations;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.SpriteSourceProvider;
import net.nova.cosmicore.Cosmicore;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static net.nova.cosmicore.Cosmicore.MODID;

public class ModAtlasesProvider extends SpriteSourceProvider {
    protected static final ResourceLocation ARMOR_TRIMS = ResourceLocation.withDefaultNamespace("armor_trims");

    public ModAtlasesProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, MODID, existingFileHelper);
    }

    private final List<ResourceLocation> textures = List.of(
            ResourceLocation.withDefaultNamespace("trims/items/leggings_trim"),
            ResourceLocation.withDefaultNamespace("trims/items/chestplate_trim"),
            ResourceLocation.withDefaultNamespace("trims/items/helmet_trim"),
            ResourceLocation.withDefaultNamespace("trims/items/boots_trim")
    );

    private final List<ResourceLocation> trimTextures = List.of(
            ResourceLocation.withDefaultNamespace("trims/models/armor/coast"),
            ResourceLocation.withDefaultNamespace("trims/models/armor/coast_leggings"),
            ResourceLocation.withDefaultNamespace("trims/models/armor/sentry"),
            ResourceLocation.withDefaultNamespace("trims/models/armor/sentry_leggings"),
            ResourceLocation.withDefaultNamespace("trims/models/armor/dune"),
            ResourceLocation.withDefaultNamespace("trims/models/armor/dune_leggings"),
            ResourceLocation.withDefaultNamespace("trims/models/armor/wild"),
            ResourceLocation.withDefaultNamespace("trims/models/armor/wild_leggings"),
            ResourceLocation.withDefaultNamespace("trims/models/armor/ward"),
            ResourceLocation.withDefaultNamespace("trims/models/armor/ward_leggings"),
            ResourceLocation.withDefaultNamespace("trims/models/armor/eye"),
            ResourceLocation.withDefaultNamespace("trims/models/armor/eye_leggings"),
            ResourceLocation.withDefaultNamespace("trims/models/armor/vex"),
            ResourceLocation.withDefaultNamespace("trims/models/armor/vex_leggings"),
            ResourceLocation.withDefaultNamespace("trims/models/armor/tide"),
            ResourceLocation.withDefaultNamespace("trims/models/armor/tide_leggings"),
            ResourceLocation.withDefaultNamespace("trims/models/armor/snout"),
            ResourceLocation.withDefaultNamespace("trims/models/armor/snout_leggings"),
            ResourceLocation.withDefaultNamespace("trims/models/armor/rib"),
            ResourceLocation.withDefaultNamespace("trims/models/armor/rib_leggings"),
            ResourceLocation.withDefaultNamespace("trims/models/armor/spire"),
            ResourceLocation.withDefaultNamespace("trims/models/armor/spire_leggings"),
            ResourceLocation.withDefaultNamespace("trims/models/armor/wayfinder"),
            ResourceLocation.withDefaultNamespace("trims/models/armor/wayfinder_leggings"),
            ResourceLocation.withDefaultNamespace("trims/models/armor/shaper"),
            ResourceLocation.withDefaultNamespace("trims/models/armor/shaper_leggings"),
            ResourceLocation.withDefaultNamespace("trims/models/armor/silence"),
            ResourceLocation.withDefaultNamespace("trims/models/armor/silence_leggings"),
            ResourceLocation.withDefaultNamespace("trims/models/armor/raiser"),
            ResourceLocation.withDefaultNamespace("trims/models/armor/raiser_leggings"),
            ResourceLocation.withDefaultNamespace("trims/models/armor/host"),
            ResourceLocation.withDefaultNamespace("trims/models/armor/host_leggings"),
            ResourceLocation.withDefaultNamespace("trims/models/armor/flow"),
            ResourceLocation.withDefaultNamespace("trims/models/armor/flow_leggings"),
            ResourceLocation.withDefaultNamespace("trims/models/armor/bolt"),
            ResourceLocation.withDefaultNamespace("trims/models/armor/bolt_leggings")
    );

    private final Map<String, ResourceLocation> permutations = Map.of(
            "titanium", Cosmicore.rl("trims/color_palettes/titanium"),
            "titanium_darker", Cosmicore.rl("trims/color_palettes/titanium_darker")
    );

    @Override
    protected void gather() {
        atlas(SpriteSourceProvider.BLOCKS_ATLAS).addSource(new PalettedPermutations(
                textures,
                ResourceLocation.withDefaultNamespace("trims/color_palettes/trim_palette"),
                permutations
        ));

        atlas(ARMOR_TRIMS).addSource(new PalettedPermutations(
                trimTextures,
                ResourceLocation.withDefaultNamespace("trims/color_palettes/trim_palette"),
                permutations
        ));
    }
}
