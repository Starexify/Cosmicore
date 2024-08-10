package net.nova.cosmicore.data.loot_table;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.InstrumentTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.EmptyLootItem;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantRandomlyFunction;
import net.minecraft.world.level.storage.loot.functions.SetInstrumentFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.nova.cosmicore.init.CItems;
import net.nova.cosmicore.init.CTags;

import java.util.function.BiConsumer;

public record ChestLoot(HolderLookup.Provider registries) implements LootTableSubProvider {
    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> pOutput) {
        pOutput.accept(CTags.ChestLootTags.METEOR_SITE_1, this.meteorSite1LootTable());
    }

    public LootTable.Builder meteorSite1LootTable() {
        return LootTable.lootTable().withPool(LootPool.lootPool()
                .setRolls(UniformGenerator.between(0.0F, 1.0F))
                .add(LootItem.lootTableItem(Items.CROSSBOW))

        ).withPool(LootPool.lootPool()
                .setRolls(UniformGenerator.between(1.0F, 2.0F))
                .add(LootItem.lootTableItem(Items.WHEAT).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 5.0F))))
                .add(LootItem.lootTableItem(Items.POTATO).setWeight(3).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 5.0F))))
                .add(LootItem.lootTableItem(Items.CARROT).setWeight(3).apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 5.0F))))

        ).withPool(LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1.0F))
                .add(LootItem.lootTableItem(Blocks.DARK_OAK_LOG).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))

        ).withPool(LootPool.lootPool()
                .setRolls(UniformGenerator.between(1.0F, 2.0F))
                .add(LootItem.lootTableItem(Items.EXPERIENCE_BOTTLE).setWeight(6))
                .add(LootItem.lootTableItem(Items.ARROW).setWeight(3).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 7.0F))))
                .add(LootItem.lootTableItem(Items.IRON_INGOT).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                .add(LootItem.lootTableItem(Items.BOOK).setWeight(1).apply(EnchantRandomlyFunction.randomApplicableEnchantment(this.registries)))

        ).withPool(LootPool.lootPool()
                .setRolls(UniformGenerator.between(0.0F, 1.0F))
                .add(LootItem.lootTableItem(Items.GOAT_HORN))
                .apply(SetInstrumentFunction.setInstrumentOptions(InstrumentTags.REGULAR_GOAT_HORNS))

        ).withPool(LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1.0F))
                .add(LootItem.lootTableItem(CItems.TITANIUM_UPGRADE_SMITHING_TEMPLATE).setWeight(3).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F))))

        ).withPool(LootPool.lootPool()
                .setRolls(UniformGenerator.between(2.0F, 3.0f))
                .add(LootItem.lootTableItem(CItems.INFERNIUM_CRYSTAL).setWeight(6).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 3.0F))))
        );
    }
}
