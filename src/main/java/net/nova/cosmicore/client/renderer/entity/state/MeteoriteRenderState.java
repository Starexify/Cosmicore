package net.nova.cosmicore.client.renderer.entity.state;

import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.world.entity.AnimationState;

public class MeteoriteRenderState extends EntityRenderState {
    public final AnimationState fallingAnimationState = new AnimationState();
    public final AnimationState explodedAnimationState = new AnimationState();

    public MeteoriteRenderState() {
    }
}