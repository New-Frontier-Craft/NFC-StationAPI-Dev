package net.newfrontiercraft.nfc.compat.ami.brickoven;

import net.glasslauncher.mods.alwaysmoreitems.api.gui.AMIDrawable;
import net.glasslauncher.mods.alwaysmoreitems.api.gui.GuiItemStackGroup;
import net.glasslauncher.mods.alwaysmoreitems.api.gui.RecipeLayout;
import net.glasslauncher.mods.alwaysmoreitems.api.recipe.RecipeCategory;
import net.glasslauncher.mods.alwaysmoreitems.api.recipe.RecipeWrapper;
import net.glasslauncher.mods.alwaysmoreitems.gui.DrawableHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.client.Minecraft;
import org.jetbrains.annotations.NotNull;

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
    int descriptionFade;
    int descriptionFadeTick;

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
        int x = -28;
        int y = -33;

        int maxRows = 8;

        if(minecraft.currentScreen.height > 300){
            y -= 45;
            maxRows =  13;
        }

        int columns = (int) Math.ceil((double) cost.size() / (double)maxRows);
        int rows = Math.min(cost.size(), maxRows);

        int currentCostIndex = 0;
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
            for(int j = 0; j < rows; j++){
                if(i == 0){
                    costMiddle.draw(minecraft, x, y);
                }
                else {
                    costExtensionMiddle.draw(minecraft, x, y);
                }
                if(currentCostIndex < cost.size()){
                    itemStackGroup.init(currentCostIndex, true, x + 5, y);
                    itemStackGroup.setFromRecipe(currentCostIndex, cost.get(currentCostIndex));
                }
                y += 18;
                currentCostIndex++;
            }
            if(i == 0){
                costBottom.draw(minecraft, x, y);
            }
            else {
                costExtensionBottom.draw(minecraft, x, y);
            }
            x -= 18;
        }
        int colour = switch (descriptionFade) {
            case 0:
                yield 0x0B0B0B;
            case 1:
                yield 0x1B1B1B;
            case 2:
                yield 0x2B2B2B;
            case 3:
                yield 0x3B3B3B;
            case 4:
                yield 0x4B4B4B;
            case 5:
                yield 0x5B5B5B;
            case 6:
                yield 0x6B6B6B;
            case 7:
                yield 0x7B7B7B;
            case 8:
                yield 0x8B8B8B;
            default:
                yield 0;
        };
        minecraft.textRenderer.draw("Hold shift for description!", 2, 118, colour);
        if (descriptionFadeTick >= 20) {
            descriptionFadeTick = 0;
            descriptionFade++;
        } else if (descriptionFade < 8) {
            descriptionFadeTick++;
        }
    }

    @Override
    public void drawAnimations(Minecraft minecraft) {

    }

    @Override
    public void setRecipe(@NotNull RecipeLayout recipeLayout, @NotNull RecipeWrapper recipeWrapper) {
        this.cost = ((MultiBlockRecipeWrapper)recipeWrapper).getCost();
        this.itemStackGroup = recipeLayout.getItemStacks();
        descriptionFadeTick = 0;
        descriptionFade = 0;
    }
}
