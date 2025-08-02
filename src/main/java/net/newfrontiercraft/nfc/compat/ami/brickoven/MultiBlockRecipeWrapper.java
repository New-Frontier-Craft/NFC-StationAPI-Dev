package net.newfrontiercraft.nfc.compat.ami.brickoven;

import net.glasslauncher.mods.alwaysmoreitems.api.recipe.RecipeWrapper;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.block.BlockRenderManager;
import net.newfrontiercraft.nfc.gui.InventoryBlockView;
import net.newfrontiercraft.nfc.registry.MultiBlockRecipe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

public class MultiBlockRecipeWrapper implements RecipeWrapper {
    private float rotation = 12f;
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
        InventoryBlockView blockView = new InventoryBlockView();
        BlockRenderManager blockRenderManager = new BlockRenderManager(blockView);
        GL11.glPushMatrix();
        GL11.glRotatef(rotation, 0, 1f, 0);
        //GL11.glTranslatef(-1,-1,-1);
        Tessellator tessellator = Tessellator.INSTANCE;
        tessellator.startQuads();
        GL11.glScalef(10f, 10f, 10f);
        minecraft.textureManager.bindTexture(minecraft.textureManager.getTextureId("/terrain.png"));
        blockView.setBlockStateWithMetadata(0, 0, 0, Block.DIAMOND_BLOCK.getDefaultState(), 0);
        for (int x = -10; x < 10; x++){
            for (int y = -10; y < 10; y++){
                for (int z = -10; z < 10; z++){
                    blockRenderManager.render(Block.DIAMOND_BLOCK, x, y, z);
                }
            }
        }

//        blockRenderManager.render(Block.DIAMOND_BLOCK, 4, 1, 2);
//        blockRenderManager.render(Block.DIAMOND_BLOCK, 2, 50, 3);
//        blockRenderManager.render(Block.DIAMOND_BLOCK, 0, 100, -100);
//        blockRenderManager.render(Block.DIAMOND_BLOCK, 0, 2, -1);
//        blockRenderManager.render(Block.DIAMOND_BLOCK, 0, 4, -2);
//        blockRenderManager.render(Block.DIAMOND_BLOCK, 0, 5, -4);
//        blockRenderManager.render(Block.DIAMOND_BLOCK, 0, 7, 0);
        tessellator.draw();
        GL11.glPopMatrix();
    }

    @Override
    public @Nullable ArrayList<Object> getTooltip(int i, int i1) {
        return null;
    }

    @Override
    public boolean handleClick(@NotNull Minecraft minecraft, int i, int i1, int i2) {
        rotation += 12f;
        return true;
    }
}
