package net.nova.cosmicore.client.renderer.blockentity;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.nova.cosmicore.blockentity.AdvancedCrusherTile;
import net.nova.cosmicore.client.model.CrusherPistonModel;
import net.nova.cosmicore.client.renderer.blockentity.state.CrusherRenderState;

public class AdvancedCrusherTileRenderer extends AbstractCrusherTileRenderer<AdvancedCrusherTile, CrusherRenderState> {
  public AdvancedCrusherTileRenderer(BlockEntityRendererProvider.Context context) {
    super(context, CrusherPistonModel.ADVANCED_TEXTURE, CrusherPistonModel.LAYER_LOCATION,
        new CrusherPistonModel(context.bakeLayer(CrusherPistonModel.LAYER_LOCATION)));
  }

  @Override
  protected int getCrushingProgress(AdvancedCrusherTile advancedCrusherTile) {
    return advancedCrusherTile.getCrushingProgress();
  }

  @Override
  protected boolean hasRecipe(AdvancedCrusherTile advancedCrusherTile) {
    return advancedCrusherTile.hasRecipe;
  }

  @Override
  public CrusherRenderState createRenderState() {
    return new CrusherRenderState();
  }
}