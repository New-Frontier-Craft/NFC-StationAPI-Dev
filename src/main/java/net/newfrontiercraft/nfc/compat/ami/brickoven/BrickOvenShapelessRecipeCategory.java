package net.newfrontiercraft.nfc.compat.ami.brickoven;

import net.glasslauncher.mods.alwaysmoreitems.api.gui.AMIDrawable;
import net.glasslauncher.mods.alwaysmoreitems.api.gui.GuiItemStackGroup;
import net.glasslauncher.mods.alwaysmoreitems.api.gui.RecipeLayout;
import net.glasslauncher.mods.alwaysmoreitems.api.recipe.RecipeCategory;
import net.glasslauncher.mods.alwaysmoreitems.api.recipe.RecipeWrapper;
import net.glasslauncher.mods.alwaysmoreitems.gui.DrawableHelper;
import net.minecraft.client.Minecraft;
import org.jetbrains.annotations.NotNull;

public class BrickOvenShapelessRecipeCategory implements RecipeCategory {

    @NotNull
    private final AMIDrawable background = DrawableHelper.createDrawable("/assets/nfc/stationapi/gui/brick_oven.png", 8, 8, 160, 100);

    @NotNull
    @Override
    public String getUid() {
        return "brick_oven_shapeless";
    }

    @NotNull
    @Override
    public String getTitle() {
        return "Brick Oven Shapeless";
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
        int xOffset = 21;
        int yOffset = 26;
        for (int i = 0; i < recipeWrapper.getInputs().size(); i++) {
            guiItemStacks.init(i, true, xOffset + (i % 3) * 18, yOffset + (i / 3) * 18);
        }
        for (int i = 0; i < recipeWrapper.getInputs().size(); i++) {
            guiItemStacks.setFromRecipe(i, recipeWrapper.getInputs().get(i));
        }
        guiItemStacks.init(9, false, 112 + xOffset, yOffset + 18);
        guiItemStacks.setFromRecipe(9, recipeWrapper.getOutputs().get(0));
    }
}
