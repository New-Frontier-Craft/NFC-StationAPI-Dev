package net.newfrontiercraft.nfc.gui;

import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.newfrontiercraft.nfc.block.entity.FilteringItemChuteBlockEntity;
import net.newfrontiercraft.nfc.inventory.FilteringItemChuteScreenHandler;
import org.lwjgl.opengl.GL11;

public class FilteringItemChuteScreen extends HandledScreen {
    private final FilteringItemChuteBlockEntity filteringItemChuteBlockEntity;

    public FilteringItemChuteScreen(PlayerInventory inventoryplayer, FilteringItemChuteBlockEntity filteringItemChuteBlockEntity) {
        super(new FilteringItemChuteScreenHandler(inventoryplayer, filteringItemChuteBlockEntity));
        this.filteringItemChuteBlockEntity = filteringItemChuteBlockEntity;
    }

    @Override
    protected void drawForeground() {
        this.textRenderer.draw(filteringItemChuteBlockEntity.precise ? "Precise Item Chute" : "Filtering Item Chute", 8, 6, 0x404040);
        this.textRenderer.draw("Inventory", 8, (backgroundHeight - 96) + 2, 0x404040);
    }

    @Override
    protected void drawBackground(float f) {
        int i = this.minecraft.textureManager.getTextureId("/assets/nfc/stationapi/gui/filtering_chute.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.textureManager.bindTexture(i);
        int j = (width - backgroundWidth) / 2;
        int k = (height - backgroundHeight) / 2;
        drawTexture(j, k, 0, 0, backgroundWidth, backgroundHeight);
    }
}
