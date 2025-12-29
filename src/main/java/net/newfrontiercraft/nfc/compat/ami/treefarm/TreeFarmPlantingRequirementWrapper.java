package net.newfrontiercraft.nfc.compat.ami.treefarm;

import net.glasslauncher.mods.alwaysmoreitems.api.recipe.RecipeWrapper;
import net.minecraft.client.Minecraft;
import net.newfrontiercraft.nfc.wrappers.PlantingRequirementRecipe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class TreeFarmPlantingRequirementWrapper implements RecipeWrapper {
    private final PlantingRequirementRecipe recipe;

    public TreeFarmPlantingRequirementWrapper(PlantingRequirementRecipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public List<?> getInputs() {
        return List.of(recipe.getBlock());
    }

    @Override
    public List<?> getOutputs() {
        return recipe.getOutputs();
    }

    @Override
    public void drawInfo(@NotNull Minecraft minecraft, int i, int i1, int i2, int i3) {

    }

    @Override
    public void drawAnimations(@NotNull Minecraft minecraft, int i, int i1) {

    }

    @Override
    public @Nullable ArrayList<Object> getTooltip(int i, int i1) {
        return null;
    }

    @Override
    public boolean handleClick(@NotNull Minecraft minecraft, int i, int i1, int i2) {
        return false;
    }
}
