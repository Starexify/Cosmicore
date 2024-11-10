package net.nova.cosmicore;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.Vec3;
import net.nova.cosmicore.entity.Achondrite;

import java.util.Random;

public class MeteorSpawner {
    private final Level level;
    private int tickCounter;
    private int ticksUntilNextMeteor;
    private final Random random;
    private final int minTicksUntilNextMeteor;
    private final int maxTicksUntilNextMeteor;

    public MeteorSpawner(Level level, int minTicksUntilNextMeteor, int maxTicksUntilNextMeteor) {
        this.level = level;
        this.tickCounter = 0;
        this.random = new Random();
        this.minTicksUntilNextMeteor = minTicksUntilNextMeteor;
        this.maxTicksUntilNextMeteor = maxTicksUntilNextMeteor;
        this.ticksUntilNextMeteor = getNewTickGoal();
    }

    public void onTick() {
        if (!level.isClientSide()) {
            tickCounter++;

            if (tickCounter >= ticksUntilNextMeteor) {
                spawnMeteor();
                tickCounter = 0;
                ticksUntilNextMeteor = getNewTickGoal();
            }
        }
    }

    private void spawnMeteor() {
        Vec3 spawnPos = getRandomSpawnPosition();
        level.addFreshEntity(new Achondrite(level, (int) spawnPos.x, (int) spawnPos.z));
    }

    private boolean isInWhitelistedDimension() {
        return level.dimension() == Level.OVERWORLD;
    }

    private Vec3 getRandomSpawnPosition() {
        double x = random.nextInt(1000) - 500;
        double z = random.nextInt(1000) - 500;
        double y = level.getHeight(Heightmap.Types.WORLD_SURFACE, (int) x, (int) z);
        return new Vec3(x, y, z);
    }

    private int getNewTickGoal() {
        return random.nextInt(maxTicksUntilNextMeteor - minTicksUntilNextMeteor + 1) + minTicksUntilNextMeteor;
    }
}