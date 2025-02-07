package net.nova.cosmicore.data;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.AddValue;
import net.nova.cosmicore.Cosmicore;
import net.nova.cosmicore.enchantment.MagnetismEffect;

public class CEnchantments {
    public static final ResourceKey<Enchantment> MAGNETISM = key("magnetism");

    public static void bootstrap(BootstrapContext<Enchantment> context) {
        HolderGetter<Item> itemGetter = context.lookup(Registries.ITEM);

        register(context, MAGNETISM, Enchantment.enchantment(
                        Enchantment.definition(
                                itemGetter.getOrThrow(ItemTags.DURABILITY_ENCHANTABLE),
                                2,
                                3,
                                Enchantment.dynamicCost(17, 8),
                                Enchantment.dynamicCost(36, 8),
                                3,
                                EquipmentSlotGroup.ANY
                        ))
                .withCustomName(component -> Component.literal("Magnetism"))
                .withEffect(
                        EnchantmentEffectComponents.TICK,
                        new MagnetismEffect(new AddValue(LevelBasedValue.perLevel(3.0F, 1.0F)).value())
                )
        );
    }

    public static void register(BootstrapContext<Enchantment> pContext, ResourceKey<Enchantment> pKey, Enchantment.Builder pBuilder) {
        pContext.register(pKey, pBuilder.build(pKey.location()));
    }

    public static ResourceKey<Enchantment> key(String name) {
        return ResourceKey.create(Registries.ENCHANTMENT, Cosmicore.rl(name));
    }
}
