package net.nova.cosmicore.entity;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.nova.cosmicore.data.worldgen.CStructures;

public class Achondrite extends BaseMeteor {
    public Achondrite(EntityType<? extends BaseMeteor> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public Structure getStructure() {
        return level().registryAccess().lookupOrThrow(Registries.STRUCTURE).getValueOrThrow(CStructures.ACHONDRITE_METEOR);
    }
}