package net.nova.cosmicore.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.SectionPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.TickTask;
import net.minecraft.server.level.ServerChunkCache;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.*;
import net.minecraft.world.phys.Vec3;
import net.nova.cosmicore.Cosmicore;
import net.nova.cosmicore.init.CTags;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Achondrite extends BaseMeteor {
    public static final int DEATH_ANIMATION_DURATION = 40;
    public final AnimationState fallingAnimationState = new AnimationState();
    public final AnimationState explodedAnimationState = new AnimationState();
    public int deathAnimationTimer = -1;
    public BlockPos landingPos;
    public boolean isLanded = false;
    public static final int DESTRUCTION_RADIUS = 8;
    public int shieldCheckCounter = 0;

    public Achondrite(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public void tick() {
        super.tick();

        if (++shieldCheckCounter >= SHIELD_CHECK_INTERVAL) {
            shieldCheckCounter = 0;
            if (isShieldNearby()) {
                this.remove(RemovalReason.KILLED);
                if (!level().isClientSide()) {
                    ServerLevel serverLevel = (ServerLevel) level();
                    serverLevel.getServer().getPlayerList().broadcastSystemMessage(METEOR_SHIELDED_MESSAGE, false);
                }
                return;
            }
        }

        // *dies*
        if (deathAnimationTimer >= 0) {
            deathAnimationTimer--;

            if (this.level().isClientSide()) {
                this.explodedAnimationState.startIfStopped(this.tickCount);
            }

            if (deathAnimationTimer <= 0) {
                if (!this.level().isClientSide()) {
                    ServerLevel serverLevel = (ServerLevel) this.level();
                    serverLevel.getServer().tell(new TickTask(1, this::safeStructurePlacement));
                }
                this.remove(RemovalReason.KILLED);
                return;
            }
        }

        // Destroy blocks around and update death animations
        if (this.level().isClientSide()) {
            updateClientAnimations();
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

        if (this.onGround() && !isLanded) {
            handleLanding();
        }

        // Handle Falling
        if (!this.onGround()) {
            if (this.level().isClientSide()) {
                this.fallingAnimationState.startIfStopped(this.tickCount);
            } else {
                destroyNearbyBlocks();
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

    public void updateClientAnimations() {
        if (this.onGround() && !isLanded) {
            this.fallingAnimationState.stop();
            this.explodedAnimationState.startIfStopped(this.tickCount);
        } else if (!this.onGround()) {
            this.fallingAnimationState.startIfStopped(this.tickCount);
        }
    }

    public void handleLanding() {
        isLanded = true;
        this.landingPos = this.blockPosition();
        this.setDeltaMovement(Vec3.ZERO);
        this.level().addParticle(ParticleTypes.GUST_EMITTER_LARGE, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);

        // Start the death animation timer
        this.deathAnimationTimer = DEATH_ANIMATION_DURATION;
    }

    // Destroy Blocks Nearby method
    public void destroyNearbyBlocks() {
        ServerLevel serverLevel = (ServerLevel) this.level();
        BlockPos centerPos = this.blockPosition();
        RandomSource random = serverLevel.getRandom();

        final double DROP_CHANCE = 0.5;
        boolean shouldDrop = random.nextDouble() < DROP_CHANCE;

        for (BlockPos pos : BlockPos.betweenClosed(
                centerPos.offset(-DESTRUCTION_RADIUS, -DESTRUCTION_RADIUS, -DESTRUCTION_RADIUS),
                centerPos.offset(DESTRUCTION_RADIUS, DESTRUCTION_RADIUS, DESTRUCTION_RADIUS))) {
            BlockState state = serverLevel.getBlockState(pos);
            if (shouldDestroyBlock(state)) {
                serverLevel.destroyBlock(pos, shouldDrop);
            }
        }
    }

    public boolean shouldDestroyBlock(BlockState state) {
        return state.is(CTags.BlockTags.METEOR_BREAKABLES);
    }

    public Structure getStructure() {
        return level().registryAccess().registryOrThrow(BuiltinStructures.SWAMP_HUT.registryKey()).getHolderOrThrow(BuiltinStructures.SWAMP_HUT).value();
    }

    public void safeStructurePlacement() {
        if (!(level() instanceof ServerLevel serverlevel)) {
            return;
        }

        BlockPos pos = this.landingPos != null ? this.landingPos : this.blockPosition();
        Structure structure = getStructure();
        ChunkGenerator chunkgenerator = serverlevel.getChunkSource().getGenerator();
        ServerChunkCache chunkCache = serverlevel.getChunkSource();
        StructureStart structurestart = structure.generate(
                serverlevel.registryAccess(),
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
            Cosmicore.logger.error("Failed to generate Structure");
            return;
        }

        BoundingBox boundingbox = structurestart.getBoundingBox();

        // Calculate the offset to center the structure on the given position
        int offsetX = pos.getX() - (boundingbox.minX() + boundingbox.maxX()) / 2;
        int offsetY = pos.getY() - boundingbox.minY();
        int offsetZ = pos.getZ() - (boundingbox.minZ() + boundingbox.maxZ()) / 2;
        boundingbox = boundingbox.move(offsetX, offsetY, offsetZ);

        for (StructurePiece piece : structurestart.getPieces()) {
            piece.move(offsetX, offsetY, offsetZ);
        }

        ChunkPos chunkpos = new ChunkPos(SectionPos.blockToSectionCoord(boundingbox.minX()), SectionPos.blockToSectionCoord(boundingbox.minZ()));
        ChunkPos chunkpos1 = new ChunkPos(SectionPos.blockToSectionCoord(boundingbox.maxX()), SectionPos.blockToSectionCoord(boundingbox.maxZ()));

        List<CompletableFuture<ChunkAccess>> chunkLoadFutures = new ArrayList<>();

/*        for (int x = chunkpos.x; x <= chunkpos1.x; x++) {
            for (int z = chunkpos.z; z <= chunkpos1.z; z++) {
                chunkLoadFutures.add(chunkCache.getChunkFutureMainThread(x, z, ChunkStatus.FULL, true));
            }
        }*/

        CompletableFuture.allOf(chunkLoadFutures.toArray(new CompletableFuture[0])).thenRun(() -> {
            for (int x = chunkpos.x; x <= chunkpos1.x; x++) {
                for (int z = chunkpos.z; z <= chunkpos1.z; z++) {
                    ChunkPos currentChunkPos = new ChunkPos(x, z);
                    structurestart.placeInChunk(
                            serverlevel,
                            serverlevel.structureManager(),
                            chunkgenerator,
                            serverlevel.getRandom(),
                            new BoundingBox(
                                    currentChunkPos.getMinBlockX(),
                                    serverlevel.getMinBuildHeight(),
                                    currentChunkPos.getMinBlockZ(),
                                    currentChunkPos.getMaxBlockX(),
                                    serverlevel.getMaxBuildHeight(),
                                    currentChunkPos.getMaxBlockZ()
                            ),
                            currentChunkPos
                    );
                }
            }
            Cosmicore.logger.info("Structure placement completed successfully");
        });
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag pCompound) {
        if (pCompound.contains("LandingPos")) {
            int[] pos = pCompound.getIntArray("LandingPos");
            if (pos.length == 3) {
                this.landingPos = new BlockPos(pos[0], pos[1], pos[2]);
            }
        }
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag pCompound) {
        if (landingPos != null) {
            pCompound.putIntArray("LandingPos", new int[]{landingPos.getX(), landingPos.getY(), landingPos.getZ()});
        }
    }
}
