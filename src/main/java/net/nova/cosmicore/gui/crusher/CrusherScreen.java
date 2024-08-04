package net.nova.cosmicore.gui.crusher;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.nova.cosmicore.Cosmicore;

public class CrusherScreen extends AbstractContainerScreen<CrusherMenu> {
    private static final ResourceLocation IGNIS_SPRITE = Cosmicore.rl("container/crusher/full_ignis");
    private static final ResourceLocation CRUSHING_PROGRESS_SPRITE = Cosmicore.rl("container/crusher/crushing_progress");
    private static final ResourceLocation TEXTURE = Cosmicore.rl("textures/gui/container/crusher.png");

    public CrusherScreen(CrusherMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init() {
        int i = 184;
        this.titleLabelX = 6;
        this.imageHeight = 184;
        this.inventoryLabelY = this.imageHeight - 103;
        super.init();
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        guiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);

        if (this.getMenu().isCharged()) {
            int l = Mth.ceil(this.getMenu().getChargedProgress()) + 1;
            guiGraphics.blitSprite(IGNIS_SPRITE, 8, 56, 0, 8 - l, x + 156, y + 15 + 56 - l, 8, l);
        }

        int j1 = Mth.ceil(this.getMenu().getCrushingProgress());
        guiGraphics.blitSprite(CRUSHING_PROGRESS_SPRITE, 18, 18, 0, 0, x + 80, y + 34, 18, j1);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        renderBackground(guiGraphics, mouseX, mouseY, delta);
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }
}
