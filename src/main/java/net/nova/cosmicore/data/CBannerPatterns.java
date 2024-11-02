package net.nova.cosmicore.data;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.nova.cosmicore.Cosmicore;

public class CBannerPatterns {
    public static final ResourceKey<BannerPattern> METEORITE = create("meteorite");

    public static void bootstrap(BootstrapContext<BannerPattern> context) {
        register(context, METEORITE);
    }

    public static void register(BootstrapContext<BannerPattern> context, ResourceKey<BannerPattern> resourceKey) {
        context.register(resourceKey, new BannerPattern(resourceKey.location(), "block.cosmicore.banner." + resourceKey.location().toShortLanguageKey()));
    }

    public static ResourceKey<BannerPattern> create(String name) {
        return ResourceKey.create(Registries.BANNER_PATTERN, Cosmicore.rl(name));
    }
}
