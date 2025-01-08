package net.nova.cosmicore.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.nova.cosmicore.Cosmicore;
import net.nova.cosmicore.client.model.BaseMeteorModel;
import net.nova.cosmicore.client.renderer.entity.state.MeteoriteRenderState;
import net.nova.cosmicore.entity.Meteorite;
import net.nova.cosmicore.init.CModelLayers;

@OnlyIn(Dist.CLIENT)
public class MeteoriteRenderer extends EntityRenderer<Meteorite, MeteoriteRenderState> {
    public final BaseMeteorModel meteorModel;
    public static final ResourceLocation METEORITE_LOCATION = Cosmicore.rl("textures/entity/meteors/meteorite.png");

    public MeteoriteRenderer(EntityRendererProvider.Context context) {
        super(context);
        meteorModel = new BaseMeteorModel(context.bakeLayer(CModelLayers.METEORITE));
    }

    public ResourceLocation getTextureLocation(MeteoriteRenderState renderState) {
        return METEORITE_LOCATION;
    }

    @Override
    public MeteoriteRenderState createRenderState() {
        return new MeteoriteRenderState();
    }

    @Override
    public void extractRenderState(Meteorite p_entity, MeteoriteRenderState reusedState, float partialTick) {
        super.extractRenderState(p_entity, reusedState, partialTick);
        reusedState.fallingAnimationState.copyFrom(p_entity.fallingAnimationState);
        reusedState.explodedAnimationState.copyFrom(p_entity.explodedAnimationState);
    }
}