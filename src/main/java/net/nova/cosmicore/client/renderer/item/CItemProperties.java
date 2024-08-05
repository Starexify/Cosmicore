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
    public static void addCustomItemProperties() {
        makeCrossbow(CItems.TITANIUM_CROSSBOW.get());
    }

    private static void makeCrossbow(Item item) {
        ItemProperties.register(item, ResourceLocation.withDefaultNamespace("pull"),
                (p_351682_, p_351683_, p_351684_, p_351685_) -> {
                    if (p_351684_ == null) {
                        return 0.0F;
                    } else {
                        return TitaniumCrossbow.isCharged(p_351682_)
                                ? 0.0F
                                : (float) (p_351682_.getUseDuration(p_351684_) - p_351684_.getUseItemRemainingTicks())
                                / (float) TitaniumCrossbow.getChargeDuration(p_351682_, p_351684_);
                    }
                }
        );
        ItemProperties.register(item, ResourceLocation.withDefaultNamespace("pulling"),
                (p_174605_, p_174606_, p_174607_, p_174608_) -> p_174607_ != null
                        && p_174607_.isUsingItem()
                        && p_174607_.getUseItem() == p_174605_
                        && !TitaniumCrossbow.isCharged(p_174605_)
                        ? 1.0F
                        : 0.0F
        );

        ItemProperties.register(item, ResourceLocation.withDefaultNamespace("charged"),
                (p_275891_, p_275892_, p_275893_, p_275894_) -> TitaniumCrossbow.isCharged(p_275891_) ? 1.0F : 0.0F
        );

        ItemProperties.register(item, ResourceLocation.withDefaultNamespace("firework"), (p_329796_, p_329797_, p_329798_, p_329799_) -> {
            ChargedProjectiles chargedprojectiles = p_329796_.get(DataComponents.CHARGED_PROJECTILES);
            return chargedprojectiles != null && chargedprojectiles.contains(Items.FIREWORK_ROCKET) ? 1.0F : 0.0F;
        });
    }
}
