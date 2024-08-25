package net.nova.cosmicore.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.block.state.BlockState;
import net.nova.cosmicore.gui.crusher.AdvancedCrusherMenu;
import net.nova.cosmicore.init.CBlockEntities;
import net.nova.cosmicore.init.CRecipeTypes;
import net.nova.cosmicore.recipe.crusher.AdvancedCrushingRecipe;

import java.util.Optional;

public class AdvancedCrusherTile extends BaseCrusherTile {
    private static final int ADDITIONAL_SLOT = 1;
    protected final ContainerData dataAccess = new ContainerData() {
        @Override
        public int get(int pIndex) {
            return switch (pIndex) {
                case 0 -> AdvancedCrusherTile.this.ignisCharge;
                case 1 -> AdvancedCrusherTile.this.ignisPower;
                case 2 -> AdvancedCrusherTile.this.crushingProgress;
                case 3 -> AdvancedCrusherTile.this.maxCrushingProgress;
                default -> 0;
            };
        }

        @Override
        public void set(int pIndex, int pValue) {
            switch (pIndex) {
                case 0 -> AdvancedCrusherTile.this.ignisCharge = pValue;
                case 1 -> AdvancedCrusherTile.this.ignisPower = pValue;
                case 2 -> AdvancedCrusherTile.this.crushingProgress = pValue;
                case 3 -> AdvancedCrusherTile.this.maxCrushingProgress = pValue;
            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    };

    public AdvancedCrusherTile(BlockPos pPos, BlockState pBlockState) {
        super(CBlockEntities.ADVANCED_CRUSHER_TILE.get(), pPos, pBlockState);
        this.FUEL_SLOT = 2;
        this.RESULT_SLOT_START = 3;
        this.RESULT_SLOT_END = 11;

        this.inventory = NonNullList.withSize(11, ItemStack.EMPTY);
    }

    // Crafting stuff
    @Override
    public void hasIgnis() {
        boolean hasFuel = isFuel(this.inventory.get(FUEL_SLOT).getItem().getDefaultInstance());
        if (hasFuel && !isCharged()) {
            int chargeTime = 11;

            this.ignisCharge = chargeTime;
            this.ignisPower = chargeTime;
            this.inventory.get(FUEL_SLOT).setCount(this.inventory.get(FUEL_SLOT).getCount() - 1);
        }
    }

    // Render Item
    public ItemStack getRenderedStack() {
        return inventory.getFirst();
    }

    public ItemStack getRenderedAddition() {
        return inventory.get(ADDITIONAL_SLOT);
    }

    // Crafting stuff
    @Override
    public boolean hasRecipe() {
        Optional<RecipeHolder<AdvancedCrushingRecipe>> recipe = getCurrentRecipe(this.inventory.getFirst());
        Optional<RecipeHolder<AdvancedCrushingRecipe>> additionalRecipe = getCurrentRecipe(this.inventory.get(ADDITIONAL_SLOT));
        if (recipe.isEmpty() && additionalRecipe.isEmpty()) return false;

        ItemStack result = recipe.map(r -> r.value().getResultItem(null))
                .orElseGet(() -> additionalRecipe.get().value().getResultItem(null));

        return canInsertAmountIntoOutputSlot(result.getCount()) && canInsertItemInOutputSlot(result.getItem());
    }

    @Override
    public void craftItem() {
        Optional<RecipeHolder<AdvancedCrushingRecipe>> recipe = getCurrentRecipe(this.inventory.getFirst());
        Optional<RecipeHolder<AdvancedCrushingRecipe>> additionalRecipe = getCurrentRecipe(this.inventory.get(ADDITIONAL_SLOT));

        if (recipe.isPresent()) {
            ItemStack result = recipe.get().value().getResultItem(null);
            this.inventory.getFirst().shrink(1);
            insertOrMergeResult(result);
        }

        if (additionalRecipe.isPresent()) {
            ItemStack additionalResult = additionalRecipe.get().value().getResultItem(null);
            this.inventory.get(ADDITIONAL_SLOT).shrink(1);
            insertOrMergeResult(additionalResult);
        }
    }

    public Optional<RecipeHolder<AdvancedCrushingRecipe>> getCurrentRecipe(ItemStack itemStack) {
        return this.level.getRecipeManager().getRecipeFor(CRecipeTypes.ADVANCED_CRUSHING_RECIPE_TYPE.get(), new SingleRecipeInput(itemStack), level);
    }

    // GUI title
    @Override
    protected Component getDefaultName() {
        return Component.translatable("block.cosmicore.advanced_crusher");
    }

    // Menu
    @Override
    protected AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory) {
        return new AdvancedCrusherMenu(pContainerId, pInventory, this, this.dataAccess);
    }
}
