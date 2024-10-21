package net.nova.cosmicore.client.renderer.item;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.ChargedProjectiles;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.nova.cosmicore.init.CItems;
import net.nova.cosmicore.item.TitaniumCrossbow;

@OnlyIn(Dist.CLIENT)
public class CItemProperties {
    public static ResourceLocation pullPredicate = ResourceLocation.withDefaultNamespace("pull");
    public static ResourceLocation pullingPredicate = ResourceLocation.withDefaultNamespace("pulling");
    public static ResourceLocation chargedPredicate = ResourceLocation.withDefaultNamespace("charged");
    public static ResourceLocation fireworkPredicate = ResourceLocation.withDefaultNamespace("firework");

    public static void addCustomItemProperties() {
        makeCrossbow(CItems.TITANIUM_CROSSBOW.get());
    }

    private static void makeCrossbow(Item item) {
        ItemProperties.register(item, pullPredicate, (stack, level, entity, seed) -> {
                    if (entity == null) {
                        return 0.0F;
                    } else {
                        return TitaniumCrossbow.isCharged(stack)
                                ? 0.0F
                                : (float) (stack.getUseDuration(entity) - entity.getUseItemRemainingTicks())
                                / (float) TitaniumCrossbow.getChargeDuration(stack, entity);
                    }
                }
        );
        ItemProperties.register(item, pullingPredicate, (stack, level, entity, seed) -> entity != null
                && entity.isUsingItem()
                && entity.getUseItem() == stack
                && !TitaniumCrossbow.isCharged(stack)
                ? 1.0F
                : 0.0F
        );

        ItemProperties.register(item, chargedPredicate, (stack, level, entity, seed) -> TitaniumCrossbow.isCharged(stack) ? 1.0F : 0.0F);

        ItemProperties.register(item, fireworkPredicate, (stack, level, entity, seed) -> {
            ChargedProjectiles chargedprojectiles = stack.get(DataComponents.CHARGED_PROJECTILES);
            return chargedprojectiles != null && chargedprojectiles.contains(Items.FIREWORK_ROCKET) ? 1.0F : 0.0F;
        });
    }
}
