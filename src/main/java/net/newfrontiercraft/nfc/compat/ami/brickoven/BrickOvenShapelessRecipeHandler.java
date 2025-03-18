package net.newfrontiercraft.nfc.compat.ami.brickoven;

import net.glasslauncher.mods.alwaysmoreitems.api.recipe.RecipeHandler;
import net.glasslauncher.mods.alwaysmoreitems.api.recipe.RecipeWrapper;
import net.newfrontiercraft.nfc.registry.BrickOvenShapelessRecipe;
import org.jetbrains.annotations.NotNull;

public class BrickOvenShapelessRecipeHandler implements RecipeHandler<BrickOvenShapelessRecipe> {
    @NotNull
    @Override
    public Class<BrickOvenShapelessRecipe> getRecipeClass() {
        return BrickOvenShapelessRecipe.class;
    }

    @NotNull
    @Override
    public String getRecipeCategoryUid() {
        return "brick_oven_shapeless";
    }

    @NotNull
    @Override
    public RecipeWrapper getRecipeWrapper(@NotNull BrickOvenShapelessRecipe recipe) {
        return new BrickOvenShapelessRecipeWrapper(recipe);
    }

    @Override
    public boolean isRecipeValid(@NotNull BrickOvenShapelessRecipe recipe) {
        return true;
    }
}
