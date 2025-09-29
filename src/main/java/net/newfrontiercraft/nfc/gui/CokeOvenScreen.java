package net.newfrontiercraft.nfc.gui;

import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.newfrontiercraft.nfc.block.entity.CokeOvenBlockEntity;
import net.newfrontiercraft.nfc.inventory.CokeOvenScreenHandler;
import org.lwjgl.opengl.GL11;

public class CokeOvenScreen extends HandledScreen {

    private CokeOvenBlockEntity furnaceInventory;

    public CokeOvenScreen(PlayerInventory inventoryplayer, CokeOvenBlockEntity tileentityfurnace) {
        super(new CokeOvenScreenHandler(inventoryplayer, tileentityfurnace));
        furnaceInventory = tileentityfurnace;
        backgroundHeight = 202;
    }

    protected void drawForeground() {
        if (furnaceInventory.isMultiBlock) {
            this.textRenderer.draw("Multi Block Mode", 68, (backgroundHeight - 96) + 2, 0x006600);
        }
        this.textRenderer.draw("Coke Oven", 60, 6, 0x404040);
        this.textRenderer.draw("Inventory", 8, (backgroundHeight - 96) + 2, 0x404040);
    }

    protected void drawBackground(float f) {
        int var2 = this.minecraft.textureManager.getTextureId("/assets/nfc/stationapi/gui/coke_oven.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.textureManager.bindTexture(var2);
        int j = (width - backgroundWidth) / 2;
        int k = (height - backgroundHeight) / 2;
        this.drawTexture(j, k, 0, 0, backgroundWidth, backgroundHeight);
        int i1 = furnaceInventory.getCookProgressScaled(24);
        this.drawTexture(j + 79, k + 70, 176, 14, i1 + 1, 16);
        int heat = getThermometerHeightFromHeatValue(furnaceInventory.getHeatLevel());
        int heatHeightLevel = 96 - heat;
        int maximumHeat = getThermometerHeightFromHeatValue(furnaceInventory.getMaximumRequiredHeatLevel());
        int maximumHeatHeightLevel = 96 - maximumHeat - 3;
        int requiredHeat = getThermometerHeightFromHeatValue(furnaceInventory.getMinimumRequiredHeatLevel());
        int requiredHeatHeightLevel = 96 - requiredHeat - 3;
        this.drawTexture(j + 12, k + 11 + heatHeightLevel, 176, 46 + heatHeightLevel, 8, heat);
        this.drawTexture(j + 4, k + 11 + maximumHeatHeightLevel, 176, 38, 8, 7);
        this.drawTexture(j + 20, k + 11 + maximumHeatHeightLevel, 184, 38, 8, 7);
        this.drawTexture(j + 4, k + 11 + requiredHeatHeightLevel, 176, 31, 8, 7);
        this.drawTexture(j + 20, k + 11 + requiredHeatHeightLevel, 184, 31, 8, 7);
    }

    private int getThermometerHeightFromHeatValue(int heatValue) {
        return 16 * heatValue / 200;
    }
}
