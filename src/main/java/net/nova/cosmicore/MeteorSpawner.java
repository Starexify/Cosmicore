package net.nova.cosmicore;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.nova.cosmicore.entity.Achondrite;
import net.nova.cosmicore.entity.BaseMeteor;
import net.nova.cosmicore.entity.Meteorite;
import net.nova.cosmicore.init.CEntities;

public class MeteorSpawner {
    public final ServerLevel level;
    public int tickCounter;
    public int ticksUntilNextMeteor;
    public final RandomSource random;
    public final int minTicksUntilNextMeteor;
    public final int maxTicksUntilNextMeteor;

    public MeteorSpawner(ServerLevel level, int minTicksUntilNextMeteor, int maxTicksUntilNextMeteor) {
        this.level = level;
        this.random = level.getRandom();
        this.minTicksUntilNextMeteor = minTicksUntilNextMeteor;
        this.maxTicksUntilNextMeteor = maxTicksUntilNextMeteor;
        this.tickCounter = 0;
        this.ticksUntilNextMeteor = random.nextIntBetweenInclusive(minTicksUntilNextMeteor, maxTicksUntilNextMeteor);
    }

    private void announceNextMeteor() {
        // Convert ticks to seconds (20 ticks = 1 second)
        int seconds = (ticksUntilNextMeteor - tickCounter) / 20;
        Component message = Component.literal("Next meteor will spawn in " + seconds + " seconds").withStyle(ChatFormatting.GOLD);

        // Broadcast to all players
        for (ServerPlayer player : level.players()) {
            player.sendSystemMessage(message);
        }
    }

    public void resetAfterSpawn() {
        this.tickCounter = 0;
        this.ticksUntilNextMeteor = random.nextIntBetweenInclusive(minTicksUntilNextMeteor, maxTicksUntilNextMeteor);
    }

    public void onTick() {
        if (level.players().isEmpty()) return;

        tickCounter++;

        if (tickCounter >= ticksUntilNextMeteor) {
            spawnMeteorNearRandomPlayer();
            resetAfterSpawn();
        }
        //announceNextMeteor();
    }

    public void spawnMeteorNearRandomPlayer() {
        if (level.players().isEmpty()) return;

        ServerPlayer randomPlayer = level.players().get(random.nextInt(level.players().size()));
        BlockPos playerPos = randomPlayer.blockPosition();

        int distance = random.nextIntBetweenInclusive(900, 2400);
        int angle = random.nextInt(360);

        double radians = Math.toRadians(angle);
        int x = playerPos.getX() + (int) (distance * Math.cos(radians));
        int z = playerPos.getZ() + (int) (distance * Math.sin(radians));

        BlockPos spawnPos = new BlockPos(x, 320, z);

        // 70% chance for Achondrite, 30% chance for Meteorite
        BaseMeteor meteor = random.nextFloat() < 0.7
                ? new Achondrite(CEntities.ACHONDRITE.get(), level)
                : new Meteorite(CEntities.METEORITE.get(), level);

        meteor.setPos(spawnPos.getX() + 0.5, 320, spawnPos.getZ() + 0.5);
        level.addFreshEntity(meteor);
    }
}