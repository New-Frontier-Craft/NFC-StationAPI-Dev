package net.newfrontiercraft.nfc.compat.ami.cokeoven;

import net.glasslauncher.mods.alwaysmoreitems.api.gui.*;
import net.glasslauncher.mods.alwaysmoreitems.api.recipe.RecipeCategory;
import net.glasslauncher.mods.alwaysmoreitems.api.recipe.RecipeWrapper;
import net.glasslauncher.mods.alwaysmoreitems.gui.DrawableHelper;
import net.minecraft.client.Minecraft;
import net.newfrontiercraft.nfc.compat.ami.brickoven.BrickOvenRecipeWrapper;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

public class CokeOvenRecipeCategory implements RecipeCategory {
    @NotNull
    private final AMIDrawable background = DrawableHelper.createDrawable("/assets/nfc/stationapi/gui/coke_oven.png", 8, 8, 160, 100);
    @Nonnull
    protected final StaticDrawable arrowDrawable;
    @Nonnull
    protected AnimatedDrawable arrow;

    public CokeOvenRecipeCategory() {
        arrowDrawable = DrawableHelper.createDrawable("/assets/nfc/stationapi/gui/coke_oven.png", 176, 14, 24, 17);
        this.arrow = DrawableHelper.createAnimatedDrawable(arrowDrawable, 200, AnimatedDrawable.StartDirection.LEFT, false);
    }

    @Override
    public @NotNull String getUid() {
        return "coke_oven";
    }

    @Override
    public @NotNull String getTitle() {
        return "Coke Oven";
    }

    @Override
    public @NotNull AMIDrawable getBackground() {
        return background;
    }

    @Override
    public void drawExtras(Minecraft minecraft) {

    }

    @Override
    public void drawAnimations(Minecraft minecraft) {
        this.arrow.draw(minecraft, 71, 62);
    }

    @Override
    public void setRecipe(@NotNull RecipeLayout recipeLayout, @NotNull RecipeWrapper recipeWrapper) {
        GuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
        int xOffset = 29;
        int yOffset = 8;
        guiItemStacks.init(0, true, xOffset + 18, yOffset + 54);
        guiItemStacks.setFromRecipe(0, recipeWrapper.getInputs().get(0));
        guiItemStacks.init(1, false, 78 + xOffset, yOffset + 54);
        guiItemStacks.setFromRecipe(1, recipeWrapper.getOutputs().get(0));
        arrow = DrawableHelper.createAnimatedDrawable(arrowDrawable, 200, AnimatedDrawable.StartDirection.LEFT, false);
    }
}
