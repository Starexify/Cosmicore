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

public class AdvancedCrusherScreen extends AbstractContainerScreen<AdvancedCrusherMenu> {
    private static final ResourceLocation IGNIS_SPRITE = Cosmicore.rl("container/crusher/full_ignis");
    private static final ResourceLocation CRUSHING_PROGRESS_SPRITE = Cosmicore.rl("container/crusher/crushing_progress");
    private static final ResourceLocation TEXTURE = Cosmicore.rl("textures/gui/container/advanced_crusher.png");

    public AdvancedCrusherScreen(AdvancedCrusherMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        this.imageHeight = 184;
        this.titleLabelX = 8;
        this.titleLabelY = 6;
        this.inventoryLabelX = 8;
        this.inventoryLabelY = this.imageHeight - 94;
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int i  = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;

        guiGraphics.blit(TEXTURE, i, j, 0, 0, this.imageWidth, this.imageHeight);

        if (this.getMenu().isCharged()) {
            int l = Mth.ceil(this.getMenu().getChargedProgress());
            guiGraphics.blitSprite(IGNIS_SPRITE, 8, 56, 0, 56 - l, i + 156, j + 15 + 56 - l, 8, l);
        }

        int j1 = Mth.ceil(this.getMenu().getCrushingProgress());
        guiGraphics.blitSprite(CRUSHING_PROGRESS_SPRITE, 18, 18, 0, 0, i + 80, j + 34, 18, j1);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        renderBackground(guiGraphics, mouseX, mouseY, delta);
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }
}
