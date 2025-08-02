package net.newfrontiercraft.nfc.compat.ami.brickoven;

import net.glasslauncher.mods.alwaysmoreitems.api.recipe.RecipeWrapper;
import net.glasslauncher.mods.alwaysmoreitems.gui.DrawableHelper;
import net.glasslauncher.mods.alwaysmoreitems.util.AMIHelpers;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.util.math.BlockPos;
import net.modificationstation.stationapi.api.block.BlockState;
import net.newfrontiercraft.nfc.gui.InventoryBlockView;
import net.newfrontiercraft.nfc.registry.MultiBlockRecipe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import paulevs.bnb.block.BNBBlocks;

import java.util.ArrayList;
import java.util.List;

public class MultiBlockRecipeWrapper implements RecipeWrapper {
    float pitch = 0f;
    float yaw = 0f;
    boolean mouseDown = false;
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
        if(!Mouse.isButtonDown(0) && mouseDown){
            mouseDown = false;
        }
        if(mouseDown){
            pitch -= Mouse.getDY();
            yaw += Mouse.getDX();
        }
    }




    @Override
    public void drawAnimations(@NotNull Minecraft minecraft, int i, int i1) {
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthFunc(GL11.GL_LEQUAL); // optional, default
        GL11.glDepthMask(true);
        InventoryBlockView blockView = new InventoryBlockView();
        BlockRenderManager blockRenderManager = new BlockRenderManager(blockView);
        GL11.glPushMatrix();
        //GLU.gluPerspective(70.0f, (float) minecraft.displayWidth / (float)minecraft.displayHeight, 0.05f, 100.0f);
        GL11.glTranslatef(81,65,0);
        GL11.glRotatef(pitch, 1f, 0f, 0);
        GL11.glRotatef(yaw, 0f, 1f, 0);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glScalef(10f, 10f, 10f);
        minecraft.textureManager.bindTexture(minecraft.textureManager.getTextureId("/terrain.png"));
        blockView.setBlockStateWithMetadata(0, 0, 0, Block.DIAMOND_BLOCK.getDefaultState(), 0);
        blockView.setBlockStateWithMetadata(1, 0, 0, Block.DIAMOND_BLOCK.getDefaultState(), 0);
        blockView.setBlockStateWithMetadata(2, 0, 0, Block.DIAMOND_BLOCK.getDefaultState(), 0);
        blockView.setBlockStateWithMetadata(0, 1, 0, Block.DIAMOND_BLOCK.getDefaultState(), 0);
        blockView.setBlockStateWithMetadata(0, 2, 0, Block.DIAMOND_BLOCK.getDefaultState(), 0);
        blockView.setBlockStateWithMetadata(0, 3, 0, Block.DIAMOND_BLOCK.getDefaultState(), 0);
        blockView.setBlockStateWithMetadata(0, 4, 0, Block.DIAMOND_BLOCK.getDefaultState(), 0);
        blockView.setBlockStateWithMetadata(0, 5, 0, BNBBlocks.SPINNING_WHEEL.getDefaultState(), 0);


        Tessellator tessellator = Tessellator.INSTANCE;
        tessellator.startQuads();
        List<BlockPos> blockPositions = blockView.getBlockPositions();
        for(BlockPos blockPos : blockPositions){
            BlockState blockState = blockView.getBlockState(blockPos.getX(), blockPos.getY(), blockPos.getZ());
            blockRenderManager.render(blockState.getBlock(), blockPos.getX(), blockPos.getY(), blockPos.getZ());
        }
        tessellator.draw();
        GL11.glPopMatrix();
    }

    @Override
    public @Nullable ArrayList<Object> getTooltip(int i, int i1) {
        return null;
    }

    @Override
    public boolean handleClick(@NotNull Minecraft minecraft, int i, int i1, int i2) {
        if(i2 == 0){
            mouseDown = true;
        }
        System.out.println(i + " " + i1 + " " + i2);
        rotation += 12f;
        return true;
    }
}
