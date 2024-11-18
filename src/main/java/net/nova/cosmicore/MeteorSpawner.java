package net.nova.cosmicore;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.nova.cosmicore.entity.Achondrite;
import net.nova.cosmicore.init.CEntities;

public class MeteorSpawner {
    private final ServerLevel level;
    private int tickCounter;
    private int ticksUntilNextMeteor;
    private final RandomSource random;
    private int lastSecondAnnounced = -1;

    public MeteorSpawner(ServerLevel level, int minTicksUntilNextMeteor, int maxTicksUntilNextMeteor) {
        this.level = level;
        this.tickCounter = 0;
        this.random = level.getRandom();
        this.ticksUntilNextMeteor = random.nextIntBetweenInclusive(minTicksUntilNextMeteor, maxTicksUntilNextMeteor);
    }

    public void onTick() {
        if (!level.players().isEmpty()) {
            tickCounter++;

            // Calculate remaining seconds
            int remainingSeconds = (ticksUntilNextMeteor - tickCounter) / 20;

            // Announce each second until meteor spawns
            if (remainingSeconds >= 0 && remainingSeconds != lastSecondAnnounced) {
                broadcastCountdown(remainingSeconds);
                lastSecondAnnounced = remainingSeconds;
            }

            if (tickCounter >= ticksUntilNextMeteor) {
                if (isInWhitelistedDimension()) {
                    spawnMeteorNearRandomPlayer();
                }
                tickCounter = 0;
                this.ticksUntilNextMeteor = 36000;
                lastSecondAnnounced = -1;
            }
        } else {
            tickCounter = 0;
            lastSecondAnnounced = -1;
        }
    }

    private void broadcastCountdown(int seconds) {
        if (seconds > 0) {
            for (ServerPlayer player : level.players()) {
                player.sendSystemMessage(Component.literal("Meteor spawning in " + seconds + " second" + (seconds != 1 ? "s" : "")));
            }
        }
    }

    private boolean isInWhitelistedDimension() {
        return level.dimension() == Level.OVERWORLD;
    }

    private void spawnMeteorNearRandomPlayer() {
        if (level.players().isEmpty()) return;

        ServerPlayer randomPlayer = level.players().get(random.nextInt(level.players().size()));
        BlockPos playerPos = randomPlayer.blockPosition();

        int distance = random.nextIntBetweenInclusive(1000, 3000);
        int angle = random.nextInt(360);

        double radians = Math.toRadians(angle);
        int x = playerPos.getX() + (int) (distance * Math.cos(radians));
        int z = playerPos.getZ() + (int) (distance * Math.sin(radians));

        BlockPos spawnPos = new BlockPos(x, 320, z);
        Achondrite meteor = new Achondrite(CEntities.ACHONDRITE.get(), level);
        meteor.setPos(spawnPos.getX() + 0.5, 320, spawnPos.getZ() + 0.5);
        level.addFreshEntity(meteor);
    }
}