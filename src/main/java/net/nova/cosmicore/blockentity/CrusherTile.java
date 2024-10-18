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
import net.nova.cosmicore.gui.crusher.CrusherMenu;
import net.nova.cosmicore.init.CBlockEntities;
import net.nova.cosmicore.init.CRecipeTypes;
import net.nova.cosmicore.recipe.crusher.CrushingRecipe;

import java.util.Optional;

public class CrusherTile extends BaseCrusherTile {
    protected final ContainerData dataAccess = new ContainerData() {
        @Override
        public int get(int pIndex) {
            return switch (pIndex) {
                case 0 -> CrusherTile.this.ignisCharge;
                case 1 -> CrusherTile.this.ignisPower;
                case 2 -> CrusherTile.this.crushingProgress;
                case 3 -> CrusherTile.this.maxCrushingProgress;
                default -> 0;
            };
        }

        @Override
        public void set(int pIndex, int pValue) {
            switch (pIndex) {
                case 0 -> CrusherTile.this.ignisCharge = pValue;
                case 1 -> CrusherTile.this.ignisPower = pValue;
                case 2 -> CrusherTile.this.crushingProgress = pValue;
                case 3 -> CrusherTile.this.maxCrushingProgress = pValue;
            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    };

    public CrusherTile(BlockPos pPos, BlockState pBlockState) {
        super(CBlockEntities.CRUSHER_TILE.get(), pPos, pBlockState);
        this.FUEL_SLOT = 1;
        this.RESULT_SLOT_START = 2;
        this.RESULT_SLOT_END = 7;

        this.inventory = NonNullList.withSize(8, ItemStack.EMPTY);
    }

    // Render Item
    public ItemStack getRenderedStack() {
        return inventory.getFirst();
    }

    public int getCrushingProgress() {
        return crushingProgress;
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

    @Override
    public boolean hasRecipe() {
        Optional<RecipeHolder<CrushingRecipe>> recipe = getCurrentRecipe(this.inventory.getFirst());
        if (recipe.isEmpty()) return false;

        ItemStack result = recipe.get().value().getResultItem(null);

        return canInsertAmountIntoOutputSlot(result.getCount()) && canInsertItemInOutputSlot(result.getItem());
    }

    @Override
    public void craftItem() {
        Optional<RecipeHolder<CrushingRecipe>> recipe = getCurrentRecipe(this.inventory.getFirst());
        ItemStack result = recipe.get().value().getResultItem(null);
        this.inventory.getFirst().setCount(this.inventory.getFirst().getCount() - 1);

        // Find the first available result slot
        if (recipe.isPresent()) {
            insertOrMergeResult(result);
        }
    }

    public Optional<RecipeHolder<CrushingRecipe>> getCurrentRecipe(ItemStack itemStack) {
        return this.level.getRecipeManager().getRecipeFor(CRecipeTypes.CRUSHING_RECIPE_TYPE.get(), new SingleRecipeInput(itemStack), level);
    }

    // GUI title
    @Override
    protected Component getDefaultName() {
        return Component.translatable("block.cosmicore.crusher");
    }

    // Menu
    @Override
    protected AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory) {
        return new CrusherMenu(pContainerId, pInventory, this, this.dataAccess);
    }
}
