package net.nova.cosmicore.init;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.serialization.Codec;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.level.gamerules.*;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static net.nova.cosmicore.Cosmicore.MODID;

public class CGameRules {
  public static final DeferredRegister<GameRule<?>> GAME_RULES = DeferredRegister.create(Registries.GAME_RULE, MODID);

  public static Supplier<GameRule<Boolean>> ALLOW_METEORS_SPAWNING = GAME_RULES.register(
      "do_meteor_spawning",
      () ->  new GameRule<>(GameRuleCategory.SPAWNING, GameRuleType.BOOL, BoolArgumentType.bool(), GameRuleTypeVisitor::visitBoolean, Codec.BOOL, (b) -> b ? 1 : 0, true, FeatureFlagSet.of())
  );
}
