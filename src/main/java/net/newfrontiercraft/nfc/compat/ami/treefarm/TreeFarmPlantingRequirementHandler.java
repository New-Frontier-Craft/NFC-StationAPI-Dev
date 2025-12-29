package net.newfrontiercraft.nfc.compat.ami.treefarm;

import net.glasslauncher.mods.alwaysmoreitems.api.recipe.RecipeHandler;
import net.glasslauncher.mods.alwaysmoreitems.api.recipe.RecipeWrapper;
import net.newfrontiercraft.nfc.wrappers.PlantingRequirementRecipe;
import org.jetbrains.annotations.NotNull;

public class TreeFarmPlantingRequirementHandler implements RecipeHandler<PlantingRequirementRecipe> {
    @Override
    public @NotNull Class<PlantingRequirementRecipe> getRecipeClass() {
        return PlantingRequirementRecipe.class;
    }

    @Override
    public @NotNull String getRecipeCategoryUid() {
        return "tree_farm_planting_requirement";
    }

    @Override
    public @NotNull RecipeWrapper getRecipeWrapper(@NotNull PlantingRequirementRecipe plantingRequirementRecipe) {
        return new TreeFarmPlantingRequirementWrapper(plantingRequirementRecipe);
    }

    @Override
    public boolean isRecipeValid(@NotNull PlantingRequirementRecipe plantingRequirementRecipe) {
        return true;
    }
}
