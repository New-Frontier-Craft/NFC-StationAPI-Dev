package net.newfrontiercraft.nfc.gui;

import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.world.World;
import net.newfrontiercraft.nfc.inventory.CarpentryWorkstationScreenHandler;
import org.lwjgl.opengl.GL11;

public class CarpentryWorkstationGui extends HandledScreen {

    public CarpentryWorkstationGui(PlayerInventory inventoryplayer, World world, int i, int j, int k) {
        super(new CarpentryWorkstationScreenHandler(inventoryplayer, world, i, j, k));
    }

    @Override
    public void removed() {
        super.removed();
        container.onClosed(minecraft.player);
    }

    @Override
    protected void drawForeground() {
        textRenderer.draw("Carpentry Workstation", 28, 6, 0x404040);
        textRenderer.draw("Inventory", 8, (backgroundHeight - 96) + 2, 0x404040);
    }

    @Override
    protected void drawBackground(float f) {
        int i = this.minecraft.textureManager.getTextureId("/assets/nfc/stationapi/gui/carpentry.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.textureManager.bindTexture(i);
        int j = (width - backgroundWidth) / 2;
        int k = (height - backgroundHeight) / 2;
        drawTexture(j, k, 0, 0, backgroundWidth, backgroundHeight);
    }
}
