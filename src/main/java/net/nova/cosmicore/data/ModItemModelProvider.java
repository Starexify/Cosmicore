package net.nova.cosmicore.data;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.nova.cosmicore.init.ModItems;

import static net.nova.cosmicore.Cosmicore.MODID;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.RAW_TITANIUM.get());
        simpleItem(ModItems.TITANIUM_INGOT.get());

        handheldItem(ModItems.TITANIUM_SHOVEL.get());
        handheldItem(ModItems.TITANIUM_PICKAXE.get());
        handheldItem(ModItems.TITANIUM_AXE.get());
        handheldItem(ModItems.TITANIUM_HOE.get());
        handheldItem(ModItems.TITANIUM_SWORD.get());

        simpleItem(ModItems.ASTRALITE_CLUSTER.get());
        simpleItem(ModItems.ASTRALITE_CRYSTAL.get());

        simpleItem(ModItems.UNSTABLE_HYPERIONITE.get());
        simpleItem(ModItems.STABLE_HYPERIONITE.get());

        simpleItem(ModItems.LONSDALEITE.get());
    }

    private void simpleItem(Item item) {
        String name = getItemName(item);
        getBuilder(name)
                .parent(getExistingFile(mcLoc("item/generated")))
                .texture("layer0", "item/" + name);
    }

    private void handheldItem(Item item) {
        String name = getItemName(item);
        getBuilder(name)
                .parent(getExistingFile(mcLoc("item/handheld")))
                .texture("layer0", "item/" + name);
    }

    private String getItemName(Item item) {
        return BuiltInRegistries.ITEM.getKey(item).toString().replace(MODID + ":", "");
    }
}
