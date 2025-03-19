package net.newfrontiercraft.nfc.compat.ami.carpentry;

import net.glasslauncher.mods.alwaysmoreitems.api.gui.AMIDrawable;
import net.glasslauncher.mods.alwaysmoreitems.api.gui.GuiItemStackGroup;
import net.glasslauncher.mods.alwaysmoreitems.api.gui.RecipeLayout;
import net.glasslauncher.mods.alwaysmoreitems.api.recipe.RecipeCategory;
import net.glasslauncher.mods.alwaysmoreitems.api.recipe.RecipeWrapper;
import net.glasslauncher.mods.alwaysmoreitems.gui.DrawableHelper;
import net.minecraft.client.Minecraft;
import org.jetbrains.annotations.NotNull;

public class CarpentryRecipeCategory implements RecipeCategory {

    @NotNull
    private final AMIDrawable background = DrawableHelper.createDrawable("/assets/nfc/stationapi/gui/carpentry.png", 8, 8, 160, 100);

    @NotNull
    @Override
    public String getUid() {
        return "carpentry";
    }

    @NotNull
    @Override
    public String getTitle() {
        return "Carpentry";
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
        int xOffset = 29;
        int yOffset = 8;
        guiItemStacks.init(0, false, 20 + xOffset, yOffset + 20);
        guiItemStacks.setFromRecipe(0, recipeWrapper.getInputs().get(0));
        for (int i = 0; i < recipeWrapper.getOutputs().size(); i++) {
            guiItemStacks.init(i + 1, true, xOffset + (i % 4) * 18 + 54, yOffset + (i / 4) * 18);
        }
        for (int i = 0; i < recipeWrapper.getOutputs().size(); i++) {
            guiItemStacks.setFromRecipe(i + 1, recipeWrapper.getOutputs().get(i));
        }
    }
}
