package net.nova.cosmicore.item;

import net.minecraft.core.component.DataComponents;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ChargedProjectiles;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.Optional;

public class TitaniumCrossbow extends CrossbowItem {
    private static final CrossbowItem.ChargingSounds DEFAULT_SOUNDS = new CrossbowItem.ChargingSounds(
            Optional.of(SoundEvents.CROSSBOW_LOADING_START), Optional.of(SoundEvents.CROSSBOW_LOADING_MIDDLE), Optional.of(SoundEvents.CROSSBOW_LOADING_END)
    );

    public TitaniumCrossbow(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void releaseUsing(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving, int pTimeLeft) {
        int i = this.getUseDuration(pStack, pEntityLiving) - pTimeLeft;
        float f = getPowerForTime(i, pStack, pEntityLiving);
        if (f >= 1.0F && !isCharged(pStack) && tryLoadProjectiles(pEntityLiving, pStack)) {
            CrossbowItem.ChargingSounds crossbowitem$chargingsounds = this.getChargingSounds(pStack);
            crossbowitem$chargingsounds.end()
                    .ifPresent(
                            p_352852_ -> pLevel.playSound(
                                    null,
                                    pEntityLiving.getX(),
                                    pEntityLiving.getY(),
                                    pEntityLiving.getZ(),
                                    p_352852_.value(),
                                    pEntityLiving.getSoundSource(),
                                    1.0F,
                                    1.0F / (pLevel.getRandom().nextFloat() * 0.5F + 1.0F) + 0.2F
                            )
                    );
        }
    }

    private static boolean tryLoadProjectiles(LivingEntity pShooter, ItemStack pCrossbowStack) {
        List<ItemStack> list = draw(pCrossbowStack, pShooter.getProjectile(pCrossbowStack), pShooter);
        if (!list.isEmpty()) {
            pCrossbowStack.set(DataComponents.CHARGED_PROJECTILES, ChargedProjectiles.of(list));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int getUseDuration(ItemStack pStack, LivingEntity pEntity) {
        return getChargeDuration(pStack, pEntity) + 3;
    }

    // Had to add 6 functions to set this to 0.75, cool Mojang
    public static int getChargeDuration(ItemStack pStack, LivingEntity pShooter) {
        float f = EnchantmentHelper.modifyCrossbowChargingTime(pStack, pShooter, 1.1F);
        return Mth.floor(f * 20.0F);
    }

    private static float getPowerForTime(int pTimeLeft, ItemStack pStack, LivingEntity pShooter) {
        float f = (float)pTimeLeft / (float)getChargeDuration(pStack, pShooter);
        if (f > 1.0F) {
            f = 1.0F;
        }

        return f;
    }

    CrossbowItem.ChargingSounds getChargingSounds(ItemStack pStack) {
        return EnchantmentHelper.pickHighestLevel(pStack, EnchantmentEffectComponents.CROSSBOW_CHARGING_SOUNDS).orElse(DEFAULT_SOUNDS);
    }

    @Override
    public int getDefaultProjectileRange() {
        return 10;
    }
}
