package net.newfrontiercraft.nfc.compat.ami.brickoven;

import net.glasslauncher.mods.alwaysmoreitems.api.gui.AMIDrawable;
import net.glasslauncher.mods.alwaysmoreitems.api.recipe.RecipeWrapper;
import net.glasslauncher.mods.alwaysmoreitems.gui.DrawableHelper;
import net.glasslauncher.mods.alwaysmoreitems.util.AMIHelpers;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.modificationstation.stationapi.api.block.BlockState;
import net.newfrontiercraft.nfc.gui.InventoryBlockView;
import net.newfrontiercraft.nfc.registry.BlockPatternEntry;
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

    private final AMIDrawable leftButton = DrawableHelper.createDrawable("/assets/nfc/stationapi/gui/multiblock.png", 187, 0, 7, 11);
    private final AMIDrawable leftButtonHover = DrawableHelper.createDrawable("/assets/nfc/stationapi/gui/multiblock.png", 187, 11, 7, 11);
    private final AMIDrawable rightButton = DrawableHelper.createDrawable("/assets/nfc/stationapi/gui/multiblock.png", 194, 0, 7, 11);
    private final AMIDrawable rightButtonHover = DrawableHelper.createDrawable("/assets/nfc/stationapi/gui/multiblock.png", 194, 11, 7, 11);

    float pitch = 0f;
    float yaw = 0f;
    boolean mouseDown = false;
    int currentLayer = -1;
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

    private void loadRecipeStructure(InventoryBlockView blockView, MultiBlockRecipe recipe){
        List<String[]> layers = recipe.getLayers();
        int x = 0;
        int y = 0;
        int z = 0;
        for(String[] layer : layers){
            z = 0;
            for(String section : layer){
                x = 0;
                for(char pattern : section.toCharArray()){
                    BlockPatternEntry entry = recipe.getEntryForPattern(pattern);
                    if(entry != null){
                        blockView.setBlockStateWithMetadata(x, y, z, entry.blockstate(), entry.meta());
                    }
                    // TODO: warn for missing keys
                    else {

                    }
                    x++;
                }
                z++;
            }
            y++;
        }
    }

    @Override
    public void drawInfo(@NotNull Minecraft minecraft, int i, int i1, int i2, int i3) {
        if(!Mouse.isButtonDown(0) && mouseDown){
            mouseDown = false;
        }
        if(mouseDown){
            pitch += Mouse.getDY();
            yaw += Mouse.getDX();
        }
    }

    public List<ItemStack> getCost(){
        return recipe.getCost();
    }




    @Override
    public void drawAnimations(@NotNull Minecraft minecraft, int i, int i1) {
        InventoryBlockView blockView = new InventoryBlockView(minecraft.world);
        BlockRenderManager blockRenderManager = new BlockRenderManager(blockView);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glPushMatrix();
        GL11.glTranslatef(81f,58.5f,80f);
        GL11.glRotatef(pitch, 1f, 0f, 0);
        GL11.glRotatef(yaw, 0f, 1f, 0);
        GL11.glScalef(-10f, -10f, -10f);
        minecraft.textureManager.bindTexture(minecraft.textureManager.getTextureId("/terrain.png"));
        loadRecipeStructure(blockView, recipe);

        Tessellator tessellator = Tessellator.INSTANCE;
        tessellator.startQuads();
        tessellator.translate(-(recipe.getStructureWidth() / 2f), -(recipe.getStructureHeight() / 2f), -(recipe.getStructureDepth() / 2f));

        List<BlockPos> blockPositions = blockView.getBlockPositions();
        for(int renderLayer = 0; renderLayer < 2; renderLayer++){
            for(BlockPos blockPos : blockPositions){
                BlockState blockState = blockView.getBlockState(blockPos.getX(), blockPos.getY(), blockPos.getZ());
                if(blockState.getBlock().getRenderLayer() == renderLayer){
                    blockRenderManager.render(blockState.getBlock(), blockPos.getX(), blockPos.getY(), blockPos.getZ());
                }
            }
        }
        tessellator.draw();
        tessellator.translate(0.0, 0.0, 0.0);

        GL11.glPopMatrix();
        GL11.glDisable(GL11.GL_BLEND);

        leftButton.draw(minecraft, 0, 118);
        leftButtonHover.draw(minecraft, 60, 0);
        rightButton.draw(minecraft, 70, 0);
        rightButtonHover.draw(minecraft, 80, 0);
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
        return false;
    }
}
