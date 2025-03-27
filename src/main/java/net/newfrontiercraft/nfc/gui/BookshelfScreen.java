package net.newfrontiercraft.nfc.gui;

import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.resource.language.TranslationStorage;
import net.minecraft.inventory.Inventory;
import net.newfrontiercraft.nfc.block.entity.BookshelfBlockEntity;
import net.newfrontiercraft.nfc.inventory.BookshelfScreenHandler;
import org.lwjgl.opengl.GL11;

public class BookshelfScreen extends HandledScreen {
    private Inventory playerInventory;
    private Inventory bookshelfInventory;
    private int inventoryRows = 0;

    public BookshelfScreen(Inventory playerInventory, BookshelfBlockEntity blockEntity) {
        super(new BookshelfScreenHandler(playerInventory, blockEntity));
        this.playerInventory = playerInventory;
        this.bookshelfInventory = blockEntity;
        passEvents = false;
        int i = 222 - 108;
        this.inventoryRows = blockEntity.size() / 5;
        this.backgroundHeight = i + this.inventoryRows * 18;
    }

    @Override
    protected void drawForeground() {
        TranslationStorage translationStorage = TranslationStorage.getInstance();
        this.textRenderer.draw(translationStorage.get("gui.nfc.bookshelf"), 8, 6, 4210752);
        this.textRenderer.draw(playerInventory.getName(), 8, this.backgroundHeight - 96 + 2, 4210752);
    }

    @Override
    protected void drawBackground(float tickDelta) {
        int textureId = this.minecraft.textureManager.getTextureId("/assets/nfc/stationapi/gui/shelf.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.textureManager.bindTexture(textureId);
        int j = (this.width - this.backgroundWidth) / 2;
        int k = (this.height - this.backgroundHeight) / 2;
        this.drawTexture(j, k, 0, 0, this.backgroundWidth, this.inventoryRows * 18 + 17);
        this.drawTexture(j, k + this.inventoryRows * 18 + 17, 0, 126, this.backgroundWidth, 96);
    }
}
