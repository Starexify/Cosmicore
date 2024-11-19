package net.nova.cosmicore.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.nova.cosmicore.data.worldgen.CStructures;

public class Achondrite extends BaseMeteor {
    public Achondrite(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public Structure getStructure() {
        return level().registryAccess().registryOrThrow(CStructures.ACHONDRITE_METEOR.registryKey()).getHolderOrThrow(CStructures.ACHONDRITE_METEOR).value();
    }
}