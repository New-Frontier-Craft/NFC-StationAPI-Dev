package net.newfrontiercraft.nfc.gui;

import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.newfrontiercraft.nfc.block.entity.CombustionHeaterBlockEntity;
import net.newfrontiercraft.nfc.inventory.CombustionHeaterScreenHandler;
import org.lwjgl.opengl.GL11;

public class CombustionHeaterScreen extends HandledScreen {

    private final CombustionHeaterBlockEntity combustionHeater;

    public CombustionHeaterScreen(PlayerInventory inventoryplayer, CombustionHeaterBlockEntity tileEntityCombustionHeater) {
        super(new CombustionHeaterScreenHandler(inventoryplayer, tileEntityCombustionHeater));
        combustionHeater = tileEntityCombustionHeater;
    }

    protected void drawForeground() {
        this.textRenderer.draw("Combustion Heater", 60, 6, 0x404040);
        this.textRenderer.draw("Inventory", 8, (backgroundHeight - 96) + 2, 0x404040);
    }

    protected void drawBackground(float f) {
        int i = this.minecraft.textureManager.getTextureId("/assets/nfc/stationapi/gui/combustion_heater.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.textureManager.bindTexture(i);
        int j = (width - backgroundWidth) / 2;
        int k = (height - backgroundHeight) / 2;
        drawTexture(j, k, 0, 0, backgroundWidth, backgroundHeight);
        if(combustionHeater.isBurning()) {
            int l = combustionHeater.getBurnTimeRemainingScaled(12);
            drawTexture(j + 80, (k + 36 + 12) - l, 176, 12 - l, 14, l + 2);
        }
    }
}
