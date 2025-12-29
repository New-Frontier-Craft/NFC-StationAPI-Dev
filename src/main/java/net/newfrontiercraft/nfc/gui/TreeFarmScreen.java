package net.newfrontiercraft.nfc.gui;

import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.newfrontiercraft.nfc.block.entity.TreeFarmBlockEntity;
import net.newfrontiercraft.nfc.inventory.TreeFarmScreenHandler;
import org.lwjgl.opengl.GL11;

public class TreeFarmScreen extends HandledScreen {

    private final TreeFarmBlockEntity treeFarmBlockEntity;

    public TreeFarmScreen(PlayerInventory playerInventory, TreeFarmBlockEntity treeFarmBlockEntity) {
        super(new TreeFarmScreenHandler(playerInventory, treeFarmBlockEntity));
        this.treeFarmBlockEntity = treeFarmBlockEntity;
        backgroundHeight = 166;
    }

    protected void drawForeground() {
        if (treeFarmBlockEntity.isMultiBlock) {
            this.textRenderer.draw("Multi Block Mode", 68, (backgroundHeight - 96) + 2, 0x006600);
        }
        if (!treeFarmBlockEntity.hasFrame()) {
            this.textRenderer.draw("Missing Frame!", 68, 6, 0x660000);
        }
        this.textRenderer.draw("Tree Farm", 10, 6, 0x404040);
        this.textRenderer.draw("Inventory", 8, (backgroundHeight - 96) + 2, 0x404040);
    }

    protected void drawBackground(float f) {
        int var2 = this.minecraft.textureManager.getTextureId("/assets/nfc/stationapi/gui/tree_farm.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.textureManager.bindTexture(var2);
        int j = (width - backgroundWidth) / 2;
        int k = (height - backgroundHeight) / 2;
        this.drawTexture(j, k, 0, 0, backgroundWidth, backgroundHeight);
        int i1 = treeFarmBlockEntity.getScaledCraftingProgress(24);
        this.drawTexture(j + 79, k + 34, 176, 14, i1 + 1, 16);
    }
}
