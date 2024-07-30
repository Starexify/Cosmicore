package net.nova.cosmicore.data;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.nova.cosmicore.init.ModItems;

import java.util.LinkedHashMap;

import static net.nova.cosmicore.Cosmicore.MODID;

public class ModItemModelProvider extends ItemModelProvider {
    public static LinkedHashMap<ResourceKey<TrimMaterial>, Float> trimMaterials = new LinkedHashMap<>();

    static {
        trimMaterials.put(TrimMaterials.QUARTZ, 0.1F);
        trimMaterials.put(TrimMaterials.IRON, 0.2F);
        trimMaterials.put(TrimMaterials.NETHERITE, 0.3F);
        trimMaterials.put(TrimMaterials.REDSTONE, 0.4F);
        trimMaterials.put(TrimMaterials.COPPER, 0.5F);
        trimMaterials.put(TrimMaterials.GOLD, 0.6F);
        trimMaterials.put(TrimMaterials.EMERALD, 0.7F);
        trimMaterials.put(TrimMaterials.DIAMOND, 0.8F);
        trimMaterials.put(TrimMaterials.LAPIS, 0.9F);
        trimMaterials.put(TrimMaterials.AMETHYST, 1.0F);
    }

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        // Titanium Stuff
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
        if (item instanceof ArmorItem armorItem) {
            trimMaterials.entrySet().forEach(entry -> {
                // Variables
                String armorType = switch (armorItem.getEquipmentSlot()) {
                    case HEAD -> "helmet";
                    case CHEST -> "chestplate";
                    case LEGS -> "leggings";
                    case FEET -> "boots";
                    default -> "";
                };

                String trimType = entry.getKey().location().getPath();
                String name = getItemName(item);
                float trimValue = entry.getValue();
                ResourceLocation textureLocation = mcLoc("trims/items/" + armorType + "_trim_" + trimType);
                String itemName = "item/" + name;
                ModelFile mcItem = getExistingFile(mcLoc("item/generated"));

                // Trimmed parts
                getBuilder(name + "_" + trimType + "_trim")
                        .parent(mcItem)
                        .texture("layer0", itemName)
                        .texture("layer1", textureLocation);

                // Armor with trimmed parts
                getBuilder(name)
                        .parent(mcItem)
                        .override()
                        .predicate(ResourceLocation.parse("trim_type"), trimValue)
                        .model(getExistingFile(modLoc(itemName + "_" + trimType + "_trim"))).end()
                        .texture("layer0", modLoc(itemName));
            });
        }
    }

    private String getItemName(Item item) {
        return BuiltInRegistries.ITEM.getKey(item).toString().replace(MODID + ":", "");
    }
}