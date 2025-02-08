package net.nova.cosmicore.item;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class Magnetite extends Item {
    public Magnetite(Properties properties) {
        super(properties);
    }

    @Override
    public boolean onEntityItemUpdate(ItemStack stack, ItemEntity itemEntity) {

        // Try some magnetic interaction with metallic stuff
        /*if (itemEntity.level() instanceof ServerLevel serverLevel) {
            serverLevel.blocks
        }*/
        return super.onEntityItemUpdate(stack, itemEntity);
    }
}
