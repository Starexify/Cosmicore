package net.nova.cosmicore.client.renderer.blockentity;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.nova.cosmicore.blockentity.CrusherTile;
import net.nova.cosmicore.client.model.CrusherPistonModel;
import net.nova.cosmicore.client.renderer.blockentity.state.CrusherRenderState;

public class CrusherTileRenderer extends AbstractCrusherTileRenderer<CrusherTile, CrusherRenderState> {
  public CrusherTileRenderer(BlockEntityRendererProvider.Context context) {
    super(context, CrusherPistonModel.TEXTURE, CrusherPistonModel.LAYER_LOCATION, new CrusherPistonModel(context.bakeLayer(CrusherPistonModel.LAYER_LOCATION)));
  }

  @Override
  protected int getCrushingProgress(CrusherTile crusherTile) {
    return crusherTile.getCrushingProgress();
  }

  @Override
  protected boolean hasRecipe(CrusherTile crusherTile) {
    return crusherTile.hasRecipe;
  }

  @Override
  public CrusherRenderState createRenderState() {
    return new CrusherRenderState();
  }
}
