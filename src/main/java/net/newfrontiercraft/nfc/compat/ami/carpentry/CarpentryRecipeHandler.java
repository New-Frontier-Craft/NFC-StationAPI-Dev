package net.newfrontiercraft.nfc.compat.ami.carpentry;

import net.glasslauncher.mods.alwaysmoreitems.api.recipe.RecipeHandler;
import net.glasslauncher.mods.alwaysmoreitems.api.recipe.RecipeWrapper;
import net.newfrontiercraft.nfc.wrappers.CarpentryRecipe;
import org.jetbrains.annotations.NotNull;

public class CarpentryRecipeHandler implements RecipeHandler<CarpentryRecipe> {
    @NotNull
    @Override
    public Class<CarpentryRecipe> getRecipeClass() {
        return CarpentryRecipe.class;
    }

    @NotNull
    @Override
    public String getRecipeCategoryUid() {
        return "carpentry";
    }

    @NotNull
    @Override
    public RecipeWrapper getRecipeWrapper(@NotNull CarpentryRecipe recipe) {
        return new CarpentryRecipeWrapper(recipe);
    }

    @Override
    public boolean isRecipeValid(@NotNull CarpentryRecipe recipe) {
        return true;
    }
}
