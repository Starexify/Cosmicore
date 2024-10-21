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
import net.nova.cosmicore.init.CItems;

import java.util.LinkedHashMap;

import static net.nova.cosmicore.Cosmicore.MODID;

public class CItemModelProvider extends ItemModelProvider {
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

    public CItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        // Titanium Models
        trimmableArmorItem(CItems.TITANIUM_HELMET.get());
        trimmableArmorItem(CItems.TITANIUM_CHESTPLATE.get());
        trimmableArmorItem(CItems.TITANIUM_LEGGINGS.get());
        trimmableArmorItem(CItems.TITANIUM_BOOTS.get());

        handheldItem(CItems.TITANIUM_SWORD.get());
        handheldItem(CItems.TITANIUM_PICKAXE.get());
        handheldItem(CItems.TITANIUM_AXE.get());
        handheldItem(CItems.TITANIUM_SHOVEL.get());
        handheldItem(CItems.TITANIUM_HOE.get());

        basicItem(CItems.TITANIUM_HORSE_ARMOR.get());

        basicItem(CItems.RAW_TITANIUM.get());
        basicItem(CItems.TITANIUM_NUGGET.get());
        basicItem(CItems.TITANIUM_INGOT.get());

        basicItem(CItems.TITANIUM_UPGRADE_SMITHING_TEMPLATE.get());

        // Lonsdaleite Models
        trimmableArmorItem(CItems.LONSDALEITE_HELMET.get());
        trimmableArmorItem(CItems.LONSDALEITE_CHESTPLATE.get());
        trimmableArmorItem(CItems.LONSDALEITE_LEGGINGS.get());
        trimmableArmorItem(CItems.LONSDALEITE_BOOTS.get());

        handheldItem(CItems.LONSDALEITE_SWORD.get());
        handheldItem(CItems.LONSDALEITE_PICKAXE.get());
        handheldItem(CItems.LONSDALEITE_AXE.get());
        handheldItem(CItems.LONSDALEITE_SHOVEL.get());
        handheldItem(CItems.LONSDALEITE_HOE.get());

        basicItem(CItems.LONSDALEITE_HORSE_ARMOR.get());

        basicItem(CItems.LONSDALEITE.get());

        basicItem(CItems.LONSDALEITE_UPGRADE_SMITHING_TEMPLATE.get());

        // Infernium Model
        basicItem(CItems.INFERNIUM_CRYSTAL.get());

        // Gear Model
        gearItem(CItems.IRON_GEAR.get());
        gearItem(CItems.TITANIUM_GEAR.get());
    }

    // Models
    private void gearItem(Item item) {
        String name = getItemName(item);
        getBuilder(name)
                .parent(getExistingFile(modLoc("item/template_gear")))
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