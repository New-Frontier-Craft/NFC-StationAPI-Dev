package net.newfrontiercraft.nfc.inventory;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerListener;
import net.minecraft.screen.slot.FurnaceOutputSlot;
import net.minecraft.screen.slot.Slot;
import net.newfrontiercraft.nfc.block.entity.AutomaticCraftingTableBlockEntity;

public class AutomaticCraftingTableScreenHandler extends ScreenHandler {

    private final AutomaticCraftingTableBlockEntity automaticCraftingTable;
    private int cookTime;

    public AutomaticCraftingTableScreenHandler(PlayerInventory playerInventory, AutomaticCraftingTableBlockEntity automaticCraftingTableBlockEntity) {
        cookTime = 0;
        automaticCraftingTable = automaticCraftingTableBlockEntity;
        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 3; k++) {
                addSlot(new Slot(automaticCraftingTableBlockEntity,  k + i*3, 20 + k*18, 17 + i * 18));
            }
        }
        addSlot(new FurnaceOutputSlot(playerInventory.player, automaticCraftingTableBlockEntity, 9, 116, 35));
        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 9; k++) {
                addSlot(new Slot(playerInventory, k + i * 9 + 9, 8 + k * 18, 84 + i * 18));
            }
        }

        for (int j = 0; j < 9; j++) {
            addSlot(new Slot(playerInventory, j, 8 + j * 18, 142));
        }

    }

    public void sendContentUpdates() {
        super.sendContentUpdates();
        for (Object listener : this.listeners) {
            ScreenHandlerListener screenHandlerListener = (ScreenHandlerListener) listener;
            if (cookTime != automaticCraftingTable.craftingProgress) {
                screenHandlerListener.onPropertyUpdate(this, 0, automaticCraftingTable.craftingProgress);
            }
        }

        cookTime = automaticCraftingTable.craftingProgress;
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void setProperty(int i, int j) {
        if (i == 0) {
            automaticCraftingTable.craftingProgress = j;
        }
    }

    @Override
    public boolean canUse(PlayerEntity entityplayer) {
        return automaticCraftingTable.canPlayerUse(entityplayer);
    }

    @Override
    public ItemStack quickMove(int i) {
        ItemStack itemstack = null;
        Slot slot = (Slot) slots.get(i);
        if (slot != null && slot.hasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (i == 9) {
                insertItem(itemstack1, 10, 46, true);
            } else if (i >= 10 && i < 37) {
                insertItem(itemstack1, 37, 38, false);
            } else if (i >= 37 && i < 46) {
                insertItem(itemstack1, 10, 37, false);
            } else {
                insertItem(itemstack1, 10, 46, false);
            }
            if (itemstack1.count == 0) {
                slot.setStack(null);
            } else {
                slot.markDirty();
            }
            if (itemstack1.count != itemstack.count) {
                slot.onTakeItem(itemstack1);
            } else {
                return null;
            }
        }
        return itemstack;
    }
}
