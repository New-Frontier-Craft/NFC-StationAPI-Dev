package net.newfrontiercraft.nfc.compat.ami.brickoven;

import net.glasslauncher.mods.alwaysmoreitems.api.gui.AnimatedDrawable;
import net.glasslauncher.mods.alwaysmoreitems.api.gui.StaticDrawable;
import net.glasslauncher.mods.alwaysmoreitems.api.recipe.RecipeCategory;
import net.glasslauncher.mods.alwaysmoreitems.gui.DrawableHelper;

import javax.annotation.Nonnull;

public abstract class BrickOvenRecipeCategory implements RecipeCategory {
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
}
