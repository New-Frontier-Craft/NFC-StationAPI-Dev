package net.newfrontiercraft.nfc.compat.ami.treefarm;

import net.glasslauncher.mods.alwaysmoreitems.api.recipe.RecipeHandler;
import net.glasslauncher.mods.alwaysmoreitems.api.recipe.RecipeWrapper;
import net.newfrontiercraft.nfc.wrappers.HarvestingRecipe;
import org.jetbrains.annotations.NotNull;

public class TreeFarmHarvestingHandler implements RecipeHandler<HarvestingRecipe> {
    @Override
    public @NotNull Class<HarvestingRecipe> getRecipeClass() {
        return HarvestingRecipe.class;
    }

    @Override
    public @NotNull String getRecipeCategoryUid() {
        return "tree_farm_harvesting";
    }

    @Override
    public @NotNull RecipeWrapper getRecipeWrapper(@NotNull HarvestingRecipe recipe) {
        return new TreeFarmHarvestingWrapper(recipe);
    }

    @Override
    public boolean isRecipeValid(@NotNull HarvestingRecipe recipe) {
        return true;
    }
}
