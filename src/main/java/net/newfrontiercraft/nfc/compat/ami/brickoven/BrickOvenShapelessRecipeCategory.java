package net.newfrontiercraft.nfc.compat.ami.brickoven;

import net.glasslauncher.mods.alwaysmoreitems.api.gui.AMIDrawable;
import net.glasslauncher.mods.alwaysmoreitems.api.gui.AnimatedDrawable;
import net.glasslauncher.mods.alwaysmoreitems.api.gui.GuiItemStackGroup;
import net.glasslauncher.mods.alwaysmoreitems.api.gui.RecipeLayout;
import net.glasslauncher.mods.alwaysmoreitems.api.recipe.RecipeCategory;
import net.glasslauncher.mods.alwaysmoreitems.api.recipe.RecipeWrapper;
import net.glasslauncher.mods.alwaysmoreitems.gui.DrawableHelper;
import net.glasslauncher.mods.alwaysmoreitems.plugins.vanilla.crafting.ShapelessRecipesWrapper;
import net.glasslauncher.mods.alwaysmoreitems.util.HoverChecker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ScreenScaler;
import net.newfrontiercraft.nfc.registry.BrickOvenRecipe;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.input.Mouse;

public class BrickOvenShapelessRecipeCategory extends BrickOvenRecipeCategory {

    @NotNull
    private final AMIDrawable background = DrawableHelper.createDrawable("/assets/nfc/stationapi/gui/brick_oven.png", 8, 8, 160, 100);

    @NotNull
    private int ticks = 0;

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
        ScreenScaler scaledresolution1 = new ScreenScaler(minecraft.options, minecraft.displayWidth, minecraft.displayHeight);
        int i1 = scaledresolution1.getScaledWidth();
        int j1 = scaledresolution1.getScaledHeight();
        int mouseX = Mouse.getX() * i1 / minecraft.displayWidth;
        int mouseY = j1 - Mouse.getY() * j1 / minecraft.displayHeight - 1;

        //System.out.println(mouseX + " : " + mouseY);

        //minecraft.textRenderer.draw(text, 69 - minecraft.textRenderer.getWidth(text) / 2, 70, 0xFFFFFF);
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
        int time = ((BrickOvenShapelessRecipeWrapper)recipeWrapper).getTime();
        ticks = time;
        arrow = DrawableHelper.createAnimatedDrawable(arrowDrawable, time, AnimatedDrawable.StartDirection.LEFT, false);
    }
}
