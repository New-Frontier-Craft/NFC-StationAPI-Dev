package net.newfrontiercraft.nfc.inventory;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerListener;
import net.minecraft.screen.slot.Slot;
import net.newfrontiercraft.nfc.block.entity.CombustionHeaterBlockEntity;

public class CombustionHeaterScreenHandler extends ScreenHandler {

    private final CombustionHeaterBlockEntity combustionHeater;
    private int furnaceBurnTime;
    private int currentItemBurnTime;

    public CombustionHeaterScreenHandler(PlayerInventory inventoryplayer, CombustionHeaterBlockEntity tileEntityCombustionHeater) {
        furnaceBurnTime = 0;
        currentItemBurnTime = 0;
        combustionHeater = tileEntityCombustionHeater;
        addSlot(new Slot(tileEntityCombustionHeater, 0, 80, 53));
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
        return combustionHeater.canPlayerUse(player);
    }

    public void sendContentUpdates() {
        super.sendContentUpdates();
        for (Object listener : this.listeners) {
            ScreenHandlerListener screenHandlerListener = (ScreenHandlerListener) listener;
            if (furnaceBurnTime != combustionHeater.furnaceBurnTime) {
                screenHandlerListener.onPropertyUpdate(this, 0, combustionHeater.furnaceBurnTime);
            }
            if (currentItemBurnTime != combustionHeater.currentItemBurnTime) {
                screenHandlerListener.onPropertyUpdate(this, 1, combustionHeater.currentItemBurnTime);
            }
        }
        furnaceBurnTime = combustionHeater.furnaceBurnTime;
        currentItemBurnTime = combustionHeater.currentItemBurnTime;
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void setProperty(int i, int j) {
        if(i == 0) {
            combustionHeater.furnaceBurnTime = j;
        }
        if(i == 1) {
            combustionHeater.currentItemBurnTime = j;
        }
    }

    @Override
    public ItemStack quickMove(int slot) {
        ItemStack itemStack = null;
        Slot selectedSlot = (Slot) slots.get(slot);
        if (selectedSlot != null && selectedSlot.hasStack()) {
            ItemStack slotStack = selectedSlot.getStack();
            itemStack = slotStack.copy();
            if (slot == 0) {
                insertItem(slotStack, 1, 37, true);
            } else {
                insertItem(slotStack, 0, 1, false);
            }
            if (slotStack.count == 0) {
                selectedSlot.setStack(null);
            } else {
                selectedSlot.markDirty();
            }
            if (slotStack.count != itemStack.count) {
                selectedSlot.onTakeItem(slotStack);
            } else {
                return null;
            }
        }
        return itemStack;
    }
}
