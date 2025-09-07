package net.newfrontiercraft.nfc.inventory.crafting;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class AutocraftingScreenHandler extends ScreenHandler {

    public AutocraftingScreenHandler(Inventory inventory) {
        for(int slotIndex = 0; slotIndex < inventory.size(); slotIndex++) {
            int yIndex = slotIndex / 3;
            this.addSlot(new Slot(inventory, slotIndex, slotIndex - yIndex * 3, yIndex));
        }
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return false;
    }
}
