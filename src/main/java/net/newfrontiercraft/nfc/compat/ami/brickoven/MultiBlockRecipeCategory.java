package net.newfrontiercraft.nfc.compat.ami.brickoven;

import net.glasslauncher.mods.alwaysmoreitems.api.gui.AMIDrawable;
import net.glasslauncher.mods.alwaysmoreitems.api.gui.GuiItemStackGroup;
import net.glasslauncher.mods.alwaysmoreitems.api.gui.RecipeLayout;
import net.glasslauncher.mods.alwaysmoreitems.api.recipe.RecipeCategory;
import net.glasslauncher.mods.alwaysmoreitems.api.recipe.RecipeWrapper;
import net.glasslauncher.mods.alwaysmoreitems.gui.DrawableHelper;
import net.minecraft.block.Block;
import net.minecraft.client.render.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraft.world.BlockView;
import net.minecraft.client.Minecraft;
import net.minecraft.client.render.block.BlockRenderManager;
import net.newfrontiercraft.nfc.gui.InventoryBlockView;
import net.newfrontiercraft.nfc.gui.InventoryDimension;
import net.newfrontiercraft.nfc.gui.InventoryWorld;
import net.newfrontiercraft.nfc.mixin.graphics.WorldRendererAccessor;
import net.newfrontiercraft.nfc.registry.MultiBlockRecipe;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

import java.util.List;

public class MultiBlockRecipeCategory implements RecipeCategory {
    private final AMIDrawable background = DrawableHelper.createDrawable("/assets/nfc/stationapi/gui/multiblock.png", 0, 0, 162, 130);
    private final AMIDrawable costTop = DrawableHelper.createDrawable("/assets/nfc/stationapi/gui/multiblock.png", 162, 0, 25, 5);
    private final AMIDrawable costMiddle = DrawableHelper.createDrawable("/assets/nfc/stationapi/gui/multiblock.png", 162, 5, 25, 18);
    private final AMIDrawable costBottom = DrawableHelper.createDrawable("/assets/nfc/stationapi/gui/multiblock.png", 162, 23, 25, 5);

    GuiItemStackGroup itemStackGroup;
    List<ItemStack> cost;

    @Override
    public @NotNull String getUid() {
        return "multi_block";
    }

    @Override
    public @NotNull String getTitle() {
        return "Multi Block Structures";
    }

    @Override
    public @NotNull AMIDrawable getBackground() {
        return background;
    }

    @Override
    public void drawExtras(Minecraft minecraft) {
        //System.out.println(minecraft.currentScreen.height);

        int x = -28;
        int y = -33;

        if(minecraft.currentScreen.height > 300){
            y -= 45;
        }

        costTop.draw(minecraft, x, y);
        y += 5;
        for(int i = 0; i < cost.size(); i++){
            costMiddle.draw(minecraft, x, y);
            itemStackGroup.init(i, true, x + 5, y);
            itemStackGroup.setFromRecipe(i, cost.get(i));
            y += 18;
        }
        costBottom.draw(minecraft, x, y);
    }

    @Override
    public void drawAnimations(Minecraft minecraft) {

    }

    @Override
    public void setRecipe(@NotNull RecipeLayout recipeLayout, @NotNull RecipeWrapper recipeWrapper) {
        this.cost = ((MultiBlockRecipeWrapper)recipeWrapper).getCost();
        this.itemStackGroup = recipeLayout.getItemStacks();
//        int xOffset = 29;
//        int yOffset = 8;
//        for (int i = 0; i < ((MultiBlockRecipeWrapper)recipeWrapper).getCost().size(); i++) {
//            guiItemStacks.init(i, true, xOffset + (i % 3) * 18, yOffset + (i / 3) * 18);
//        }
//        for (int i = 0; i < ((MultiBlockRecipeWrapper)recipeWrapper).getCost().size(); i++) {
//            guiItemStacks.setFromRecipe(i, ((MultiBlockRecipeWrapper)recipeWrapper).getCost().get(i));
//        }
    }
}
