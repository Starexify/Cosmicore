package net.nova.cosmicore.gui.crusher;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.nova.cosmicore.Cosmicore;

public abstract class AbstractCrusherScreen<T extends AbstractContainerMenu> extends AbstractContainerScreen<T> {
  protected static final Identifier IGNIS_SPRITE = Cosmicore.rl("container/crusher/full_ignis");
  protected static final Identifier CRUSHING_PROGRESS_SPRITE = Cosmicore.rl("container/crusher/crushing_progress");
  public static final String IGNIS_TOOLTIP = "container.crushing.ignis_tooltip";

  protected final Identifier texture;

  // Ignis bar position
  public static final int IGNIS_X = 156;
  public static final int IGNIS_Y = 21;
  public static final int IGNIS_WIDTH = 8;
  public static final int IGNIS_TEXTURE_HEIGHT = 45;

  public AbstractCrusherScreen(T menu, Inventory playerInventory, Component title, Identifier texture) {
    super(menu, playerInventory, title, 176, 184);
    this.texture = texture;
    this.titleLabelX = 8;
    this.titleLabelY = 6;
    this.inventoryLabelX = 8;
    this.inventoryLabelY = this.imageHeight - 94;
  }

  protected abstract boolean isCharged();

  protected abstract float getChargedProgress();

  protected abstract float getCrushingProgress();

  protected abstract int getCurrentIgnis();

  protected abstract int getMaxIgnis();

  @Override
  public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
    super.extractBackground(graphics, mouseX, mouseY, a);
    int i = (this.width - this.imageWidth) / 2;
    int j = (this.height - this.imageHeight) / 2;
    graphics.blit(RenderPipelines.GUI_TEXTURED, texture, i, j, 0.0F, 0.0F, this.imageWidth, this.imageHeight, 256, 256);

    if (this.isCharged()) {
      int l = Mth.ceil(this.getChargedProgress());
      graphics.blitSprite(RenderPipelines.GUI_TEXTURED, IGNIS_SPRITE, IGNIS_WIDTH, IGNIS_TEXTURE_HEIGHT, 0, IGNIS_TEXTURE_HEIGHT - l, i + IGNIS_X, j + IGNIS_Y + IGNIS_TEXTURE_HEIGHT - l, IGNIS_WIDTH, l);
    }

    int j1 = Mth.ceil(this.getCrushingProgress());
    graphics.blitSprite(RenderPipelines.GUI_TEXTURED, CRUSHING_PROGRESS_SPRITE, 18, 18, 0, 0, i + 80, j + 34, 18, j1);
  }

  @Override
  public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
    super.extractRenderState(graphics, mouseX, mouseY, a);
    this.renderIgnisTooltip(graphics, mouseX, mouseY);
  }

  protected void renderIgnisTooltip(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY) {
    if (!isHovering(IGNIS_X, IGNIS_Y, IGNIS_WIDTH, IGNIS_TEXTURE_HEIGHT, mouseX, mouseY)) return;

    int currentIgnis = getCurrentIgnis();
    int maxIgnis = getMaxIgnis();
    Component tooltip = Component.translatable(IGNIS_TOOLTIP, currentIgnis, maxIgnis);
    guiGraphics.setTooltipForNextFrame(this.font, tooltip, mouseX, mouseY);
  }

  protected boolean isHovering(int x, int y, int width, int height, int mouseX, int mouseY) {
    int i = (this.width - this.imageWidth) / 2;
    int j = (this.height - this.imageHeight) / 2;
    mouseX -= i;
    mouseY -= j;
    return mouseX >= x && mouseX < x + width && mouseY >= y && mouseY < y + height;
  }
}
