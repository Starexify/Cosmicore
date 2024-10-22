package net.nova.cosmicore.entity;

import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.SectionPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.nova.cosmicore.init.CBlocks;

import static net.nova.cosmicore.Cosmicore.rl;

public class BaseMeteor extends Entity {
    public static final int SHIELD_CHECK_RADIUS = 100; // 100 blocks in each direction, creating a 200x200 area
    public static final int SHIELD_CHECK_INTERVAL = 20; // Check every second (20 ticks)

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
                    for (int y = serverLevel.getMinBuildHeight(); y < serverLevel.getMaxBuildHeight(); y++) {
                        BlockPos pos = new BlockPos(x, y, z);
                        BlockState state = serverLevel.getBlockState(pos);
                        if (isShieldBlock(state)) {
                            return true;
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

    // Attributes
    @Override
    public boolean isAttackable() {
        return false;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compound) {

    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compound) {

    }
}
