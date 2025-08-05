package net.newfrontiercraft.nfc.compat.ami.brickoven;

import net.glasslauncher.mods.alwaysmoreitems.api.gui.AMIDrawable;
import net.glasslauncher.mods.alwaysmoreitems.api.recipe.RecipeWrapper;
import net.glasslauncher.mods.alwaysmoreitems.gui.DrawableHelper;
import net.glasslauncher.mods.alwaysmoreitems.gui.RecipeLayout;
import net.glasslauncher.mods.alwaysmoreitems.gui.screen.OverlayScreen;
import net.glasslauncher.mods.alwaysmoreitems.gui.screen.RecipesGui;
import net.glasslauncher.mods.alwaysmoreitems.util.HoverChecker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.util.ScreenScaler;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.modificationstation.stationapi.api.block.BlockState;
import net.newfrontiercraft.nfc.gui.InventoryBlockView;
import net.newfrontiercraft.nfc.mixin.graphics.RecipesGuiAccessor;
import net.newfrontiercraft.nfc.registry.BlockPatternEntry;
import net.newfrontiercraft.nfc.registry.MultiBlockRecipe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

public class MultiBlockRecipeWrapper implements RecipeWrapper {

    private HoverChecker leftButtonHoverChecker;
    private HoverChecker rightButtonHoverChecker;
    private final AMIDrawable leftButton = DrawableHelper.createDrawable("/assets/nfc/stationapi/gui/multiblock.png", 187, 0, 7, 11);
    private final AMIDrawable leftButtonHover = DrawableHelper.createDrawable("/assets/nfc/stationapi/gui/multiblock.png", 187, 11, 7, 11);
    private final AMIDrawable rightButton = DrawableHelper.createDrawable("/assets/nfc/stationapi/gui/multiblock.png", 194, 0, 7, 11);
    private final AMIDrawable rightButtonHover = DrawableHelper.createDrawable("/assets/nfc/stationapi/gui/multiblock.png", 194, 11, 7, 11);

    float pitch = -45f;
    float yaw = -45f;
    float scale = -10f;
    boolean leftMouseDown = false;
    boolean rightMouseDown = false;
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
    public void drawInfo(@NotNull Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
        if(mouseX == 0 && mouseY == 0) return;
        if(!Mouse.isButtonDown(0) && leftMouseDown){
            leftMouseDown = false;
        }
        if(!Mouse.isButtonDown(1) && rightMouseDown){
            rightMouseDown = false;
        }
        if(leftMouseDown){
            pitch += Mouse.getDY();
            yaw += Mouse.getDX();
        }
        if(rightMouseDown){
            scale -= Mouse.getDY();
            scale = Math.min(-5f, scale);
        }

        if(leftButtonHoverChecker.isOver(mouseX, mouseY)){
            leftButtonHover.draw(minecraft, 0, 118);
        }
        else {
            leftButton.draw(minecraft, 0, 118);
        }
    }

    public List<ItemStack> getCost(){
        return recipe.getCost();
    }

    private String getLayerString(int layer){
        if(layer == -1) return "A";
        return String.valueOf(layer);
    }

    @Override
    public void drawAnimations(@NotNull Minecraft minecraft, int i, int i1) {
        InventoryBlockView blockView = new InventoryBlockView(minecraft.world);
        BlockRenderManager blockRenderManager = new BlockRenderManager(blockView);

        RecipesGui recipesGui = OverlayScreen.INSTANCE.recipesGui;
        List<RecipeLayout> recipeLayouts = ((RecipesGuiAccessor)recipesGui).getRecipeLayouts();
        RecipeLayout recipeLayout = null;
        for(RecipeLayout r : recipeLayouts){
            if(r.getRecipeCategory() instanceof MultiBlockRecipeCategory){
                recipeLayout = r;
            }
        }
        if(recipeLayout != null){
        }

        float xScale = (float) minecraft.displayWidth / recipesGui.width;
        float yScale = (float) minecraft.displayHeight / recipesGui.height;

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glPushMatrix();
        GL11.glTranslatef(81f,58.5f,180f);
        GL11.glRotatef(pitch, 1f, 0f, 0);
        GL11.glRotatef(yaw, 0f, 1f, 0);
        GL11.glScalef(scale, scale, scale);
        minecraft.textureManager.bindTexture(minecraft.textureManager.getTextureId("/terrain.png"));
        loadRecipeStructure(blockView, recipe);

        GL11.glScissor((int) ((recipeLayout.getPosX() + 1) * xScale), (int) (minecraft.displayHeight - ((recipeLayout.getPosY() + 116) * yScale)), (int)(160 * xScale), (int)(115 * yScale));
        GL11.glEnable(GL11.GL_SCISSOR_TEST);


        Tessellator tessellator = Tessellator.INSTANCE;
        tessellator.startQuads();
        tessellator.translate(-(recipe.getStructureWidth() / 2f), -(recipe.getStructureHeight() / 2f), -(recipe.getStructureDepth() / 2f));
        blockView.setVisibleLayer(currentLayer);
        List<BlockPos> blockPositions = blockView.getBlockPositions();
        for(int renderLayer = 0; renderLayer < 2; renderLayer++){
            for(BlockPos blockPos : blockPositions){
                if(currentLayer == -1 || currentLayer == blockPos.y){
                    BlockState blockState = blockView.getBlockState(blockPos.getX(), blockPos.getY(), blockPos.getZ());
                    if(blockState.getBlock().getRenderLayer() == renderLayer){
                        blockRenderManager.render(blockState.getBlock(), blockPos.getX(), blockPos.getY(), blockPos.getZ());
                    }
                }
            }
        }
        tessellator.draw();
        tessellator.translate(0.0, 0.0, 0.0);

        GL11.glPopMatrix();
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_SCISSOR_TEST);
        leftButtonHoverChecker = new HoverChecker(118, 128, 0, 6);
        String layerText = "Layer: " + getLayerString(currentLayer);
        minecraft.textRenderer.drawWithShadow(layerText, 10, 120, 0xFFFFFF);
        int rightButtonXOffset = 13 + minecraft.textRenderer.getWidth(layerText);
        rightButton.draw(minecraft, rightButtonXOffset, 118);
        rightButtonHoverChecker = new HoverChecker(118, 128, rightButtonXOffset, rightButtonXOffset + 6);
    }

    @Override
    public @Nullable ArrayList<Object> getTooltip(int i, int i1) {
        return null;
    }

    @Override
    public boolean handleClick(@NotNull Minecraft minecraft, int i, int i1, int i2) {
        if(i2 == 0){
            leftMouseDown = true;
        }
        if(i2 == 1){
            rightMouseDown = true;
        }

        System.out.println(i + " " + i1 + " " + i2);

        if(leftButtonHoverChecker.isOver(i, i1)){
            currentLayer--;
            if(currentLayer < -1){
                currentLayer = recipe.getLayers().size() - 1;
            }
        }
        if(rightButtonHoverChecker.isOver(i, i1)){
            currentLayer++;
            if(currentLayer > recipe.getLayers().size() - 1){
                currentLayer = -1;
            }
        }

//        int mouseX = Mouse.getX() * screenScaler.getScaledWidth() / minecraft.displayWidth;
//        int mouseY = screenScaler.getScaledHeight() - Mouse.getY() * screenScaler.getScaledHeight() / minecraft.displayHeight - 1;

//        System.out.println(mouseX + " : " + mouseY);
        return false;
    }
}
