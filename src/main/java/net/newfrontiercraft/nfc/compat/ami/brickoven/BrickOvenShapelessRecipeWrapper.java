package net.newfrontiercraft.nfc.compat.ami.brickoven;

import net.glasslauncher.mods.alwaysmoreitems.api.recipe.RecipeWrapper;
import net.minecraft.client.Minecraft;
import net.newfrontiercraft.nfc.registry.BrickOvenShapelessRecipe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BrickOvenShapelessRecipeWrapper implements RecipeWrapper {
    private final BrickOvenShapelessRecipe recipe;

    public BrickOvenShapelessRecipeWrapper(BrickOvenShapelessRecipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public List<?> getInputs() {
        return recipe.getRecipeItems();
    }

    @Override
    public List<?> getOutputs() {
        return List.of(recipe.getRecipeOutput());
    }

    @Override
    public void drawInfo(@NotNull Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {

    }

    @Override
    public void drawAnimations(@NotNull Minecraft minecraft, int recipeWidth, int recipeHeight) {

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
}
