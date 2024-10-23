package net.nova.cosmicore.gui.crusher;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.nova.cosmicore.Cosmicore;

public class AdvancedCrusherScreen extends AbstractCrusherScreen<AdvancedCrusherMenu> {
    protected static final ResourceLocation TEXTURE = Cosmicore.rl("textures/gui/container/advanced_crusher.png");

    public AdvancedCrusherScreen(AdvancedCrusherMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle, TEXTURE);
    }

    @Override
    protected boolean isCharged() {
        return this.getMenu().isCharged();
    }

    @Override
    protected float getChargedProgress() {
        return this.getMenu().getChargedProgress();
    }

    @Override
    protected float getCrushingProgress() {
        return this.getMenu().getCrushingProgress();
    }

    @Override
    protected int getCurrentIgnis() {
        return this.getMenu().data.get(0);
    }

    @Override
    protected int getMaxIgnis() {
        return this.getMenu().data.get(1);
    }
}
