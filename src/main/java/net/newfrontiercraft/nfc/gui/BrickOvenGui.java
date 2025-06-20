package net.newfrontiercraft.nfc.gui;

import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.newfrontiercraft.nfc.inventory.BrickOvenScreenHandler;
import net.newfrontiercraft.nfc.block.entity.BrickOvenBlockEntity;
import org.lwjgl.opengl.GL11;

public class BrickOvenGui extends HandledScreen {

    public BrickOvenGui(PlayerInventory inventoryplayer, BrickOvenBlockEntity tileentityfurnace) {
        super(new BrickOvenScreenHandler(inventoryplayer, tileentityfurnace));
        furnaceInventory = tileentityfurnace;
        backgroundHeight = 202;
    }

    protected void drawForeground() {
        if (furnaceInventory.isMultiBlock) {
            this.textRenderer.draw("Multi Block Mode", 68, (backgroundHeight - 96) + 2, 0x006600);
        }
        this.textRenderer.draw("Brick Oven", 60, 6, 0x404040);
        this.textRenderer.draw("Inventory", 8, (backgroundHeight - 96) + 2, 0x404040);
    }

    protected void drawBackground(float f) {
        int var2 = this.minecraft.textureManager.getTextureId("/assets/nfc/stationapi/gui/brick_oven.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.textureManager.bindTexture(var2);
        int j = (width - backgroundWidth) / 2;
        int k = (height - backgroundHeight) / 2;
        this.drawTexture(j, k, 0, 0, backgroundWidth, backgroundHeight);
        if (furnaceInventory.isBurning()) {
            int l = furnaceInventory.getBurnTimeRemainingScaled(12);
            this.drawTexture(j + 56, (k + 72 + 12) - l, 176, 12 - l, 14,
                    l + 2);
        }
        int i1 = furnaceInventory.getCookProgressScaled(24);
        this.drawTexture(j + 79, k + 70, 176, 14, i1 + 1, 16);
    }

    private BrickOvenBlockEntity furnaceInventory;
}
