package net.newfrontiercraft.nfc.gui;

import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.newfrontiercraft.nfc.block.entity.AutomaticCraftingTableBlockEntity;
import net.newfrontiercraft.nfc.inventory.AutomaticCraftingTableScreenHandler;
import org.lwjgl.opengl.GL11;

public class AutomaticCraftingTableScreen extends HandledScreen {

    private final AutomaticCraftingTableBlockEntity automaticCraftingTableBlockEntity;

    public AutomaticCraftingTableScreen(PlayerInventory playerInventory, AutomaticCraftingTableBlockEntity automaticCraftingTableBlockEntity) {
        super(new AutomaticCraftingTableScreenHandler(playerInventory, automaticCraftingTableBlockEntity));
        this.automaticCraftingTableBlockEntity = automaticCraftingTableBlockEntity;
        backgroundHeight = 166;
    }

    protected void drawForeground() {
        if (automaticCraftingTableBlockEntity.isMultiBlock) {
            this.textRenderer.draw("Multi Block Mode", 68, (backgroundHeight - 96) + 2, 0x006600);
        }
        this.textRenderer.draw("Automatic Crafting Table", 40, 6, 0x404040);
        this.textRenderer.draw("Inventory", 8, (backgroundHeight - 96) + 2, 0x404040);
    }

    protected void drawBackground(float f) {
        int var2 = this.minecraft.textureManager.getTextureId("/assets/nfc/stationapi/gui/automatic_crafting_table.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.textureManager.bindTexture(var2);
        int j = (width - backgroundWidth) / 2;
        int k = (height - backgroundHeight) / 2;
        this.drawTexture(j, k, 0, 0, backgroundWidth, backgroundHeight);
        int i1 = automaticCraftingTableBlockEntity.getScaledCraftingProgress(24);
        this.drawTexture(j + 79, k + 34, 176, 14, i1 + 1, 16);
    }
}
