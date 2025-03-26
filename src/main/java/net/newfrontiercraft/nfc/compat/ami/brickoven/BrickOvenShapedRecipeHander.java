package net.newfrontiercraft.nfc.compat.ami.brickoven;

import net.glasslauncher.mods.alwaysmoreitems.api.recipe.RecipeHandler;
import net.glasslauncher.mods.alwaysmoreitems.api.recipe.RecipeWrapper;
import net.minecraft.recipe.ShapedRecipe;
import net.newfrontiercraft.nfc.registry.BrickOvenShapedRecipe;
import org.jetbrains.annotations.NotNull;

public class BrickOvenShapedRecipeHander implements RecipeHandler<BrickOvenShapedRecipe> {
    @Override
    public @NotNull Class<BrickOvenShapedRecipe> getRecipeClass() {
        return BrickOvenShapedRecipe.class;
    }

    @Override
    public @NotNull String getRecipeCategoryUid() {
        return "brick_oven_shaped";
    }

    @Override
    public @NotNull RecipeWrapper getRecipeWrapper(@NotNull BrickOvenShapedRecipe shapedRecipe) {
        return new BrickOvenRecipeWrapper(shapedRecipe);
    }

    @Override
    public boolean isRecipeValid(@NotNull BrickOvenShapedRecipe shapedRecipe) {
        return true;
    }
}
