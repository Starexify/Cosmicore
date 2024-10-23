package net.nova.cosmicore.gui.crusher;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.nova.cosmicore.Cosmicore;

public abstract class AbstractCrusherScreen<T extends AbstractContainerMenu> extends AbstractContainerScreen<T> {
    protected static final ResourceLocation IGNIS_SPRITE = Cosmicore.rl("container/crusher/full_ignis");
    protected static final ResourceLocation CRUSHING_PROGRESS_SPRITE = Cosmicore.rl("container/crusher/crushing_progress");
    public static final String IGNIS_TOOLTIP = "container.crushing.ignis_tooltip";

    protected final ResourceLocation texture;

    // Ignis bar position
    public static final int IGNIS_X = 156;
    public static final int IGNIS_Y = 15;
    public static final int IGNIS_WIDTH = 8;
    public static final int IGNIS_TEXTURE_HEIGHT = 56;

    public AbstractCrusherScreen(T menu, Inventory playerInventory, Component title, ResourceLocation texture) {
        super(menu, playerInventory, title);
        this.texture = texture;
        this.imageHeight = 184;
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
    protected void renderBg(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, texture);
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;

        guiGraphics.blit(texture, i, j, 0, 0, this.imageWidth, this.imageHeight);

        if (this.isCharged()) {
            int l = Mth.ceil(this.getChargedProgress());
            guiGraphics.blitSprite(IGNIS_SPRITE, 8, 56, 0, 56 - l, i + 156, j + 15 + 56 - l, 8, l);
        }

        int j1 = Mth.ceil(this.getCrushingProgress());
        guiGraphics.blitSprite(CRUSHING_PROGRESS_SPRITE, 18, 18, 0, 0, i + 80, j + 34, 18, j1);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        renderBackground(guiGraphics, mouseX, mouseY, delta);
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);
        this.renderIgnisTooltip(guiGraphics, mouseX, mouseY);
    }

    protected void renderIgnisTooltip(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        if (isHovering(IGNIS_X, IGNIS_Y, IGNIS_WIDTH, IGNIS_TEXTURE_HEIGHT, mouseX, mouseY)) {
            int currentIgnis = getCurrentIgnis();
            int maxIgnis = getMaxIgnis();
            Component tooltip = Component.translatable(IGNIS_TOOLTIP, currentIgnis, maxIgnis);
            guiGraphics.renderTooltip(this.font, tooltip, mouseX, mouseY);
        }
    }

    protected boolean isHovering(int x, int y, int width, int height, int mouseX, int mouseY) {
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;
        mouseX -= i;
        mouseY -= j;
        return mouseX >= x && mouseX < x + width && mouseY >= y && mouseY < y + height;
    }
}
