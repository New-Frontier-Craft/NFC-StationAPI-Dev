package net.newfrontiercraft.nfc.compat.ami.brickoven;

import net.glasslauncher.mods.alwaysmoreitems.api.recipe.RecipeWrapper;
import net.minecraft.client.Minecraft;
import net.newfrontiercraft.nfc.registry.MultiBlockRecipe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MultiBlockRecipeWrapper implements RecipeWrapper {
    private final MultiBlockRecipe recipe;
    public MultiBlockRecipeWrapper(MultiBlockRecipe recipe){
        this.recipe = recipe;
    }
    @Override
    public List<?> getInputs() {
        return recipe.getItems();
    }

    @Override
    public List<?> getOutputs() {
        return List.of();
    }

    @Override
    public void drawInfo(@NotNull Minecraft minecraft, int i, int i1, int i2, int i3) {

    }

    @Override
    public void drawAnimations(@NotNull Minecraft minecraft, int i, int i1) {

    }

    @Override
    public @Nullable ArrayList<Object> getTooltip(int i, int i1) {
        return null;
    }

    @Override
    public boolean handleClick(@NotNull Minecraft minecraft, int i, int i1, int i2) {
        return false;
    }
}
