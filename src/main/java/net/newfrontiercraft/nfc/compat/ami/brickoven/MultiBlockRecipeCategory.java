package net.newfrontiercraft.nfc.compat.ami.brickoven;

import net.glasslauncher.mods.alwaysmoreitems.api.gui.AMIDrawable;
import net.glasslauncher.mods.alwaysmoreitems.api.gui.GuiItemStackGroup;
import net.glasslauncher.mods.alwaysmoreitems.api.gui.RecipeLayout;
import net.glasslauncher.mods.alwaysmoreitems.api.recipe.RecipeCategory;
import net.glasslauncher.mods.alwaysmoreitems.api.recipe.RecipeWrapper;
import net.glasslauncher.mods.alwaysmoreitems.gui.DrawableHelper;
import net.minecraft.block.Block;
import net.minecraft.client.render.Tessellator;
import net.minecraft.item.Item;
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
    private final AMIDrawable costExtensionTop = DrawableHelper.createDrawable("/assets/nfc/stationapi/gui/multiblock.png", 162, 0, 23, 5);
    private final AMIDrawable costMiddle = DrawableHelper.createDrawable("/assets/nfc/stationapi/gui/multiblock.png", 162, 5, 25, 18);
    private final AMIDrawable costExtensionMiddle = DrawableHelper.createDrawable("/assets/nfc/stationapi/gui/multiblock.png", 162, 5, 23, 18);
    private final AMIDrawable costBottom = DrawableHelper.createDrawable("/assets/nfc/stationapi/gui/multiblock.png", 162, 23, 25, 5);
    private final AMIDrawable costExtensionBottom = DrawableHelper.createDrawable("/assets/nfc/stationapi/gui/multiblock.png", 162, 28, 23, 5);

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

        int availableSpace = minecraft.currentScreen.height + y;

        int columns = (int)Math.ceil((10 + 18 * cost.size()) / (double)availableSpace);

        System.out.println(minecraft.currentScreen.height + " " + availableSpace);

        int startY = y;
        for(int i = 0; i < columns; i++){
            y = startY;
            if(i == 0){
                costTop.draw(minecraft, x, y);
            }
            else {
                costExtensionTop.draw(minecraft, x, y);
            }
            y += 5;
            for(int j = 0; j < cost.size(); j++){
                if(i == 0){
                    costMiddle.draw(minecraft, x, y);
                }
                else {
                    costExtensionMiddle.draw(minecraft, x, y);
                }
                itemStackGroup.init(j, true, x + 5, y);
                itemStackGroup.setFromRecipe(j, cost.get(i));
                y += 18;
            }
            if(i == 0){
                costBottom.draw(minecraft, x, y);
            }
            else {
                costExtensionBottom.draw(minecraft, x, y);
            }
            x -= 18;
        }
    }

    @Override
    public void drawAnimations(Minecraft minecraft) {

    }

    @Override
    public void setRecipe(@NotNull RecipeLayout recipeLayout, @NotNull RecipeWrapper recipeWrapper) {
        this.cost = ((MultiBlockRecipeWrapper)recipeWrapper).getCost();

        for(int i = 0; i < 10; i++){
            cost.add(new ItemStack(Item.APPLE));
        }

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
