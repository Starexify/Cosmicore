package net.nova.cosmicore.entity;

import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.SectionPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.phys.Vec3;
import net.nova.cosmicore.Cosmicore;
import net.nova.cosmicore.blockentity.CosmicShieldTile;
import net.nova.cosmicore.data.worldgen.CStructures;
import net.nova.cosmicore.init.CBlocks;
import net.nova.cosmicore.init.CTags;

import static net.nova.cosmicore.Cosmicore.rl;

public class BaseMeteor extends Entity {
    public final AnimationState fallingAnimationState = new AnimationState();
    public final AnimationState explodedAnimationState = new AnimationState();
    public boolean isLanded = false;

    public static final int DEATH_ANIMATION_DURATION = 40;
    public int deathAnimationTimer = -1;
    public BlockPos landingPos;

    public static final int DESTRUCTION_RADIUS = 10;

    public static final int SHIELD_CHECK_RADIUS = 100; // 100 blocks in each direction, creating a 200x200 area
    public static final int SHIELD_CHECK_INTERVAL = 20; // Check every second (20 ticks)
    public int shieldCheckCounter = 0;

    public static final Component METEOR_FALL_MESSAGE = Component.translatable(
            Util.makeDescriptionId("message", rl("meteor_fall"))
    ).withStyle(ChatFormatting.RED, ChatFormatting.BOLD);

    public static final Component METEOR_SHIELDED_MESSAGE = Component.translatable(
            Util.makeDescriptionId("message", rl("meteor_shielded"))
    ).withStyle(ChatFormatting.BLUE, ChatFormatting.BOLD);

