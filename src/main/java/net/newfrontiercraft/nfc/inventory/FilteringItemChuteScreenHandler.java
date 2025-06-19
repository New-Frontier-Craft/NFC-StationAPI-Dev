package net.newfrontiercraft.nfc.inventory;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.newfrontiercraft.nfc.block.entity.FilteringItemChuteBlockEntity;

public class FilteringItemChuteScreenHandler extends ScreenHandler {

    public FilteringItemChuteScreenHandler(PlayerInventory inventoryplayer, FilteringItemChuteBlockEntity filteringItemChuteBlockEntity) {
        addSlot(new Slot(filteringItemChuteBlockEntity, 0, 80, 35));
        addSlot(new Slot(filteringItemChuteBlockEntity, 1, 80, 59));
        for(int i = 0; i < 3; i++) {
            for(int k = 0; k < 9; k++) {
                addSlot(new Slot(inventoryplayer, k + i * 9 + 9, 8 + k * 18, 84 + i * 18));
            }
        }
        for(int j = 0; j < 9; j++) {
            addSlot(new Slot(inventoryplayer, j, 8 + j * 18, 142));
        }
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }
}
