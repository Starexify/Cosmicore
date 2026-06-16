package net.nova.cosmicore.client.renderer.entity.state;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.AnimationState;

public class TitaniumGolemRenderState extends LivingEntityRenderState {
  public final AnimationState idleAnimationState = new AnimationState();
  public final AnimationState standbyAnimationState = new AnimationState();

  public TitaniumGolemRenderState() {
  }
}
