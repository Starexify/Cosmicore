package net.nova.cosmicore.client.model;

import mod.azure.azurelib.common.api.client.model.GeoModel;
import net.minecraft.resources.ResourceLocation;
import net.nova.cosmicore.Cosmicore;
import net.nova.cosmicore.blockentity.AdvancedCrusherTile;
import net.nova.cosmicore.blockentity.CrusherTile;

public class AdvancedCrusherTileModel extends GeoModel<AdvancedCrusherTile> {
    @Override
    public ResourceLocation getModelResource(AdvancedCrusherTile animatable) {
        return Cosmicore.rl("geo/crusher.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(AdvancedCrusherTile animatable) {
        return Cosmicore.rl("textures/block/advanced_crusher.png");
    }

    @Override
    public ResourceLocation getAnimationResource(AdvancedCrusherTile animatable) {
        return Cosmicore.rl("animations/crusher.animation.json");
    }
}
