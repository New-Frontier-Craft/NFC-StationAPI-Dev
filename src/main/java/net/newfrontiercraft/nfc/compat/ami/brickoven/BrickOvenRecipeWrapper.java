package net.newfrontiercraft.nfc.compat.ami.brickoven;

import net.glasslauncher.mods.alwaysmoreitems.api.recipe.RecipeWrapper;
import net.glasslauncher.mods.alwaysmoreitems.util.HoverChecker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resource.language.TranslationStorage;
import net.newfrontiercraft.nfc.registry.BrickOvenRecipe;
import net.newfrontiercraft.nfc.registry.BrickOvenShapelessRecipe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BrickOvenRecipeWrapper implements RecipeWrapper {
    private final BrickOvenRecipe recipe;

    @NotNull HoverChecker hoverChecker = new HoverChecker(62, 77, 72, 93);
    public BrickOvenRecipeWrapper(BrickOvenRecipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public List<?> getInputs() {
        return recipe.getInput();
    }

    @Override
    public List<?> getOutputs() {
        return List.of(recipe.getOutput());
    }

    public int getTime(){
        return recipe.getTime();
    }

    @Override
    public void drawInfo(@NotNull Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
    }

    @Override
    public void drawAnimations(@NotNull Minecraft minecraft, int recipeWidth, int recipeHeight) {
        int requiredHeat = getThermometerHeightFromHeatValue(recipe.getHeatRequirement());
        int requiredHeatHeightLevel = 96 - requiredHeat - 3;
        minecraft.draw(4, 4 + requiredHeatHeightLevel, 176, 46 + requiredHeatHeightLevel, 8, requiredHeat);
        minecraft.textRenderer.draw("Heat Requirement: " + TranslationStorage.getInstance().get(recipe.getHeatLevelName()), 4, 100, 0x000000);
    }

    @Nullable
    @Override
    public ArrayList<Object> getTooltip(int mouseX, int mouseY) {
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

    private int getThermometerHeightFromHeatValue(int heatValue) {
        return 16 * heatValue / 200;
    }
}
