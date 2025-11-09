package net.newfrontiercraft.nfc.inventory;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerListener;
import net.minecraft.screen.slot.FurnaceOutputSlot;
import net.minecraft.screen.slot.Slot;
import net.newfrontiercraft.nfc.block.entity.CokeOvenBlockEntity;

public class CokeOvenScreenHandler extends ScreenHandler {

    private final CokeOvenBlockEntity furnace;
    private int cookTime;
    private int requiredTime;
    private int heatLevel;
    private int minimumRequiredHeatLevel;
    private int maximumRequiredHeatLevel;

    public CokeOvenScreenHandler(PlayerInventory playerInventory, CokeOvenBlockEntity cokeOvenBlockEntity) {
        cookTime = 0;
        furnace = cokeOvenBlockEntity;

        addSlot(new Slot(cokeOvenBlockEntity,  0, 56 , 71));
        addSlot(new FurnaceOutputSlot(playerInventory.player, cokeOvenBlockEntity, 1, 116, 71));

        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 9; k++) {
                addSlot(new Slot(playerInventory, k + i * 9 + 9, 8 + k * 18, 120 + i * 18));
            }
        }

        for (int j = 0; j < 9; j++) {
            addSlot(new Slot(playerInventory, j, 8 + j * 18, 178));
        }

    }

    public void sendContentUpdates() {
        super.sendContentUpdates();
        for (int i = 0; i < this.listeners.size(); i++) {
            ScreenHandlerListener screenHandlerListener = (ScreenHandlerListener) this.listeners.get(i);
            if (cookTime != furnace.furnaceCookTime) {
                screenHandlerListener.onPropertyUpdate(this, 0, furnace.furnaceCookTime);
            }
            if (requiredTime != furnace.requiredTime) {
                screenHandlerListener.onPropertyUpdate(this, 1, furnace.requiredTime);
            }
            if (heatLevel != furnace.getHeatLevel()) {
                screenHandlerListener.onPropertyUpdate(this, 2, furnace.getHeatLevel());
            }
            if (minimumRequiredHeatLevel != furnace.getMinimumRequiredHeatLevel()) {
                screenHandlerListener.onPropertyUpdate(this, 3, furnace.getMinimumRequiredHeatLevel());
            }
            if (maximumRequiredHeatLevel != furnace.getMaximumRequiredHeatLevel()) {
                screenHandlerListener.onPropertyUpdate(this, 4, furnace.getMaximumRequiredHeatLevel());
            }
        }

        cookTime = furnace.furnaceCookTime;
        requiredTime = furnace.requiredTime;
        heatLevel = furnace.getHeatLevel();
        minimumRequiredHeatLevel = furnace.getMinimumRequiredHeatLevel();
        maximumRequiredHeatLevel = furnace.getMaximumRequiredHeatLevel();
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void setProperty(int id, int value) {
        if (id == 0) {
            furnace.furnaceCookTime = value;
        }
        if (id == 1) {
            furnace.requiredTime = value;
        }
        if (id == 2) {
            furnace.setHeatLevel(value);
        }
        if (id == 3) {
            furnace.setMinimumRequiredHeatLevel(value);
        }
        if (id == 4) {
            furnace.setMaximumRequiredHeatLevel(value);
        }
    }

    @Override
    public boolean canUse(PlayerEntity entityplayer) {
        return furnace.canPlayerUse(entityplayer);
    }

//    @Override
//    public ItemStack quickMove(int i) {
//        ItemStack itemstack = null;
//        Slot slot = (Slot) slots.get(i);
//        if (slot != null && slot.hasStack()) {
//            ItemStack itemstack1 = slot.getStack();
//            itemstack = itemstack1.copy();
//            if (i == 10) {
//                insertItem(itemstack1, 11, 47, true);
//            } else if (i >= 11 && i < 38) {
//                insertItem(itemstack1, 38, 39, false);
//            } else if (i >= 38 && i < 47) {
//                insertItem(itemstack1, 11, 38, false);
//            } else {
//                insertItem(itemstack1, 11, 47, false);
//            }
//            if (itemstack1.count == 0) {
//                slot.setStack(null);
//            } else {
//                slot.markDirty();
//            }
//            if (itemstack1.count != itemstack.count) {
//                slot.onTakeItem(itemstack1);
//            } else {
//                return null;
//            }
//        }
//        return itemstack;
//    }
}
