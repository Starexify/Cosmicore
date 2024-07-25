package net.nova.cosmicore.data;

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
    private static final List<ResourceKey<TrimMaterial>> TRIM_MATERIALS = List.of(
            TrimMaterials.QUARTZ,
            TrimMaterials.IRON,
            TrimMaterials.NETHERITE,
            TrimMaterials.REDSTONE,
            TrimMaterials.COPPER,
            TrimMaterials.GOLD,
            TrimMaterials.EMERALD,
            TrimMaterials.DIAMOND,
            TrimMaterials.LAPIS,
            TrimMaterials.AMETHYST
    );


    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        trimmableArmorItem(ModItems.TITANIUM_HELMET.get());
        trimmableArmorItem(ModItems.TITANIUM_CHESTPLATE.get());
        trimmableArmorItem(ModItems.TITANIUM_LEGGINGS.get());
        trimmableArmorItem(ModItems.TITANIUM_BOOTS.get());

        handheldItem(ModItems.TITANIUM_SWORD.get());
        handheldItem(ModItems.TITANIUM_PICKAXE.get());
        handheldItem(ModItems.TITANIUM_AXE.get());
        handheldItem(ModItems.TITANIUM_SHOVEL.get());
        handheldItem(ModItems.TITANIUM_HOE.get());

        basicItem(ModItems.RAW_TITANIUM.get());
        basicItem(ModItems.TITANIUM_NUGGET.get());
        basicItem(ModItems.TITANIUM_INGOT.get());

        basicItem(ModItems.TITANIUM_UPGRADE_SMITHING_TEMPLATE.get());
    }

    private void handheldItem(Item item) {
        String name = getItemName(item);
        getBuilder(name)
                .parent(getExistingFile(mcLoc("item/handheld")))
                .texture("layer0", "item/" + name);
    }

    private void trimmableArmorItem(Item item) {
        String name = getItemName(item);
        String armorType;

        // Change armor type name
        if (item instanceof ArmorItem armorItem) {
            EquipmentSlot slot = armorItem.getEquipmentSlot();
            switch (slot) {
                case HEAD:
                    armorType = "helmet";
                    break;
                case CHEST:
                    armorType = "chestplate";
                    break;
                case LEGS:
                    armorType = "leggings";
                    break;
                case FEET:
                    armorType = "boots";
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
            String textureLocation = "trims/items/" + armorType + "_trim_" + trimType;

            getBuilder(name + "_" + trimType + "_trim")
                    .parent(getExistingFile(mcLoc("item/generated")))
                    .texture("layer0", "item/" + name)
                    .texture("layer1", mcLoc(textureLocation));
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
