package net.nova.cosmicore.animations;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

public interface TitaniumGolemAnimation {
  AnimationDefinition IDLE = AnimationDefinition.Builder.withLength(2.0F).looping()
      .addAnimation("arms", new AnimationChannel(AnimationChannel.Targets.POSITION,
          new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
          new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, -0.1F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
          new Keyframe(2.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
      ))
      .addAnimation("torso_main", new AnimationChannel(AnimationChannel.Targets.POSITION,
          new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -0.1F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
          new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, -0.1F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
          new Keyframe(2.0F, KeyframeAnimations.posVec(0.0F, -0.1F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
      ))
      .addAnimation("mouth", new AnimationChannel(AnimationChannel.Targets.POSITION,
          new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
          new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, -0.1F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
          new Keyframe(2.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
      ))
      .build();

  AnimationDefinition WALK = AnimationDefinition.Builder.withLength(1.75F).looping()
      .addAnimation("arm0", new AnimationChannel(AnimationChannel.Targets.ROTATION,
          new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("arm1", new AnimationChannel(AnimationChannel.Targets.ROTATION,
          new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("leg0", new AnimationChannel(AnimationChannel.Targets.POSITION,
          new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
          new Keyframe(0.25F, KeyframeAnimations.posVec(0.0F, 3.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
          new Keyframe(0.5F, KeyframeAnimations.posVec(0.0F, 3.0F, -1.25F), AnimationChannel.Interpolations.LINEAR),
          new Keyframe(0.75F, KeyframeAnimations.posVec(0.0F, 0.0F, -1.25F), AnimationChannel.Interpolations.LINEAR),
          new Keyframe(1.75F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("leg1", new AnimationChannel(AnimationChannel.Targets.POSITION,
          new Keyframe(0.75F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
          new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, 3.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
          new Keyframe(1.25F, KeyframeAnimations.posVec(0.0F, 3.0F, -1.25F), AnimationChannel.Interpolations.LINEAR),
          new Keyframe(1.5F, KeyframeAnimations.posVec(0.0F, 0.0F, -1.25F), AnimationChannel.Interpolations.LINEAR),
          new Keyframe(1.75F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .build();

  AnimationDefinition WAKE = AnimationDefinition.Builder.withLength(2.0F)
      .addAnimation("arms", new AnimationChannel(AnimationChannel.Targets.ROTATION,
          new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("arms", new AnimationChannel(AnimationChannel.Targets.POSITION,
          new Keyframe(2.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("arms", new AnimationChannel(AnimationChannel.Targets.SCALE,
          new Keyframe(2.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("arm0", new AnimationChannel(AnimationChannel.Targets.ROTATION,
          new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("arm0", new AnimationChannel(AnimationChannel.Targets.POSITION,
          new Keyframe(2.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("arm0", new AnimationChannel(AnimationChannel.Targets.SCALE,
          new Keyframe(2.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("up0", new AnimationChannel(AnimationChannel.Targets.ROTATION,
          new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("up0", new AnimationChannel(AnimationChannel.Targets.POSITION,
          new Keyframe(0.5F, KeyframeAnimations.posVec(0.0F, -13.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
          new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("up0", new AnimationChannel(AnimationChannel.Targets.SCALE,
          new Keyframe(1.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("down_and_piston0", new AnimationChannel(AnimationChannel.Targets.ROTATION,
          new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("down_and_piston0", new AnimationChannel(AnimationChannel.Targets.POSITION,
          new Keyframe(1.75F, KeyframeAnimations.posVec(0.0F, -9.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
          new Keyframe(2.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("down_and_piston0", new AnimationChannel(AnimationChannel.Targets.SCALE,
          new Keyframe(2.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("up1", new AnimationChannel(AnimationChannel.Targets.ROTATION,
          new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("up1", new AnimationChannel(AnimationChannel.Targets.POSITION,
          new Keyframe(0.5F, KeyframeAnimations.posVec(0.0F, -13.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
          new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("up1", new AnimationChannel(AnimationChannel.Targets.SCALE,
          new Keyframe(1.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("down_and_piston1", new AnimationChannel(AnimationChannel.Targets.POSITION,
          new Keyframe(1.75F, KeyframeAnimations.posVec(0.0F, -9.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
          new Keyframe(2.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("torso_down_and_legs", new AnimationChannel(AnimationChannel.Targets.ROTATION,
          new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("torso_down_and_legs", new AnimationChannel(AnimationChannel.Targets.POSITION,
          new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, 21.5F, 0.0F), AnimationChannel.Interpolations.LINEAR),
          new Keyframe(1.5F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("torso_down_and_legs", new AnimationChannel(AnimationChannel.Targets.SCALE,
          new Keyframe(1.5F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("legs", new AnimationChannel(AnimationChannel.Targets.ROTATION,
          new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("legs", new AnimationChannel(AnimationChannel.Targets.POSITION,
          new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
          new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("legs", new AnimationChannel(AnimationChannel.Targets.SCALE,
          new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("leg0", new AnimationChannel(AnimationChannel.Targets.ROTATION,
          new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("leg0", new AnimationChannel(AnimationChannel.Targets.POSITION,
          new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("leg0", new AnimationChannel(AnimationChannel.Targets.SCALE,
          new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("leg1", new AnimationChannel(AnimationChannel.Targets.ROTATION,
          new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("leg1", new AnimationChannel(AnimationChannel.Targets.POSITION,
          new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("leg1", new AnimationChannel(AnimationChannel.Targets.SCALE,
          new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION,
          new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("head", new AnimationChannel(AnimationChannel.Targets.POSITION,
          new Keyframe(0.5F, KeyframeAnimations.posVec(0.0F, -21.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
          new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("head", new AnimationChannel(AnimationChannel.Targets.SCALE,
          new Keyframe(1.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("mouth", new AnimationChannel(AnimationChannel.Targets.POSITION,
          new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -5.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
          new Keyframe(0.5F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("torso_and_legs", new AnimationChannel(AnimationChannel.Targets.ROTATION,
          new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
          new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("torso_and_legs", new AnimationChannel(AnimationChannel.Targets.POSITION,
          new Keyframe(0.5F, KeyframeAnimations.posVec(0.0F, -21.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
          new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("torso_and_legs", new AnimationChannel(AnimationChannel.Targets.SCALE,
          new Keyframe(0.5F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR),
          new Keyframe(1.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .build();

  AnimationDefinition STANDBY = AnimationDefinition.Builder.withLength(2.0F)
      .addAnimation("arms", new AnimationChannel(AnimationChannel.Targets.ROTATION,
          new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("arms", new AnimationChannel(AnimationChannel.Targets.POSITION,
          new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("arms", new AnimationChannel(AnimationChannel.Targets.SCALE,
          new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("arm0", new AnimationChannel(AnimationChannel.Targets.ROTATION,
          new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("arm0", new AnimationChannel(AnimationChannel.Targets.POSITION,
          new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("arm0", new AnimationChannel(AnimationChannel.Targets.SCALE,
          new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("up0", new AnimationChannel(AnimationChannel.Targets.ROTATION,
          new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("up0", new AnimationChannel(AnimationChannel.Targets.POSITION,
          new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
          new Keyframe(1.5F, KeyframeAnimations.posVec(0.0F, -13.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("up0", new AnimationChannel(AnimationChannel.Targets.SCALE,
          new Keyframe(1.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("down_and_piston0", new AnimationChannel(AnimationChannel.Targets.ROTATION,
          new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("down_and_piston0", new AnimationChannel(AnimationChannel.Targets.POSITION,
          new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
          new Keyframe(0.25F, KeyframeAnimations.posVec(0.0F, -9.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("down_and_piston0", new AnimationChannel(AnimationChannel.Targets.SCALE,
          new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("arm1", new AnimationChannel(AnimationChannel.Targets.ROTATION,
          new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("arm1", new AnimationChannel(AnimationChannel.Targets.POSITION,
          new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("arm1", new AnimationChannel(AnimationChannel.Targets.SCALE,
          new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("up1", new AnimationChannel(AnimationChannel.Targets.ROTATION,
          new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("up1", new AnimationChannel(AnimationChannel.Targets.POSITION,
          new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
          new Keyframe(1.5F, KeyframeAnimations.posVec(0.0F, -13.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("up1", new AnimationChannel(AnimationChannel.Targets.SCALE,
          new Keyframe(1.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("down_and_piston1", new AnimationChannel(AnimationChannel.Targets.ROTATION,
          new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("down_and_piston1", new AnimationChannel(AnimationChannel.Targets.POSITION,
          new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
          new Keyframe(0.25F, KeyframeAnimations.posVec(0.0F, -9.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("down_and_piston1", new AnimationChannel(AnimationChannel.Targets.SCALE,
          new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("torso_down_and_legs", new AnimationChannel(AnimationChannel.Targets.ROTATION,
          new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("torso_down_and_legs", new AnimationChannel(AnimationChannel.Targets.POSITION,
          new Keyframe(0.5F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
          new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, 21.5F, 0.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("torso_down_and_legs", new AnimationChannel(AnimationChannel.Targets.SCALE,
          new Keyframe(0.5F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("legs", new AnimationChannel(AnimationChannel.Targets.ROTATION,
          new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("legs", new AnimationChannel(AnimationChannel.Targets.POSITION,
          new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
          new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("legs", new AnimationChannel(AnimationChannel.Targets.SCALE,
          new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("leg0", new AnimationChannel(AnimationChannel.Targets.ROTATION,
          new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("leg0", new AnimationChannel(AnimationChannel.Targets.POSITION,
          new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("leg0", new AnimationChannel(AnimationChannel.Targets.SCALE,
          new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("leg1", new AnimationChannel(AnimationChannel.Targets.ROTATION,
          new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("leg1", new AnimationChannel(AnimationChannel.Targets.POSITION,
          new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("leg1", new AnimationChannel(AnimationChannel.Targets.SCALE,
          new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION,
          new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("head", new AnimationChannel(AnimationChannel.Targets.POSITION,
          new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
          new Keyframe(1.5F, KeyframeAnimations.posVec(0.0F, -21.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("head", new AnimationChannel(AnimationChannel.Targets.SCALE,
          new Keyframe(1.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("mouth", new AnimationChannel(AnimationChannel.Targets.POSITION,
          new Keyframe(1.5F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
          new Keyframe(2.0F, KeyframeAnimations.posVec(0.0F, -5.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("torso_and_legs", new AnimationChannel(AnimationChannel.Targets.ROTATION,
          new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
          new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("torso_and_legs", new AnimationChannel(AnimationChannel.Targets.POSITION,
          new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
          new Keyframe(1.5F, KeyframeAnimations.posVec(0.0F, -21.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("torso_and_legs", new AnimationChannel(AnimationChannel.Targets.SCALE,
          new Keyframe(1.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR),
          new Keyframe(1.5F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .build();

  AnimationDefinition ATTACK = AnimationDefinition.Builder.withLength(1.25F)
      .addAnimation("arms", new AnimationChannel(AnimationChannel.Targets.ROTATION,
          new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
          new Keyframe(0.5F, KeyframeAnimations.degreeVec(-180.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
          new Keyframe(1.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("arms", new AnimationChannel(AnimationChannel.Targets.POSITION,
          new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("arms", new AnimationChannel(AnimationChannel.Targets.SCALE,
          new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .build();

  AnimationDefinition PISTON_ATTACK = AnimationDefinition.Builder.withLength(2.5F)
      .addAnimation("arms", new AnimationChannel(AnimationChannel.Targets.ROTATION,
          new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
          new Keyframe(0.5F, KeyframeAnimations.degreeVec(-67.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
          new Keyframe(1.0F, KeyframeAnimations.degreeVec(-67.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
          new Keyframe(2.5F, KeyframeAnimations.degreeVec(-360.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("arms", new AnimationChannel(AnimationChannel.Targets.POSITION,
          new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
          new Keyframe(0.5F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
          new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
          new Keyframe(2.5F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("arms", new AnimationChannel(AnimationChannel.Targets.SCALE,
          new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR),
          new Keyframe(0.5F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR),
          new Keyframe(1.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR),
          new Keyframe(2.5F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("down_and_piston0", new AnimationChannel(AnimationChannel.Targets.ROTATION,
          new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
          new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
          new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("down_and_piston0", new AnimationChannel(AnimationChannel.Targets.POSITION,
          new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
          new Keyframe(0.5F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
          new Keyframe(0.5833F, KeyframeAnimations.posVec(0.0F, 3.83F, 0.0F), AnimationChannel.Interpolations.LINEAR),
          new Keyframe(0.75F, KeyframeAnimations.posVec(0.0F, -19.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
          new Keyframe(2.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("down_and_piston0", new AnimationChannel(AnimationChannel.Targets.SCALE,
          new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR),
          new Keyframe(0.5F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR),
          new Keyframe(2.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("down_and_piston1", new AnimationChannel(AnimationChannel.Targets.ROTATION,
          new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
          new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
          new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("down_and_piston1", new AnimationChannel(AnimationChannel.Targets.POSITION,
          new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
          new Keyframe(0.5F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
          new Keyframe(0.5833F, KeyframeAnimations.posVec(0.0F, 3.83F, 0.0F), AnimationChannel.Interpolations.LINEAR),
          new Keyframe(0.75F, KeyframeAnimations.posVec(0.0F, -19.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
          new Keyframe(2.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .addAnimation("down_and_piston1", new AnimationChannel(AnimationChannel.Targets.SCALE,
          new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR),
          new Keyframe(0.5F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR),
          new Keyframe(2.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR)
      ))
      .build();
}