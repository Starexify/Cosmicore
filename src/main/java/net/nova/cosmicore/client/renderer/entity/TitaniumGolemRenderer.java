package net.nova.cosmicore.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.nova.cosmicore.Cosmicore;
import net.nova.cosmicore.client.model.TitaniumGolemModel;
import net.nova.cosmicore.client.renderer.entity.state.TitaniumGolemRenderState;
import net.nova.cosmicore.entity.TitaniumGolem;
import net.nova.cosmicore.init.CModelLayers;

public class TitaniumGolemRenderer extends MobRenderer<TitaniumGolem, TitaniumGolemRenderState, TitaniumGolemModel> {
    private static final ResourceLocation GOLEM_LOCATION = Cosmicore.rl("textures/entity/titanium_golem/titanium_golem.png");

    public TitaniumGolemRenderer(EntityRendererProvider.Context provider) {
        super(provider, new TitaniumGolemModel(provider.bakeLayer(CModelLayers.TITANIUM_GOLEM)), 0.7F);
    }

    public ResourceLocation getTextureLocation(TitaniumGolemRenderState titaniumGolemRenderState) {
        return GOLEM_LOCATION;
    }

    @Override
    public TitaniumGolemRenderState createRenderState() {
        return new TitaniumGolemRenderState();
    }

    @Override
    public void extractRenderState(TitaniumGolem titaniumGolem, TitaniumGolemRenderState renderState, float p_361157_) {
        super.extractRenderState(titaniumGolem, renderState, p_361157_);
        renderState.idleAnimationState.copyFrom(titaniumGolem.idleAnimationState);
        renderState.standbyAnimationState.copyFrom(titaniumGolem.standbyAnimationState);
    }
}
