package net.newfrontiercraft.nfc.compat.ami.cokeoven;

import net.glasslauncher.mods.alwaysmoreitems.api.recipe.RecipeWrapper;
import net.glasslauncher.mods.alwaysmoreitems.util.HoverChecker;
import net.minecraft.client.Minecraft;
import net.newfrontiercraft.nfc.wrappers.CokeOvenRecipe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CokeOvenRecipeWrapper implements RecipeWrapper {
    private final CokeOvenRecipe recipe;

    @NotNull HoverChecker hoverChecker = new HoverChecker(62, 77, 72, 93);
    public CokeOvenRecipeWrapper(CokeOvenRecipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public List<?> getInputs() {
        return List.of(recipe.input());
    }

    @Override
    public List<?> getOutputs() {
        return List.of(recipe.output().result());
    }

    @Override
    public void drawInfo(@NotNull Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
    }

    @Override
    public void drawAnimations(@NotNull Minecraft minecraft, int recipeWidth, int recipeHeight) {
        int maximumHeat = getThermometerHeightFromHeatValue(recipe.output().maximum().getHeat());
        int maximumHeatHeightLevel = 96 - maximumHeat - 3;
        int requiredHeat = getThermometerHeightFromHeatValue(recipe.output().minimum().getHeat());
        int requiredHeatHeightLevel = 96 - requiredHeat - 3;
        minecraft.draw(-4, 4 + maximumHeatHeightLevel, 176, 38, 8, 7);
        minecraft.draw(12, 4 + maximumHeatHeightLevel, 184, 38, 8, 7);
        minecraft.draw(-4, 4 + requiredHeatHeightLevel, 176, 31, 8, 7);
        minecraft.draw(12, 4 + requiredHeatHeightLevel, 184, 31, 8, 7);
    }

    @Nullable
    @Override
    public ArrayList<Object> getTooltip(int mouseX, int mouseY) {
        return null;
    }

    @Override
    public boolean handleClick(@NotNull Minecraft minecraft, int mouseX, int mouseY, int mouseButton) {
        return false;
    }

    private int getThermometerHeightFromHeatValue(int heatValue) {
        return 16 * heatValue / 200;
    }
}
