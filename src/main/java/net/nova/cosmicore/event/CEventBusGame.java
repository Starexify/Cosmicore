package net.nova.cosmicore.event;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.AnvilUpdateEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;
import net.nova.cosmicore.Cosmicore;
import net.nova.cosmicore.MeteorSpawner;
import net.nova.cosmicore.data.CEnchantments;
import net.nova.cosmicore.init.CItems;
import net.nova.cosmicore.init.CTags;

import static net.nova.cosmicore.Cosmicore.MODID;

@EventBusSubscriber(modid = MODID)
public class CEventBusGame {
    // Meteor Spawning Event
    public static MeteorSpawner meteorSpawner;

    @SubscribeEvent
    public static void onServerStarting(ServerStartingEvent event) {
        meteorSpawner = new MeteorSpawner(event.getServer().overworld(), 72000, 144000); // 1h - 2h (72000 - 144000 ticks)
    }

    @SubscribeEvent
    public static void onLevelTick(LevelTickEvent.Post event) {
        if (event.getLevel() instanceof ServerLevel serverLevel && serverLevel.dimension() == Level.OVERWORLD && serverLevel.getGameRules().getBoolean(Cosmicore.ALLOW_METEORS_SPAWNING)) {
            if (meteorSpawner != null) meteorSpawner.onTick();
        }
    }

    // Magnetism from Anvil
    @SubscribeEvent
    public static void onAnvilUpdate(AnvilUpdateEvent event) {
        ItemStack left = event.getLeft();
        ItemStack right = event.getRight();
        if (left.isEnchantable() || left.is(CTags.CItemTags.MAGNETIC_ENCHANTABLE) && right.is(CItems.MAGNETITE)) {
            Holder<Enchantment> magnetism = event.getPlayer().level().registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(CEnchantments.MAGNETISM);
            int stackSize = right.getCount();
            int enchantLevel = Math.min(stackSize, 3);
            event.setMaterialCost(enchantLevel);

            ItemStack result;
            if (left.is(Items.BOOK)) {
                result = EnchantmentHelper.createBook(new EnchantmentInstance(magnetism, enchantLevel));
            } else {
                result = left.copy();
                result.enchant(magnetism, enchantLevel);
            }

            event.setCost((long) magnetism.value().getAnvilCost() * enchantLevel);
            event.setOutput(result);
        }
    }
}
