package net.newfrontiercraft.nfc.guis;

import net.minecraft.client.gui.screen.container.ContainerBase;
import net.minecraft.entity.player.PlayerInventory;
import net.newfrontiercraft.nfc.containers.ContainerBrickOven;
import net.newfrontiercraft.nfc.tileentities.TileEntityBrickOven;
import org.lwjgl.opengl.GL11;

public class GUIBrickOven extends ContainerBase {

    public GUIBrickOven(PlayerInventory inventoryplayer, TileEntityBrickOven tileentityfurnace) {
        super(new ContainerBrickOven(inventoryplayer, tileentityfurnace));
        furnaceInventory = tileentityfurnace;
        containerHeight = 202;
    }

    protected void renderForeground() {

        this.textManager.drawText("Brick Oven", 60, 6, 0x404040);
        this.textManager.drawText("Inventory", 8, (containerHeight - 96) + 2, 0x404040);
    }

    protected void renderContainerBackground(float f) {
        int var2 = this.minecraft.textureManager.getTextureId("/assets/nfc/stationapi/gui/brick_oven.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.textureManager.bindTexture(var2);
        int j = (width - containerWidth) / 2;
        int k = (height - containerHeight) / 2;
        this.blit(j, k, 0, 0, containerWidth, containerHeight);
        if (furnaceInventory.isBurning()) {
            int l = furnaceInventory.getBurnTimeRemainingScaled(12);
            this.blit(j + 56, (k + 72 + 12) - l, 176, 12 - l, 14,
                    l + 2);
        }
        int i1 = furnaceInventory.getCookProgressScaled(24);
        this.blit(j + 79, k + 70, 176, 14, i1 + 1, 16);
    }

    private TileEntityBrickOven furnaceInventory;
}
