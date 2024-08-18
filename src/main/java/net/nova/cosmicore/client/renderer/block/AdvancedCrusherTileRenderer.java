package net.nova.cosmicore.client.renderer.block;

import mod.azure.azurelib.common.api.client.renderer.GeoBlockRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.nova.cosmicore.blockentity.AdvancedCrusherTile;
import net.nova.cosmicore.blockentity.CrusherTile;
import net.nova.cosmicore.client.model.AdvancedCrusherTileModel;
import net.nova.cosmicore.client.model.CrusherTileModel;

public class AdvancedCrusherTileRenderer extends GeoBlockRenderer<AdvancedCrusherTile> {
    public AdvancedCrusherTileRenderer(BlockEntityRendererProvider.Context context) {
        super(new AdvancedCrusherTileModel());
    }
}
