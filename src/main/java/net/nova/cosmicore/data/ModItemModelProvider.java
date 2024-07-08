package net.nova.cosmicore.data;

import com.google.gson.JsonObject;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.nova.cosmicore.init.ModItems;

import java.util.List;

import static net.nova.cosmicore.Cosmicore.MODID;

public class ModItemModelProvider extends ItemModelProvider {
    private static final List<ResourceKey<TrimMaterial>> TRIM_MATERIALS = List.of(TrimMaterials.QUARTZ, TrimMaterials.IRON, TrimMaterials.NETHERITE, TrimMaterials.REDSTONE, TrimMaterials.COPPER, TrimMaterials.GOLD, TrimMaterials.EMERALD, TrimMaterials.DIAMOND, TrimMaterials.LAPIS, TrimMaterials.AMETHYST);

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.RAW_TITANIUM.get());
        basicItem(ModItems.TITANIUM_INGOT.get());

        handheldItem(ModItems.TITANIUM_SHOVEL.get());
        handheldItem(ModItems.TITANIUM_PICKAXE.get());
        handheldItem(ModItems.TITANIUM_AXE.get());
        handheldItem(ModItems.TITANIUM_HOE.get());
        handheldItem(ModItems.TITANIUM_SWORD.get());

        trimmableArmorItem(ModItems.TITANIUM_HELMET.get());
        trimmableArmorItem(ModItems.TITANIUM_CHESTPLATE.get());
        trimmableArmorItem(ModItems.TITANIUM_LEGGINGS.get());
        trimmableArmorItem(ModItems.TITANIUM_BOOTS.get());
    }

    private void handheldItem(Item item) {
        String name = getItemName(item);
        getBuilder(name)
                .parent(getExistingFile(mcLoc("item/handheld")))
                .texture("layer0", "item/" + name);
    }

    private void trimmableArmorItem(Item item) {
        String name = getItemName(item);
        String modelPrefix;

        // Change the original names to the model name
        if (item instanceof ArmorItem) {
            ArmorItem armorItem = (ArmorItem) item;
            EquipmentSlot slot = armorItem.getEquipmentSlot();
            switch (slot) {
                case HEAD:
                    modelPrefix = "helmet";
                    break;
                case CHEST:
                    modelPrefix = "chestplate";
                    break;
                case LEGS:
                    modelPrefix = "leggings";
                    break;
                case FEET:
                    modelPrefix = "boots";
                    break;
                default:
                    return;
            }
        } else {
            return;
        }

        // Armor trim models
        for (ResourceKey<TrimMaterial> trim : TRIM_MATERIALS) {
            String trimType = trim.location().getPath();
            getBuilder(name + "_" + trimType + "_trim")
                    .parent(getExistingFile(mcLoc("item/generated")))
                    .texture("layer0", "item/" + name)
                    .texture("layer1", mcLoc("trims/items/" + modelPrefix + "_trim_" + trimType));
        }

        // Normal armor models with trims
        getBuilder(name)
                .parent(getExistingFile(mcLoc("item/generated")));

        int index = 0;
        for (ResourceKey<TrimMaterial> trim : TRIM_MATERIALS) {
            String trimType = trim.location().getPath();
            index++;

            // Format the value to have exactly one decimal place
            String formattedValue = String.format("%.1f", index * 0.1);

            getBuilder(name)
                    .override()
                    .predicate(ResourceLocation.parse("trim_type"), Float.parseFloat(formattedValue))
                    .model(getExistingFile(modLoc("item/" + name + "_" + trimType + "_trim")));
        }

        getBuilder(name)
                .texture("layer0", modLoc("item/" + name));
    }



    private String getItemName(Item item) {
        return BuiltInRegistries.ITEM.getKey(item).toString().replace(MODID + ":", "");
    }
}
