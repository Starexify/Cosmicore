package net.nova.cosmicore.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.nova.cosmicore.init.CItems;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class TitaniumGolem extends AbstractGolem implements NeutralMob {
    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState standbyAnimationState = new AnimationState();
    private boolean isInStandbyMode = false;
    private static final byte STANDBY_MODE_EVENT = 66;
    private static final byte REACTIVATE_EVENT = 67;

    public TitaniumGolem(EntityType<? extends TitaniumGolem> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public void tick() {
        if (this.level().isClientSide()) {
            if (!isInStandbyMode) {
                this.idleAnimationState.animateWhen(this.onGround() && !this.walkAnimation.isMoving(), this.tickCount);
            }
        }

        if (isInStandbyMode) {
            tickStandby();
        } else {
            super.tick();
        }
    }

    private void tickStandby() {
        this.setDeltaMovement(0, this.getDeltaMovement().y, 0);
    }

    @Override
    public boolean hurtServer(ServerLevel serverLevel, DamageSource damageSource, float damage) {
        if (isInStandbyMode) {
            if (shouldReactivate()) {
                reactivate();
                return false;
            }
            return false;
        }
        if (this.getHealth() - damage <= 0) {
            enterStandbyMode();
            return false;
        }

        return super.hurtServer(serverLevel, damageSource, damage);
    }

    private boolean shouldReactivate() {
        return false;
    }

    public void enterStandbyMode() {
        isInStandbyMode = true;
        this.level().broadcastEntityEvent(this, STANDBY_MODE_EVENT);
        if (this.level().isClientSide()) this.standbyAnimationState.start(this.tickCount);
        this.gameEvent(GameEvent.ENTITY_ACTION);
        this.setNoAi(true);
    }

    public void reactivate() {
        isInStandbyMode = false;
        if (this.level().isClientSide()) {
            this.idleAnimationState.start(this.tickCount);
            this.standbyAnimationState.stop();
        }
        this.level().broadcastEntityEvent(this, REACTIVATE_EVENT);
        this.gameEvent(GameEvent.ENTITY_ACTION);
        this.setPose(Pose.STANDING);
        this.setNoAi(false);
        this.setHealth(this.getMaxHealth());
    }

    @Override
    protected void tickDeath() {}

    @Override
    protected void updateWalkAnimation(float partialTick) {
        float f;
        if (this.getPose() == Pose.STANDING && !isInStandbyMode) {
            f = Math.min(partialTick * 6F, 1F);
        } else {
            f = 0;
        }
        super.updateWalkAnimation(f);
    }

    @Override
    protected InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (isInStandbyMode) {
            ItemStack itemStack = player.getItemInHand(hand);
            // Check if player has a special item to reactivate
            if (itemStack.is(CItems.OLIVINE)) {
                if (!player.getAbilities().instabuild) {
                    itemStack.shrink(1);
                }
                reactivate();
                return InteractionResult.SUCCESS;
            }
            return InteractionResult.PASS;
        }

        return super.mobInteract(player, hand);
    }

    @Override
    public void handleEntityEvent(byte id) {
        if (id == STANDBY_MODE_EVENT) {
            this.standbyAnimationState.start(this.tickCount);
            isInStandbyMode = true;
        } else if (id == REACTIVATE_EVENT) {
            this.idleAnimationState.start(this.tickCount);
            isInStandbyMode = false;
            this.setPose(Pose.STANDING);
        } else {
            super.handleEntityEvent(id);
        }
    }

    public boolean isInStandbyMode() {
        return isInStandbyMode;
    }

    @Override
    protected void registerGoals() {

        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 0.5, 1.0000001E-5F));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 1.0)
                .add(Attributes.MOVEMENT_SPEED, 0.17)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.7)
                .add(Attributes.ATTACK_DAMAGE, 30.0)
                .add(Attributes.STEP_HEIGHT, 1.0);
    }

    // Sounds
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.IRON_GOLEM_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.IRON_GOLEM_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.IRON_GOLEM_STEP, 1.0F, 0.6F);
    }

    @Override
    public int getRemainingPersistentAngerTime() {
        return 0;
    }

    @Override
    public void setRemainingPersistentAngerTime(int i) {

    }

    @Override
    public UUID getPersistentAngerTarget() {
        return null;
    }

    @Override
    public void setPersistentAngerTarget(@Nullable UUID uuid) {

    }

    @Override
    public void startPersistentAngerTimer() {

    }
}
