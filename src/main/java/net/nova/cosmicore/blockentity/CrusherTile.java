package net.nova.cosmicore.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.state.BlockState;
import net.nova.cosmicore.gui.CrusherItemStackHandler;
import net.nova.cosmicore.gui.crusher.CrusherMenu;
import net.nova.cosmicore.init.CBlockEntities;
import net.nova.cosmicore.init.CRecipeTypes;
import net.nova.cosmicore.recipe.crusher.CrushingRecipe;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class CrusherTile extends AbstractCrusherTile {
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
        super(CBlockEntities.CRUSHER_TILE.get(), pPos, pBlockState, CRecipeTypes.CRUSHING_RECIPE_TYPE.get());
        this.FUEL_SLOT = 1;
        this.RESULT_SLOT_START = 2;
        this.RESULT_SLOT_END = 7;

        this.inventory = new CrusherItemStackHandler(CrusherTile.this, 8);
    }

    public int getCrushingProgress() {
        return crushingProgress;
    }

    // Crafting stuff
    @Override
    public boolean hasRecipe() {
        Optional<RecipeHolder<CrushingRecipe>> recipe = getCurrentRecipe();
        if (recipe.isEmpty()) return false;
        ItemStack result = recipe.get().value().assemble(createRecipeInput(), level.registryAccess());

        return canInsertAmountIntoOutputSlot(result.getCount()) && canInsertItemInOutputSlot(result.getItem());
    }

    @Override
    public void craftItem() {
        Optional<RecipeHolder<CrushingRecipe>> recipe = getCurrentRecipe();
        if (recipe.isPresent()) {
            ItemStack result = recipe.get().value().assemble(createRecipeInput(), level.registryAccess());
            inventory.getFirst().shrink(1);
            insertOrMergeResult(result);
        }
    }

    public Optional<RecipeHolder<CrushingRecipe>> getCurrentRecipe() {
        if (level instanceof ServerLevel serverlevel) {
            return serverlevel.recipeAccess().getRecipeFor(CRecipeTypes.CRUSHING_RECIPE_TYPE.get(), createRecipeInput(), serverlevel);
        } else {
            return Optional.empty();
        }
    }

    // GUI title
    @Override
    public Component getDisplayName() {
        return Component.translatable("block.cosmicore.crusher");
    }

    // Menu
    @Override
    public @Nullable AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new CrusherMenu(containerId, playerInventory, this, dataAccess, inventory);
    }
}
