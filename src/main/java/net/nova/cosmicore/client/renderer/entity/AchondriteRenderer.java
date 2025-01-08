package net.nova.cosmicore.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.nova.cosmicore.Cosmicore;
import net.nova.cosmicore.client.model.BaseMeteorModel;
import net.nova.cosmicore.client.renderer.entity.state.MeteoriteRenderState;
import net.nova.cosmicore.entity.Achondrite;
import net.nova.cosmicore.init.CModelLayers;

@OnlyIn(Dist.CLIENT)
public class AchondriteRenderer extends EntityRenderer<Achondrite, MeteoriteRenderState> {
    private final BaseMeteorModel meteorModel;
    public static final ResourceLocation ACHONDRITE_LOCATION = Cosmicore.rl("textures/entity/meteors/achondrite.png");

    public AchondriteRenderer(EntityRendererProvider.Context context) {
        super(context);
        meteorModel = new BaseMeteorModel(context.bakeLayer(CModelLayers.ACHONDRITE));
    }

    public ResourceLocation getTextureLocation(MeteoriteRenderState renderState) {
        return ACHONDRITE_LOCATION;
    }

    @Override
    public MeteoriteRenderState createRenderState() {
        return new MeteoriteRenderState();
    }

    @Override
    public void extractRenderState(Achondrite p_entity, MeteoriteRenderState reusedState, float partialTick) {
        super.extractRenderState(p_entity, reusedState, partialTick);
        reusedState.fallingAnimationState.copyFrom(p_entity.fallingAnimationState);
        reusedState.explodedAnimationState.copyFrom(p_entity.explodedAnimationState);
    }
}
