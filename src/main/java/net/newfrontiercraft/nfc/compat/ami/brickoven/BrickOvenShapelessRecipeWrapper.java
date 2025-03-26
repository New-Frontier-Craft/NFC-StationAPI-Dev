package net.newfrontiercraft.nfc.compat.ami.brickoven;

import net.glasslauncher.mods.alwaysmoreitems.api.recipe.RecipeWrapper;
import net.glasslauncher.mods.alwaysmoreitems.plugins.vanilla.furnace.FuelRecipe;
import net.glasslauncher.mods.alwaysmoreitems.util.HoverChecker;
import net.minecraft.client.Minecraft;
import net.newfrontiercraft.nfc.registry.BrickOvenShapelessRecipe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BrickOvenShapelessRecipeWrapper implements RecipeWrapper {
    private final BrickOvenShapelessRecipe recipe;

    @NotNull HoverChecker hoverChecker = new HoverChecker(62, 77, 72, 93);
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

    public int getTime(){
        return recipe.getTime();
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
        System.out.println(mouseX + " : " + mouseY);
        if(hoverChecker.isOver(mouseX, mouseY)){
            return new ArrayList<Object>() {
                {
                    this.add(String.format("%.1f", (float)getTime() / 20f) + "s");
                    this.add(getTime() + "t");
                }
            };
        }
        return null;
    }

    @Override
    public boolean handleClick(@NotNull Minecraft minecraft, int mouseX, int mouseY, int mouseButton) {
        return false;
    }
}
