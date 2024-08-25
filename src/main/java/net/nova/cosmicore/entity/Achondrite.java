package net.nova.cosmicore.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.SectionPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.phys.Vec3;
import net.nova.cosmicore.Cosmicore;
import net.nova.cosmicore.data.worldgen.CStructures;

public class Achondrite extends Entity {
    private static final int DEATH_ANIMATION_DURATION = 40;
    public final AnimationState fallingAnimationState = new AnimationState();
    public final AnimationState explodedAnimationState = new AnimationState();
    private int deathAnimationTimer = -1;

    public Achondrite(EntityType<?> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    public void tick() {
        super.tick();

        if (deathAnimationTimer >= 0) {
            deathAnimationTimer--;

            if (this.level().isClientSide()) {
                this.explodedAnimationState.startIfStopped(this.tickCount);
            }

            if (deathAnimationTimer <= 0) {
                this.remove(RemovalReason.KILLED);
                placeStructure();
            }
            return;
        }

        double currentY = this.getY();

        double fallSpeed = -0.05;
        double forwardSpeed = 0.01;

        if (currentY < 100) {
            fallSpeed *= 4;
        } else if (currentY < 250) {
            forwardSpeed = forwardSpeed * (250 - currentY) / 250;
            fallSpeed *= 2.5;
        }

        Vec3 deltaMovement = this.getDeltaMovement();
        double motionXPart = deltaMovement.x;
        double motionZPart = deltaMovement.z;

        if (this.onGround()) {
            // Explosion Animation
            if (!this.isRemoved()) {
                if (this.level().isClientSide()) {
                    this.fallingAnimationState.stop();
                    this.explodedAnimationState.startIfStopped(this.tickCount);
                }
                this.level().addParticle(ParticleTypes.GUST_EMITTER_LARGE, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);

                // Start the death animation timer
                this.deathAnimationTimer = DEATH_ANIMATION_DURATION;
            }
            this.setDeltaMovement(Vec3.ZERO);
        } else {
            // Falling Animation
            if (this.level().isClientSide()) {
                this.fallingAnimationState.startIfStopped(this.tickCount);
            }

            for (int i = 0; i < 4; i++) {
                double xOffset = (this.random.nextDouble() - 0.5) * 0.2;
                double yOffset = this.random.nextDouble() * 0.2;
                double zOffset = (this.random.nextDouble() - 0.5) * 0.2;
                double xVelocity = (this.random.nextDouble() - 0.5) * 0.1;
                double yVelocity = this.random.nextDouble() * 0.1;
                double zVelocity = (this.random.nextDouble() - 0.5) * 0.1;
                this.level().addParticle(ParticleTypes.GUST, this.getX() + xOffset, this.getY() + yOffset, this.getZ() + zOffset, xVelocity, yVelocity, zVelocity);
                this.level().addParticle(ParticleTypes.FLAME, this.getX() + xOffset, this.getY() + yOffset, this.getZ() + zOffset, xVelocity, yVelocity, zVelocity);
            }

            for (int i = 0; i < 4; i++) {
                double xOffset = -motionXPart * (this.random.nextDouble() * 0.5 + 0.5);
                double yOffset = this.random.nextDouble() * 0.2;
                double zOffset = -motionZPart * (this.random.nextDouble() * 0.5 + 0.5);
                double xVelocity = -motionXPart * 0.1;
                double yVelocity = this.random.nextDouble() * 0.1;
                double zVelocity = -motionZPart * 0.1;
                this.level().addParticle(ParticleTypes.FLAME, this.getX() + xOffset, this.getY() + yOffset, this.getZ() + zOffset, xVelocity, yVelocity, zVelocity);
            }

            // Meteor Moving
            double motionX = -Math.sin(Math.toRadians(this.getYRot())) * forwardSpeed;
            double motionZ = Math.cos(Math.toRadians(this.getYRot())) * forwardSpeed;
            this.setDeltaMovement(this.getDeltaMovement().add(motionX, fallSpeed, motionZ));
            this.move(MoverType.SELF, this.getDeltaMovement());
        }
    }

    public Structure getStructure() {
        return level().registryAccess().registryOrThrow(CStructures.DESERT_METEOR_SITE.registryKey()).getHolderOrThrow(CStructures.DESERT_METEOR_SITE).value();
    }

    public void placeStructure() {
        if (level() instanceof ServerLevel serverlevel) {
            BlockPos pos = this.blockPosition();
            Structure structure = getStructure();
            ChunkGenerator chunkgenerator = serverlevel.getChunkSource().getGenerator();
            StructureStart structurestart = structure.generate(
                    this.registryAccess(),
                    chunkgenerator,
                    chunkgenerator.getBiomeSource(),
                    serverlevel.getChunkSource().randomState(),
                    serverlevel.getStructureManager(),
                    serverlevel.getSeed(),
                    new ChunkPos(pos),
                    0,
                    serverlevel,
                    p_214580_ -> true
            );
            if (!structurestart.isValid()) {
                Cosmicore.logger.error("Failed to place Structure");
            } else {
                BoundingBox boundingbox = structurestart.getBoundingBox();
                ChunkPos chunkpos = new ChunkPos(SectionPos.blockToSectionCoord(boundingbox.minX()), SectionPos.blockToSectionCoord(boundingbox.minZ()));
                ChunkPos chunkpos1 = new ChunkPos(SectionPos.blockToSectionCoord(boundingbox.maxX()), SectionPos.blockToSectionCoord(boundingbox.maxZ()));
                ChunkPos.rangeClosed(chunkpos, chunkpos1)
                        .forEach(
                                p_340665_ -> structurestart.placeInChunk(
                                        serverlevel,
                                        serverlevel.structureManager(),
                                        chunkgenerator,
                                        serverlevel.getRandom(),
                                        new BoundingBox(
                                                p_340665_.getMinBlockX(),
                                                serverlevel.getMinBuildHeight(),
                                                p_340665_.getMinBlockZ(),
                                                p_340665_.getMaxBlockX(),
                                                serverlevel.getMaxBuildHeight(),
                                                p_340665_.getMaxBlockZ()
                                        ),
                                        p_340665_
                                )
                        );
            }
        }
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder pBuilder) {
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag pCompound) {
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag pCompound) {
    }

    @Override
    public boolean isAttackable() {
        return false;
    }
}
