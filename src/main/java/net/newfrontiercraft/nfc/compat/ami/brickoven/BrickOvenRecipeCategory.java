package net.newfrontiercraft.nfc.compat.ami.brickoven;

import net.glasslauncher.mods.alwaysmoreitems.api.gui.*;
import net.glasslauncher.mods.alwaysmoreitems.api.recipe.RecipeCategory;
import net.glasslauncher.mods.alwaysmoreitems.api.recipe.RecipeWrapper;
import net.glasslauncher.mods.alwaysmoreitems.gui.DrawableHelper;
import net.minecraft.client.Minecraft;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

public class BrickOvenRecipeCategory implements RecipeCategory {
    @NotNull
    private final AMIDrawable background = DrawableHelper.createDrawable("/assets/nfc/stationapi/gui/brick_oven.png", 8, 8, 160, 100);
    @Nonnull
    protected final AnimatedDrawable flame;
    @Nonnull
    protected final StaticDrawable arrowDrawable;
    @Nonnull
    protected AnimatedDrawable arrow;

    public BrickOvenRecipeCategory() {
        StaticDrawable flameDrawable = DrawableHelper.createDrawable("/assets/nfc/stationapi/gui/brick_oven.png", 176, 0, 14, 14);
        this.flame = DrawableHelper.createAnimatedDrawable(flameDrawable, 300, AnimatedDrawable.StartDirection.TOP, true);
        arrowDrawable = DrawableHelper.createDrawable("/assets/nfc/stationapi/gui/brick_oven.png", 176, 14, 24, 17);
        this.arrow = DrawableHelper.createAnimatedDrawable(arrowDrawable, 200, AnimatedDrawable.StartDirection.LEFT, false);
    }

    @Override
    public @NotNull String getUid() {
        return "";
    }

    @Override
    public @NotNull String getTitle() {
        return "";
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
        this.flame.draw(minecraft, 48, 64);
        this.arrow.draw(minecraft, 71, 62);
    }

    @Override
    public void setRecipe(@NotNull RecipeLayout recipeLayout, @NotNull RecipeWrapper recipeWrapper) {
        GuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
        int xOffset = 29;
        int yOffset = 8;
        for (int i = 0; i < recipeWrapper.getInputs().size(); i++) {
            guiItemStacks.init(i, true, xOffset + (i % 3) * 18, yOffset + (i / 3) * 18);
        }
        for (int i = 0; i < recipeWrapper.getInputs().size(); i++) {
            guiItemStacks.setFromRecipe(i, recipeWrapper.getInputs().get(i));
        }
        guiItemStacks.init(9, false, 78 + xOffset, yOffset + 54);
        guiItemStacks.setFromRecipe(9, recipeWrapper.getOutputs().get(0));
        int time = ((BrickOvenRecipeWrapper)recipeWrapper).getTime();
        arrow = DrawableHelper.createAnimatedDrawable(arrowDrawable, time, AnimatedDrawable.StartDirection.LEFT, false);
    }
}
