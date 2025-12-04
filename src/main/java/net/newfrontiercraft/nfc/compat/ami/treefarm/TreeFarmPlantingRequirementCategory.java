package net.newfrontiercraft.nfc.compat.ami.treefarm;

import net.glasslauncher.mods.alwaysmoreitems.api.gui.AMIDrawable;
import net.glasslauncher.mods.alwaysmoreitems.api.gui.GuiItemStackGroup;
import net.glasslauncher.mods.alwaysmoreitems.api.gui.RecipeLayout;
import net.glasslauncher.mods.alwaysmoreitems.api.recipe.RecipeCategory;
import net.glasslauncher.mods.alwaysmoreitems.api.recipe.RecipeWrapper;
import net.glasslauncher.mods.alwaysmoreitems.gui.DrawableHelper;
import net.minecraft.client.Minecraft;
import org.jetbrains.annotations.NotNull;

public class TreeFarmPlantingRequirementCategory implements RecipeCategory {
    @NotNull
    private final AMIDrawable background = DrawableHelper.createDrawable("/assets/nfc/stationapi/gui/planting_requirement.png", 3, 6, 170, 70);

    @NotNull
    @Override
    public String getUid() {
        return "tree_farm_planting_requirement";
    }

    @NotNull
    @Override
    public String getTitle() {
        return "Tree Farm Planting Requirement";
    }

    @NotNull
    @Override
    public AMIDrawable getBackground() {
        return background;
    }

    @Override
    public void drawExtras(Minecraft minecraft) {
    }

    @Override
    public void drawAnimations(Minecraft minecraft) {

    }

    @Override
    public void setRecipe(@NotNull RecipeLayout recipeLayout, @NotNull RecipeWrapper recipeWrapper) {
        GuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
        int xOffset = 28;
        int yOffset = 10;
        guiItemStacks.init(0, false, xOffset + 12, yOffset);
        guiItemStacks.setFromRecipe(0, recipeWrapper.getInputs().get(0));
        guiItemStacks.init(1, true, xOffset + 48, yOffset);
        guiItemStacks.setFromRecipe(1, recipeWrapper.getOutputs().get(0));
        for (int i = 1; i < recipeWrapper.getOutputs().size(); i++) {
            guiItemStacks.init(i + 1, true, xOffset + (i - 1) * 18 - 24, yOffset + 36);
        }
        for (int i = 1; i < recipeWrapper.getOutputs().size(); i++) {
            guiItemStacks.setFromRecipe(i + 1, recipeWrapper.getOutputs().get(i));
        }
    }
}
