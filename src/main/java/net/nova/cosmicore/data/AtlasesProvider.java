package net.nova.cosmicore.data;

import net.minecraft.client.renderer.texture.atlas.sources.PalettedPermutations;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.AtlasIds;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.neoforged.neoforge.client.data.SpriteSourceProvider;
import net.nova.cosmicore.Cosmicore;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static net.nova.cosmicore.Cosmicore.MODID;

public class AtlasesProvider extends SpriteSourceProvider {
  public AtlasesProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
    super(output, lookupProvider, MODID);
  }

  // Add here the palettes
  public final Map<String, Identifier> permutations = Map.of(
      "titanium", Cosmicore.rl("trims/color_palettes/titanium"),
      "titanium_darker", Cosmicore.rl("trims/color_palettes/titanium_darker"),
      "lonsdaleite", Cosmicore.rl("trims/color_palettes/lonsdaleite"),
      "lonsdaleite_darker", Cosmicore.rl("trims/color_palettes/lonsdaleite_darker")
  );

  // Just some lists of things idk
  public final List<Identifier> textures = List.of(
      Identifier.withDefaultNamespace("trims/items/leggings_trim"),
      Identifier.withDefaultNamespace("trims/items/chestplate_trim"),
      Identifier.withDefaultNamespace("trims/items/helmet_trim"),
      Identifier.withDefaultNamespace("trims/items/boots_trim")
  );

  public final List<Identifier> trimTextures = List.of(
      Identifier.withDefaultNamespace("trims/models/armor/coast"),
      Identifier.withDefaultNamespace("trims/models/armor/coast_leggings"),
      Identifier.withDefaultNamespace("trims/models/armor/sentry"),
      Identifier.withDefaultNamespace("trims/models/armor/sentry_leggings"),
      Identifier.withDefaultNamespace("trims/models/armor/dune"),
      Identifier.withDefaultNamespace("trims/models/armor/dune_leggings"),
      Identifier.withDefaultNamespace("trims/models/armor/wild"),
      Identifier.withDefaultNamespace("trims/models/armor/wild_leggings"),
      Identifier.withDefaultNamespace("trims/models/armor/ward"),
      Identifier.withDefaultNamespace("trims/models/armor/ward_leggings"),
      Identifier.withDefaultNamespace("trims/models/armor/eye"),
      Identifier.withDefaultNamespace("trims/models/armor/eye_leggings"),
      Identifier.withDefaultNamespace("trims/models/armor/vex"),
      Identifier.withDefaultNamespace("trims/models/armor/vex_leggings"),
      Identifier.withDefaultNamespace("trims/models/armor/tide"),
      Identifier.withDefaultNamespace("trims/models/armor/tide_leggings"),
      Identifier.withDefaultNamespace("trims/models/armor/snout"),
      Identifier.withDefaultNamespace("trims/models/armor/snout_leggings"),
      Identifier.withDefaultNamespace("trims/models/armor/rib"),
      Identifier.withDefaultNamespace("trims/models/armor/rib_leggings"),
      Identifier.withDefaultNamespace("trims/models/armor/spire"),
      Identifier.withDefaultNamespace("trims/models/armor/spire_leggings"),
      Identifier.withDefaultNamespace("trims/models/armor/wayfinder"),
      Identifier.withDefaultNamespace("trims/models/armor/wayfinder_leggings"),
      Identifier.withDefaultNamespace("trims/models/armor/shaper"),
      Identifier.withDefaultNamespace("trims/models/armor/shaper_leggings"),
      Identifier.withDefaultNamespace("trims/models/armor/silence"),
      Identifier.withDefaultNamespace("trims/models/armor/silence_leggings"),
      Identifier.withDefaultNamespace("trims/models/armor/raiser"),
      Identifier.withDefaultNamespace("trims/models/armor/raiser_leggings"),
      Identifier.withDefaultNamespace("trims/models/armor/host"),
      Identifier.withDefaultNamespace("trims/models/armor/host_leggings"),
      Identifier.withDefaultNamespace("trims/models/armor/flow"),
      Identifier.withDefaultNamespace("trims/models/armor/flow_leggings"),
      Identifier.withDefaultNamespace("trims/models/armor/bolt"),
      Identifier.withDefaultNamespace("trims/models/armor/bolt_leggings")
  );

  @Override
  protected void gather() {
    atlas(AtlasIds.BLOCKS).addSource(new PalettedPermutations(textures, Identifier.withDefaultNamespace("trims/color_palettes/trim_palette"), permutations));
    atlas(AtlasIds.ARMOR_TRIMS).addSource(new PalettedPermutations(trimTextures, Identifier.withDefaultNamespace("trims/color_palettes/trim_palette"), permutations));
  }
}
