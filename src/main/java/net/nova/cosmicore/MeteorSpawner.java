package net.nova.cosmicore;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.nova.cosmicore.entity.Achondrite;
import net.nova.cosmicore.init.CEntities;

public class MeteorSpawner {
    private final ServerLevel level;
    private int tickCounter;
    private int ticksUntilNextMeteor;
    private final RandomSource random;

    public MeteorSpawner(ServerLevel level, int minTicksUntilNextMeteor, int maxTicksUntilNextMeteor) {
        this.level = level;
        this.tickCounter = 0;
        this.random = level.getRandom();
        this.ticksUntilNextMeteor = random.nextIntBetweenInclusive(minTicksUntilNextMeteor, maxTicksUntilNextMeteor);
    }

    public void onTick() {
        tickCounter++;

        if (tickCounter >= ticksUntilNextMeteor) {
            if (isInWhitelistedDimension()) {
                spawnMeteor();
            }
            tickCounter = 0;
            // Randomize cooldown between 200 and 600 ticks (10-30 seconds)
            this.ticksUntilNextMeteor = random.nextIntBetweenInclusive(200, 600);
        }
    }

    private void spawnMeteor() {
        BlockPos spawnPos = getRandomSpawnPosition();
        Achondrite meteor = new Achondrite(CEntities.ACHONDRITE.get(), level);
        meteor.setPos(spawnPos.getX() + 0.5, 320, spawnPos.getZ() + 0.5);
        level.addFreshEntity(meteor);
    }

    private boolean isInWhitelistedDimension() {
        return level.dimension() == Level.OVERWORLD;
    }

    private BlockPos getRandomSpawnPosition() {
        int x = random.nextInt(6000) - 3000;
        int z = random.nextInt(6000) - 3000;
        return new BlockPos(x, 320, z);
    }
}