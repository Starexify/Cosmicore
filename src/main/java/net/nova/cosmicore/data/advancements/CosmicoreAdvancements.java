package net.nova.cosmicore.data.advancements;

import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.function.Consumer;

public class CosmicoreAdvancements implements AdvancementSubProvider {
  @Override
  public void generate(HolderLookup.Provider registries, Consumer<AdvancementHolder> save) {
    HolderGetter<Block> blockGetter = registries.lookupOrThrow(Registries.BLOCK);
    HolderGetter<Item> itemGetter = registries.lookupOrThrow(Registries.ITEM);

/*        AdvancementHolder root = Advancement.Builder.advancement().display(
                        CBlocks.METEORITE,
                        Component.translatable("advancements." + MODID + ".root.title"),
                        Component.translatable("advancements." + MODID + ".root.description"),
                        Cosmicore.rl("textures/gui/advancements/backgrounds/cosmicore.png"),
                        AdvancementType.TASK,
                        true,
                        false,
                        false
                )
                .addCriterion(
                        "found_meteor",
                        PlayerTrigger.TriggerInstance.located(
                                LocationPredicate.Builder.inStructure(registries.lookupOrThrow(Registries.STRUCTURE).getOrThrow(BuiltinStructures.STRONGHOLD))
                        )
                )
                .save(save, MODID + ":root");*/
  }
}