    public BaseMeteor(EntityType<?> entityType, Level level) {
        super(entityType, level);

        if (!level.isClientSide()) {
            ServerLevel serverLevel = (ServerLevel) level;
            serverLevel.getServer().getPlayerList().broadcastSystemMessage(METEOR_FALL_MESSAGE, false);
        }
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
                craterPlacement();

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

    @Override
    public boolean hurtServer(ServerLevel serverLevel, DamageSource damageSource, float v) {
        return false;
    }

    // Handlers
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

    // Structure Placement
    public Structure getStructure() {
        return level().registryAccess().lookupOrThrow(Registries.STRUCTURE).getValueOrThrow(CStructures.ACHONDRITE_METEOR);
    }

    public void craterPlacement() {
        if (!(level() instanceof ServerLevel serverlevel)) return;

        BlockPos pos = this.landingPos != null ? this.landingPos : this.blockPosition();
        Structure structure = getStructure();
        ChunkGenerator chunkgenerator = serverlevel.getChunkSource().getGenerator();
        StructureStart structurestart = structure.generate(
                Holder.direct(structure),
                serverlevel.dimension(),
                serverlevel.registryAccess(),
                chunkgenerator,
                chunkgenerator.getBiomeSource(),
                serverlevel.getChunkSource().randomState(),
                serverlevel.getStructureManager(),
                serverlevel.getSeed(),
                new ChunkPos(pos),
                0,
                serverlevel,
                biomeHolder -> true
        );

        if (!structurestart.isValid()) {
            Cosmicore.logger.error("[Cosmicore] Failed to generate Crater");
            return;
        }

        BoundingBox boundingbox = structurestart.getBoundingBox();

        int offsetX = pos.getX() - (boundingbox.minX() + boundingbox.maxX()) / 2;
        int targetY = pos.getY() - 36; // - x means it places it x blocks underground (because structure)
        int offsetY = targetY - boundingbox.minY();
        int offsetZ = pos.getZ() - (boundingbox.minZ() + boundingbox.maxZ()) / 2;

        boundingbox = boundingbox.moved(offsetX, offsetY, offsetZ);

        for (StructurePiece piece : structurestart.getPieces()) {
            piece.move(offsetX, offsetY, offsetZ);
        }

        ChunkPos chunkpos = new ChunkPos(SectionPos.blockToSectionCoord(boundingbox.minX()), SectionPos.blockToSectionCoord(boundingbox.minZ()));
        ChunkPos chunkpos1 = new ChunkPos(SectionPos.blockToSectionCoord(boundingbox.maxX()), SectionPos.blockToSectionCoord(boundingbox.maxZ()));

        serverlevel.getChunkSource().chunkMap.getPlayers(new ChunkPos(pos), false).forEach(serverPlayer -> {
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
                                    serverlevel.getMinY(),
                                    currentChunkPos.getMinBlockZ(),
                                    currentChunkPos.getMaxBlockX(),
                                    serverlevel.getMaxY(),
                                    currentChunkPos.getMaxBlockZ()
                            ),
                            currentChunkPos
                    );
                }
            }
        });

        for (int x = boundingbox.minX(); x <= boundingbox.maxX(); x++) {
            for (int y = boundingbox.minY(); y <= boundingbox.maxY(); y++) {
                for (int z = boundingbox.minZ(); z <= boundingbox.maxZ(); z++) {
                    BlockPos updatePos = new BlockPos(x, y, z);
                    BlockState state = serverlevel.getBlockState(updatePos);

                    // Force update for all block types
                    serverlevel.setBlock(updatePos, state, 3);
                    serverlevel.updateNeighborsAt(updatePos, state.getBlock());

                    // Additional check for blocks that need support
                    if (!state.canSurvive(serverlevel, updatePos)) {
                        serverlevel.removeBlock(updatePos, false);
                    }
                }
            }
        }

        Cosmicore.logger.info("[Cosmicore] Crater placed successfully");
    }

    // Shield Detection
    public boolean isShieldNearby() {
        if (this.level() instanceof ServerLevel serverLevel) {
            BlockPos centerPos = this.blockPosition();
            int chunkRadius = SHIELD_CHECK_RADIUS >> 4; // Convert block radius to chunk radius

            for (int chunkX = -chunkRadius; chunkX <= chunkRadius; chunkX++) {
                for (int chunkZ = -chunkRadius; chunkZ <= chunkRadius; chunkZ++) {
                    ChunkPos chunkPos = new ChunkPos(
                            SectionPos.blockToSectionCoord(centerPos.getX()) + chunkX,
                            SectionPos.blockToSectionCoord(centerPos.getZ()) + chunkZ
                    );

                    // Only check loaded chunks to avoid lag
                    if (serverLevel.isLoaded(chunkPos.getWorldPosition())) {
                        if (checkChunkForShield(serverLevel, chunkPos, centerPos)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean checkChunkForShield(ServerLevel serverLevel, ChunkPos chunkPos, BlockPos centerPos) {
        int minX = chunkPos.getMinBlockX();
        int minZ = chunkPos.getMinBlockZ();
        int maxX = chunkPos.getMaxBlockX();
        int maxZ = chunkPos.getMaxBlockZ();

        for (int x = minX; x <= maxX; x++) {
            for (int z = minZ; z <= maxZ; z++) {
                if (Math.abs(x - centerPos.getX()) <= SHIELD_CHECK_RADIUS &&
                        Math.abs(z - centerPos.getZ()) <= SHIELD_CHECK_RADIUS) {
                    for (int y = serverLevel.getMinY(); y < serverLevel.getMaxY(); y++) {
                        BlockPos pos = new BlockPos(x, y, z);
                        BlockState state = serverLevel.getBlockState(pos);
                        if (isShieldBlock(state)) {
                            BlockEntity blockEntity = serverLevel.getBlockEntity(pos);
                            if (blockEntity instanceof CosmicShieldTile && ((CosmicShieldTile) blockEntity).inventory.getSlots() != 0) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean isShieldBlock(BlockState state) {
        return state.is(CBlocks.COSMIC_SHIELD);
    }

    // Destroy Blocks Nearby method
    public void destroyNearbyBlocks() {
        ServerLevel serverLevel = (ServerLevel) this.level();
        BlockPos centerPos = this.blockPosition();

        for (BlockPos pos : BlockPos.betweenClosed(
                centerPos.offset(-DESTRUCTION_RADIUS, -DESTRUCTION_RADIUS, -DESTRUCTION_RADIUS),
                centerPos.offset(DESTRUCTION_RADIUS, DESTRUCTION_RADIUS, DESTRUCTION_RADIUS))) {
            BlockState state = serverLevel.getBlockState(pos);
            if (shouldDestroyBlock(state)) {
                serverLevel.removeBlock(pos, false);
            }
        }
    }

    public boolean shouldDestroyBlock(BlockState state) {
        return state.is(CTags.BlockTags.METEOR_BREAKABLES);
    }

    // Attributes
    @Override
    public boolean isAttackable() {
        return false;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
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
