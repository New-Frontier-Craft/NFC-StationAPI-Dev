package net.newfrontiercraft.nfc.compat.ami.cokeoven;

import net.glasslauncher.mods.alwaysmoreitems.api.recipe.RecipeHandler;
import net.glasslauncher.mods.alwaysmoreitems.api.recipe.RecipeWrapper;
import net.newfrontiercraft.nfc.wrappers.CokeOvenRecipe;
import org.jetbrains.annotations.NotNull;

public class CokeOvenRecipeHandler implements RecipeHandler<CokeOvenRecipe> {
    @Override
    public @NotNull Class<CokeOvenRecipe> getRecipeClass() {
        return CokeOvenRecipe.class;
    }

    @Override
    public @NotNull String getRecipeCategoryUid() {
        return "coke_oven";
    }

    @Override
    public @NotNull RecipeWrapper getRecipeWrapper(@NotNull CokeOvenRecipe shapedRecipe) {
        return new CokeOvenRecipeWrapper(shapedRecipe);
    }

    @Override
    public boolean isRecipeValid(@NotNull CokeOvenRecipe shapedRecipe) {
        return true;
    }
}
