package net.nova.cosmicore.client.model;

import mod.azure.azurelib.common.api.client.model.GeoModel;
import net.minecraft.resources.ResourceLocation;
import net.nova.cosmicore.Cosmicore;
import net.nova.cosmicore.blockentity.CrusherTile;

public class CrusherTileModel extends GeoModel<CrusherTile> {
    @Override
    public ResourceLocation getModelResource(CrusherTile animatable) {
        return Cosmicore.rl("geo/crusher.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(CrusherTile animatable) {
        return Cosmicore.rl("textures/block/crusher.png");
    }

    @Override
    public ResourceLocation getAnimationResource(CrusherTile animatable) {
        return Cosmicore.rl("animations/crusher.animation.json");
    }
}
