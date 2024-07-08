package net.nova.cosmicore.init;

import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.nova.cosmicore.Cosmicore;

import java.util.EnumMap;
import java.util.List;

import static net.nova.cosmicore.Cosmicore.MODID;

public class ModArmorMaterial {
    public static final DeferredRegister<ArmorMaterial> ARMOR_MATERIALS = DeferredRegister.create(Registries.ARMOR_MATERIAL, MODID);

    public static final Holder<ArmorMaterial> TITANIUM = ARMOR_MATERIALS.register("titanium", () -> new ArmorMaterial(
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 3);
                map.put(ArmorItem.Type.LEGGINGS, 6);
                map.put(ArmorItem.Type.CHESTPLATE, 8);
                map.put(ArmorItem.Type.HELMET, 3);
                map.put(ArmorItem.Type.BODY, 11);
            }),17, SoundEvents.ARMOR_EQUIP_IRON, () -> Ingredient.of(ModItems.TITANIUM_INGOT),
            List.of(new ArmorMaterial.Layer(
                    Cosmicore.rl("titanium")
            )),2.5F,0.1F
    ));
}
