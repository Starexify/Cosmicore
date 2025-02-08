package net.nova.cosmicore.data.advancements;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.critereon.PlayerTrigger;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.nova.cosmicore.Cosmicore;
import net.nova.cosmicore.init.CBlocks;

import java.util.function.Consumer;

import static net.nova.cosmicore.Cosmicore.MODID;

public class CosmicoreAdvancements implements AdvancementSubProvider {
    @Override
    public void generate(HolderLookup.Provider registries, Consumer<AdvancementHolder> save) {
        HolderGetter<Block> blockGetter = registries.lookupOrThrow(Registries.BLOCK);
        HolderGetter<Item> itemGetter = registries.lookupOrThrow(Registries.ITEM);

        AdvancementHolder root = Advancement.Builder.advancement().display(
                        CBlocks.METEORITE,
                        Component.translatable("advancements." + MODID + ".root.title"),
                        Component.translatable("advancements." + MODID + ".root.description"),
                        Cosmicore.rl("textures/gui/advancements/backgrounds/cosmicore.png"),
                        AdvancementType.TASK,
                        true,
                        false,
                        false
                )
                .addCriterion("player_join", PlayerTrigger.TriggerInstance.tick())
                .save(save, MODID + ":root");
    }
}
